package application;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class DefaultProperties {
	public static final Background defaultBackground;
	public static final int SCENE_WIDTH = 1280;
	public static final int SCENE_HEIGHT = 720;
	
	private DefaultProperties() {}
	
	static {	
		BackgroundFill backFill = new BackgroundFill(
				Color.valueOf("#1b2040"), 
				new CornerRadii(1),
				new Insets(0.0, 0.0, 0.0, 0.0));
		 
		defaultBackground = new Background(backFill);
		
	}
}
