package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	private MainMenuScene mainMenu;
	private LevelSelectionScene levelSelectScene;
	private Stage stage;

	@Override
	public void start(Stage stage) {
		this.stage = stage;
		stage.setTitle("445 Capstone");
		
		initScenes();
		
		stage.setScene(mainMenu.getScene()); // start on main Menu
		stage.show();
	}
	
	// initialize scenes and bind buttons
	private void initScenes() {
		levelSelectScene = new LevelSelectionScene(stage);
		mainMenu = new MainMenuScene(stage);
		
	
		mainMenu.bindButtonToScene(mainMenu.getLevelButton(), levelSelectScene);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
