package application;

import java.awt.Font;
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
	void display() {
		Stage popupwindow = new Stage();
		popupwindow.initModality(Modality.APPLICATION_MODAL); //make sure only be able to work with this current window
		popupwindow.setTitle("Import");
		popupwindow.setWidth(400);
		popupwindow.setHeight(200);

		BorderPane bp = new BorderPane();
		Insets insets = new Insets(10, 10, 10, 10);
		bp.setPadding(insets);

		Label filepath = new Label("File Path:");
		filepath.setStyle("-fx-font-size: 14px;");

		TextField t1field = new TextField();
		t1field.setPrefWidth(350);

		Button back = new Button("Back");
		back.setOnAction(e -> popupwindow.close());
		Button impbutton = new Button("Import");

		HBox hbox = new HBox();
		hbox.setSpacing(10);
		hbox.getChildren().addAll(back, impbutton);
		hbox.setAlignment(Pos.BOTTOM_RIGHT);

		VBox vbox = new VBox();
		vbox.getChildren().addAll(filepath, t1field);

		bp.setLeft(vbox);
		bp.setBottom(hbox);

		Scene scene1 = new Scene(bp, 500, 300);
		popupwindow.setScene(scene1);
		popupwindow.show();

	}
}
