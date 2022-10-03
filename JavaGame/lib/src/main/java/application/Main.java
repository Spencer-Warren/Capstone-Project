package application;
import static application.DefaultProperties.*;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Main extends Application {
	Scene mainMenu;
	Scene levelSelectScene;
	Stage stage;

	@Override
	public void start(Stage stage) {
		this.stage = stage;

		stage.setTitle("445 Capstone");

		mainMenu = new MainMenuScene();
		initLevelSelect();

		stage.setScene(mainMenu);
		stage.show();
	}

	private void initLevelSelect() {

		HBox root = new HBox();
		root.setBackground(defaultBackground);
		levelSelectScene = new Scene(root, sceneWidth, sceneHeight);
	}


	public static void main(String[] args) {
		launch(args);
	}
}
