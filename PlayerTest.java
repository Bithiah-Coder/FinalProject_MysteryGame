public class PlayerTest {
    public static void main(String[] args) {
        Player p = new Player("Detective");
        System.out.println(p);
        p.findClue();
        p.useGuess();
        System.out.println(p);
    }
}
