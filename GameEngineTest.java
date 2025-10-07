public class GameEngineTest {
    public static void main(String[] args) {
        Player player = new Player("Detective");
        GameEngine engine = new GameEngine(player);

        SceneNode start = new SceneNode("start", "You stand before a locked door.", false);
        start.addChoice("Try key", "scene1");
        start.addChoice("Walk away", "scene2");

        SceneNode scene1 = new SceneNode("scene1", "The door opens! You found the way out.", true);
        SceneNode scene2 = new SceneNode("scene2", "You give up. The mystery remains unsolved.", true);

        engine.addScene(start);
        engine.addScene(scene1);
        engine.addScene(scene2);
        engine.start("start");

        System.out.println(engine.getCurrentSceneText());
        engine.makeChoice(0);
        System.out.println(engine.getCurrentSceneText());
    }
}
