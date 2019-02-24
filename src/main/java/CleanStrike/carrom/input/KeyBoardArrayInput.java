package CleanStrike.carrom.input;

import java.util.Scanner;

public class KeyBoardArrayInput implements Input{
	private int[] inputNum;
	private int[] cur = new int[] {0};
	/**
	 * This method first accepts total count
	 * and then acepts that many integer
	 */
	public void setInput() {
		Scanner input = new Scanner(System.in);
		System.out.println("Total ");
		int total = input.nextInt();
		System.out.println("Enter  " + total +" elements");
		inputNum = new int[total];
		for(int i = 0; i < total;i++) {			
			inputNum[i] = input.nextInt();
		}
		//return inputNum;
	}
	public void setInput(int[] ar) {
		inputNum = ar;
		//return inputNum;
	}
	@Override
	public int getInput() {				
		if( cur[0] < inputNum.length)  {
			return InputHandler.getRespose(inputNum, cur);
		}
		else {
			throw new ArrayIndexOutOfBoundsException("Array has been exhausted . Result has not been generated");
		}
	}
	
}
