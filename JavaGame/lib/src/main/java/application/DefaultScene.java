package application;

import static application.DefaultProperties.*;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

public abstract class DefaultScene {
	private Scene scene;
	private Pane root;
	private Stage stage;
	
	protected DefaultScene(Pane root, Stage stage) {
		this.root = root;
		this.stage = stage;
		root.setBackground(DEFAULT_BACKGROUND);
		scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
	}

	public Scene getScene() {
		return scene;
	}

	public Pane getRoot() {
		return root;
	}

	public Stage getStage() {
		return stage;
	}
	
	public void bindButtonToScene(Button button, DefaultScene sceneWrapper) {
		button.setOnAction(
				(ActionEvent a) -> stage.setScene(sceneWrapper.getScene())
				);
	}

	protected abstract void initElements();
}
