package application;
	
import java.awt.Font;
//import java.awt.Insets;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.FontWeight;
import javafx.scene.control.*; 
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.control.Label;
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
//			primaryStage.setTitle("Import");
//			primaryStage.setWidth(400);
//		    primaryStage.setHeight(200);
//		    Insets insets = new Insets(10, 10, 10, 10);
//			//Label imp = new Label("Import");
//			//imp.setFont(new Font("Arial",14, 0));
//		   root.setPadding(insets);
//			Label filepath=new Label ("File Path:");
//			//filepath.setMinWidth(50);
//			//filepath.setMinHeight(50);
//			filepath.setStyle("-fx-font-size: 14px;");
//			TextField t1field = new TextField();
//			t1field.setPrefWidth(350);
//			Button back = new Button("Back");//purpose if we have the exit button??
//			Button impbutton=new Button("Import");
//			HBox hbox = new HBox();
//			VBox vbox = new VBox();
//			hbox.setSpacing(10);
//			
//			hbox.getChildren().addAll(back,impbutton);
//			vbox.getChildren().addAll(filepath,t1field);
//			hbox.setAlignment(Pos.BOTTOM_RIGHT);
//			root.setLeft(vbox);
//			root.setBottom(hbox);
			Import i = new Import();
			i.display();
			Filter f = new Filter();
			f.display();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}
}
