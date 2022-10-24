package application;

import org.json.simple.JSONObject;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Level extends SubScene {
	private JSONObject jsonData;
	private String title;
	private String body;
	private String example;
	public Level(Stage stage, DefaultScene subScene, JSONObject levelData) {
		super(stage, new VBox(), subScene);
		this.title =   (String) levelData.get("title");
		this.body =    (String) levelData.get("body");
		this.example = (String) levelData.get("example");
		initElements();
//		bindButtons();
		
	}

	@Override
	protected void initElements() {
		createTitleBar(title);
	}

	public void bindButtons(DefaultScene scene) {
		bindButtonToScene(getBack(), scene);
	}

}
