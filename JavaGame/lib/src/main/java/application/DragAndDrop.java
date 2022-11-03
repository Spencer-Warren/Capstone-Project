package application;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class DragAndDrop {
	private Text start;
	private Text end;

	public DragAndDrop() {
		start = new Text("Drag Me");
		end = new Text("Replace me");

		styleDrag(start);
		styleDrag(end);
	}

	public VBox create() {
		VBox root = new VBox();

		drag();

		root.getChildren().addAll(start, end);
		return root;

	}

	public void drag() {
		start.setOnDragDetected((MouseEvent event) -> {
			System.out.println("Dragging...");
			Dragboard db = start.startDragAndDrop(TransferMode.ANY);
			ClipboardContent content = new ClipboardContent();
			content.putString(start.getText());
			db.setContent(content);
			event.consume();
		});

		start.setOnMouseDragged((MouseEvent event) -> {
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

		end.setOnDragDropped((DragEvent event) -> {
			Dragboard db = event.getDragboard();
			if (db.hasString()) {
				System.out.print("Dropped: " + db.getString());
				end.setText(db.getString());
				event.setDropCompleted(true);
			} else {
				event.setDropCompleted(false);
			}
			event.consume();
		});

	}
	
	private void styleDrag(Text item) {
	 
		item.getStyleClass().add("drag");
	}
}
