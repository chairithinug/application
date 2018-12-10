package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
//import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
//import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * This class add new food to existiing food list
 **/
public class Create {
	Button btn1 = new Button();
	Scene mainmenu;
	private static Button createButton;
	private static Button backButton;

	/**
	 * This method set the display of the new food
	 **/
	public void display(FoodData list, FoodData OriginalList) {
		Scene scene = new Scene(new Group());

		Stage window = new Stage();
		window.setTitle("Create"); // set title of the window
		window.initModality(Modality.APPLICATION_MODAL); // make sure only be able to work with this current window
		TextField notification = new TextField("53E4F123GG");
		TextField notification2 = new TextField("QQ_PeanutButterChicken");
		TextField notification3 = new TextField("9");
		TextField notification4 = new TextField("7");
		TextField notification5 = new TextField("2");
		TextField notification6 = new TextField("4");
		TextField notification7 = new TextField("15");

		final ContextMenu contextMenu = new ContextMenu();
		// display layout
		notification.setContextMenu(contextMenu);
		GridPane grid = new GridPane();
		grid.setVgap(4);
		grid.setHgap(10);
		grid.setPadding(new Insets(5, 5, 5, 5));
		grid.add(new Label("ID: "), 0, 0);
		grid.add(notification, 1, 0);

		// text field to prompt the user to enter the information of the food.
		grid.add(new Label("Name: "), 0, 1);
		grid.add(notification2, 1, 1);

		grid.add(new Label("calories: "), 0, 2);
		grid.add(notification3, 1, 2);

		grid.add(new Label("Fat: "), 0, 3);
		grid.add(notification4, 1, 3);
		grid.add(new Label("g"), 2, 3);

		grid.add(new Label("Carbohydrate: "), 0, 4);
		grid.add(notification5, 1, 4);
		grid.add(new Label("g"), 2, 4);

		grid.add(new Label("Fiber: "), 0, 5);
		grid.add(notification6, 1, 5);
		grid.add(new Label("g"), 2, 5);

		grid.add(new Label("Protein: "), 0, 6);
		grid.add(notification7, 1, 6);
		grid.add(new Label("g"), 2, 6);

		// buttons layout for exit and create
		backButton = new Button("Back");
		backButton.setOnAction(e -> window.close());
		createButton = new Button("Create");
		// createButton.setPrefSize(10, 10);
		// createButton.setAlignment(Pos.CENTER);
		createButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				boolean isError = false;
				if (!(notification.getText().trim().isEmpty() && notification2.getText().trim().isEmpty()
						&& notification3.getText().trim().isEmpty() && notification4.getText().trim().isEmpty()
						&& notification5.getText().trim().isEmpty() && notification6.getText().trim().isEmpty()
						&& notification7.getText().trim().isEmpty())) {
					try {
						if (notification.getText().trim().isEmpty() || notification2.getText().trim().isEmpty()) {
							isError = true;
						} else {
							FoodItem newFood = new FoodItem(notification.getText().trim(),
									notification2.getText().trim());
							double cal = Double.parseDouble(notification3.getText().trim());
							if (cal >= 0) {
								newFood.addNutrient("calories", cal);
							} else {
								isError = true;
							}
							double fat = Double.parseDouble(notification4.getText().trim());
							if (fat >= 0) {
								newFood.addNutrient("fat", fat);
							} else {
								isError = true;
							}
							double car = Double.parseDouble(notification5.getText().trim());
							if (car >= 0) {
								newFood.addNutrient("carbohydrate", car);
							} else {
								isError = true;
							}
							double fib = Double.parseDouble(notification6.getText().trim());
							if (fib >= 0) {
								newFood.addNutrient("fiber", fib);
							} else {
								isError = true;
							}
							double pro = Double.parseDouble(notification7.getText().trim());
							if (pro >= 0) {
								newFood.addNutrient("protein", pro);
							} else {
								isError = true;
							}
							if (!isError) {
								list.addFoodItem(newFood);
								OriginalList.addFoodItem(newFood);
								window.close();
							}
						}
						if (!isError) {
							window.close();
						}
					} catch (Exception e) {

					}
				} else {

				}
			}

		});
		// add button in the grid
		grid.add(backButton, 1, 8);
		grid.add(createButton, 2, 8);

		Group root = (Group) scene.getRoot();
		root.getChildren().add(grid);

		window.setScene(scene);
		window.showAndWait();

	}
}
