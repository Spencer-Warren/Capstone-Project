package application;

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
	
	LevelCreation() {
		levels = new ArrayList<>();		
	}

	@SuppressWarnings("unchecked")
	public List<Level> initLevels(Stage stage, LevelSelectionScene levelSelectScene) {
		JSONObject levelData;
		
		pullFromFile();		
		
		int i = 1;
		for (Object o : levelList) {
			JSONObject O = (JSONObject) o;			
			levelData = (JSONObject) O.get(String.valueOf(i++));
			levels.add(new Level(stage, levelSelectScene, levelData));
		}
		return levels;
	}
	
	private void pullFromFile() {
		File file = new File("src/main/resources/leveldata.json");
		
		try (FileReader inFile = new FileReader(file);) {
			JSONParser jsonIn = new JSONParser();
			levelList = (JSONArray)jsonIn.parse(inFile);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
