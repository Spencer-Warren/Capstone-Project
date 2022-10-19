package application;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public abstract class SubScene extends DefaultScene {
	private DefaultScene subScene;
	private Button back;

	protected SubScene(Stage stage, VBox root, DefaultScene subScene) {
		super(stage, root);
		this.subScene = subScene;
		this.back = new Button("Back...");
//		initElements(); // Initialize elements, this is to be defined in subclasses
	}

	@Override
	protected abstract void initElements();
	
	protected Button getBack() {
		return back;
	}

	public void bindButtons() {
		this.bindButtonToScene(back, subScene);
	}
}
