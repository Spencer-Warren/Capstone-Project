package application.Scenes;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenuScene extends DefaultScene {
	private Button levelButton;
	private Button aboutButton;

	public MainMenuScene(Stage stage) {
		super(stage, new VBox());
		initElements(); // Initialize elements, this is to be defined in subclasses
	}

	@Override
	protected void initElements() {
		levelButton = new Button("Levels");
		aboutButton = new Button("About..");
		Button exit = new Button("Exit");

		exit.setOnAction((ActionEvent event) -> Platform.exit());

		createTitleBar("Learn to Use Java!!");

		Text foot = new Text();
		foot.setText("By: Spencer Warren");
		foot.setFont(Font.font(20));
		foot.getStyleClass().add("footer");
		
		VBox buttons = new VBox(65);
		buttons.setAlignment(Pos.CENTER);
		buttons.getChildren().addAll(levelButton, aboutButton, exit);

		// make buttons fill empty space
		buttons.prefHeightProperty().bind(getScene().heightProperty());
				
		for (Node n : buttons.getChildren()) {
			if (n instanceof Button b) {
				b.setPrefSize(450, 40);
				b.getStyleClass().add("button-menu");
			}
		}
		getRoot().getChildren().addAll(buttons, foot);
	}

	public Button getLevelButton() {
		return levelButton;
	}

	public Button getAboutButton() {
		return aboutButton;
	}
}
