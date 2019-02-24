package CleanStrike.carrom.input;

import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class InputHandler {
	
	public static int getRespose() {
		Scanner input = new Scanner(System.in);
		int inp = input.nextInt();
		Objects.requireNonNull(inp);
		if (inp < 0 || inp > 6) {
			System.out.println("Not correct choice. Choose again");
			return getRespose();
		}
		return inp;
	}
	public static int getRespose(final int[] num,int[] cur) {
		if(cur[0] >= num.length ) {
			--cur[0];
			return getRespose(num,cur);
		}
		int inp = num[cur[0]++];
		Objects.requireNonNull(inp);
		if (inp < 0 || inp > 6) {
			System.out.println("Not correct choice. Choose again");
			++cur[0];
			return getRespose(num,cur);
		}
		return inp;
	}

	public static int getRandomResponse() {
		int inp = ThreadLocalRandom.current().nextInt(1, 7);
		//System.out.println("input is " + inp);
		return inp;
	}

}
