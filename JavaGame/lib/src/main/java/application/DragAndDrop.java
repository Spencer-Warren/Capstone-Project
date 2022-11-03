package application;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;

public class DragAndDrop {
	private Label start;
	private Label end;

	public DragAndDrop() {
		start = new Label("Drag Me");
		end = new Label();
		
		start.setMinHeight(50);
		start.setMinWidth(50);
		end.setMinHeight(50);
		end.setMinWidth(50);
	}

	public VBox create() {
		VBox root = new VBox();
		
	}

	public void drag() {
		start.setOnDragDetected( (MouseEvent event) -> {
			System.out.println("Dragging...");
			Dragboard db = start.startDragAndDrop(TransferMode.ANY);
			ClipboardContent content = new ClipboardContent();
			content.putString("Source text");
			});
		
		start.setOnMouseDragged( (MouseEvent event) -> {
			event.setDragDetect(true);
		});
		
		end.setOnDragOver(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				if (event.getGestureSource() != end && event.getDragboard().hasString()) {
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}
				event.consume();
			}
		});
		
		end.setOnDragDropped( (DragEvent event) -> {
			Dragboard db = event.getDragboard();
			if (db.hasString()) {
				System.out.print("Dropped: " + db.getString());
				event.setDropCompleted(true);
			}
			else {
				event.setDropCompleted(false);
			}
			event.consume();
		});
		

	}
}
