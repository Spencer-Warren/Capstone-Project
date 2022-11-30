package application.drag;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

public class Draggable extends Label implements Serializable {
	private static final long serialVersionUID = 3518436839543353170L;
	public static final String EMPTY_WORD = " ";
	private String correctWord;
	private String originalWord;
	private boolean isEmpty;

	// overload
	public Draggable(String originalWord) {
		this(originalWord, EMPTY_WORD, false);
	}

	// overload
	public Draggable(String originalWord, boolean willMove) {
		this(originalWord, EMPTY_WORD, willMove);
	}

	public Draggable(String originalWord, String correctWord, boolean willMove) {
		super();
		this.originalWord = originalWord;
		this.correctWord = correctWord;
		setMinHeight(30);
		setPrefHeight(USE_COMPUTED_SIZE);
		setMinWidth(70);
		setAlignment(Pos.CENTER);
		getStyleClass().add("drag");

		reset();
		init();
	}

	// Called when reset is called in DragandDrop
	public void reset() {
		if (originalWord == null || originalWord.equals(EMPTY_WORD)) {
			empty();
		} else {
			set(originalWord);
		}
	}

	// Set up this label to be blank
	// and able to receive a draggable
	private void empty() {
		setText("");
		ObservableList<String> style = getStyleClass();
		if (!style.contains("drag-empty")) {
			style.add("drag-empty");
		}
		style.remove("drag-full");
		isEmpty = true;
	}

	// Set up label to display given string
	// and able to be dragged
	private void set(String text) {
		setText(text);
		ObservableList<String> style = getStyleClass();
		if (!style.contains("drag-full")) {
			style.add("drag-full");
		}
		style.remove("drag-empty");
		isEmpty = false;
	}

	// Initialize all the mouse events
	private void init() {

		// called when you try to drag
		setOnDragDetected((MouseEvent event) -> {
			if (!isEmpty) { // cant drag empty ones
				Dragboard db = startDragAndDrop(TransferMode.ANY);

				// clipboard to copy string
				ClipboardContent content = new ClipboardContent();
				content.putString(getText());
				db.setContent(content);

				// Snapshot to have a view when you drag it
				WritableImage snapshot = snapshot(new SnapshotParameters(), null);
				db.setDragView(snapshot);
			}

			event.consume();
		});

		setOnMouseDragged((MouseEvent event) -> event.setDragDetect(true));

		// make sure we arent dragging to the same node
		setOnDragOver((DragEvent event) -> {
			if (event.getGestureSource() != this && event.getDragboard().hasString()) {
				event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
			}
			event.consume();
		});

		// Called when you drag something into this node
		setOnDragDropped((DragEvent event) -> {
			// get the info
			Dragboard db = event.getDragboard();
			// check contents and if this node is empty
			if (db.hasString() && isEmpty) {
				// Paste string into this node
				set(db.getString());

				// empty the source node
				Draggable source = (Draggable) event.getGestureSource();
				source.empty();

				event.setDropCompleted(true);
			} else {
				event.setDropCompleted(false);
			}
			event.consume();
		});

	}

	// Check if the set word is the correct one
	public boolean isCorrect() {
		return getText().equals(correctWord);
	}

	public void write(ObjectOutputStream out) {
		try {
			if (getText().equals("")) {
				out.writeObject("NULL");
			} else {
				out.writeObject(getText());
			}
			out.writeBoolean(isEmpty);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void read(ObjectInputStream in) {
		try {
			String text = (String) in.readObject();
			boolean wasEmpty = in.readBoolean();
			if (wasEmpty) {
				empty();
			} else {
				set(text);
			}

		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

	}
}
