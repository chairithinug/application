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
	private String t = "g to";// helps promote consistency
	private String g = "g";// helps promote consistency

	private TextField t1field = new TextField();// Text Field for the Name filter
	private TextField t2field = new TextField();// text fields for the range of nutrients
	private TextField t3field = new TextField();// can't have duplicate children in a HBox
	private TextField t4field = new TextField();// separate HBoxes can't share children with other HBoxes
	private TextField t5field = new TextField();
	private TextField t6field = new TextField();
	private TextField t7field = new TextField();
	private TextField t8field = new TextField();
	private TextField t9field = new TextField();
	private TextField t10field = new TextField();
	private TextField t11field = new TextField();

	private CheckBox cb1 = new CheckBox("By Name");

	private CheckBox cb2 = new CheckBox("By Nutrient");

//, List<Integer> memoryChoice
	void display(FoodData list, List<FoodItem> returnList) {
		Stage popupwindow = new Stage();
		popupwindow.initModality(Modality.APPLICATION_MODAL);// make sure only be able to work with this current window
		popupwindow.setTitle("Filter");

		BorderPane bp = new BorderPane();
		Insets insets = new Insets(10, 10, 10, 10);
		bp.setPadding(insets);

		cb1.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 18px;");
		cb2.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 18px;");

		Button back = new Button("Back");
		back.setOnAction(e -> popupwindow.close());

		t1field.setPrefWidth(150);
		textFieldsInit();

		cb1.setOnAction(e -> t1field.setDisable(!cb1.isSelected()));
		cb2.setOnAction(e -> {
			t1field.setDisable(!cb1.isSelected());
			textFieldsInit();
		});

		Button clr = new Button("Clear");
		clr.setOnAction(e -> {
			cb1.setSelected(false);
			cb2.setSelected(false);
			clearFields();
			textFieldsInit();
		});
		Button apply = new Button("Apply");
		apply.setOnAction(e -> {
			List<FoodItem> filtered1 = null;
			List<FoodItem> filtered2 = null;
			if (cb1.isSelected()) {
				if (t1field.getText() != null && list != null) {
					filtered1 = list.filterByName(t1field.getText());
				}
			}
			boolean isError = false;
			if (cb2.isSelected()) {
				try {
					List<String> rule = new ArrayList<String>();
					if (!t2field.getText().trim().isEmpty()) {
						if (Double.parseDouble(t2field.getText()) >= 0)
							rule.add("calories >= " + Double.parseDouble(t2field.getText()));
						else
							isError = true;
					}
					if (!t7field.getText().trim().isEmpty()) {
						if (Double.parseDouble(t7field.getText()) >= 0)
							rule.add("calories <= " + Double.parseDouble(t7field.getText()));
						else
							isError = true;
					}
					if (!t3field.getText().trim().isEmpty()) {
						if (Double.parseDouble(t3field.getText()) >= 0)
							rule.add("fat >= " + Double.parseDouble(t3field.getText()));
						else
							isError = true;
					}
					if (!t8field.getText().trim().isEmpty()) {
						if (Double.parseDouble(t8field.getText()) >= 0)
							rule.add("fat <= " + Double.parseDouble(t8field.getText()));
						else
							isError = true;
					}
					if (!t4field.getText().trim().isEmpty()) {
						if (Double.parseDouble(t4field.getText()) >= 0)
							rule.add("carbohydrate >= " + Double.parseDouble(t4field.getText()));
						else
							isError = true;
					}
					if (!t9field.getText().trim().isEmpty()) {
						if (Double.parseDouble(t9field.getText()) >= 0)
							rule.add("carbohydrate <= " + Double.parseDouble(t9field.getText()));
						else
							isError = true;
					}
					if (!t5field.getText().trim().isEmpty()) {
						if (Double.parseDouble(t5field.getText()) >= 0)
							rule.add("fiber >= " + Double.parseDouble(t5field.getText()));
						else
							isError = true;
					}
					if (!t10field.getText().trim().isEmpty()) {
						if (Double.parseDouble(t10field.getText()) >= 0)
							rule.add("fiber <= " + Double.parseDouble(t10field.getText()));
						else
							isError = true;
					}
					if (!t6field.getText().trim().isEmpty()) {
						if (Double.parseDouble(t6field.getText()) >= 0)
							rule.add("protein >= " + Double.parseDouble(t6field.getText()));
						else
							isError = true;
					}
					if (!t11field.getText().trim().isEmpty()) {
						if (Double.parseDouble(t11field.getText()) >= 0)
							rule.add("protein <= " + Double.parseDouble(t11field.getText()));
						else
							isError = true;
					}
					filtered2 = list.filterByNutrients(rule);
				} catch (Exception f) {
					isError = true;
				}
			}
			if (returnList != null && list != null) {
				if (cb1.isSelected() && cb2.isSelected() && filtered1 != null && filtered2 != null) {
					returnList.clear();
					filtered1.retainAll(filtered2);
					returnList.addAll(filtered1);
				} else if (cb1.isSelected() && filtered1 != null) {
					returnList.clear();
					returnList.addAll(filtered1);
				} else if (cb2.isSelected() && filtered2 != null) {
					returnList.clear();
					returnList.addAll(filtered2);
				} else {
					
				}
			}
			if (!isError) {
				popupwindow.close();
			}
		});

		Label to1 = new Label("  to");
		to1.setStyle(twelvePxFont);
		Label to2 = new Label(t);
		to2.setStyle(twelvePxFont);
		Label to3 = new Label(t);
		to3.setStyle(twelvePxFont);
		Label to4 = new Label(t);
		to4.setStyle(twelvePxFont);
		Label to5 = new Label(t);
		to5.setStyle(twelvePxFont);

		Label g1 = new Label(g);
		to1.setStyle(twelvePxFont);
		Label g2 = new Label(g);
		to2.setStyle(twelvePxFont);
		Label g3 = new Label(g);
		to3.setStyle(twelvePxFont);
		Label g4 = new Label(g);
		to4.setStyle(twelvePxFont);

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
		VBox vb5 = new VBox();

		Label empty = new Label(" ");
		empty.setStyle(twelvePxFont);

		vb1.getChildren().addAll(cal, fat, carb, fiber, protein);
		vb2.getChildren().addAll(t2field, t3field, t4field, t5field, t6field);
		vb3.getChildren().addAll(to1, to2, to3, to4, to5);
		vb4.getChildren().addAll(t7field, t8field, t9field, t10field, t11field);
		vb5.getChildren().addAll(empty, g1, g2, g3, g4);

		vb1.setSpacing(20);
		vb2.setSpacing(10);
		vb3.setSpacing(20);
		vb4.setSpacing(10);
		vb5.setSpacing(20);

		HBox hb1 = new HBox();
		hb1.getChildren().addAll(vb1, vb2, vb3, vb4, vb5);
		hb1.setAlignment(Pos.CENTER);

		HBox hb2 = new HBox();
		hb2.setSpacing(10);
		hb2.getChildren().addAll(back, clr, apply);
		hb2.setAlignment(Pos.BOTTOM_RIGHT);

		HBox hb3 = new HBox();
		hb3.setSpacing(10);
		hb3.getChildren().addAll(cb1, t1field);

		VBox vb6 = new VBox();
		vb6.setSpacing(10);
		vb6.getChildren().addAll(hb3, cb2);
		vb6.setAlignment(Pos.TOP_LEFT);

		bp.setCenter(hb1);
		bp.setBottom(hb2);
		bp.setTop(vb6);
		Scene scene1 = new Scene(bp, 500, 300);
		popupwindow.setScene(scene1);
		popupwindow.showAndWait();
	}

	private void textFieldsInit() {
		t1field.setDisable(!cb1.isSelected());
		t2field.setDisable(!cb2.isSelected());
		t3field.setDisable(!cb2.isSelected());
		t4field.setDisable(!cb2.isSelected());
		t5field.setDisable(!cb2.isSelected());
		t6field.setDisable(!cb2.isSelected());
		t7field.setDisable(!cb2.isSelected());
		t8field.setDisable(!cb2.isSelected());
		t9field.setDisable(!cb2.isSelected());
		t10field.setDisable(!cb2.isSelected());
		t11field.setDisable(!cb2.isSelected());
	}

	private void clearFields() {
		t1field.clear();
		t2field.clear();
		t3field.clear();
		t4field.clear();
		t5field.clear();
		t6field.clear();
		t7field.clear();
		t8field.clear();
		t9field.clear();
		t10field.clear();
		t11field.clear();
	}
}
