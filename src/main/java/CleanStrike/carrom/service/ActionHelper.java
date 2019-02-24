package CleanStrike.carrom.service;

import java.util.concurrent.ThreadLocalRandom;

import CleanStrike.carrom.model.CarromBoard;
import CleanStrike.carrom.model.Player;

class ActionHelper {
	static int strike(Object board1, Object player1) {

		CarromBoard board = (CarromBoard) board1;
		Player player = (Player) player1;
		if (board.getBlackCoin() > 0) {
			board.decrementBlack();
			player.incrementPoint();
			player.resetFoul();
		} else {
			player.incrementFoul();
		}
		// System.out.println("Strike method called for " + player.getName());
		board.checkWin();
		return 0;
	}

	static int multiStrike(Object board1, Object player1) {
		CarromBoard board = (CarromBoard) board1;
		Player player = (Player) player1;
		if (board.getBlackCoin() > 1) {
			int rs = strike(board, player);
			int rs1 = strike(board, player);
			player.resetFoul();
		} else {
			//player.incrementFoul();
			player.resetFoul();
		}
		board.checkWin();
		return 0;
	}

	static int redStrike(Object board1, Object player1) {
		CarromBoard board = (CarromBoard) board1;
		Player player = (Player) player1;
		if (board.getRedCoin() > 0) {
			board.decrementRed();
			player.incrementPoint();
			player.incrementPoint();
			player.incrementPoint();
			player.resetFoul();
			// System.out.println("Red Strike method called for " + player.getName());
		} else {
			//player.incrementFoul();//On This condition on random number all games are drawn in 1000
			player.resetFoul();
		}
		board.checkWin();
		return 0;
	}

	static int strikerStrike(Object board1, Object player1) {
		CarromBoard board = (CarromBoard) board1;
		Player player = (Player) player1;

		player.decrementPoint();
		player.incrementFoul();
		// System.out.println("Striker Strike called for " + player.getName());
		board.checkWin();
		return 0;
	}

	static int defunction(Object board1, Object player1) {
		CarromBoard board = (CarromBoard) board1;
		Player player = (Player) player1;

		if (board.getRedCoin() > 0 && randomNumber() == 9) {
			board.decrementRed();
		} else {
			if (board.getBlackCoin() > 0) {
				board.decrementBlack();
			}
		}
		player.decrementPoint();
		player.decrementPoint();
		player.incrementFoul();
		// System.out.println("Defunct method called for " + player.getName());
		board.checkWin();
		return 0;
	}

	static int none(Object board1, Object player1) {
		CarromBoard board = (CarromBoard) board1;
		Player player = (Player) player1;
		player.incrementFoul();
		// System.out.println("None method called for " + player.getName());
		return 0;
	}

	private static int randomNumber() {
		return ThreadLocalRandom.current().nextInt(0, 10);
	}

}
