package application;

import org.json.simple.JSONObject;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Level extends SubScene {
	private String title;
	private String bodyText;
	private String example;
	
	public Level(Stage stage, DefaultScene subScene, JSONObject levelData) {
		super(stage, new VBox(), subScene);
		this.title =   (String) levelData.get("title");
		this.bodyText =    (String) levelData.get("body");
		this.example = (String) levelData.get("example");
		initElements();		
	}

	@Override
	protected void initElements() {
		createTitleBar(title);
		Label body = new Label(this.bodyText);
		body.setWrapText(true);
		body.setTextAlignment(TextAlignment.JUSTIFY);
		body.setFont(new Font(15));
		
		getRoot().getChildren().add(body);
	}

	public void bindButtons(DefaultScene scene) {
		bindButtonToScene(getBack(), scene);
	}

}
