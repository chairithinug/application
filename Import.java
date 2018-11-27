package application;

import java.awt.Font;
//import java.awt.Insets;

import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.text.FontWeight;
import javafx.scene.control.*; 
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
public class Import {
	public static void display() {
	Stage popupwindow=new Stage();
    
	popupwindow.initModality(Modality.APPLICATION_MODAL);
	
	popupwindow.setTitle("Import");
	popupwindow.setWidth(400);
	popupwindow.setHeight(200);
	
	BorderPane bp = new BorderPane();
    Insets insets = new Insets(10, 10, 10, 10);
	//Label imp = new Label("Import");
	//imp.setFont(new Font("Arial",14, 0));
   bp.setPadding(insets);
	Label filepath=new Label ("File Path:");
	//filepath.setMinWidth(50);
	//filepath.setMinHeight(50);
	filepath.setStyle("-fx-font-size: 14px;");
	TextField t1field = new TextField();
	t1field.setPrefWidth(350);
	Button back = new Button("Back");//purpose if we have the exit button??
	Button impbutton=new Button("Import");
	HBox hbox = new HBox();
	VBox vbox = new VBox();
	hbox.setSpacing(10);
	
	hbox.getChildren().addAll(back,impbutton);
	vbox.getChildren().addAll(filepath,t1field);
	hbox.setAlignment(Pos.BOTTOM_RIGHT);
	bp.setLeft(vbox);
	bp.setBottom(hbox);
	
	Scene scene1=new Scene(bp,500,300);
	popupwindow.setScene(scene1);

	      
	//Label label1= new Label("Pop up window now displayed");     
	//Button button1= new Button("Close this pop up window");     
	//button1.setOnAction(e -> popupwindow.close());
	 //VBox layout= new VBox(10);
	 //layout.getChildren().addAll(label1, button1);
	 //layout.setAlignment(Pos.CENTER);
	  //Scene scene1= new Scene(layout, 300, 250);
	  //popupwindow.setScene(scene1);
	      
	popupwindow.show();
	
	}
}
