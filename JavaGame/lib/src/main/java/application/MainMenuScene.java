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

public class MainMenuScene extends DefaultScene {

	protected MainMenuScene(Scene s) {
		super(new HBox());
	}

	@Override
	protected void initElements() {
		Button levelSelect = new Button("Levels");
		Button about = new Button("About..");
		Button exit = new Button("Exit");

		levelSelect.setOnAction((ActionEvent event) -> stage.setScene(levelSelectScene));

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
		root.setBackground(defaultBackground);
		HBox.setMargin(root, new Insets(50, 50, 50, 50));
		root.getChildren().addAll(buttons);
	}
	
}
