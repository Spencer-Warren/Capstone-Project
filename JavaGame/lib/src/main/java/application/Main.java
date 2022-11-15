package application;

import java.util.List;

import application.Scenes.AboutScene;
import application.Scenes.Level;
import application.Scenes.LevelCreation;
import application.Scenes.LevelSelectionScene;
import application.Scenes.MainMenuScene;
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

		initScenes();
		
		stage.setScene(mainMenu.getScene()); // start on main Menu
		stage.setResizable(false); // Disable resizing to reduce complexity
		stage.show();
	}

	// initialize scenes and bind buttons
	// Load order is very important
	private void initScenes() {
		levels = new LevelCreation().initLevels(stage, levelSelectScene);
		mainMenu = new MainMenuScene(stage);
		levelSelectScene = new LevelSelectionScene(stage, mainMenu, levels);
		aboutScene = new AboutScene(stage, mainMenu);
		
		// Binding buttons to switch between different scenes
		mainMenu.bindButtonToScene(mainMenu.getLevelButton(), levelSelectScene);
		mainMenu.bindButtonToScene(mainMenu.getAboutButton(), aboutScene);
		levelSelectScene.bindButtons();
		aboutScene.bindButtons();
		for(Level l : levels) {
			l.bindButtons(levelSelectScene);
		}
	}

	
	public static void main(String[] args) {
		launch(args);
	}
}
