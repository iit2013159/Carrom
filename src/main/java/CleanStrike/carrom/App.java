package CleanStrike.carrom;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

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
        Player p3 = Player.Builder.of().ofName("robo").build();        
        GameInstance game = new GamePlay( new Player[] {p1,p2,p3}, board);
       game.startGame();
       game.getResult();
        System.exit(0);
        try {
        	boolean turn = false;
        	Player currPlayer;
        	while(true) {
        		if(!turn) {
        			currPlayer = p1;
        		}else {
        			currPlayer = p2;
        		}
        		System.out.println(currPlayer.getName() +" : Choose an outcome from the list below");
        		System.out.println("1. Strike");
        		System.out.println("2. MultiStrike");
        		System.out.println("3. Red Strike");
        		System.out.println("4. Striker strike");
        		System.out.println("5. Defunct Coin");
        		System.out.println("6. None");
        		/*Scanner input =  new Scanner(System.in);
        		int inp = input.nextInt();   */
        		int inp = ThreadLocalRandom.current().nextInt(1, 7);
        		System.out.println("input is " + inp);
        		int res = (int) Action.values()[inp-1].getOperator().apply(board, currPlayer);
        		turn = !turn;
            	System.out.println("Returned value is  " + res + " Score is " +p1.getPoints() + "-" +p2.getPoints() +" carrom " + board + p1 + p2);
        	}
        	
        	
        }catch(IllegalStateException e) {
        	if(Math.abs(p1.getPoints() - p2.getPoints()) >= 3 ) {
        		if(p1.getPoints() >= 5) {
        			System.out.println(p1.getName() +" wins");
        		}
        		else if(p2.getPoints() >= 5) {
        			System.out.println(p2.getPoints() +" wins");
        		}
        		else {
        			System.out.println(" game is drawn ");
        		}
        	}else {
    			System.out.println(" game is drawn ");
    		}
        	System.out.println("Final Score " + p1.getPoints() +"-" +p2.getPoints());
        }
                
    }
}
