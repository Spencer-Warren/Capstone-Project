package application;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public abstract class Mechanic {

	public abstract VBox create();

	protected abstract void reset();

	protected abstract boolean isCorrect();

	protected HBox makeGradeBar() {

		HBox gradingBar = new HBox();
		Label grade = new Label();
		Button reset = new Button("Reset");
		Button check = new Button("Check");

		gradingBar.setSpacing(10);
		gradingBar.getChildren().addAll(reset, check, grade);

		reset.setOnAction((ActionEvent a) -> reset());
		
		// Will Probably expand later
		check.setOnAction((ActionEvent a) -> 
			grade.setText(isCorrect() ? "Correct!" : "Try Again!")
		);

		return gradingBar;
	}
}
