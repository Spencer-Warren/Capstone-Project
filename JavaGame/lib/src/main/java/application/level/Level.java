package application.level;

import org.json.simple.JSONObject;

import application.Mechanic;
import application.Move.Move;
import application.drag.DragAndDrop;
import application.multiplechoice.*;
import application.scenes.DefaultScene;
import application.scenes.SubScene;
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
	private JSONObject levelData;
	private String title;
	private String bodyText;
	private String example;
	private VBox body;
	private Mechanic mechanic;

	public Level(Stage stage, DefaultScene subScene, JSONObject levelData) {
		super(stage, new VBox(), subScene);
		this.levelData = levelData;
		this.body = new VBox();
		
		convertJSON();
		initElements();
		addMechanic();
	}
	
	public Mechanic getMechanic() {
		return mechanic;
	}
	
	// Save strings gotten from JSON file
	private void convertJSON() {
		this.title    = (String) levelData.get("title");
		this.bodyText = (String) levelData.get("body");
		this.example  = (String) levelData.get("example");
	}
	
	// Detect what mechanic is specified in the JSON file
	// Then load that mechanic
	private void addMechanic() {
		if (levelData.containsKey("remove")) {
			mechanic = new DragAndDrop(example, (String) levelData.get("remove"));
		} 
		
		else if (levelData.containsKey("move")) {
			mechanic = new Move((JSONObject)levelData.get("move"));
		}
		
		else if (levelData.containsKey("choice")) {
			mechanic = new MultipleChoice((JSONObject) levelData.get("choice"));
			
		}
		
		if (mechanic != null)
			body.getChildren().add(mechanic.create());
	}

	@Override
	protected void initElements() {
		createTitleBar(title);
		createBody();
		
		body.setSpacing(15);
		body.setPadding(new Insets(20));
		body.getStyleClass().add("level-body");
		
		// not sure if ill use a scroll pane yet
		ScrollPane scroll = new ScrollPane();
		scroll.setContent(getRoot());
		scroll.setFitToWidth(true);
		scroll.setFitToHeight(true);
		scroll.getStyleClass().add("scroll-pane");
		
		// ScrollPane as root to fit all elements
		setRoot(scroll);
		
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
