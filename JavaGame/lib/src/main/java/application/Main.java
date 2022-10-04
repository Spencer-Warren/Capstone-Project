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
		levelSelectScene = new LevelSelectionScene(stage);
		mainMenu = new MainMenuScene( stage);

		stage.setScene(mainMenu.getScene());
		stage.show();
	}
	
	public LevelSelectionScene getLevelSelectScene() {
		return levelSelectScene;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
