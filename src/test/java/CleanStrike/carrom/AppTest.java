package CleanStrike.carrom;

import javax.management.MXBean;

import CleanStrike.carrom.model.CarromBoard;
import CleanStrike.carrom.model.Player;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    
    public void testApp()
    {
    	CarromBoard board = CarromBoard.CarromBuilder.of(9, 1).ofPockets(4).build();
		Player p1 = Player.Builder.of().ofName("Abhishek").build();
		Player p2 = Player.Builder.of().ofName("Sahaj").build();
		GameInstance game = new GamePlay(new Player[] { p1, p2 }, board);
		game.setInput(new int[] {1,6,4,4,1,1,1,6,3,6,1,5,2,3,2,1});
		game.startGame();
		String res = game.getFinalResult();
		//System.out.println(res);
        assertTrue(res.equalsIgnoreCase("Final Score 8 - -3Abhishek wins"));
    }
    
    /*public void testAppException()
    {
    	CarromBoard board = CarromBoard.CarromBuilder.of(9, 1).ofPockets(4).build();
		Player p1 = Player.Builder.of().ofName("Abhishek").build();
		Player p2 = Player.Builder.of().ofName("Sahaj").build();
		GameInstance game = new GamePlay(new Player[] { p1, p2 }, board);
		//game.setInput(new int[] {1,6,4,4,1,1,1,6,3,6,1,5,2,3,2,1});
		game.startGame();
		String res = game.getFinalResult();
		//System.out.println(res);
        assertTrue(res.equalsIgnoreCase("Final Score 8 - -3Abhishek wins"));
    }*/
}
