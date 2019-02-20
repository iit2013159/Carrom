package CleanStrike.carrom;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        CarromBoard board = CarromBoard.CarromBuilder.of(9, 1).ofPockets(4).build();
        Player p1 = Player.Builder.of().ofName("Abhishek").build();
        Player p2 = Player.Builder.of().ofName("Sahaj").build();
                
    }
}
