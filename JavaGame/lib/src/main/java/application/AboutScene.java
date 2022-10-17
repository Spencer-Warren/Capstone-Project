package application;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AboutScene extends SubScene {
	
	protected AboutScene(Stage stage, DefaultScene subScene) {
		super(stage, new VBox(), subScene);
	}
	@Override
	protected void initElements() {
		
		getRoot().getChildren().add(getBack());
	}


}
