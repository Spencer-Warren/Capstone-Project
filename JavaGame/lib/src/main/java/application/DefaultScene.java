package application;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public abstract class DefaultScene {
	private Scene scene;
	private Pane root;
	private Stage stage;
	
	protected DefaultScene(Pane root, Stage stage) {
		this.root = root;
		this.stage = stage;
		root.setBackground(DefaultProperties.defaultBackground);
		scene = new Scene(root, DefaultProperties.SCENE_WIDTH, DefaultProperties.SCENE_HEIGHT);
		initElements();
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

	protected abstract void initElements();
}
