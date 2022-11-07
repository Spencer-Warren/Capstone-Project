package application;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class DragAndDrop {
	private VBox root;
	private String fullExample;
	private List<String> wordsToRemove;
	private List<String> removedWords;
	private List<Draggable> blanks;

	public DragAndDrop(String example, String... wordsToRemove) {
		fullExample = example;
		this.wordsToRemove = new ArrayList<>(List.of(wordsToRemove));
		root = new VBox();
		blanks = new ArrayList<>();

	}

	private void takeOutWords() {
		List<String> lines = List.of(fullExample.split("\r\n"));
		List<Draggable> words = new ArrayList<>();

		for (String line : lines) {
			HBox row = new HBox();
			for (String word : line.split(" ")) {
				if (wordsToRemove.contains(word)) {
					wordsToRemove.remove(word);
					Draggable emptyDrag = new Draggable(null, word);
					blanks.add(emptyDrag);
					words.add(new Draggable(word, null));

					row.getChildren().add(emptyDrag);
				} else {
					row.getChildren().add(new Text(word + " "));
				}
			}

			root.getChildren().add(row);
		}

		// Add all words weve taken out
		HBox wordsBlock = new HBox();
		wordsBlock.getChildren().addAll(words);

		HBox gradingBar = new HBox();
		Label grade = new Label();
		Button check = new Button("check");

		gradingBar.getChildren().addAll(grade, check);

		check.setOnAction((ActionEvent a) -> {
			if (isCorrect()) {
				grade.setText("Passed!");
			} else {
				grade.setText("Failed!");
			}
		});

		root.getChildren().addAll(wordsBlock, gradingBar);

	}

	public VBox create() {
		takeOutWords();
		return root;

	}

	public boolean isCorrect() {
		for (Draggable d : blanks) {
			if (!d.isCorrect())
				return false;
		}
		return true;
	}

}
