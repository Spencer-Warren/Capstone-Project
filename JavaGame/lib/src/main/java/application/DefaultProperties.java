package application;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class DefaultProperties {
	public static final Background DEFAULT_BACKGROUND;
	public static final int SCENE_WIDTH = 1280;
	public static final int SCENE_HEIGHT = 720;

	private DefaultProperties() {
	}

	static {
		BackgroundFill backFill = new BackgroundFill(Color.WHITESMOKE, new CornerRadii(1),
				new Insets(0.0, 0.0, 0.0, 0.0));

		DEFAULT_BACKGROUND = new Background(backFill);
	}
}
