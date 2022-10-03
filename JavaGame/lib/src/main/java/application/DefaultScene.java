package application;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public abstract class DefaultScene {
	Scene scene;
	Pane root;
	
	protected DefaultScene(Pane root) {
		this.root = root;
		root.setBackground(DefaultProperties.defaultBackground);
		scene = new Scene(root, DefaultProperties.SCENE_WIDTH, DefaultProperties.SCENE_HEIGHT);
		initElements();
	}
	
	protected abstract void initElements();
}
