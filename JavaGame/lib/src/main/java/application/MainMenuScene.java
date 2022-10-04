package application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenuScene extends DefaultScene {
	private Button levelButton; 
	

	protected MainMenuScene(Stage stage) {
		super(new HBox(), stage);
	} 
 
	@Override
	protected void initElements() {
		levelButton = new Button("Levels");
		Button about = new Button("About..");
		Button exit = new Button("Exit");

		exit.setOnAction((ActionEvent event) -> Platform.exit());
	
		VBox buttons = new VBox(50);
		buttons.setAlignment(Pos.CENTER_LEFT);
		buttons.getChildren().addAll(levelButton, about, exit);

		for (Node n : buttons.getChildren()) {
			if (n instanceof Button b) {
				b.setPrefSize(400, 40);
			}
		}
		getRoot().getChildren().addAll(buttons);
		
	}
	
	public Button getLevelButton() {
		return levelButton;
	}
}

