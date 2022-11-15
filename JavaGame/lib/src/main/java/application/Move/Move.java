package application.Move;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import application.Mechanic;
import application.Drag.Draggable;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Move implements Mechanic {
	private BorderPane root;
	private VBox wrapper;
	private JSONObject rightWrong;
	private List<Draggable> options;
	private List<Draggable> destinations;

	public Move(JSONObject rightWrong) {

		root = new BorderPane();
		wrapper = new VBox(root);
		wrapper.setAlignment(Pos.CENTER);
		wrapper.setFillWidth(false);
		blanks = new ArrayList<>();

		root.getStyleClass().add("drag-root");
	}

	public VBox create() {

		return wrapper;
	}
	
	private void originDrags() {
		String rights = (String) rightWrong.get("correct");
		Draggable temp;
		for(String right : rights.split(",")) {
			temp = new Draggable(right);
		}
	}
	
	
	private boolean isCorrect() {
		for (Draggable d : destinations) {
			if (!d.isCorrect())
				return false;
		}
		return true;
	}

}
