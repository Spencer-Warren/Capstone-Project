package application.level;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import application.Mechanic;
import application.scenes.LevelSelectionScene;
import javafx.stage.Stage;

public class LevelCreation {
	private List<Level> levels;
	private JSONArray levelList;
	private HashMap<Integer, Mechanic> mechanicStates;

	public LevelCreation() {
		levels = new ArrayList<>();
	}

	public List<Level> initLevels(Stage stage, LevelSelectionScene levelSelectScene) {
		JSONObject levelData;

		pullFromFile();
		pullMechanicStates();

		// The way each level is stored in JSON
		// we use the level number as the key
		int i = 1;
		for (Object o : levelList) {
			JSONObject O = (JSONObject) o;
			levelData = (JSONObject) O.get(String.valueOf(i++));
			// create level with JSON array of just that levels data
			if (mechanicStates.containsKey(i)) {
				Mechanic mech = mechanicStates.get(i);
				levels.add(new Level(stage, levelSelectScene, levelData, mech));
			} else {
				levels.add(new Level(stage, levelSelectScene, levelData));
			}
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

	private void pullMechanicStates() {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/main/resources/states.txt"));
			int numToRead = in.readInt();
			int levelNum;
			Mechanic temp;
			for(int i = 0; i < numToRead: i++) {
				levelNum
				temp 
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
