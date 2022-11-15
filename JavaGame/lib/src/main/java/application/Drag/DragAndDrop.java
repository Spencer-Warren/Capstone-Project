package application.Drag;

import java.util.ArrayList;
import java.util.List;

import application.Mechanic;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class DragAndDrop implements Mechanic{
	private VBox root;
	private VBox wrapper;
	private String fullExample;
	private List<String> wordsToRemove;
	private List<Draggable> blanks;

	public DragAndDrop(String example, String wordsToRemove) {
		this.fullExample = example;
		this.wordsToRemove = new ArrayList<>(List.of(wordsToRemove.split(",")));

		root = new VBox();
		root.getStyleClass().add("drag-root");
		root.setAlignment(Pos.CENTER);

		wrapper = new VBox(root);
		wrapper.setAlignment(Pos.CENTER);
		wrapper.setFillWidth(false);

		blanks = new ArrayList<>();
	}

	private List<String> takeOutWords() {
		List<String> lines = List.of(fullExample.split("\r\n"));
		List<String> words = new ArrayList<>();

		for (String line : lines) {
			HBox row = new HBox();
			for (String word : line.split(" ")) {
				if (wordsToRemove.contains(word)) {
					wordsToRemove.remove(word);
					Draggable emptyDrag = new Draggable(null, word, false);
					blanks.add(emptyDrag);
					words.add(word);

					row.getChildren().add(emptyDrag);
				} else {
					Text temp = new Text(word + " ");
					temp.getStyleClass().add("drag");
					row.getChildren().add(temp);
				}
			}

			root.getChildren().add(row);
		}
		return words;
	}

	private void addWordDrags(List<String> words) {
		List<Draggable> wordDrags = words.stream().map(Draggable::new).toList();
		// Add all words weve taken out
		HBox wordsBlock = new HBox();
		wordsBlock.setSpacing(15);
		wordsBlock.getStyleClass().add("drag-box");
		wordsBlock.getChildren().addAll(wordDrags);

		HBox gradingBar = new HBox();
		Label grade = new Label();
		Button reset = new Button("Reset");
		Button check = new Button("Check");

		gradingBar.setSpacing(10);
		gradingBar.getChildren().addAll(reset, check, grade);

		reset.setOnAction((ActionEvent a) -> {
			for (Draggable d : blanks) {
				d.reset();
			}
			for (Draggable d : wordDrags) {
				d.reset();
			}
		});

		check.setOnAction((ActionEvent a) -> {
			if (isCorrect()) {
				grade.setText("Correct!");
			} else {
				grade.setText("Try Again!");
			}
		});

		root.getChildren().addAll(wordsBlock, gradingBar);
	}

	public VBox create() {
		List<String> words = takeOutWords();
		addWordDrags(words);
		return wrapper;
	}

	public boolean isCorrect() {
		for (Draggable d : blanks) {
			if (!d.isCorrect())
				return false;
		}
		return true;
	}

}
