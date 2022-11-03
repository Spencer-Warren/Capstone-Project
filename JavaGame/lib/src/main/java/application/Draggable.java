package application;

import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

public class Draggable extends Label{
	private boolean isEmpty;

	public Draggable(String text) {
		super();
		set(text);
		init();
	}

	public Draggable() {
		super();
		empty();
		init();
	}
	
	private void empty() {
		setText("");
		isEmpty = true;
	}
	
	private void set(String text) {
		
	}

	private void init() {
		setOnDragDetected((MouseEvent event) -> {
			System.out.println("Dragging...");
			Dragboard db = startDragAndDrop(TransferMode.ANY);
			ClipboardContent content = new ClipboardContent();
			content.putString(getText());
			db.setContent(content);
			
			WritableImage snapshot = snapshot(new SnapshotParameters(), null);
			db.setDragView(snapshot);
			
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
				setText(db.getString());
				isEmpty = false;
				
				Draggable source = (Draggable) event.getGestureSource();
				source.empty();
				
				event.setDropCompleted(true);
			} else {
				event.setDropCompleted(false);
			}
			event.consume();
		});

	}
}
