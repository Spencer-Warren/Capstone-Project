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
		this.root = root; // the root pane we are adding elements too
		this.stage = stage; // the stage (or window) we are anchored to
		root.setBackground(DEFAULT_BACKGROUND); // Use our default background in default properties
		scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
		initElements(); // Initialize elements, this is to be defined in subclasses
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
	
	/**
	 * 	Given a button and a scene, 
	 * 	bind the button activation to 
	 * 	switch to the given scene
	 */
	public void bindButtonToScene(Button button, DefaultScene sceneWrapper) {
		button.setOnAction(
				(ActionEvent a) -> stage.setScene(sceneWrapper.getScene())
				);
	}

	protected abstract void initElements();
}
