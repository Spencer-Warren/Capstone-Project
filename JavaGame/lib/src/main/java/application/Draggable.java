package application;

import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

public class Draggable extends Label {
	private String correctWord;
	private String originalWord;
	private boolean isEmpty;
	private boolean isCorrect;

	public Draggable(String originalWord, String correctWord) {
		super();
		this.originalWord = originalWord;
		this.correctWord = correctWord;
		this.setPrefSize(50, 20);
		this.getStyleClass().add("drag");

		if (originalWord == null) {
			empty();
		} else {
			set(originalWord);
		}
		init();
	}

	// Set up this label to be blank
	// and able to receive a draggable
	private void empty() {
		setText("");
		getStyleClass().add("empty-drag");
		getStyleClass().remove("full-drag");
		isEmpty = true;
	}

	// Set up label to display given string
	// and able to be dragged
	private void set(String text) {
		setText(text);
		getStyleClass().add("full-drag");
		getStyleClass().remove("empty-drag");
		isEmpty = false;
	}

	private void init() {

		setOnDragDetected((MouseEvent event) -> {
			if (!isEmpty) {
				System.out.println("Dragging...");
				Dragboard db = startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
				content.putString(getText());
				db.setContent(content);

				WritableImage snapshot = snapshot(new SnapshotParameters(), null);
				db.setDragView(snapshot);
			}

			event.consume();
		});

		setOnMouseDragged((MouseEvent event) -> event.setDragDetect(true));

		setOnDragOver((DragEvent event) -> {
			if (event.getGestureSource() != this && event.getDragboard().hasString()) {
				event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
			}
			event.consume();
		});

		setOnDragDropped((DragEvent event) -> {
			Dragboard db = event.getDragboard();
			if (db.hasString() && isEmpty) {
				System.out.println("Dropped: " + db.getString());
				set(db.getString());

				Draggable source = (Draggable) event.getGestureSource();
				source.empty();

				event.setDropCompleted(true);
			} else {
				event.setDropCompleted(false);
			}
			event.consume();
		});

	}

	public boolean isCorrect() {
		return getText().equals(correctWord);
	}
}
