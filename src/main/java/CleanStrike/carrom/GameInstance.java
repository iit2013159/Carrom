package CleanStrike.carrom;

public interface GameInstance {

	void showOption();

	int getRespose();

	int getRandomResponse();

	void startGame();
	
	void setInput();
	void setInput(int[] ar);
	Thread getResult();
	String getFinalResult();
	String getScore();
	void setRandomInput();
	
	void setKeyBoardInput();

}