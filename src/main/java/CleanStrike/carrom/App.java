package CleanStrike.carrom;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import CleanStrike.carrom.input.Input;
import CleanStrike.carrom.input.InputHandler;
import CleanStrike.carrom.input.KeyBoardArrayInput;
import CleanStrike.carrom.model.CarromBoard;
import CleanStrike.carrom.model.Player;

/**
 * It can be played by multi people.
 * Simultaneously many games can be programmed.
 * This carrom has three mode of input. You can choose either
 * a)get input from keyboard one by one
 * b)pass all input collectively
 * c)get random input
 *
 */
public class App {
	public static void main(String[] args) throws InterruptedException {
		CarromBoard board = CarromBoard.CarromBuilder.of(9, 1).ofPockets(4).build();
		Player p1 = Player.Builder.of().ofName("Abhishek").build();
		Player p2 = Player.Builder.of().ofName("Sahaj").build();
		// Player p3 = Player.Builder.of().ofName("robo").build();
		//System.out.println("Hello");
		//for (int i = 0; i < 10; i++) {
			GameInstance game = new GamePlay(new Player[] { p1, p2 }, board);
			//game.setInput(new int[] {1,6,4,4,1,1,1,6,3,6,1,5,2,3,2,1});
			//game.setInput();
			//game.setRandomInput();
			game.setKeyBoardInput();
			game.startGame();			
			//game.startGame();
			System.out.println(game.getFinalResult());
		//}
		
		
		
	}
}
