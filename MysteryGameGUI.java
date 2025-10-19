// MysteryGameGUI.java
// JavaFX GUI for your text-based Mystery Game
// Integrates with your existing GameEngine, Player, SceneNode classes

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

public class MysteryGameGUI extends Application {

    private GameEngine gameEngine;   // Your existing game logic
    private Player player;           // Tracks clues, guesses, etc.

    private TextArea txtScene;       // Displays scene descriptions
    private VBox choiceBox;          // Contains choice buttons
    private Label lblClues;          // Shows remaining clues
    private Label lblGuesses;        // Shows remaining guesses
    private Button btnStartGame;     // Start button

    @Override
    public void start(Stage primaryStage) {
        gameEngine = new GameEngine();
        player = new Player();

        // === Layout ===
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(12));

        // Scene output
        txtScene = new TextArea();
        txtScene.setEditable(false);
        txtScene.setWrapText(true);
        txtScene.setPrefHeight(300);
        root.setTop(txtScene);

        // Choice buttons
        choiceBox = new VBox(8);
        root.setCenter(choiceBox);

        // Status bar
        HBox statusBar = new HBox(12);
        lblClues = new Label("Clues left: " + player.getCluesLeft());
        lblGuesses = new Label("Guesses left: " + player.getGuessesLeft());
        statusBar.getChildren().addAll(lblClues, lblGuesses);
        root.setBottom(statusBar);

        // Start Game button
        btnStartGame = new Button("Start Game");
        btnStartGame.setOnAction(e -> startGame());
        root.setRight(btnStartGame);

        Scene scene = new Scene(root, 700, 500);
        primaryStage.setTitle("Mystery Game - GUI");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Display initial summary
        txtScene.setText("Something feels off in your world. " +
                "Nothing is where it should be. Time doesn't feel real. " +
                "Conversations loop. Memories are missing. " +
                "Can you uncover the truth in the lie?");
    }

    // === Start Game Event ===
    private void startGame() {
        btnStartGame.setDisable(true); // disable start button
        gameEngine.initializeGame(player); // reset game logic
        updateScene();
    }

    // === Update Scene Display & Choices ===
    private void updateScene() {
        SceneNode current = gameEngine.getCurrentScene();
        txtScene.setText(current.getDescription());
        choiceBox.getChildren().clear();

        List<String> options = current.getOptions();
        for (int i = 0; i < options.size(); i++) {
            int choiceIndex = i;
            Button btn = new Button((i + 1) + ") " + options.get(i));
            btn.setMaxWidth(Double.MAX_VALUE);
            btn.setOnAction(e -> handleChoice(choiceIndex));
            choiceBox.getChildren().add(btn);
        }

        // Update clues and guesses
        lblClues.setText("Clues left: " + player.getCluesLeft());
        lblGuesses.setText("Guesses left: " + player.getGuessesLeft());
    }

    // === Choice Button Event ===
    private void handleChoice(int choiceIndex) {
        boolean gameOver = gameEngine.makeChoice(choiceIndex, player);
        if (gameOver) {
            showEndScreen();
        } else {
            updateScene();
        }
    }

    // === End Screen ===
    private void showEndScreen() {
        txtScene.setText(gameEngine.getEndingText(player));
        choiceBox.getChildren().clear();

        Button btnReplay = new Button("Play Again");
        btnReplay.setOnAction(e -> {
            player.reset();
            gameEngine.initializeGame(player);
            btnStartGame.setDisable(false);
            txtScene.setText("Something feels off in your world. " +
                    "Nothing is where it should be. Time doesn't feel real. " +
                    "Conversations loop. Memories are missing. " +
                    "Can you uncover the truth in the lie?");
            choiceBox.getChildren().clear();
            lblClues.setText("Clues left: " + player.getCluesLeft());
            lblGuesses.setText("Guesses left: " + player.getGuessesLeft());
        });
        choiceBox.getChildren().add(btnReplay);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
