package application.Drag;

import javafx.geometry.Pos;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Region;

public class Draggable extends Label {
	private String correctWord;
	private String originalWord;
	private int spaces;
	private boolean willMove;
	private boolean isEmpty;
	
	// overload
	public Draggable(String originalWord) {
		this(originalWord, null, false);
	}
	// overload
	public Draggable(String originalWord, boolean willMove) {
		this(originalWord, null, willMove);
	}


	public Draggable(String originalWord, String correctWord, boolean willMove) {
		super();
		this.originalWord = originalWord;
		this.correctWord = correctWord;
		spaces = -1;
//		setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
//		setPrefSize(70, 10);
		setAlignment(Pos.CENTER);
		getStyleClass().add("drag");

		reset();
		init();
	}
	
	// Called when reset is called in DragandDrop
	public void reset() {
		if (originalWord == null) {
			empty();
			if (spaces > 0) setSpaces();
		} else {
			set(originalWord);
		}
	}

	// Set up this label to be blank
	// and able to receive a draggable
	private void empty() {
		setText("");
		getStyleClass().add("drag-empty");
		getStyleClass().remove("drag-full");
		isEmpty = true;
	}

	// Set up label to display given string
	// and able to be dragged
	private void set(String text) {
		setText(text);
		getStyleClass().add("drag-full");
		getStyleClass().remove("drag-empty");
		isEmpty = false;
	}
	
	// Initialize all the mouse events
	private void init() {
		
		// called when you try to drag
		setOnDragDetected((MouseEvent event) -> {
			if (!isEmpty) { // cant drag empty ones
//				System.out.println("Dragging...");
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
//				System.out.println("Dropped: " + db.getString());
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
	
	public void setSpaceString(int spaces) {
		this.spaces = spaces;
		setSpaces();
	}
	
	private void setSpaces() {
		String format = "\"%-" + spaces + "s\"";
		System.out.println(format);
		String spaces = String.format(format, " ");
		setText(spaces);
	}
}
