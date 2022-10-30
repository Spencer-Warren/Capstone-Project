package application;

import org.json.simple.JSONObject;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Level extends SubScene {
	private String title;
	private String bodyText;
	private String example;
	private VBox body;

	public Level(Stage stage, DefaultScene subScene, JSONObject levelData) {
		super(stage, new VBox(), subScene);
		this.title = (String) levelData.get("title");
		this.bodyText = (String) levelData.get("body");
		this.example = (String) levelData.get("example");
		this.body = new VBox();
		initElements();
	}

	@Override
	protected void initElements() {
		createTitleBar(title);
		createBody();

//		Label body = new Label(this.bodyText);
		body.setPadding(new Insets(20));
		DefaultProperties.border(body);

		getRoot().getChildren().add(body);
	}

	private void createBody() {

		String[] lines = bodyText.split("\n");

		VBox body = new VBox();
		boolean isCode = false;
		StringBuilder codeString = new StringBuilder();
		StringBuilder textString = new StringBuilder();
		
		for (String line : lines) {
			if (line.startsWith("```") && !line.contains("java")) {
				addToBody(codeString, true);
				isCode = false;
			}
			
			else if (isCode) {
				codeString.append(line);
			}
			
			else if (line.startsWith("```java")) {
				addToBody(textString, false);
				isCode = true;
			}
			
			else if (!isCode) {
				textString.append(line);
			}

		}

	}
	
	private void addToBody(StringBuilder text, boolean isCode) {
		Label block = new Label(text.toString());
		text.setLength(0); // clear string builder
		if (isCode) {
			DefaultProperties.border(block);
			block.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(10), Insets.EMPTY)));
			block.setPadding(new Insets(15));
			block.setStyle("-fx-text-fill: white;");
		}
		block.setWrapText(true);
		block.setTextAlignment(TextAlignment.JUSTIFY);
		block.setFont(new Font(15));
		
		body.getChildren().add(block);
	}

	public void bindButtons(DefaultScene scene) {
		bindButtonToScene(getBack(), scene);
	}

}
