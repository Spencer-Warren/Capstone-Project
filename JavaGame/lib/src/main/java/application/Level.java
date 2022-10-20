package application;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Level extends SubScene {
	private int number;
	public Level(Stage stage, DefaultScene subScene, int number) {
		super(stage, new VBox(), subScene);
		this.number = number;
		initElements();
//		bindButtons();
		
	}

	@Override
	protected void initElements() {
		createTitleBar("Level: " + number);
	}

	public void bindButtons(DefaultScene scene) {
		bindButtonToScene(getBack(), scene);
	}

}
