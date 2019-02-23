package CleanStrike.carrom;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class GamePlay implements GameInstance {
	Player[] players;
	CarromBoard board;
	String result = "";
	
	/**
	 * Asynchronous method to get Result
	 */
	public String getResult() {
		/*CompletableFuture future = new CompletableFuture<>();
		Executors.newCachedThreadPool().submit(() -> {});*/
		return result;
	}

	public GamePlay(Player[] players, CarromBoard board) {
		super();
		this.players = players;
		this.board = board;
	}

	public Player[] getPlayers() {
		return players;
	}

	public void setPlayers(Player[] players) {
		this.players = players;
	}

	public CarromBoard getBoard() {
		return board;
	}

	public void setBoard(CarromBoard board) {
		this.board = board;
	}

	/* (non-Javadoc)
	 * @see CleanStrike.carrom.GameInstance#showOption()
	 */
	@Override
	public  void showOption() {
		System.out.println(" : Choose an outcome from the list below");
		System.out.println("1. Strike");
		System.out.println("2. MultiStrike");
		System.out.println("3. Red Strike");
		System.out.println("4. Striker strike");
		System.out.println("5. Defunct Coin");
		System.out.println("6. None");
	}

	/* (non-Javadoc)
	 * @see CleanStrike.carrom.GameInstance#getRespose()
	 */
	@Override
	public  int getRespose() {
		Scanner input = new Scanner(System.in);
		int inp = input.nextInt();
		Objects.requireNonNull(inp);
		if (inp < 0 || inp > 6) {
			System.out.println("Not correct choice. Choose again");
			return getRespose();
		}
		return inp;
	}

	/* (non-Javadoc)
	 * @see CleanStrike.carrom.GameInstance#getRandomResponse()
	 */
	@Override
	public  int getRandomResponse() {
		int inp = ThreadLocalRandom.current().nextInt(1, 7);
		System.out.println("input is " + inp);
		return inp;
	}

	private  int getTurn(final int noOfPlayer, int lastTurn) {
		if (lastTurn == noOfPlayer - 1) {
			return 0;
		}
		return lastTurn + 1;
	}

	private  String scoreFormat(Player... players) {
		Arrays.stream(players).map(i -> i.getPoints()).forEach(i -> System.out.print(i + "-"));
		return "";
	}

	private  String calculateWin() {
		Arrays.sort(players);
		if (Math.abs(players[0].getPoints() - players[players.length - 1].getPoints()) >= 3) {
			if (players[0].getPoints() >= 5) {
				return players[0].getName() + " wins";
			}
		}
		return " game is drawn ";
	}
	private  String printArray(Player[] players) {
		Optional<String> res ;
		res = Stream.of(players).map(i -> i.toString()).reduce((x,y)-> x.toString() + y.toString());
		return res.orElse("Empty array");
				
	}
	/* (non-Javadoc)
	 * @see CleanStrike.carrom.GameInstance#startGame(CleanStrike.carrom.CarromBoard, CleanStrike.carrom.Player)
	 */
	@Override
	public  void startGame() {
		int turn = -1;
		if (players.length <= 1) {
			System.out.println("Players cannot be less than 1");
			return;
		}
		try {
			while (true) {
				turn = getTurn(players.length, turn);
				Player currPlayer = players[turn];
				System.out.print(currPlayer.getName());
				showOption();
				//int inp = getRespose();
				int inp = getRandomResponse();
				int res = (int) Action.values()[inp - 1].getOperator().apply(board, currPlayer);
				System.out.println("Returned value is  " + res + " " + "Score is " + scoreFormat(players) + " carrom "
						+ board +  printArray(players)   );

			}
		} catch (IllegalStateException e) {
			System.out.println(calculateWin());
			result = "Final Score " + scoreFormat(players);
			
		}

	}
}
