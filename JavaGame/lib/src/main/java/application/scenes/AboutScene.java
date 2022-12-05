package application.scenes;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class AboutScene extends SubScene {

	public AboutScene(Stage stage, DefaultScene subScene) {
		super(stage, new VBox(), subScene);
		initElements();
	}

	@Override
	protected void initElements() {
		createTitleBar("About");
		VBox body = new VBox();
		body.setSpacing(15);
		body.setPadding(new Insets(20));
		body.getStyleClass().add("level-body");
		
		Label text = new Label(
				"""
				This desktop app is a coding tutorial aimed towards a less technical audience.
				
				It includes 8 topics each with its own mini activity. And saves where you were in those activities.
				It has all the text, information and activity answers stored in a JSON file.
				
				This application was built using Java and utilizes the JavaFX Libraries for its UI.
				
				Created by:
				        Spencer Warren
				        
				Version 1.0
				12/3/2022
			
				""");
		
		text.setWrapText(true);
		text.setTextAlignment(TextAlignment.JUSTIFY);
		text.setFont(new Font(15));
		
		body.getChildren().addAll(text);
		
		getRoot().getChildren().add(body);

	}

}
