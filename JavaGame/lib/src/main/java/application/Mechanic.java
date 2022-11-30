package application;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public abstract class Mechanic {

	public abstract VBox create();

	public abstract void save(ObjectOutputStream out);

	public abstract void load(ObjectInputStream in);

	protected abstract void reset();

	protected abstract boolean isCorrect();

	protected abstract int getTotal();

	protected abstract int getCurrentScore();

	/*
	 * Create reset and check buttons and functionality
	 * 
	 * @return VBox with created buttons and grade label
	 */
	protected HBox makeGradeBar() {

		HBox gradingBar = new HBox();

		HBox scoreBox = new HBox(3);
		Label currentScore = new Label("0");
		Label slash = new Label("  /  ");
		Label totalScore = new Label(String.valueOf(getTotal()));
		
		scoreBox.setAlignment(Pos.CENTER_LEFT);
		scoreBox.getStyleClass().add("score-box");
		scoreBox.getChildren().addAll(currentScore, slash, totalScore);

		Label grade = new Label();
		Button reset = new Button("Reset");
		Button check = new Button("Check");

		gradingBar.setSpacing(10);
		gradingBar.getChildren().addAll(reset, check, scoreBox, grade);

		reset.setOnAction((ActionEvent a) -> reset());

		// Will Probably expand later
		check.setOnAction((ActionEvent a) -> {
			currentScore.setText(String.valueOf(getCurrentScore()));
			grade.setText(isCorrect() ? "Correct!" : "Try Again!");
		});

		return gradingBar;
	}
}
