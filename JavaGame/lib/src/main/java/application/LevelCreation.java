package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javafx.stage.Stage;

public class LevelCreation {

	private final static int NUMBER_LEVELS = 10;
	private List<Level> levels;
	private JSONArray levelList;
	private JSONArray json;

	@SuppressWarnings("unchecked")
	public List<Level> initLevels(Stage stage, LevelSelectionScene levelSelectScene) {
		levels = new ArrayList<>();
		json = new JSONArray();
		JSONObject levelData;
		pullFromFile();

		for (Object o : json) {
			JSONObject O = (JSONObject) o;
			levelData = (JSONObject) O.get("level");
			
			levels.add(new Level(stage, levelSelectScene, levelData));
		}
		return levels;
	}
	
	private void write() {
		json = new JSONArray();
		for(int i = 1; i < 13; i++ ) {
			JSONObject levelData = new JSONObject();
			levelData.put("title", "This title");
			levelData.put("body", "this body");
			levelData.put("example", "this example");
			JSONObject level = new JSONObject();
			level.put(i, levelData);
			json.add(level);
		}
		System.out.print(json.toJSONString());
		pushToFile();
	}

	private void pushToFile() {
		File file = new File("src/main/resources/leveldata.json");
		try (FileWriter oFile = new FileWriter(file);) {
			oFile.write(json.toJSONString());
			System.out.println(json.toJSONString());

		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
	public static void main(String[] args) {
		LevelCreation l = new LevelCreation();
		l.write();
	}

}
