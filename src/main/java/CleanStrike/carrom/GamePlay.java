package CleanStrike.carrom;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import CleanStrike.carrom.input.Input;
import CleanStrike.carrom.input.KeyBoardArrayInput;
import CleanStrike.carrom.input.KeyBoardInput;
import CleanStrike.carrom.input.RandomInput;
import CleanStrike.carrom.model.CarromBoard;
import CleanStrike.carrom.model.Player;
import CleanStrike.carrom.service.Action;

public class GamePlay implements GameInstance {
	Player[] players;
	CarromBoard board;
	String result = "";
	Input inp;

	/**
	 * Asynchronous method to get Result
	 */
	public Thread getResult() {
		Thread rb = new Thread(new Runnable() {

			@Override
			public void run() {
				// System.out.println("Thread started");
				// TODO Auto-generated method stub
				while (result == null || result == "") {
					try {
						wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (result != null) {
					System.out.println("Result is " + result);
				}
			}
		});
		rb.start();
		return rb;
	}

	public String getFinalResult() {
		return this.result;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see CleanStrike.carrom.GameInstance#showOption()
	 */
	@Override
	public void showOption() {
		System.out.println(" : Choose an outcome from the list below");
		System.out.println("1. Strike");
		System.out.println("2. MultiStrike");
		System.out.println("3. Red Strike");
		System.out.println("4. Striker strike");
		System.out.println("5. Defunct Coin");
		System.out.println("6. None");
	}

	private void setInpOfKeyBoardInput() {
		inp = new KeyBoardInput();		
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see CleanStrike.carrom.GameInstance#getRespose()
	 */
	@Override
	public int getRespose() {
		if (inp == null) {
			setInpOfKeyBoardInput();
		}
		return inp.getInput();
	}

	private void setInpOfKeyBoardArrayInput() {
		KeyBoardArrayInput keyInput = new KeyBoardArrayInput();
		keyInput.setInput();
		inp = keyInput;
	}

	public int getResposeFromArray() {
		if (inp == null) {
			setInpOfKeyBoardArrayInput();
			return inp.getInput();
		} else if (inp instanceof KeyBoardArrayInput) {
			return inp.getInput();
		} else {
			throw new IllegalArgumentException("instance is not of KeyBoardArrayInput ");
		}
	}

	private void setInpOfKeyBoardArrayInput(int[] ar) {
		KeyBoardArrayInput keyInput = new KeyBoardArrayInput();
		keyInput.setInput(ar);
		inp = keyInput;
	}

	public int getResposeFromArray(int[] ar) {
		if (inp == null) {
			setInpOfKeyBoardArrayInput(ar);
			return inp.getInput();
		} else if (inp instanceof KeyBoardArrayInput) {
			return inp.getInput();
		} else {
			throw new IllegalArgumentException("instance is not of KeyBoardArrayInput ");
		}

	}

	private void setInpOfRandomInput() {
		inp = new RandomInput();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see CleanStrike.carrom.GameInstance#getRandomResponse()
	 */
	@Override
	public int getRandomResponse() {
		if (inp == null) {
			setInpOfRandomInput();
		}
		return inp.getInput();
	}

	private int getTurn(final int noOfPlayer, int lastTurn) {
		if (lastTurn == noOfPlayer - 1) {
			return 0;
		}
		return lastTurn + 1;
	}

	private String scoreFormat(Player... players) {
		Arrays.stream(players).map(i -> i.getPoints()).forEach(i -> System.out.print(i + "-"));
		return "";
	}

	public String getScore() {
		Optional<String> res;
		res = Stream.of(players).map(i -> String.valueOf(i.getPoints()))
				.reduce((x, y) -> x.toString() + " - " + y.toString());
		return res.orElse("Empty array");
	}

	private String calculateWin() {
		Arrays.sort(players);
		// System.out.println("After " + printArray(players));
		if (Math.abs(players[0].getPoints() - players[players.length - 1].getPoints()) >= 3) {
			if (players[0].getPoints() >= 5) {
				return players[0].getName() + " wins";
			}
		}
		return " game is drawn ";
	}

	private String printArray(Player[] players) {
		Optional<String> res;
		res = Stream.of(players).map(i -> i.toString()).reduce((x, y) -> x.toString() + y.toString());
		return res.orElse("Empty array");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * CleanStrike.carrom.GameInstance#startGame(CleanStrike.carrom.CarromBoard,
	 * CleanStrike.carrom.Player)
	 */
	@Override
	public void startGame() {
		if (inp == null) {
			throw new IllegalArgumentException("Input method has not been set");
		}
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
				int input;
				if (inp instanceof KeyBoardInput) {
					input = getRespose();
				} else if (inp instanceof KeyBoardArrayInput) {
					input = getResposeFromArray();
				} else {
					input = getRandomResponse();
				}
				System.out.println("input is " + input);
				int res = (int) Action.values()[input - 1].getOperator().apply(board, currPlayer);
				System.out.println("Returned value is  " + res + " " + "Score is " + scoreFormat(players) + " carrom "
						+ board + " " + printArray(players));

			}
		} catch (IllegalStateException e) {
			// System.out.println(calculateWin());
			// System.out.println("previous " +printArray(players));
			result = "Final Score " + getScore() + calculateWin();

		}

	}

	@Override
	public void setInput() {
		// TODO Auto-generated method stub
		setInpOfKeyBoardArrayInput();
	}

	@Override
	public void setInput(int[] ar) {
		setInpOfKeyBoardArrayInput(ar);
	}

	public void setRandomInput() {
		// TODO Auto-generated method stub
		setInpOfRandomInput();
	}
	public void setKeyBoardInput() {
		setInpOfKeyBoardInput();
	}
}
