package application.multiplechoice;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.simple.JSONObject;

import application.Mechanic;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MultipleChoice extends Mechanic {

	private VBox root;
	private VBox wrapper;

	private String question;
	private List<String> correctAnswers;
	private List<String> wrongAnswers;

	private List<CheckBox> choiceButtons;

	public MultipleChoice(JSONObject data) {

		question = (String) data.get("question");

		String tempA = (String) data.get("correct");
		correctAnswers = new ArrayList<>(List.of(tempA.split(",")));

		String tempB = (String) data.get("incorrect");
		wrongAnswers = new ArrayList<>(List.of(tempB.split(",")));

		root = new VBox(20);
		root.getStyleClass().add("mechanic-root");
		root.setAlignment(Pos.CENTER);

		wrapper = new VBox(root);
		wrapper.setAlignment(Pos.CENTER);
		wrapper.setFillWidth(false);
	}

	@Override
	public VBox create() {
		Label questionLabel = new Label(question);

		VBox choiceBox = makeChoices();

		root.getChildren().addAll(questionLabel, choiceBox, makeGradeBar());
		return wrapper;
	}

	private VBox makeChoices() {
		VBox choiceBox = new VBox(10);
		
		// Shuffle answers so we get diffrent ones
		Collections.shuffle(correctAnswers);
		Collections.shuffle(wrongAnswers);
		
		// Grab two right answers and two wrong
		CheckBox b1 = new CheckBox(correctAnswers.get(0));
		CheckBox b2 = new CheckBox(correctAnswers.get(1));
		CheckBox b3 = new CheckBox(wrongAnswers.get(0));
		CheckBox b4 = new CheckBox(wrongAnswers.get(1));
		
		choiceButtons = new ArrayList<>(List.of(b1, b2, b3, b4));
		// shuffle order of checkboxes so its not always
		// right right wrong wrong
		Collections.shuffle(choiceButtons);

		choiceBox.getChildren().addAll(choiceButtons);

		return choiceBox;
	}

	@Override
	protected void reset() {
		for (CheckBox b : choiceButtons) {
			b.setSelected(false);
		}

	}

	@Override
	protected boolean isCorrect() {
		return getCurrentScore() == 2;
	}

	@Override
	public void save(ObjectOutputStream out) {
		try {
			out.writeObject(correctAnswers.toArray());
			out.writeObject(wrongAnswers.toArray());
			for (CheckBox box : choiceButtons) {

				out.writeBoolean(box.isSelected());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void load(ObjectInputStream in) {
		try {
			Object temp1 = in.readObject();
			Object temp2 = in.readObject();
			if (temp1 instanceof String[] correct && temp2 instanceof String[] wrong) {
				correctAnswers = new ArrayList<>(List.of(correct));
				wrongAnswers = new ArrayList<>(List.of(wrong));
			}
			for (CheckBox box : choiceButtons) {
				box.setSelected(in.readBoolean());
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected int getTotal() {
		return 2;
	}

	@Override
	protected int getCurrentScore() {
		int numberCorrect = 0;
		for (CheckBox b : choiceButtons) {
			if (b.isSelected()) {
				if (correctAnswers.contains(b.getText())) {
					numberCorrect++;
				} else {
					return 0;
				}
			}
		}
		return numberCorrect;
	}
}
