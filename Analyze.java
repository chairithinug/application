package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Analyze {
	Button btn1 = new Button();
	Button btn2 = new Button();
	Scene mainmenu;

	public void display(Meal meal) {
		Stage window = new Stage();
		window.setTitle("Analyze My Meal");
		window.initModality(Modality.APPLICATION_MODAL); // make sure only be able to work with this current window
		// buttons
		btn1.setText("Back"); // Set the name for button 1
		// Action of the button
		// btn1.setOnAction(e -> window.setScene(mainmenu));
		btn1.setOnAction(e -> window.close());
		// Button layout
		btn1.setPrefSize(80, 40);
		btn1.setFont(Font.font("arial", 20));

		// left menu
		VBox leftmenu = new VBox(30);

		Label label1 = new Label("Total Calories: " + meal.analyzeMeal().get(0));
		Label label2 = new Label("Total Fat: " + meal.analyzeMeal().get(1) + " g");
		Label label3 = new Label("Total Carbohydrate: " + meal.analyzeMeal().get(2) + " g");
		Label label4 = new Label("Total Fiber: " + meal.analyzeMeal().get(3) + " g");
		Label label5 = new Label("Total Protein: " + meal.analyzeMeal().get(4) + " g");

		label1.setFont(Font.font("arial", 20));
		label2.setFont(Font.font("arial", 20));
		label3.setFont(Font.font("arial", 20));
		label4.setFont(Font.font("arial", 20));
		label5.setFont(Font.font("arial", 20));
		leftmenu.getChildren().addAll(label1, label2, label3, label4, label5);

		// bottom menu
		HBox bottommenu = new HBox();
		bottommenu.getChildren().add(btn1);
		bottommenu.setAlignment(Pos.BOTTOM_CENTER);
		bottommenu.setPadding(new Insets(10, 10, 50, 10)); // set position for bottom pane

		// add to border pane
		BorderPane borderPane = new BorderPane();
		borderPane.setPadding(new Insets(50, 10, 10, 10));
		borderPane.setLeft(leftmenu);
		borderPane.setBottom(bottommenu);

		Scene scene = new Scene(borderPane, 550, 400);
		window.setScene(scene);
		window.show();
	}
}
