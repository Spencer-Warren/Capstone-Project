package application;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LevelSelectionScene extends SubScene {
	
	protected LevelSelectionScene(Stage stage, DefaultScene subScene) {
		super(stage, new VBox(), subScene);
	}

	@Override
	protected void initElements() {
		Text text = new Text();
		text.setFill(Color.WHITE);
		
		text.setText("Level Selection");
		
		getRoot().getChildren().addAll(back, text);
		
	}

}
