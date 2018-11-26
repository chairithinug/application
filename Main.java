package application;

import javafx.application.Application;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
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

	static Screen screen = Screen.getPrimary();
	static Rectangle2D bounds = screen.getVisualBounds();

	// volatile?
	static final int WINDOW_WIDTH = (int) bounds.getWidth();
	static final int WINDOW_HEIGHT = (int) bounds.getHeight();
	static final int WINDOW_LEFT = (int) (WINDOW_WIDTH / 1.3);
	static final int WINDOW_RIGHT = WINDOW_WIDTH - WINDOW_LEFT;

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
	private static Button searchButton;
	private static Button exitButton;
	private static TextField searchField;

	/*
	 * private static Button[] buttonList = { addButton, removeButton, clearButton,
	 * analyzeButton, importButton, filterButton, createButton, searchButton,
	 * exitButton }; private static String[] buttonNameList = { "Add", "Remove",
	 * "Clear", "Analyze", "ImportButton", "Filter", "Create", "Search", "Exit" };
	 */

	private static Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		String resolution = WINDOW_WIDTH + " x " + WINDOW_HEIGHT;
		System.out.println("Screen resolution is: " + resolution);
		Main.primaryStage = primaryStage;
		BorderPane root = new BorderPane();
		root.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		Label welcomeText = new Label("Welcome to Food Query and Meal Analysis");
		welcomeText.setFont(new Font("Arial", 48));
		welcomeText.setPadding(new Insets(20));

		Label mealText = new Label("My Meal");
		mealText.setFont(new Font("Arial", 40));
		mealText.setPadding(new Insets(20));

		searchField = new TextField("Search Food");
		searchField.setPrefSize(210, 50);
		searchField.setAlignment(Pos.CENTER_LEFT);

		buttonInit();

		ButtonBar bottomLeftBox = new ButtonBar();
		bottomLeftBox.setPrefSize(WINDOW_LEFT, 100);
		bottomLeftBox.setButtonMinWidth(BUTTON_WIDTH);
		bottomLeftBox.getButtons().addAll(addButton, removeButton, clearButton, analyzeButton);
		ButtonBar.setButtonData(addButton, ButtonData.LEFT);
		ButtonBar.setButtonData(removeButton, ButtonData.LEFT);
		ButtonBar.setButtonData(clearButton, ButtonData.LEFT);
		ButtonBar.setButtonData(analyzeButton, ButtonData.RIGHT);
		bottomLeftBox.setPadding(new Insets(20));
		bottomLeftBox.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		HBox topLeftBox = new HBox();
		topLeftBox.setPrefSize(WINDOW_LEFT, 100);
		topLeftBox.setAlignment(Pos.CENTER_LEFT);
		topLeftBox.getChildren().add(welcomeText);
		topLeftBox.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		VBox midLeftBox = new VBox();
		midLeftBox.setPrefSize(WINDOW_LEFT, 900);
		midLeftBox.setAlignment(Pos.TOP_LEFT);
		midLeftBox.getChildren().addAll(mealText);
		midLeftBox.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		VBox leftBox = new VBox();
		leftBox.getChildren().addAll(topLeftBox, midLeftBox, bottomLeftBox);
		// leftBox.setMargin(welcomeText, new Insets(20,20,20,20,));

		VBox rightBox = new VBox();

		GridPane buttonRightBox = new GridPane();
		buttonRightBox.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		buttonRightBox.setPrefSize(WINDOW_RIGHT, 200);
		buttonRightBox.setAlignment(Pos.CENTER);
		buttonRightBox.setHgap(10);
		buttonRightBox.setVgap(10);
		buttonRightBox.add(importButton, 0, 0);
		buttonRightBox.add(filterButton, 1, 0);
		buttonRightBox.add(createButton, 2, 0);
		buttonRightBox.add(searchField, 0, 1, 2, 1);
		buttonRightBox.add(searchButton, 2, 1);

		HBox bottomRightBox = new HBox();
		bottomRightBox.setAlignment(Pos.CENTER_RIGHT);

		VBox scrollableBox = new VBox();
		scrollableBox.setAlignment(Pos.CENTER_LEFT);
		scrollableBox.setPrefSize(WINDOW_RIGHT - 25, 800);
		// Tester
		for (int i = 0; i < 100; i++) {
			Button x = new Button("" + i + "\n");
			x.setAlignment(Pos.CENTER);
			x.setFont(new Font("Arial", 30));
			x.setPadding(new Insets(20));
			x.setPrefSize(WINDOW_RIGHT, 200);
			scrollableBox.getChildren().add(x);
		}
		ScrollPane list = new ScrollPane();
		list.setContent(scrollableBox);
		list.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		list.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);


		bottomRightBox.getChildren().addAll(list);

		rightBox.getChildren().addAll(exitButton, buttonRightBox, bottomRightBox);

		root.setRight(rightBox);
		root.setLeft(leftBox);

		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setMinHeight(720);
		primaryStage.setMinWidth(1280);
		primaryStage.setMaximized(true);
		primaryStage.setFullScreen(true);
		primaryStage.setFullScreenExitHint("Press Esc to exit from fullscreen.");
		primaryStage.setScene(scene);
		primaryStage.setTitle(Title);
		primaryStage.show();
	}

	private static void buttonInit() {

		/*
		 * for(int i = 0; i < buttonList.length; i++) { //buttonList[i] = new
		 * Button(buttonNameList[i]); buttonList[i].setPrefSize(100, 50);
		 * buttonList[i].setAlignment(Pos.CENTER); }
		 */

		addButton = new Button("Add");
		// addButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		addButton.setAlignment(Pos.CENTER);
		addButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("You pressed Add!");
			}
		});

		removeButton = new Button("Remove");
		// removeButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		removeButton.setAlignment(Pos.CENTER);
		removeButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("You pressed Remove!");
			}
		});

		clearButton = new Button("Clear");
		// clearButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		clearButton.setAlignment(Pos.CENTER);
		clearButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("You pressed Clear!");
			}
		});

		analyzeButton = new Button("Analyze");
		// analyzeButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		analyzeButton.setAlignment(Pos.CENTER);
		analyzeButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("You pressed Analyze!");
			}
		});

		importButton = new Button("Import");
		importButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		importButton.setAlignment(Pos.CENTER);
		importButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("You pressed Import!");
			}
		});

		filterButton = new Button("Filter");
		filterButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		filterButton.setAlignment(Pos.CENTER);
		filterButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("You pressed Filter!");
			}
		});

		createButton = new Button("Create");
		createButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		createButton.setAlignment(Pos.CENTER);
		createButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("You pressed Create!");
			}
		});

		searchButton = new Button("Q");
		searchButton.setPrefSize(BUTTON_HEIGHT, BUTTON_HEIGHT);
		searchButton.setAlignment(Pos.CENTER);
		searchButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("You pressed Search!");
				String userSearch = searchField.getText();
				if (userSearch.equals("Search Food") || userSearch.equals("")) {
					System.out.println("Please search for something!");
					return;
				}
				System.out.println(userSearch);
			}
		});

		exitButton = new Button("Exit!");
		// exitButton.setFont();
		exitButton.setPrefSize(WINDOW_RIGHT, BUTTON_WIDTH);
		exitButton.setFont(new Font("Arial", 36));
		exitButton.setAlignment(Pos.CENTER);
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.close();
			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}
