package application.drag;

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

public class DragAndDrop extends Mechanic {
	private VBox root;
	private VBox wrapper;
	private String fullExample;
	private List<String> wordsToRemove;
	private List<Draggable> blanks;
	private List<Draggable> wordDrags;

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

	// Given the words we got from the JSON file
	// Take out the words and create blank spaces in their place
	private List<String> takeOutWords() {
		List<String> lines = List.of(fullExample.split("\r\n"));
		List<String> words = new ArrayList<>();

		for (String line : lines) {
			// We need an HBox to add the draggable objects
			HBox row = new HBox();
			for (String word : line.split(" ")) {
				// if we run into a word we need to remove
				if (wordsToRemove.contains(word)) {
					wordsToRemove.remove(word);
					// Create an empty draggable
					// but give it the correct word
					// so we can check it later
					Draggable emptyDrag = new Draggable(null, word, false);
					// add draggable to list for resetting later
					blanks.add(emptyDrag);
					// add to a list so we can create the
					// draggables with the words at the bottom
					words.add(word);

					row.getChildren().add(emptyDrag);
				} else {
					// else just add the word to the row
					Text temp = new Text(word + " ");
					temp.getStyleClass().add("drag");
					row.getChildren().add(temp);
				}
			}
			root.getChildren().add(row);
		}
		return words;
	}

	private HBox addWordDrags() {
		// Add all words weve taken out
		HBox wordsBlock = new HBox();
		wordsBlock.setSpacing(15);
		wordsBlock.getStyleClass().add("drag-box");
		wordsBlock.getChildren().addAll(wordDrags);

		return wordsBlock;
	}

	public VBox create() {
		// take out all the words
		List<String> words = takeOutWords();
		
		// stream all the words that weve taken out to new draggables to add
		wordDrags = words.stream().map(Draggable::new).toList();

		// add the created draggables
		HBox wordsBlock = addWordDrags();

		// use the mechanic class method
		HBox gradingBar = makeGradeBar();

		// add all the things
		root.getChildren().addAll(wordsBlock, gradingBar);

		// return the wrapper so we can add it to a scene
		return wrapper;
	}

	@Override
	protected boolean isCorrect() {
		for (Draggable d : blanks) {
			if (!d.isCorrect())
				return false;
		}
		return true;
	}

	@Override
	protected void reset() {
		for (Draggable d : blanks) {
			d.reset();
		}
		for (Draggable d : wordDrags) {
			d.reset();
		}

	}

}
