/**
 * Filename:   Main.java
 * Project:    Food Query and Meal Analysis
 * Version:    1.0
 * Date:       Nov 29th, 2018
 * Authors:    Anapat Chairithinugull, Brock Thern, Effy Chu, Zening Fang
 *
 * Semester:   Fall 2018
 * Course:     CS400
 * Instructor: Deppeler (deppeler@cs.wisc.edu)
 * Credits:    
 * Bugs:       
 *
 * Due Date:   before 10:00 pm on November 30th
 */
package application;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

/**
 * This class describes the import function
 **/
public class Import {
	/**
	 * This method set the display the import window
	 *
	 * @param the
	 *            lists of food data
	 **/
	public void display(FoodData list, FoodData OriginalList) {
		Stage popupwindow = new Stage();
		popupwindow.initModality(Modality.APPLICATION_MODAL); // make sure only be able to work with this current window
		popupwindow.setTitle("Import"); // title of the window
		popupwindow.setWidth(400);
		popupwindow.setHeight(200); // size of the window

		BorderPane bp = new BorderPane(); // create a new borderpane
		Insets insets = new Insets(10, 10, 10, 10);
		bp.setPadding(insets); // set padding of the window

		Label filepath = new Label("File Path:"); // create filepath label
		filepath.setStyle("-fx-font-size: 14px;"); // set label display

		TextField t1field = new TextField("src/application/foodItems.csv");
		t1field.setPrefWidth(350);

		Label msg = new Label(); // create msg label

		Button back = new Button("Back"); // create a back button
		back.setOnAction(e -> popupwindow.close()); // set the action of the back button
		Button impbutton = new Button("Import"); // create a impbutton button
		impbutton.setOnAction(e -> {
			list.loadFoodItems(t1field.getText().trim());
			OriginalList.loadFoodItems(t1field.getText().trim());
			if (list.getStatusError()) {
				msg.setText("Invalid Path!");
				list.setStatusError(false);
			} else {
				popupwindow.close();
			}
		}); // set the action of impbutton button

		HBox hbox = new HBox(); // create a new hbox
		hbox.setSpacing(10); // set spacing
		hbox.getChildren().addAll(back, impbutton); // add buttons to display
		hbox.setAlignment(Pos.BOTTOM_RIGHT); // set the postion of the hbox

		VBox vbox = new VBox(); // create a new vbox
		vbox.getChildren().addAll(filepath, t1field, msg); // add filepath, t1field and msg to display

		bp.setLeft(vbox);
		bp.setBottom(hbox); // set position

		Scene scene1 = new Scene(bp, 500, 300);
		popupwindow.setScene(scene1);
		popupwindow.showAndWait();
	}
}
