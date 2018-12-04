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

public class Create {
	Button btn1 = new Button();
	Scene mainmenu;
	private static Button createButton;
	private static Button backButton;

  public void display() {
    Scene scene = new Scene(new Group(), 600, 300);
    
    Stage window = new Stage();
	window.setTitle("create a food");
	window.initModality(Modality.APPLICATION_MODAL); //make sure only be able to work with this current window
    TextField notification = new TextField();
    TextField notification2 = new TextField();
    TextField notification3 = new TextField();
    TextField notification4 = new TextField();
    TextField notification5 = new TextField();
    TextField notification6 = new TextField();
    TextField notification7 = new TextField();

    final ContextMenu contextMenu = new ContextMenu();

    notification.setContextMenu(contextMenu);
    GridPane grid = new GridPane();
    grid.setVgap(4);
    grid.setHgap(10);
    grid.setPadding(new Insets(5, 5, 5, 5));
    grid.add(new Label("ID: "), 0, 0);
    grid.add(notification, 1, 0);

  //text field to prompt the user to enter the information of the food.  
    grid.add(new Label("Name: "), 0, 1);
    grid.add(notification2, 1, 1);
    
    grid.add(new Label("calories: "), 0, 2);
    grid.add(notification3, 1, 2);
    
    grid.add(new Label("Fat: "), 0, 3);
    grid.add(notification4, 1, 3);
    
    grid.add(new Label("Carbanhydrate: "), 0, 4);
    grid.add(notification5, 1, 4);
    
    grid.add(new Label("Fiber: "), 0, 5);
    grid.add(notification6, 1, 5);
    
    grid.add(new Label("Protein: "), 0, 6);
    grid.add(notification7, 1, 6);
    
  //buttons layout for exit and create 
    backButton = new Button("Back");
    createButton = new Button("Create");
	//createButton.setPrefSize(10, 10);
	//createButton.setAlignment(Pos.CENTER);
	createButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			System.out.println("You pressed Create!");
		}
		
	});
	// add button in the grid
	grid.add(backButton, 7, 8);
	grid.add(createButton, 8, 8);

    Group root = (Group) scene.getRoot();
    root.getChildren().add(grid);
    
    window.setScene(scene);
    window.show();
    
  }
}
