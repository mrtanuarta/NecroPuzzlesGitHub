//import java.io.InputStream;
//import javafx.application.Application;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
//import javafx.stage.Stage;
//
//public class VictoryScreen extends Application {
//
//    private static final String IMAGE_PATH = "/Images/Win.png";
//    private static final String FONT_FAMILY = "Arial";
//    private static final double LOGO_WIDTH = 400;
//    private static final double FONT_SIZE_LABEL = 24;
//
//    @Override
//    public void start(Stage primaryStage) {
//        primaryStage.setTitle("Victory Screen");
//
//        // Create the logo box
//        VBox logoBox = createLogoBox();
//
//        // Create "YOU WIN" label
//        Label youWinLabel = new Label("YOU WIN");
//        youWinLabel.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, 48));
//        youWinLabel.setTextFill(Color.LIMEGREEN);
//        youWinLabel.setAlignment(Pos.CENTER);
//
//        // VBox to hold the "YOU WIN" label
//        VBox youWinBox = new VBox(youWinLabel);
//        youWinBox.setAlignment(Pos.CENTER);
//        youWinBox.setPadding(new Insets(-150, 0, 20, 0));
//
//        // Create "Next Level" button
//        Button nextLevelButton = new Button("Next Level");
//        nextLevelButton.setStyle("-fx-font-size: 24px; -fx-background-color: white; -fx-text-fill: black; -fx-border-color: black; -fx-border-width: 2px;");
//        nextLevelButton.setPrefSize(200, 50);
//        nextLevelButton.setPadding(new Insets(5, 0, 0, 0));
//        nextLevelButton.setOnAction(e -> {
//            System.out.println("Next Level clicked!");
//            loadNextLevel(primaryStage);
//        });
//
//        // Create "Quit" button
//        Button quitButton = new Button("Quit");
//        quitButton.setStyle("-fx-font-size: 24px; -fx-background-color: white; -fx-text-fill: black; -fx-border-color: black; -fx-border-width: 2px;");
//        quitButton.setPrefSize(200, 50);
//        quitButton.setPadding(new Insets(5, 0, 0, 0));
//        quitButton.setOnAction(e -> {
//            System.exit(0);
//        });
//
//        // VBox to hold the buttons vertically
//        VBox buttonBox = new VBox(20, nextLevelButton, quitButton);
//        buttonBox.setAlignment(Pos.CENTER);
//        buttonBox.setStyle("-fx-padding: 20px;");
//
//        // BorderPane to layout the image, label, and buttons
//        BorderPane root = new BorderPane();
//        root.setTop(logoBox);
//        root.setCenter(youWinBox);
//        root.setBottom(buttonBox);
//        root.setStyle("-fx-background-color: black;");
//
//        Scene scene = new Scene(root, 800, 600);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    private VBox createLogoBox() {
//        VBox logoBox = new VBox();
//        logoBox.setAlignment(Pos.TOP_CENTER);
//        logoBox.setPadding(new Insets(10, 0, 0, 0));
//
//        InputStream imageStream = getClass().getResourceAsStream(IMAGE_PATH);
//        if (imageStream == null) {
//            imageStream = getClass().getClassLoader().getResourceAsStream(IMAGE_PATH.substring(1));
//        }
//
//        if (imageStream != null) {
//            Image logoImage = new Image(imageStream);
//            ImageView logoImageView = new ImageView(logoImage);
//            logoImageView.setFitWidth(LOGO_WIDTH);
//            logoImageView.setPreserveRatio(true);
//            logoBox.getChildren().add(logoImageView);
//        }       else {
//            System.err.println("Image file not found: " + IMAGE_PATH);
//            Label imageNotFoundLabel = new Label("Image not found");
//            imageNotFoundLabel.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, FONT_SIZE_LABEL));
//            imageNotFoundLabel.setTextFill(Color.RED);
//            logoBox.getChildren().add(imageNotFoundLabel);
//        }
//
//        return logoBox;
//    }
//
//    // Method to load the next level
//    private void loadNextLevel(Stage primaryStage) {
//        System.out.println("Loading the next level...");
//
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}