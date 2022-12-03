package application.level;

import java.util.List;

import org.json.simple.JSONObject;

import application.Mechanic;
import application.drag.DragAndDrop;
import application.move.Move;
import application.multiplechoice.MultipleChoice;
import application.scenes.DefaultScene;
import application.scenes.SubScene;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Level extends SubScene {
	private int levelNumber;
	private JSONObject levelData;
	private String title;
	private String bodyText;
	private String example;
	private VBox body;
	private Mechanic mechanic;
	private Button resetMechanic;

	public Level(Stage stage, DefaultScene subScene, int levelNumber, JSONObject levelData) {
		super(stage, new VBox(), subScene);
		this.levelData = levelData;
		this.body = new VBox();
		this.levelNumber = levelNumber;

		convertJSON();
		initElements();
		
		resetMechanic = new Button("Randomize");
		resetMechanic.setOnAction((ActionEvent e) -> resetMechanic());
		
		addMechanic();
	}

	public Mechanic getMechanic() {
		return mechanic;
	}

	public int getLevelNumber() {
		return levelNumber;
	}

	// Save strings gotten from JSON file
	private void convertJSON() {
		this.title = (String) levelData.get("title");
		this.bodyText = (String) levelData.get("body");
		this.example = (String) levelData.get("example");
	}

	// Detect what mechanic is specified in the JSON file
	// Then load that mechanic
	private void addMechanic() {

		if (levelData.containsKey("drag")) {
			mechanic = new DragAndDrop((JSONObject) levelData.get("drag"), resetMechanic);
		}

		else if (levelData.containsKey("move")) {
			mechanic = new Move((JSONObject) levelData.get("move"), resetMechanic);
		}

		else if (levelData.containsKey("choice")) {
			mechanic = new MultipleChoice((JSONObject) levelData.get("choice"), resetMechanic);

		}

		if (mechanic != null) {
			VBox mechanicBox = mechanic.create();
			mechanicBox.getStyleClass().add("mechanic");
			body.getChildren().add(mechanicBox);
		}

	}

	// Remove the mechanic and then call mechanic creation again
	private void resetMechanic() {
		List<Node> children = body.getChildren();
		for (int i = 0; i < children.size(); i++) {
			Node temp = children.get(i);
			if (temp.getStyleClass().contains("mechanic")) {
				body.getChildren().remove(temp);
				addMechanic();
			}
		}
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
		/*
		 * I've delimited code in the JSON to start with ```java and end with ``` this
		 * lets us detect code blocks and format them accordingly
		 */
		String[] lines = bodyText.split("\n");

		boolean isCode = false;
		StringBuilder codeString = new StringBuilder();
		StringBuilder textString = new StringBuilder();

		for (String line : lines) {
			// End of a code block
			if (line.startsWith("```") && !line.contains("java")) {
				addToBody(codeString, true);
				isCode = false;
			}
			// still in code block
			else if (isCode) {
				codeString.append(line);
			}
			// Start of a code block
			else if (line.startsWith("```java")) {
				// Push all the normal text
				addToBody(textString, false);
				isCode = true;
			}
			// Normal text
			else {
				textString.append(line);
			}

		}

		if (!textString.isEmpty()) {
			addToBody(textString, false);
		}

		if (example != null) {
			addToBody(new StringBuilder("Example:"), false);
			addToBody(new StringBuilder(example), true);
		}

	}

	private void addToBody(StringBuilder text, boolean isCode) {

		Label block = new Label(text.toString().trim());
		block.setWrapText(!isCode);
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
