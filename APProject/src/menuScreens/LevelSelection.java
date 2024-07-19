//import javafx.application.Application;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
//import javafx.stage.Stage;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class LevelSelection extends Application {
//    private final List<Label> currentLevels = new ArrayList<>();
//    private final List<Label> levels = new ArrayList<>();
//
//    @Override
//    public void start(Stage primaryStage) {
//        primaryStage.setTitle("Game Levels");
//
//        Label title = new Label("GAME LEVELS");
//        title.setFont(Font.font("Arial", FontWeight.BOLD, 40));
//        title.setTextFill(Color.WHITE);
//
//        VBox titleBox = new VBox(title);
//        titleBox.setAlignment(Pos.TOP_CENTER);
//        titleBox.setPadding(new javafx.geometry.Insets(30, 0, 0, 0));
//
//        VBox levelsBox = new VBox(10);
//        levelsBox.setAlignment(Pos.CENTER);
//
//        for (int i = 0; i < 5; i++) {
//            Label level = createLevelLabel(i + 1);
//            levels.add(level);
//            Label currentLevel = createCurrentLevelLabel();
//            currentLevels.add(currentLevel);
//
//            HBox levelBox = new HBox(10, currentLevel, level);
//            levelBox.setAlignment(Pos.CENTER);
//            levelsBox.getChildren().add(levelBox);
//}
//
//        selectCurrentLevel(1);
//
//        Label back = new Label("BACK");
//        back.setFont(Font.font("Arial", FontWeight.NORMAL, 24));
//        back.setTextFill(Color.WHITE);
//        back.setOnMouseClicked(this::handleBackClick);
//
//        HBox backBox = new HBox(back);
//        backBox.setAlignment(Pos.BOTTOM_LEFT);
//        backBox.setPadding(new javafx.geometry.Insets(0, 0, 30, 30));
//
//        BorderPane mainPane = new BorderPane();
//        mainPane.setTop(titleBox);
//        mainPane.setCenter(levelsBox);
//        mainPane.setBottom(backBox);
//        mainPane.setStyle("-fx-background-color: black");
//
//        Scene scene = new Scene(mainPane, 800, 600);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//}
//
//    private Label createLevelLabel(int levelNumber) {
//        Label level = new Label("LEVEL " + levelNumber);
//        level.setFont(Font.font("Arial", FontWeight.NORMAL, 30));
//        level.setTextFill(Color.WHITE);
//        level.setOnMouseClicked(this::handleLevelClick);
//        return level;
//}
//
//    private Label createCurrentLevelLabel() {
//        Label currentLevel = new Label(">");
//        currentLevel.setFont(Font.font("Arial", FontWeight.BLACK, 30));
//        currentLevel.setTextFill(Color.BLACK);
//        return currentLevel;
//}
//
//    private void handleLevelClick(MouseEvent event) {
//        Label source = (Label) event.getSource();
//        System.out.println(source.getText() + " clicked");
//        selectCurrentLevel(Integer.parseInt(source.getText().replace("LEVEL ", "")));
//}
//
//    private void handleBackClick(MouseEvent event) {
//        System.out.println("Back clicked");
//}
//
//    private void selectCurrentLevel(int level) {
//        currentLevels.forEach(label -> label.setTextFill(Color.BLACK));
//        currentLevels.get(level - 1).setTextFill(Color.WHITE);
//}
//
//    public static void main(String[] args) {
//        launch(args);
//}
//}
