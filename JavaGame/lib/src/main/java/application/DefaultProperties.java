package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class DefaultProperties {
	/**
	 * Background added to every scene
	 */
	public static final Background DEFAULT_BACKGROUND;
	/**
	 * default window size
	 */
	public static final int SCENE_WIDTH = 1280;
	public static final int SCENE_HEIGHT = 720;

	private DefaultProperties() {
	}

	static {
		BackgroundFill backFill = new BackgroundFill(Color.valueOf("#b9db92"), new CornerRadii(1),
				new Insets(0.0, 0.0, 0.0, 0.0));

		DEFAULT_BACKGROUND = new Background(backFill);
		
		Font amiga = DefaultProperties.loadFont("src/main/resources/fonts/Samson.ttf", 40);
		System.out.println(amiga);
	}

	/**
	 * adds a border to the given region mostly used for fixing layouts
	 * 
	 * @param r region to add border to
	 */
	public static void border(Region r) {
		r.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
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
		try {
			FileInputStream fileIn = new FileInputStream(new File(fileName));
			return Font.loadFont(fileIn, size);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Fail");
		return null;
	}
}
