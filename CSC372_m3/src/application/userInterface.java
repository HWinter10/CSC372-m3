package application;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Random;
import javafx.scene.paint.Color;

public class userInterface extends Application {

    TextArea textArea = new TextArea();
    Random rand = new Random();
    Color randomGreen = Color.hsb(120, rand.nextDouble(), rand.nextDouble());   // (Hue, saturation, brightness)

    public static void main(String[] args) {
        launch(args);   // Launch JavaFX application
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX -- Journal Application");

        // Setting up the menu
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Menu");
        MenuItem item1 = new MenuItem("Insert timestamp");
        MenuItem item2 = new MenuItem("Save to file");
        MenuItem item3 = new MenuItem("Change background");
        MenuItem item4 = new MenuItem("Exit");
        
        // Opening and closing prompts
        Label instructionLabel = new Label(" Today, my day was...");
        Label closingLabel = new Label(" Until next time, Happy Journaling!"); 
        
        // Layout manager and containment
        VBox vBox = new VBox(menuBar, instructionLabel, textArea, closingLabel);
        Scene scene = new Scene(vBox, 500, 300);

        // Setting up menu event handling
        primaryStage.setScene(scene);
        primaryStage.show();
        item1.setOnAction(e -> textArea.appendText(LocalDateTime.now().toString() + "\n"));
        item2.setOnAction(e -> saveToLog());
        item3.setOnAction(e -> {
            randomGreen = Color.hsb(120, rand.nextDouble(), rand.nextDouble());
            vBox.setStyle("-fx-background-color: rgb(" + 
                (int)(randomGreen.getRed() * 255) + "," + 
                (int)(randomGreen.getGreen() * 255) + "," + 
                (int)(randomGreen.getBlue() * 255) + ");"
            );
        });
        item4.setOnAction(e -> System.exit(0));

        menu.getItems().addAll(item1, item2, item3, item4);
        menuBar.getMenus().add(menu);

    }

    // Save to log method
    private void saveToLog() {
        try (PrintWriter out = new PrintWriter("log.txt")) {
            out.println(textArea.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

