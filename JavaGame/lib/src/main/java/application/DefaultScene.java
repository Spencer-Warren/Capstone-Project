package application;

import static application.DefaultProperties.*;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public abstract class DefaultScene {
	private Scene scene;
	private VBox root;
	private Stage stage;

	protected DefaultScene(Stage stage, VBox root) {
		this.stage = stage;
		this.root = root; // the root pane we are adding elements too
		//root.setBackground(DEFAULT_BACKGROUND); // Use our default background in default properties
		root.getStylesheets().add(Main.class.getResource("/Fluent.css").toString());
		root.setPadding(new Insets(20));
		scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
	}

	public Scene getScene() {
		return scene;
	}

	public VBox getRoot() {
		return root;
	}

	/**
	 * Given a button and a scene, bind the button activation to switch to the given
	 * scene
	 */
	public void bindButtonToScene(Button button, DefaultScene sceneWrapper) {
		button.setOnAction((ActionEvent a) -> stage.setScene(sceneWrapper.getScene()));
	}

	protected abstract void initElements();
}
