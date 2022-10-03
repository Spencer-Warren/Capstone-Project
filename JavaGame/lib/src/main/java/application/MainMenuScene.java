package application;

import static application.DefaultProperties.*;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenuScene extends DefaultScene {

	protected MainMenuScene(Stage stage) {
		super(new HBox(), stage);
	}

	@Override
	protected void initElements() {
		Button levelSelect = new Button("Levels");
		Button about = new Button("About..");
		Button exit = new Button("Exit");

		levelSelect.setOnAction((ActionEvent event) -> System.out.println("Ha")); //getStage().setScene(levelSelectScene));

		exit.setOnAction((ActionEvent event) -> Platform.exit());

		VBox buttons = new VBox(50);
		buttons.setAlignment(Pos.CENTER_LEFT);
		buttons.getChildren().addAll(levelSelect, about, exit);

		for (Node n : buttons.getChildren()) {
			if (n instanceof Button b) {
				b.setPrefSize(400, 40);
			}
		}

		// then you set to your node
		getRoot().setBackground(defaultBackground);
		HBox.setMargin(getRoot(), new Insets(50, 50, 50, 50));
		getRoot().getChildren().addAll(buttons);
	}
	
}
