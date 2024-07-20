//import javafx.application.Application;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
//import javafx.stage.Stage;
//
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.List;
//
//public class MenuSelection extends Application {
//    private static final String IMAGE_PATH = "/Images/NecroPuzzle_1.png";
//    private static final String[] MENU_OPTIONS = {"New Game", "Level Selection", "Resume", "Settings", "Quit"};
//    private static final double LOGO_WIDTH = 500;
//    private static final double FONT_SIZE_OPTION = 30;
//    private static final double FONT_SIZE_LABEL = 24;
//    private static final String FONT_FAMILY = "Arial";
//
//    private final List<Label> currentOptions = new ArrayList<>();
//    private final List<Label> options = new ArrayList<>();
//
//    @Override
//    public void start(Stage primaryStage) {
//        primaryStage.setTitle("Game Menu");
//
//        VBox logoBox = createLogoBox();
//        VBox optionsBox = createOptionsBox();
//        optionsBox.getChildren().add(0, logoBox);
//
//        VBox additionalLabelsBox = createAdditionalLabelsBox();
//
//        selectCurrentOption(0);
//
//        BorderPane mainPane = new BorderPane();
//        mainPane.setCenter(optionsBox);
//        mainPane.setBottom(additionalLabelsBox);
//        mainPane.setStyle("-fx-background-color: black");
//
//        Scene scene = new Scene(mainPane, 800, 600);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    private VBox createLogoBox() {
//        VBox logoBox = new VBox();
//        logoBox.setAlignment(Pos.TOP_CENTER);
//        logoBox.setPadding(new javafx.geometry.Insets(-200, 0, -100, 0));
//
//        InputStream imageStream = getClass().getResourceAsStream(IMAGE_PATH);
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
//    private VBox createOptionsBox() {
//        VBox optionsBox = new VBox(10);
//        optionsBox.setAlignment(Pos.CENTER);
//
//        for (String option : MENU_OPTIONS) {
//            Label optionLabel = createOptionLabel(option);
//            options.add(optionLabel);
//            Label currentOption = createCurrentOptionLabel();
//            currentOptions.add(currentOption);
//
//            HBox optionBox = new HBox(10, currentOption, optionLabel);
//            optionBox.setAlignment(Pos.CENTER);
//            optionsBox.getChildren().add(optionBox);
//        }
//
//        return optionsBox;
//    }
//
//    private VBox createAdditionalLabelsBox() {
//        Label apProjectLabel = new Label("AP PROJECT");
//        apProjectLabel.setFont(Font.font(FONT_FAMILY, FontWeight.NORMAL, FONT_SIZE_LABEL));
//        apProjectLabel.setTextFill(Color.WHITE);
//
//        Label estdLabel = new Label("ESTD 2024");
//        estdLabel.setFont(Font.font(FONT_FAMILY, FontWeight.NORMAL, FONT_SIZE_LABEL));
//        estdLabel.setTextFill(Color.WHITE);
//
//        VBox additionalLabelsBox = new VBox(10, apProjectLabel, estdLabel);
//        additionalLabelsBox.setAlignment(Pos.CENTER);
//        additionalLabelsBox.setPadding(new javafx.geometry.Insets(20, 0, 10, 25));
//
//        return additionalLabelsBox;
//    }
//
//    private Label createOptionLabel(String option) {
//        Label optionLabel = new Label(option.toUpperCase());
//        optionLabel.setFont(Font.font(FONT_FAMILY, FontWeight.NORMAL, FONT_SIZE_OPTION));
//        optionLabel.setTextFill(Color.WHITE);
//        optionLabel.setOnMouseClicked(this::handleOptionClick);
//        return optionLabel;
//    }
//
//    private Label createCurrentOptionLabel() {
//        Label currentOption = new Label(">");
//        currentOption.setFont(Font.font(FONT_FAMILY, FontWeight.BLACK, FONT_SIZE_OPTION));
//        currentOption.setTextFill(Color.BLACK);
//        return currentOption;
//    }
//
//    private void handleOptionClick(MouseEvent event) {
//        Label source = (Label) event.getSource();
//        System.out.println(source.getText() + " clicked");
//        selectCurrentOption(options.indexOf(source));
//
//        if (source.getText().equals("QUIT")) {
//            System.exit(0);
//        }
//    }
//
//    private void selectCurrentOption(int index) {
//        currentOptions.forEach(label -> label.setTextFill(Color.BLACK));
//        currentOptions.get(index).setTextFill(Color.WHITE);
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
