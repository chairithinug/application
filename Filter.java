/**
 * Filename:   Filter.java
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

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

/**
 * used to filter the food list
 * 
 *
 */
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

	private List<TextField> textList = new ArrayList<TextField>();
	private CheckBox cb1 = new CheckBox("By Name");

	private CheckBox cb2 = new CheckBox("By Nutrient");

	/**
	 * create filter rules based on user input
	 * 
	 * @param list
	 *            food list that can be changed
	 * @param returnList
	 *            filtered list
	 * @param OriginalList
	 *            starting list that doesn't change
	 */
	void display(FoodData list, List<FoodItem> returnList, FoodData OriginalList) {
		Stage popupwindow = new Stage();
		popupwindow.initModality(Modality.APPLICATION_MODAL);// can only access this current window
		popupwindow.setTitle("Filter");

		Tooltip filterHelp = new Tooltip("Usage Example:\nFor fat<=30 enter(Fat from: ______ to 30)\nFor protein>=10"
				+ " enter(Protein from: 10 to ______)\nFor carbohydrates==20 enter(Carbohydrates from: 20 to 20)"
				+ "\nNO NEGATIVE\nONLY NUMERICAL");

		t2field.setTooltip(filterHelp);// text field tips
		t3field.setTooltip(filterHelp);
		t4field.setTooltip(filterHelp);
		t5field.setTooltip(filterHelp);
		t6field.setTooltip(filterHelp);
		t7field.setTooltip(filterHelp);
		t8field.setTooltip(filterHelp);
		t9field.setTooltip(filterHelp);
		t10field.setTooltip(filterHelp);
		t11field.setTooltip(filterHelp);

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
					list.getAllFoodItems().removeAll(list.getAllFoodItems());
					list.getAllFoodItems().addAll(OriginalList.getAllFoodItems());
					filtered1 = list.filterByName(t1field.getText());
				}
			}
			boolean isError = false;
			if (cb2.isSelected()) {
				try {
					list.getAllFoodItems().removeAll(list.getAllFoodItems());
					list.getAllFoodItems().addAll(OriginalList.getAllFoodItems());
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
			if (returnList != null && list != null && OriginalList != null) {
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
					returnList.clear();
					returnList.addAll(OriginalList.getAllFoodItems());
				}
			}
			setTextFieldsList();
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

	/**
	 * user can't access text fields if no check box is selected
	 */
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

	/**
	 * clear text fields
	 */
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

	/**
	 * add text fields to a list to be used for user feedback about filters when
	 * hover over filter button
	 */
	private void setTextFieldsList() {

		textList.add(t1field);
		textList.add(t2field);
		textList.add(t7field);
		textList.add(t3field);
		textList.add(t8field);
		textList.add(t4field);
		textList.add(t9field);
		textList.add(t5field);
		textList.add(t10field);
		textList.add(t6field);
		textList.add(t11field);

	}

	/**
	 * creates the user feedback about filters when hover over filter button
	 * 
	 * @return user feedback about filter
	 */
	public String getTextFieldsTooltipString() {
		String str = "CURRENT FILTERS\n";
		try {
			str = str + "\tName Filter: ";
			ArrayList<TextField> toolTipList = (ArrayList<TextField>) textList;
			if (toolTipList.get(0).getText() != null) {
				str = str + toolTipList.get(0).getText();
			}
			str = str + "\n\tNutrient Filter:\n";
			if (!toolTipList.get(1).getText().trim().isEmpty() && !toolTipList.get(2).getText().trim().isEmpty()) {
				if (Double.parseDouble(toolTipList.get(1).getText()) >= 0
						&& Double.parseDouble(toolTipList.get(2).getText()) >= 0
						&& toolTipList.get(1).getText().equals(toolTipList.get(2).getText())) {
					str = str + "\t\t-calories == " + toolTipList.get(1).getText() + "\n";
				} else if (Double.parseDouble(toolTipList.get(1).getText()) >= 0
						&& Double.parseDouble(toolTipList.get(2).getText()) >= 0) {
					str = str + "\t\t-calories from " + toolTipList.get(1).getText() + " to "
							+ toolTipList.get(2).getText() + "\n";
				}

			} else if (!toolTipList.get(1).getText().trim().isEmpty()
					&& Double.parseDouble(toolTipList.get(1).getText()) >= 0) {// one text field in the pair is
																				// null
				str = str + "\t\t-calories >= " + toolTipList.get(1).getText() + "\n";
			} else if (!toolTipList.get(2).getText().trim().isEmpty()
					&& Double.parseDouble(toolTipList.get(2).getText()) >= 0) {
				str = str + "\t\t-calories <= " + toolTipList.get(2).getText() + "\n";
			}
			if (!toolTipList.get(3).getText().trim().isEmpty() && !toolTipList.get(4).getText().trim().isEmpty()) {
				if (Double.parseDouble(toolTipList.get(3).getText()) >= 0
						&& Double.parseDouble(toolTipList.get(4).getText()) >= 0
						&& toolTipList.get(3).getText().equals(toolTipList.get(4).getText())) {
					str = str + "\t\t-fat == " + toolTipList.get(3).getText() + "\n";
				} else if (Double.parseDouble(toolTipList.get(3).getText()) >= 0
						&& Double.parseDouble(toolTipList.get(4).getText()) >= 0) {
					str = str + "\t\t-fat from " + toolTipList.get(3).getText() + " to " + toolTipList.get(4).getText()
							+ "\n";
				}

			} else if (!toolTipList.get(3).getText().trim().isEmpty()
					&& Double.parseDouble(toolTipList.get(3).getText()) >= 0) {// one text field in the pair is
																				// null
				str = str + "\t\t-fat >= " + toolTipList.get(3).getText() + "\n";
			} else if (!toolTipList.get(4).getText().trim().isEmpty()
					&& Double.parseDouble(toolTipList.get(4).getText()) >= 0) {
				str = str + "\t\t-fat <= " + toolTipList.get(4).getText() + "\n";
			}
			if (!toolTipList.get(5).getText().trim().isEmpty() && !toolTipList.get(6).getText().trim().isEmpty()) {
				if (Double.parseDouble(toolTipList.get(5).getText()) >= 0
						&& Double.parseDouble(toolTipList.get(6).getText()) >= 0
						&& toolTipList.get(5).getText().equals(toolTipList.get(6).getText())) {
					str = str + "\t\t-carbohydrates == " + toolTipList.get(5).getText() + "\n";
				} else if (Double.parseDouble(toolTipList.get(5).getText()) >= 0
						&& Double.parseDouble(toolTipList.get(6).getText()) >= 0) {
					str = str + "\t\t-carbohydrates from " + toolTipList.get(5).getText() + " to "
							+ toolTipList.get(6).getText() + "\n";
				}

			} else if (!toolTipList.get(5).getText().trim().isEmpty()
					&& Double.parseDouble(toolTipList.get(5).getText()) >= 0) {// one text field in the pair is
																				// null
				str = str + "\t\t-carbohydrates >= " + toolTipList.get(5).getText() + "\n";
			} else if (!toolTipList.get(6).getText().trim().isEmpty()
					&& Double.parseDouble(toolTipList.get(6).getText()) >= 0) {
				str = str + "\t\t-carbohydrates <= " + toolTipList.get(6).getText() + "\n";
			}
			if (!toolTipList.get(7).getText().trim().isEmpty() && !toolTipList.get(8).getText().trim().isEmpty()) {
				if (Double.parseDouble(toolTipList.get(7).getText()) >= 0
						&& Double.parseDouble(toolTipList.get(8).getText()) >= 0
						&& toolTipList.get(7).getText().equals(toolTipList.get(8).getText())) {
					str = str + "\t\t-fiber == " + toolTipList.get(7).getText() + "\n";
				} else if (Double.parseDouble(toolTipList.get(7).getText()) >= 0
						&& Double.parseDouble(toolTipList.get(8).getText()) >= 0) {
					str = str + "\t\t-fiber from " + toolTipList.get(7).getText() + " to "
							+ toolTipList.get(8).getText() + "\n";
				}

			} else if (!toolTipList.get(7).getText().trim().isEmpty()
					&& Double.parseDouble(toolTipList.get(7).getText()) >= 0) {// one text field in the pair is
																				// null
				str = str + "\t\t-fiber >= " + toolTipList.get(7).getText() + "\n";
			} else if (!toolTipList.get(8).getText().trim().isEmpty()
					&& Double.parseDouble(toolTipList.get(8).getText()) >= 0) {
				str = str + "\t\t-fiber <= " + toolTipList.get(8).getText() + "\n";
			}
			if (!toolTipList.get(9).getText().trim().isEmpty() && !toolTipList.get(10).getText().trim().isEmpty()) {
				if (Double.parseDouble(toolTipList.get(9).getText()) >= 0
						&& Double.parseDouble(toolTipList.get(10).getText()) >= 0
						&& toolTipList.get(9).getText().equals(toolTipList.get(10).getText())) {
					str = str + "\t\t-fiber == " + toolTipList.get(9).getText() + "\n";
				} else if (Double.parseDouble(toolTipList.get(9).getText()) >= 0
						&& Double.parseDouble(toolTipList.get(10).getText()) >= 0) {
					str = str + "\t\t-fiber from " + toolTipList.get(9).getText() + " to "
							+ toolTipList.get(10).getText() + "\n";
				}

			} else if (!toolTipList.get(9).getText().trim().isEmpty()
					&& Double.parseDouble(toolTipList.get(9).getText()) >= 0) {// one text field in the pair is
																				// null
				str = str + "\t\t-fiber >= " + toolTipList.get(9).getText() + "\n";
			} else if (!toolTipList.get(10).getText().trim().isEmpty()
					&& Double.parseDouble(toolTipList.get(10).getText()) >= 0) {
				str = str + "\t\t-fiber <= " + toolTipList.get(10).getText() + "\n";
			}
		} catch (Exception e) {
		}

		return str;
	}
}
