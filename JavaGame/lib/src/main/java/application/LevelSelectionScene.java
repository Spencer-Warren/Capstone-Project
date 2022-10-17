package application;


import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LevelSelectionScene extends SubScene {
	
	protected LevelSelectionScene(Stage stage, DefaultScene subScene) {
		super(stage, new VBox(), subScene);
	}

	@Override
	protected void initElements() {
		Text title = new Text();
		title.setText("Level Selection");
		title.setFill(Color.BLACK);
		title.setFont(Font.font("Verdana", FontWeight.NORMAL, 50));
		
		GridPane buttonGrid = levelButtons();
		
		VBox mainColumn = new VBox(title, buttonGrid);
		VBox buttonColumn = new VBox(getBack());

		mainColumn.setAlignment(Pos.TOP_CENTER);
		buttonColumn.setAlignment(Pos.TOP_LEFT);
		
		HBox subRoot = new HBox(buttonColumn, mainColumn);
		DefaultProperties.fillParent(getRoot(), subRoot);
		
		HBox.setHgrow(mainColumn, Priority.ALWAYS);
		VBox.setVgrow(buttonGrid, Priority.ALWAYS);
		
		getRoot().getChildren().addAll(subRoot);
		
	}
	
	private GridPane levelButtons() {
		int levelNumber = 1;
		GridPane grid = new GridPane();
		Font font = Font.font("Helvetica", FontWeight.BOLD, 40);
		
		grid.setHgap(40);
		grid.setVgap(40);
		grid.setAlignment(Pos.CENTER);
		
		for(int x = 0; x < 2; x++) {
			for(int y = 0; y < 6; y++) {
				Button button = new Button(String.valueOf(levelNumber++));
				button.setMinHeight(110);
				button.setMinWidth(110);
				button.setFont(font);
				button.setTextFill(Color.RED);
				grid.add(button, y, x);
			}
			
		}
		return grid;
	}

}
