package application.scenes;

import java.util.List;

import application.DefaultProperties;
import application.level.Level;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LevelSelectionScene extends SubScene {
	private List<Level> levels;
	
	public LevelSelectionScene(Stage stage, DefaultScene subScene, List<Level> levels) {
		super(stage, new VBox(), subScene);
		this.levels = levels;
		initElements();
	}

	@Override
	protected void initElements() {		
		FlowPane buttonFlow = levelButtons();
		
		createTitleBar("Level Selection");
		
		getRoot().getChildren().addAll( buttonFlow);
		
	}
	
	
	private FlowPane levelButtons() {
		
		int levelNumber = 1;
		
		FlowPane flow = new FlowPane();
		DefaultProperties.fillParent(getRoot(), flow);
		
		flow.setHgap(40);
		flow.setVgap(40);
		flow.setAlignment(Pos.CENTER);
		flow.setPadding(new Insets(100));
		
		for(int i = 0; i < levels.size(); i++) {
				
				Button button = new Button(String.valueOf(levelNumber));
				button.setPrefHeight(130);
				button.setPrefWidth(130);
				button.getStyleClass().add("button-level");
				flow.getChildren().add(button);
				// Since button with a 1 belongs to ,
				// the 1st level, but its in the 0th position
				bindButtonToScene(button, levels.get(levelNumber - 1));
				
				levelNumber++;
			
		}
		return flow;
	}
	

}
