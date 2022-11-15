package application.Scenes;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javafx.stage.Stage;

public class LevelCreation {

	// private static final int NUMBER_LEVELS = 10;
	private List<Level> levels;
	private JSONArray levelList;

	public LevelCreation() {
		levels = new ArrayList<>();
	}

	public List<Level> initLevels(Stage stage, LevelSelectionScene levelSelectScene) {
		JSONObject levelData;

		pullFromFile();

		// The way each level is stored in JSON
		// we use the level number as the key
		int i = 1;
		for (Object o : levelList) {
			JSONObject O = (JSONObject) o;
			levelData = (JSONObject) O.get(String.valueOf(i++));
			// create level with JSON array of just that levels data
			levels.add(new Level(stage, levelSelectScene, levelData));
		}
		return levels;
	}

	// Pull out JSON array from file
	// We read each levels data in Level

	private void pullFromFile() {
		File file = new File("src/main/resources/leveldata.json");

		try (FileReader inFile = new FileReader(file);) {
			JSONParser jsonIn = new JSONParser();
			levelList = (JSONArray) jsonIn.parse(inFile);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
