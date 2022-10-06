package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	private MainMenuScene mainMenu;
	private LevelSelectionScene levelSelectScene;
	private AboutScene aboutScene;
	private Stage stage;

	@Override
	public void start(Stage stage) {
		this.stage = stage;
		stage.setTitle("445 Capstone");

		initScenes();

		stage.setScene(mainMenu.getScene()); // start on main Menu
		stage.setResizable(false);
		stage.show();
	}

	// initialize scenes and bind buttons
	private void initScenes() {
		mainMenu = new MainMenuScene(stage);
		levelSelectScene = new LevelSelectionScene(stage, mainMenu);
		aboutScene = new AboutScene(stage, mainMenu);

		mainMenu.bindButtonToScene(mainMenu.getLevelButton(), levelSelectScene);
		mainMenu.bindButtonToScene(mainMenu.getAboutButton(), aboutScene);
		levelSelectScene.bindButtons();
		aboutScene.bindButtons();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
