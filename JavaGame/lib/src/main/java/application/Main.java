package application;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	private MainMenuScene mainMenu;
	private LevelSelectionScene levelSelectScene;
	private AboutScene aboutScene;
	private Stage stage;
	private List<Level> levels;

	@Override
	public void start(Stage stage) {
		this.stage = stage;
		stage.setTitle("445 Capstone");
		
		initLevels();
		initScenes();

		stage.setScene(mainMenu.getScene()); // start on main Menu
		stage.setResizable(false);
		stage.show();
	}

	// initialize scenes and bind buttons
	private void initScenes() {
		mainMenu = new MainMenuScene(stage);
		levelSelectScene = new LevelSelectionScene(stage, mainMenu, levels);
		aboutScene = new AboutScene(stage, mainMenu);
		
		mainMenu.bindButtonToScene(mainMenu.getLevelButton(), levelSelectScene);
		mainMenu.bindButtonToScene(mainMenu.getAboutButton(), aboutScene);
		levelSelectScene.bindButtons();
		aboutScene.bindButtons();
	}

	private void initLevels() {
		levels = new ArrayList<>();
		for (int i = 1; i < 13; i++) {
			levels.add(new Level(stage, levelSelectScene, i));
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
