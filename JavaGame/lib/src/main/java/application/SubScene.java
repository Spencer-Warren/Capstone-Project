package application;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SubScene extends DefaultScene {
	private DefaultScene subScene;
	protected Button back;
	
	protected SubScene(Stage stage, VBox root, DefaultScene subScene) {
		super(stage, root);
		this.subScene = subScene;
		this.back = new Button("Back...");
		initElements(); // Initialize elements, this is to be defined in subclasses
	}

	@Override
	protected void initElements() {
	}
	
	public void bindButtons() {
		this.bindButtonToScene(back, subScene);
	}

}
