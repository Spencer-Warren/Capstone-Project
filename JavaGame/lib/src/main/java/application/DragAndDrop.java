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
	private Draggable start;
	private Draggable end;

	public DragAndDrop() {
		start = new Draggable("Drag Me");
		end = new Draggable("Replace me");


	}

	public VBox create() {
		VBox root = new VBox();

		root.getChildren().addAll(start, end);
		return root;

	}

}
