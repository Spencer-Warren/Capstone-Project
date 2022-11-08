package application;

import org.json.simple.JSONObject;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Level extends SubScene {
	private String title;
	private String bodyText;
	private String example;
	private String wordsToRemove;
	private VBox body;

	public Level(Stage stage, DefaultScene subScene, JSONObject levelData) {
		super(stage, new VBox(), subScene);
		this.title = (String) levelData.get("title");
		this.bodyText = (String) levelData.get("body");
		this.example = (String) levelData.get("example");
		this.wordsToRemove = (String) levelData.get("remove");
		this.body = new VBox();
		body.setSpacing(15);
		initElements();
	}

	@Override
	protected void initElements() {
		createTitleBar(title);
		createBody();

		body.setPadding(new Insets(20));
		body.getStyleClass().add("level-body");
		
		// not sure if ill use a scroll pane yet
		ScrollPane scroll = new ScrollPane();
		scroll.setContent(getRoot());
		scroll.setFitToWidth(true);
		scroll.setFitToHeight(true);
		scroll.getStyleClass().add("scroll-pane");
		
		setRoot(scroll);
		if (wordsToRemove != null) {
			DragAndDrop drag = new DragAndDrop(example, wordsToRemove.split(","));
			body.getChildren().add(drag.create());
		}
		
		
		getRoot().getChildren().addAll(body);
	}

	private void createBody() {

		String[] lines = bodyText.split("\n");

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
			
			else {
				textString.append(line);
			}

		}
		
		addToBody(new StringBuilder("Example:"), false);
		addToBody(new StringBuilder(example), true);

	}
	
	private void addToBody(StringBuilder text, boolean isCode) {
		
		Label block = new Label(text.toString().trim());
		text.setLength(0); // clear string builder
		block.getStyleClass().add(isCode ? "code-block" : "text-block");
		
		// Use the computed height to display all code in blocks
		block.setMinHeight(Region.USE_PREF_SIZE);
		
		body.getChildren().add(block);
	}

	public void bindButtons(DefaultScene scene) {
		bindButtonToScene(getBack(), scene);
	}

}
