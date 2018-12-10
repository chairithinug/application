/**
 * Filename:   Main.java
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

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Main extends Application {

	private static ObservableList<FoodItem> foodObservableList = FXCollections.observableArrayList();
	private static ListView<FoodItem> foodListView = new ListView<FoodItem>(foodObservableList);
	private static FoodData loadedList = new FoodData(); // Original list of all food
	private static List<FoodItem> filteredList;

	private static ObservableList<FoodItem> mealObservableList = FXCollections.observableArrayList();
	private static ListView<FoodItem> mealListView = new ListView<FoodItem>(mealObservableList);
	private static Meal meal = new Meal();

	private static Border border = new Border(
			new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));

	static Screen screen = Screen.getPrimary();
	static Rectangle2D bounds = screen.getVisualBounds();

	static final int WINDOW_WIDTH = (int) bounds.getWidth(); // User Screen Width
	static final int WINDOW_HEIGHT = (int) bounds.getHeight(); // User Screen Height
	static final int WINDOW_LEFT = (int) (WINDOW_WIDTH / 1.5);
	static final int WINDOW_RIGHT = WINDOW_WIDTH - WINDOW_LEFT;
	static final int WINDOW_TOP = (int) (WINDOW_HEIGHT / 10);
	static final int WINDOW_BOTTOM = (int) (WINDOW_HEIGHT / 10);

	// Apply to most buttons
	private static int BUTTON_WIDTH = (int) (WINDOW_WIDTH / 19.2);
	private static int BUTTON_HEIGHT = (int) (WINDOW_HEIGHT / 21.6);

	private static final String Title = "Food Query and Meal Analysis";

	private static Button addButton;
	private static Button removeButton;
	private static Button clearButton;
	private static Button analyzeButton;
	private static Button importButton;
	private static Button filterButton;
	private static Button createButton;
	private static Button saveButton;
	private static Button exitButton;

	private static Label welcomeText;
	private static Label mealText;
	private static Label nameText;
	private static Label amountText;

	private static Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		Main.primaryStage = primaryStage;
		BorderPane root = new BorderPane();
		labelInit();
		buttonInit();

		ButtonBar bottomLeftBox = new ButtonBar();
		bottomLeftBox.setPrefSize(WINDOW_LEFT, WINDOW_BOTTOM);
		bottomLeftBox.setButtonMinWidth(BUTTON_WIDTH);
		bottomLeftBox.getButtons().addAll(addButton, removeButton, clearButton, analyzeButton);
		ButtonBar.setButtonData(addButton, ButtonData.LEFT);
		ButtonBar.setButtonData(removeButton, ButtonData.LEFT);
		ButtonBar.setButtonData(clearButton, ButtonData.LEFT);
		ButtonBar.setButtonData(analyzeButton, ButtonData.RIGHT);
		bottomLeftBox.setPadding(new Insets(25));
		bottomLeftBox.setBorder(border);

		HBox topLeftBox = new HBox();
		topLeftBox.setPrefSize(WINDOW_LEFT, WINDOW_TOP);
		topLeftBox.setAlignment(Pos.CENTER_LEFT);
		topLeftBox.getChildren().add(welcomeText);
		topLeftBox.setBorder(border);

		GridPane mealGrid = new GridPane();
		mealGrid.setPrefSize(WINDOW_LEFT, WINDOW_HEIGHT - WINDOW_TOP - WINDOW_BOTTOM);
		mealGrid.setAlignment(Pos.TOP_LEFT);
		mealGrid.setHgap(20);
		mealGrid.add(mealText, 0, 0);
		nameText = new Label("Name");
		nameText.setPadding(new Insets(20));
		nameText.setFont(new Font("Arial", 20));
		mealGrid.add(nameText, 0, 1);
		amountText = new Label("Amount");
		amountText.setFont(new Font("Arial", 20));
		mealGrid.add(amountText, 1, 1);

		mealGrid.setBorder(border);

		mealListView.setPrefSize(WINDOW_LEFT, WINDOW_HEIGHT - WINDOW_TOP - WINDOW_BOTTOM);
		mealListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		foodListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		VBox leftBox = new VBox();
		leftBox.getChildren().addAll(topLeftBox, mealListView, bottomLeftBox);

		VBox rightBox = new VBox();

		GridPane buttonRightBox = new GridPane();
		buttonRightBox.setBorder(border);
		buttonRightBox.setPrefSize(WINDOW_RIGHT, WINDOW_TOP);
		buttonRightBox.setAlignment(Pos.CENTER);
		buttonRightBox.setHgap(10);
		buttonRightBox.setVgap(10);
		buttonRightBox.add(importButton, 0, 0);
		buttonRightBox.add(filterButton, 1, 0);
		buttonRightBox.add(createButton, 2, 0);
		buttonRightBox.add(saveButton, 3, 0);

		foodListView.setPrefSize(WINDOW_RIGHT, WINDOW_HEIGHT - 2 * WINDOW_TOP);

		rightBox.getChildren().addAll(exitButton, buttonRightBox, foodListView);

		root.setRight(rightBox);
		root.setLeft(leftBox);

		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		// TODO: put all style to CSS
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setMinHeight(720);
		primaryStage.setMinWidth(1280);
		primaryStage.setMaximized(true);
		// primaryStage.setFullScreen(true);
		primaryStage.setFullScreenExitHint("Press Esc to exit from fullscreen.");
		primaryStage.setScene(scene);
		primaryStage.setTitle(Title);
		primaryStage.show();
	}

	// Update foodList
	private static void updateFoodListView(List<FoodItem> list) {
		foodObservableList.removeAll(foodObservableList);
		Iterator<FoodItem> listIterator = list.iterator();
		while (listIterator.hasNext()) {
			foodObservableList.add(listIterator.next());
		}
	}

	// Initialize all buttons
	private static void buttonInit() {
		addButton = new Button("Add");
		addButton.setFont(new Font("Arial", 20));
		addButton.setAlignment(Pos.CENTER);
		addButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ObservableList<FoodItem> selected = foodListView.getSelectionModel().getSelectedItems();
				if (selected != null) {
					for (FoodItem s : selected) {
						mealObservableList.add(s);
						meal.addToMeal(s);
					}
				}
				mealObservableList.sort(new Comparator<FoodItem>() {
					@Override
					public int compare(FoodItem f1, FoodItem f2) {
						return f1.getName().toLowerCase().compareTo(f2.getName().toLowerCase());
					}
				});
			}
		});

		removeButton = new Button("Remove");
		removeButton.setFont(new Font("Arial", 20));
		removeButton.setAlignment(Pos.CENTER);
		removeButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ObservableList<FoodItem> selected = mealListView.getSelectionModel().getSelectedItems();
				if (selected != null) {
					for (FoodItem s : selected) {
						mealObservableList.remove(s);
						meal.removeFromMeal(s);
					}
				}
				mealObservableList.sort(new Comparator<FoodItem>() {
					@Override
					public int compare(FoodItem f1, FoodItem f2) {
						return f1.getName().toLowerCase().compareTo(f2.getName().toLowerCase());
					}
				});
			}
		});

		clearButton = new Button("Clear");
		clearButton.setFont(new Font("Arial", 20));
		clearButton.setAlignment(Pos.CENTER);
		clearButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mealObservableList.removeAll(mealObservableList);
				meal.clearMeal();
			}
		});

		analyzeButton = new Button("Analyze");
		analyzeButton.setFont(new Font("Arial", 20));
		analyzeButton.setAlignment(Pos.CENTER);
		analyzeButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Analyze analyzePopup = new Analyze();
				analyzePopup.display(meal);
			}
		});

		importButton = new Button("Import");
		importButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		importButton.setFont(new Font("Arial", 20));
		importButton.setAlignment(Pos.CENTER);
		importButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Import importPopup = new Import();
				importPopup.display(loadedList);
				loadedList.getAllFoodItems().sort(new Comparator<FoodItem>() {
					@Override
					public int compare(FoodItem f1, FoodItem f2) {
						return f1.getName().toLowerCase().compareTo(f2.getName().toLowerCase());
					}
				});
				updateFoodListView(loadedList.getAllFoodItems());
			}
		});

		filterButton = new Button("Filter");
		filterButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		filterButton.setFont(new Font("Arial", 20));
		filterButton.setAlignment(Pos.CENTER);
		filterButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Filter filterPopup = new Filter();
				filteredList = loadedList.getAllFoodItems();
				filterPopup.display(loadedList, filteredList);
				updateFoodListView(filteredList);
			}
		});

		createButton = new Button("Create");
		createButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		createButton.setFont(new Font("Arial", 20));
		createButton.setAlignment(Pos.CENTER);
		createButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Create createPopup = new Create();
				createPopup.display(loadedList);
				loadedList.getAllFoodItems().sort(new Comparator<FoodItem>() {
					@Override
					public int compare(FoodItem f1, FoodItem f2) {
						return f1.getName().toLowerCase().compareTo(f2.getName().toLowerCase());
					}
				});
				updateFoodListView(loadedList.getAllFoodItems());
			}
		});

		saveButton = new Button("Save");
		saveButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		saveButton.setFont(new Font("Arial", 20));
		saveButton.setAlignment(Pos.CENTER);
		saveButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Save savePopup = new Save();
				savePopup.display(loadedList);
			}
		});

		exitButton = new Button("Exit!");
		exitButton.setPrefSize(WINDOW_RIGHT, WINDOW_TOP);
		exitButton.setFont(new Font("Arial", 36));
		exitButton.setAlignment(Pos.CENTER);
		exitButton.setOnAction(e -> primaryStage.close());
	}

	private static void labelInit() {
		welcomeText = new Label("Welcome to Food Query and Meal Analysis");
		welcomeText.setFont(new Font("Arial", 48));
		welcomeText.setPadding(new Insets(20));

		mealText = new Label("My Meal");
		mealText.setFont(new Font("Arial", 40));
		mealText.setPadding(new Insets(20));
	}

	public static void main(String[] args) {
		launch(args);
	}
}
