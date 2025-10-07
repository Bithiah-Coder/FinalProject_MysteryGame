// GameEngine.java
// Handles game logic and scene transitions

import java.util.HashMap;

public class GameEngine {
    private Player player;
    private HashMap<String, SceneNode> scenes;
    private SceneNode currentScene;

    public GameEngine(Player player) {
        this.player = player;
        this.scenes = new HashMap<>();
    }

    public void addScene(SceneNode scene) {
        scenes.put(scene.getSceneID(), scene);
    }

    public void start(String startSceneID) {
        currentScene = scenes.get(startSceneID);
        player.setCurrentScene(startSceneID);
    }

    public String getCurrentSceneText() {
        return currentScene.getSceneText();
    }

    public SceneNode getCurrentScene() {
        return currentScene;
    }

    public void makeChoice(int choiceIndex) {
        String nextID = currentScene.getNextSceneID(choiceIndex);
        if (nextID != null) {
            currentScene = scenes.get(nextID);
            player.setCurrentScene(nextID);
        }
    }

    public boolean isGameOver() {
        return currentScene.isEnding();
    }
}
