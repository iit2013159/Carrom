package CleanStrike.carrom.input;

public class RandomInput implements Input {

	@Override
	public int getInput() {		
		return InputHandler.getRandomResponse();
	}
	
}
