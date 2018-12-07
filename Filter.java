package application;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class Filter {
	private String twelvePxFont = "-fx-font-size: 12px;";// helps promote consistency
	private String t = "to";// helps promote consistency

	void display(FoodData list, List<FoodItem> returnList) {
		Stage popupwindow = new Stage();
		popupwindow.initModality(Modality.APPLICATION_MODAL);// make sure only be able to work with this current window
		popupwindow.setTitle("Filter");

		BorderPane bp = new BorderPane();
		Insets insets = new Insets(10, 10, 10, 10);
		bp.setPadding(insets);

		CheckBox cb1 = new CheckBox("By Name");
		cb1.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 18px;");

		CheckBox cb2 = new CheckBox("By Nutrient");
		cb2.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 18px;");

		TextField t2field = new TextField();// text fields for the range of nutrients
		TextField t3field = new TextField();// can't have duplicate children in a HBox
		TextField t4field = new TextField();// separate HBoxes can't share children with other HBoxes
		TextField t5field = new TextField();
		TextField t6field = new TextField();
		TextField t7field = new TextField();
		TextField t8field = new TextField();
		TextField t9field = new TextField();
		TextField t10field = new TextField();
		TextField t11field = new TextField();

		TextField t1field = new TextField();// Text Field for the Name filter
		t1field.setPrefWidth(150);
		Button back = new Button("Back");
		back.setOnAction(e -> popupwindow.close());
		Button clr = new Button("Clear");
		clr.setOnAction(e -> {
			cb1.setSelected(false);
			cb2.setSelected(false);
			t1field.setText("");
			t2field.setText("");
			t3field.setText("");
			t4field.setText("");
			t5field.setText("");
			t6field.setText("");
			t7field.setText("");
			t8field.setText("");
			t9field.setText("");
			t10field.setText("");
			t11field.setText("");
		});
		Button apply = new Button("Apply");
		apply.setOnAction(e -> {
			List<FoodItem> filtered1 = null;
			List<FoodItem> filtered2 = null;
			if (cb1.isSelected()) {
				if (t1field.getText() != null) {
					filtered1 = list.filterByName(t1field.getText());
				}
			}
			if (cb2.isSelected()) {
				try {
					// FIX TO USE ==
					List<String> rule = new ArrayList<String>();
					if (t2field.getText() != null) {
						rule.add("calories >= " + t2field.getText());
					}
					if (t3field.getText() != null) {
						rule.add("calories <= " + t3field.getText());
					}
					if (t4field.getText() != null) {
						rule.add("fat >= " + t4field.getText());
					}
					if (t5field.getText() != null) {
						rule.add("fat <= " + t5field.getText());
					}
					if (t6field.getText() != null) {
						rule.add("carbohydrate >= " + t6field.getText());
					}
					if (t7field.getText() != null) {
						rule.add("carbohydrate <= " + t7field.getText());
					}
					if (t8field.getText() != null) {
						rule.add("fiber >= " + t8field.getText());
					}
					if (t9field.getText() != null) {
						rule.add("fiber <= " + t9field.getText());
					}
					if (t10field.getText() != null) {
						rule.add("protein >= " + t10field.getText());
					}
					if (t11field.getText() != null) {
						rule.add("protein <= " + t11field.getText());
					}
					filtered2 = list.filterByNutrients(rule);
				} catch (Exception f) {

				}
			}
			if (cb1.isSelected() && cb2.isSelected()) {
				filtered1.retainAll(filtered2);
				returnList.addAll(filtered1);
			} else if (cb1.isSelected()) {
				returnList.addAll(filtered1);
			} else if (cb2.isSelected()) {
				returnList.addAll(filtered2);
			}
		});

		Label to1 = new Label(t);
		to1.setStyle(twelvePxFont);
		Label to2 = new Label(t);
		to2.setStyle(twelvePxFont);
		Label to3 = new Label(t);
		to3.setStyle(twelvePxFont);
		Label to4 = new Label(t);
		to4.setStyle(twelvePxFont);
		Label to5 = new Label(t);
		to5.setStyle(twelvePxFont);

		Label cal = new Label("Calories from");
		cal.setStyle(twelvePxFont);
		Label fat = new Label("Fat from");
		fat.setStyle(twelvePxFont);
		Label carb = new Label("Carbohydrates from");
		carb.setStyle(twelvePxFont);
		Label fiber = new Label("Fiber from");
		fiber.setStyle(twelvePxFont);
		Label protein = new Label("Protein from");
		protein.setStyle(twelvePxFont);

		VBox vb1 = new VBox();
		VBox vb2 = new VBox();
		VBox vb3 = new VBox();
		VBox vb4 = new VBox();

		vb1.getChildren().addAll(cal, fat, carb, fiber, protein);
		vb2.getChildren().addAll(t2field, t3field, t4field, t5field, t6field);
		vb3.getChildren().addAll(to1, to2, to3, to4, to5);
		vb4.getChildren().addAll(t7field, t8field, t9field, t10field, t11field);

		vb1.setSpacing(20);
		vb2.setSpacing(10);
		vb3.setSpacing(20);
		vb4.setSpacing(10);

		HBox hb1 = new HBox();
		hb1.getChildren().addAll(vb1, vb2, vb3, vb4);
		hb1.setAlignment(Pos.CENTER);

		HBox hb2 = new HBox();
		hb2.setSpacing(10);
		hb2.getChildren().addAll(back, clr, apply);
		hb2.setAlignment(Pos.BOTTOM_RIGHT);

		HBox hb3 = new HBox();
		hb3.setSpacing(10);
		hb3.getChildren().addAll(cb1, t1field);

		VBox vb5 = new VBox();
		vb5.setSpacing(10);
		vb5.getChildren().addAll(hb3, cb2);
		vb5.setAlignment(Pos.TOP_LEFT);

		bp.setCenter(hb1);
		bp.setBottom(hb2);
		bp.setTop(vb5);
		Scene scene1 = new Scene(bp, 500, 300);
		popupwindow.setScene(scene1);
		popupwindow.show();

	}

}
