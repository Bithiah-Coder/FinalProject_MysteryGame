// MysteryGameApp.java
// JavaFX UI for the mystery game

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MysteryGameApp extends Application {
    private GameEngine gameEngine;
    private TextArea storyArea;
    private Button[] choiceButtons;

    @Override
    public void start(Stage primaryStage) {
        Player player = new Player("Detective");
        gameEngine = new GameEngine(player);

        // Simple demo scenes
        SceneNode startScene = new SceneNode("start", "You wake up in a strange room. What do you do?", false);
        startScene.addChoice("Look around", "scene1");
        startScene.addChoice("Call for help", "scene2");

        SceneNode scene1 = new SceneNode("scene1", "You find a key under the bed. Maybe it opens something important.", true);
        SceneNode scene2 = new SceneNode("scene2", "No one answers. The silence is unsettling.", true);

        gameEngine.addScene(startScene);
        gameEngine.addScene(scene1);
        gameEngine.addScene(scene2);
        gameEngine.start("start");

        storyArea = new TextArea(gameEngine.getCurrentSceneText());
        storyArea.setWrapText(true);
        storyArea.setEditable(false);

        choiceButtons = new Button[2];
        VBox layout = new VBox(10);
        layout.getChildren().add(storyArea);

        for (int i = 0; i < 2; i++) {
            Button btn = new Button();
            int index = i;
            btn.setOnAction(e -> updateScene(index));
            choiceButtons[i] = btn;
            layout.getChildren().add(btn);
        }

        updateChoices();
        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setTitle("Text Mystery Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateScene(int index) {
        gameEngine.makeChoice(index);
        storyArea.setText(gameEngine.getCurrentSceneText());
        if (gameEngine.isGameOver()) {
            storyArea.appendText("\n\nThe End.");
            for (Button b : choiceButtons) b.setDisable(true);
        } else {
            updateChoices();
        }
    }

    private void updateChoices() {
        SceneNode scene = gameEngine.getCurrentScene();
        for (int i = 0; i < choiceButtons.length; i++) {
            if (i < scene.getChoices().size()) {
                choiceButtons[i].setText(scene.getChoices().get(i));
                choiceButtons[i].setDisable(false);
            } else {
                choiceButtons[i].setText("");
                choiceButtons[i].setDisable(true);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
