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
	MainMenuScene mainMenu;
	LevelSelectionScene levelSelectScene;
	Stage stage;

	@Override
	public void start(Stage stage) {
		this.stage = stage;

		stage.setTitle("445 Capstone");

		mainMenu = new MainMenuScene(stage);
		levelSelectScene = new LevelSelectionScene(stage);

		stage.setScene(mainMenu.getScene());
		stage.show();
	}


	public static void main(String[] args) {
		launch(args);
	}
}
