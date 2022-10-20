package application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class MainMenuScene extends DefaultScene {
	private Button levelButton;
	private Button aboutButton;

	protected MainMenuScene(Stage stage) {
		super(stage, new VBox());
		initElements(); // Initialize elements, this is to be defined in subclasses
	}

	@Override
	protected void initElements() {
		levelButton = new Button("Levels");
		aboutButton = new Button("About..");
		Button exit = new Button("Exit");

		exit.setOnAction((ActionEvent event) -> Platform.exit());

		Label title = new Label();
		title.setText("Java Capstone Project");
		title.setAlignment(Pos.CENTER);
		title.setMaxWidth(Double.MAX_VALUE);
		title.getStyleClass().add("title-label");

		Text foot = new Text();
		foot.setText("By: Spencer Warren");
		foot.setFont(Font.font(20));
		foot.getStyleClass().add("footer");
		
		VBox buttons = new VBox(65);
		buttons.setAlignment(Pos.CENTER);
		buttons.getChildren().addAll(levelButton, aboutButton, exit);

		// make buttons fill empty space
		buttons.prefHeightProperty().bind(getScene().heightProperty());
		
		Font buttonFont = Font.font(20);
		
		for (Node n : buttons.getChildren()) {
			if (n instanceof Button b) {
				b.setPrefSize(400, 40);
				b.setFont(buttonFont);
			}
		}
		getRoot().getChildren().addAll(title, buttons, foot);
	}

	public Button getLevelButton() {
		return levelButton;
	}

	public Button getAboutButton() {
		return aboutButton;
	}
}
