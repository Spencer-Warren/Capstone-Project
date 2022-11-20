package application.Move;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.simple.JSONObject;

import application.Mechanic;
import application.Drag.Draggable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Move extends Mechanic {

	private VBox root;
	private VBox wrapper;

	private JSONObject rightWrongJSON;

	private List<String> correctStrings;
	private List<Draggable> options;
	private List<Draggable> destinations;

	private int minWidth;

	public Move(JSONObject rightWrong) {

		root = new VBox(20);
		options = new ArrayList<>();
		destinations = new ArrayList<>();

		minWidth = 0;

		this.rightWrongJSON = rightWrong;

		wrapper = new VBox(root);
		wrapper.setAlignment(Pos.CENTER);
		wrapper.setFillWidth(false);

		root.setAlignment(Pos.CENTER);
		root.getStyleClass().add("drag-root");
	}

	public VBox create() {
		Label instructions = new Label("Drag the corrrectly formatted answers to the left!");

		originDrags();

		Pane originPane = createOriginPane();

		VBox destinatonBox = createDestinationBox();

		fixWidth();

		// So we can put the instructions at the top
		HBox problem = new HBox();
		problem.getChildren().addAll(destinatonBox, originPane);

		root.getChildren().addAll(instructions, problem, makeGradeBar());

		return wrapper;
	}

	private void originDrags() {
		Draggable temp;

		String rights = (String) rightWrongJSON.get("correct");
		correctStrings = List.of(rights.split(","));
		for (String right : rights.split(",")) {
			temp = new Draggable(right, true);
			options.add(temp);
			minWidth = minWidth < right.length() ? right.length() : minWidth;
		}

		String wrongs = (String) rightWrongJSON.get("incorrect");
		for (String wrong : wrongs.split(",")) {
			temp = new Draggable(wrong, true);
			options.add(temp);
			minWidth = minWidth < wrong.length() ? minWidth : wrong.length();
		}
	}

	private Pane createOriginPane() {
		VBox p = new VBox(10);
		Collections.shuffle(options);
		p.getChildren().addAll(options);
		return p;
	}

	private VBox createDestinationBox() {
		Draggable temp;

		Label destinationLabel = new Label("Correct");
		VBox destinationBox = new VBox(10);
		destinationBox.setMinWidth(300);
		destinationBox.getStyleClass().add("move-dest");

		for (String word : correctStrings) {
			temp = new Draggable(null, word, false);
			destinations.add(temp);
		}

		destinationBox.getChildren().addAll(destinationLabel);
		destinationBox.getChildren().addAll(destinations);

		return destinationBox;
	}

	private void fixWidth() {
		for (Draggable d : destinations) {
			d.setMinWidth(minWidth * 5.0);
		}
	}

	@Override
	protected boolean isCorrect() {
		correctStrings.forEach(System.out::println);
		for (Draggable d : destinations) {
			if (!correctStrings.contains(d.getText()))
				return false;
		}
		return true;
	}

	@Override
	protected void reset() {
		for (Draggable d : options) {
			d.reset();
		}
		for (Draggable d : destinations) {
			d.reset();
		}
	}

}
