package application;

import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LevelSelectionScene extends SubScene {
	private List<Level> levels;
	
	protected LevelSelectionScene(Stage stage, DefaultScene subScene, List<Level> levels) {
		super(stage, new VBox(), subScene);
		this.levels = levels;
		initElements();
	}

	@Override
	protected void initElements() {		
		GridPane buttonGrid = levelButtons();
		
		createTitleBar("Level Selection");
		
		getRoot().getChildren().addAll( buttonGrid);
		
	}
	
	
	private GridPane levelButtons() {
		
		int levelNumber = 1;
		
		GridPane grid = new GridPane();
		DefaultProperties.fillParent(getRoot(), grid);
		
		grid.setHgap(40);
		grid.setVgap(40);
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(100));
		
		for(int x = 0; x < 2; x++) {
			for(int y = 0; y < 6
					; y++) {
				
				Button button = new Button(String.valueOf(levelNumber));
				button.setPrefHeight(130);
				button.setPrefWidth(130);
				button.getStyleClass().add("button-level");
				
				grid.add(button, y, x);
				// Since button with a 1 belongs to
				// the 1st level, but its in the 0th position
				bindButtonToScene(button, levels.get(levelNumber - 1));
				
				levelNumber++;
			}
			
		}
		return grid;
	}
	

}
