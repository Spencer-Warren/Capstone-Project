package application;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class AboutScene extends SubScene {

	protected AboutScene(Stage stage, DefaultScene subScene) {
		super(stage, new VBox(), subScene);
		initElements();
	}

	@Override
	protected void initElements() {
		createTitleBar("About");

		Label text = new Label(
				"""
				Block of text
			
				""");

		text.setWrapText(true);
		text.setTextAlignment(TextAlignment.JUSTIFY);
		text.setFont(new Font(15));

		getRoot().getChildren().add(text);

	}

}
