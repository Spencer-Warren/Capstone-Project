package application;

import java.io.InputStream;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;

public class DefaultProperties {
	/**
	 * default window size
	 */
	public static final int SCENE_WIDTH = 1280;
	public static final int SCENE_HEIGHT = 720;

	private DefaultProperties() {
	}

	static {
		Font amiga = DefaultProperties.loadFont("/fonts/Samson.ttf", 10);
	}

	/**
	 * Given a parent and child region, bind their height and width so that the
	 * child fills the parent region
	 * 
	 * @param parent region
	 * @param child  region
	 */
	public static void fillParent(Region parent, Region child) {
		child.prefHeightProperty().bind(parent.heightProperty());
		child.prefWidthProperty().bind(parent.widthProperty());
	}

	public static Font loadFont(String fileName, int size) {
		//URI fileIn = DefaultProperties.class.getClassLoader().getResource(fileName).toURI();
		InputStream fileIn = DefaultProperties.class.getClassLoader().getResourceAsStream(fileName);
		return Font.loadFont(fileIn, size);
	}
}
