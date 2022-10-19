package application;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Level extends SubScene {
	private int number;
	public Level(Stage stage, DefaultScene subScene, int number) {
		super(stage, new VBox(), subScene);
		this.number = number;
		initElements();
	}

	@Override
	protected void initElements() {
		Text title = new Text("Level: " + number);
		getRoot().getChildren().addAll(title);
	}

}
