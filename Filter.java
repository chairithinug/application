package application;

import java.awt.Font;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.FontWeight;
import javafx.stage.*;


public class Filter {
   
    
public static void display()
{
Stage popupwindow=new Stage();
      
popupwindow.initModality(Modality.APPLICATION_MODAL);
popupwindow.setTitle("Filter");

BorderPane bp = new BorderPane();
Insets insets = new Insets(10, 10, 10, 10);
bp.setPadding(insets);

CheckBox cb1 = new CheckBox("By Name");
cb1.setStyle("-fx-font-size: 18px;");
cb1.setStyle("-fx-font-weight: bold;"+"-fx-font-size: 18px;");
CheckBox cb2 = new CheckBox("By Nutrient");
cb2.setStyle("-fx-font-weight: bold;"+"-fx-font-size: 18px;");
TextField t1field = new TextField();//Name needs a text field??
t1field.setPrefWidth(150);
Button back=new Button("Back");//Purpose if have exit button??
Button clr=new Button("Clear");
Button apply=new Button("Apply");

Label cal= new Label("Calories from");
cal.setStyle("-fx-font-size: 12px;");
Label to1 = new Label("to");
to1.setStyle("-fx-font-size: 12px;");
Label to2 = new Label("to");
to2.setStyle("-fx-font-size: 12px;");
Label to3 = new Label("to");
to3.setStyle("-fx-font-size: 12px;");
Label to4 = new Label("to");
to4.setStyle("-fx-font-size: 12px;");
Label to5 = new Label("to");
to5.setStyle("-fx-font-size: 12px;");

Label fat = new Label("Fat from");
fat.setStyle("-fx-font-size: 12px;");
Label carb = new Label("Carbohydrates from");
carb.setStyle("-fx-font-size: 12px;");
Label fiber = new Label("Fiber from");
fiber.setStyle("-fx-font-size: 12px;");
Label protein = new Label("Protein from");
protein.setStyle("-fx-font-size: 12px;");


TextField t2field=new TextField();
TextField t3field=new TextField();//can't have duplicate children in a HBox
TextField t4field=new TextField();//separate hboxes can't share children with other hboxes
TextField t5field=new TextField();
TextField t6field=new TextField();
TextField t7field=new TextField();
TextField t8field=new TextField();
TextField t9field=new TextField();
TextField t10field=new TextField();
TextField t11field=new TextField();

VBox vb1 = new VBox();
VBox vb2 = new VBox();
VBox vb3 = new VBox();
VBox vb4 = new VBox();

vb1.getChildren().addAll(cal,fat,carb,fiber,protein);
vb2.getChildren().addAll(t2field,t3field,t4field,t5field,t6field);
vb3.getChildren().addAll(to1,to2,to3,to4,to5);
vb4.getChildren().addAll(t7field,t8field,t9field,t10field,t11field);

vb1.setSpacing(20);
vb2.setSpacing(10);
vb3.setSpacing(20);
vb4.setSpacing(10);

HBox hb1= new HBox();
hb1.getChildren().addAll(vb1,vb2,vb3,vb4);

HBox hb2 = new HBox();
hb2.setSpacing(10);
hb2.getChildren().addAll(back,clr,apply);
hb2.setAlignment(Pos.BOTTOM_RIGHT);

HBox hb3=new HBox();
hb3.setSpacing(10);
hb3.getChildren().addAll(cb1,t1field);
//hb2.setAlignment(Pos.TOP_LEFT);

VBox vb5= new VBox();
vb5.setSpacing(10);
vb5.getChildren().addAll(hb3,cb2);
vb5.setAlignment(Pos.TOP_LEFT);


//HBox hb1=new HBox();
//HBox hb2=new HBox();
//HBox hb3=new HBox();
//HBox hb4=new HBox();
//HBox hb5=new HBox();
//
//hb1.setSpacing(10);
//hb2.setSpacing(10);
//hb3.setSpacing(10);
//hb4.setSpacing(10);
//hb5.setSpacing(10);
//
//hb1.getChildren().addAll(cal,t2field,to1,t3field);
//hb2.getChildren().addAll(fat,t4field,to2,t5field);
//hb3.getChildren().addAll(carb,t6field,to3,t7field);
//hb4.getChildren().addAll(fiber,t8field,to4,t9field);
//hb5.getChildren().addAll(protein,t10field,to5,t11field);

//VBox vb1= new VBox();
//vb1.getChildren().addAll(hb1,hb2,hb3,hb4,hb5);

hb1.setAlignment(Pos.CENTER);
bp.setCenter(hb1);
bp.setBottom(hb2);
bp.setTop(vb5);
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
