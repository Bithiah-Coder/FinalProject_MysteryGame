// SceneNode.java
// Represents a single scene or part of the story

import java.util.ArrayList;

public class SceneNode {
    private String sceneID;
    private String sceneText;
    private ArrayList<String> choices;
    private ArrayList<String> nextSceneIDs;
    private boolean isEnding;

    public SceneNode(String sceneID, String sceneText, boolean isEnding) {
        this.sceneID = sceneID;
        this.sceneText = sceneText;
        this.choices = new ArrayList<>();
        this.nextSceneIDs = new ArrayList<>();
        this.isEnding = isEnding;
    }

    public String getSceneID() {
        return sceneID;
    }

    public String getSceneText() {
        return sceneText;
    }

    public ArrayList<String> getChoices() {
        return choices;
    }

    public void addChoice(String choiceText, String nextSceneID) {
        choices.add(choiceText);
        nextSceneIDs.add(nextSceneID);
    }

    public String getNextSceneID(int index) {
        if (index >= 0 && index < nextSceneIDs.size()) {
            return nextSceneIDs.get(index);
        }
        return null;
    }

    public boolean isEnding() {
        return isEnding;
    }
}
