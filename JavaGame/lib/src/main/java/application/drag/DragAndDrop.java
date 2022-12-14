package application.drag;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.simple.JSONObject;

import application.Mechanic;
import javafx.geometry.Insets;
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

	public DragAndDrop(JSONObject jsonData, Button resetMechanic) {
		super(resetMechanic);
		this.fullExample = (String) jsonData.get("main");
		String words = (String) jsonData.get("remove");
		
		this.wordsToRemove = new ArrayList<>(List.of(words.split(",")));

		root = new VBox(7);
		root.getStyleClass().add("mechanic-root");
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
					Draggable emptyDrag = new Draggable(Draggable.EMPTY_WORD, word, false);
					// add draggable to list for resetting later
					blanks.add(emptyDrag);
					// add to a list so we can create the
					// draggables with the words at the bottom
					words.add(word);

					row.getChildren().addAll(emptyDrag, new Text(" "));
				} else {
					// else just add the word to the row
					Label temp = new Label(word + " ");
					temp.getStyleClass().add("drag-text");
					temp.setMinHeight(30.0);
					row.getChildren().add(temp);
				}
			}
			root.getChildren().add(row);
		}
		return words;
	}

	private HBox addWordDrags() {
		// Add all words we've taken out
		HBox wordsBlock = new HBox();
		wordsBlock.setSpacing(15);
		wordsBlock.getStyleClass().add("drag-box");
		Collections.shuffle(wordDrags);
		wordsBlock.getChildren().addAll(wordDrags);

		return wordsBlock;
	}

	public VBox create() {
		// For Randomize
		root.getChildren().clear();
		
		
		Label prompt = new Label("Drag the words to the correct place to form valid synatx");
		prompt.setPadding(new Insets(0, 0, 10, 0));
		root.getChildren().add(prompt);
		
		// take out all the words
		List<String> words = takeOutWords();

		// Stream all the words that we've taken out to new draggables to add
		wordDrags = words.stream().map(Draggable::new).toList();
		// We want to shuffle these later
		wordDrags = new ArrayList<>(wordDrags);

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

	public VBox getWrapper() {
		return wrapper;
	}

	@Override
	public void save(ObjectOutputStream out) {
		for (Draggable d : blanks) {
			d.write(out);
		}
		for (Draggable d : wordDrags) {
			d.write(out);
		}
	}

	@Override
	public void load(ObjectInputStream in) {
		try {
			for (Draggable d : blanks) {
				d.read(in);
			}
			for (Draggable d : wordDrags) {
				d.read(in);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected int getTotal() {
		return blanks.size();
	}

	@Override
	protected int getCurrentScore() {
		int score = 0;
		for(Draggable d : blanks) {
			if (d.isCorrect()) {
				score++;
			}
		}
		return score;
	}
}
