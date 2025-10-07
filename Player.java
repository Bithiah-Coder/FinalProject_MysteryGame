// Player.java
// Represents the player in the mystery game

public class Player {
    private String playerName;
    private int cluesFound;
    private int guessesRemaining;
    private String currentScene;

    public Player(String playerName) {
        this.playerName = playerName;
        this.cluesFound = 0;
        this.guessesRemaining = 2;
        this.currentScene = "start";
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getCluesFound() {
        return cluesFound;
    }

    public void findClue() {
        cluesFound++;
    }

    public int getGuessesRemaining() {
        return guessesRemaining;
    }

    public void useGuess() {
        if (guessesRemaining > 0) {
            guessesRemaining--;
        }
    }

    public String getCurrentScene() {
        return currentScene;
    }

    public void setCurrentScene(String currentScene) {
        this.currentScene = currentScene;
    }

    @Override
    public String toString() {
        return "Player: " + playerName + ", Clues: " + cluesFound + ", Guesses Left: " + guessesRemaining;
    }
}
