package CleanStrike.carrom;

import java.util.concurrent.ThreadLocalRandom;

class ActionHelper {
	static int strike(Object board1, Object player1) {
		CarromBoard board = (CarromBoard) board1;
		Player player = (Player) player1;
		board.decrementBlack();
		player.incrementPoint();
		player.resetFoul();
		return 0;
	}

	static int multiStrike(Object board1, Object player1) {
		CarromBoard board = (CarromBoard) board1;
		Player player = (Player) player1;
		strike(board, player);
		strike(board, player);
		player.resetFoul();
		return 0;
	}

	static int redStrike(Object board1, Object player1) {
		CarromBoard board = (CarromBoard) board1;
		Player player = (Player) player1;
		board.decrementRed();
		player.incrementPoint();
		player.incrementPoint();
		player.resetFoul();
		return 0;
	}

	static int strikerStrike(Object board1, Object player1) {
		CarromBoard board = (CarromBoard) board1;
		Player player = (Player) player1;
		player.decrementPoint();
		player.incrementFoul();
		return 0;
	}

	static int defunction(Object board1, Object player1) {
		CarromBoard board = (CarromBoard) board1;
		Player player = (Player) player1;
		if (board.getRedCoin() > 0 && randomNumber() == randomNumber()) {
			board.decrementRed();
		} else {
			board.decrementBlack();
		}
		player.decrementPoint();
		player.decrementPoint();
		player.incrementFoul();
		return 0;
	}
	
	static int none(Object board1, Object player1) {
		CarromBoard board = (CarromBoard) board1;
		Player player = (Player) player1;		
		player.incrementFoul();
		return 0;
	}

	private static int randomNumber() {
		return ThreadLocalRandom.current().nextInt(0, 10);
	}

}
