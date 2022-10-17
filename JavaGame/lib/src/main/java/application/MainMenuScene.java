package application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
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

		Text title = new Text();
		title.setText("Java Capstone Project");
		title.setFill(Color.BLACK);
		title.setFont(Font.font("Verdana", FontWeight.NORMAL, 55));
		title.setTextAlignment(TextAlignment.CENTER);

		StackPane titlePane = new StackPane();
		titlePane.getChildren().add(title);
		StackPane.setAlignment(title, Pos.CENTER);
		titlePane.setPadding(new Insets(50, 0, 0, 0));

		Text foot = new Text();
		foot.setText("By: Spencer Warren");
		foot.setFont(Font.font(20));
		
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
		getRoot().getChildren().addAll(titlePane, buttons, foot);
	}

	public Button getLevelButton() {
		return levelButton;
	}

	public Button getAboutButton() {
		return aboutButton;
	}
}
