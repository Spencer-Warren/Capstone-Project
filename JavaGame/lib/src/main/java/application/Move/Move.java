package application.Move;

import java.awt.Toolkit;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Move extends Mechanic {
	
	private HBox root;
	private VBox wrapper;
	
	private JSONObject rightWrongJSON;
	
	private List<String> correctStrings;
	private List<Draggable> options;
	private List<Draggable> destinations;
	
	private int numberRight;
	private int  minWidth;

	public Move(JSONObject rightWrong) {

		root = new HBox();
		options = new ArrayList<>();
		destinations = new ArrayList<>();
		
		numberRight = 0;
		minWidth  = 0;
		
		this.rightWrongJSON = rightWrong;
		
		wrapper = new VBox(root);
		wrapper.setAlignment(Pos.CENTER);
		wrapper.setFillWidth(false);

		root.getStyleClass().add("drag-root");
	}

	public VBox create() {
		
		originDrags();
		
		Pane originPane = createOriginPane();
		
		VBox destinatonBox = createDestinationBox();
		
		fixWidth();

		root.getChildren().addAll(destinatonBox, originPane);
		return wrapper;
	}
	
	

	private void originDrags() {
		Draggable temp;
		
		String rights = (String) rightWrongJSON.get("correct");
		correctStrings = List.of(rights.split(","));
		for(String right : rights.split(",")) {
			temp = new Draggable(right, true);
			options.add(temp);
			numberRight++; 
			minWidth = minWidth < right.length() ? minWidth : right.length();
		}
		
		String wrongs = (String) rightWrongJSON.get("incorrect");
		for(String wrong : wrongs.split(",")) {
			temp = new Draggable(wrong, true);
			options.add(temp);
			minWidth = minWidth < wrong.length() ? minWidth : wrong.length();
		}
	}
	
	private Pane createOriginPane() {
		VBox p = new VBox(10);
		p.getChildren().addAll(options);
		return p;
	}
	
	private VBox createDestinationBox() {
		Draggable temp;
		
		Label destinationLabel = new Label("Correct things");
		VBox destinationBox = new VBox(destinationLabel);
		
		for (String word : correctStrings) {
			temp = new Draggable(null, word, false);
			destinations.add(temp);
		}
		destinationBox.getChildren().addAll(destinations);
		return destinationBox;
	}
	
	private void fixWidth() {
		for (Draggable d : destinations) {
			d.setSpaceString(minWidth);
		}
	}
	
	@Override
	protected boolean isCorrect() {
		for (Draggable d : destinations) {
			if (!d.isCorrect())
				return false;
		}
		return true;
	}

	@Override
	protected void reset() {
		
	}



}
