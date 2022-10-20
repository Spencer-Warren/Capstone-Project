package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public abstract class SubScene extends DefaultScene {
	protected DefaultScene subScene;
	private Button back;

	protected SubScene(Stage stage, VBox root, DefaultScene subScene) {
		super(stage, root);
		this.subScene = subScene;
		this.back = new Button("Back...");
//		initElements(); // Initialize elements, this is to be defined in subclasses
	}

	@Override
	protected abstract void initElements();
	
	protected Button getBack() {
		return back;
	}
	
	/**
	 * Given string create the header
	 * which includes the back button and 
	 * a title created with the 
	 * given string
	 * 
	 * @param title string title to add
	 */
	protected void createTitleBar(String title) { 
		Label titleLabel = new Label(title);
		titleLabel.getStyleClass().add("title-label");
		titleLabel.setAlignment(Pos.CENTER);
		titleLabel.setMaxWidth(Double.MAX_VALUE);
		titleLabel.setPadding(new Insets(20,0,0,0));
//		DefaultProperties.border(titleLabel);

		AnchorPane backButton = new AnchorPane(getBack());
		AnchorPane.setLeftAnchor(getBack(), 0.0);

		HBox titleBox = new HBox(backButton,  titleLabel);
		
		HBox.setHgrow(titleLabel, Priority.ALWAYS);
		
		getRoot().getChildren().addAll(titleBox);
	}

	public void bindButtons() {
		this.bindButtonToScene(back, subScene);
	}
	
	
}
