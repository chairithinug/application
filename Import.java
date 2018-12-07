package application;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

public class Import {
	public void display(FoodData list) {
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
		
		Label msg = new Label();

		Button back = new Button("Back");
		back.setOnAction(e -> popupwindow.close());
		Button impbutton = new Button("Import");
		impbutton.setOnAction(e -> {
				list.loadFoodItems(t1field.getText());
				if (list.getStatusError()) {
					msg.setText("Invalid Path!");
					list.setStatusError(false);
				} else {
					popupwindow.close();
				}
		});

		HBox hbox = new HBox();
		hbox.setSpacing(10);
		hbox.getChildren().addAll(back, impbutton);
		hbox.setAlignment(Pos.BOTTOM_RIGHT);

		VBox vbox = new VBox();
		vbox.getChildren().addAll(filepath, t1field,msg);

		bp.setLeft(vbox);
		bp.setBottom(hbox);

		Scene scene1 = new Scene(bp, 500, 300);
		popupwindow.setScene(scene1);
		popupwindow.show();
	}
}
