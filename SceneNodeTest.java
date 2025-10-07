public class SceneNodeTest {
    public static void main(String[] args) {
        SceneNode s = new SceneNode("intro", "You awaken in a dim room.", false);
        s.addChoice("Light a candle", "scene1");
        s.addChoice("Go back to sleep", "scene2");

        System.out.println("Scene Text: " + s.getSceneText());
        System.out.println("Choices: " + s.getChoices());
    }
}
