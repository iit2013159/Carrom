package CleanStrike.carrom;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class CarromBoard implements Comparable<CarromBoard> {
	private int blackCoin ;
	private int redCoin;
	private final int striker = 5;
	private int[] pockets ;
	private CarromBoard(int blackCoin,int redCoin,int pockets) {
		this.blackCoin =blackCoin;
		this.redCoin = redCoin;
		this.pockets = new int[pockets];
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(this.blackCoin,this.redCoin,this.pockets);
	}


	@Override
	public boolean equals(Object obj) {
		if(obj == this) {return true;}
		if(obj instanceof CarromBoard) {
			CarromBoard other = (CarromBoard)obj;
			return Objects.equals(other, this);
		}
		return false;
	}
	private static final Comparator<CarromBoard> COMPARATOR = Comparator.comparingInt((CarromBoard c) -> c.blackCoin)
			.thenComparingInt(c -> c.redCoin)
			.thenComparing(c -> c.pockets);
	@Override
	public int compareTo(CarromBoard o) {
		// TODO Auto-generated method stub
		return 	COMPARATOR.compare(this, o);
	}

	public static class CarromBuilder{
		int blackCoin ;
		int redCoin;
		int pockets;
		private CarromBuilder() {
			
		}
		private CarromBuilder (int blackCoin, int redCoin) {
			this.blackCoin = blackCoin;
			this.redCoin = redCoin;
			//this.pockets = pockets;
		}
		public static CarromBuilder of(int blackCoin, int redCoin) {
			return new CarromBuilder(blackCoin, redCoin) ;
		}
		public CarromBuilder ofBlackCoin(int size) {
			this.blackCoin = size;
			return this;
		}
		public CarromBuilder ofRedCoin(int size) {
			this.redCoin = size;
			return this;
		}
		public CarromBuilder ofPockets(int pocket) {
			this.pockets =pocket;
			return this;
		}
		public CarromBoard build() {
			return new CarromBoard(blackCoin,redCoin,pockets);
		}
	}

	public int getBlackCoin() {
		return blackCoin;
	}


	public void setBlackCoin(int blackCoin) {
		this.blackCoin = blackCoin;
	}


	public int getRedCoin() {
		return redCoin;
	}


	public void setRedCoin(int redCoin) {
		this.redCoin = redCoin;
	}


	public int[] getPockets() {
		return pockets;
	}


	public void setPockets(int[] pockets) {
		this.pockets = pockets;
	}


	public int getStriker() {
		return striker;
	}
	
	public int decrementBlack() {
		this.blackCoin--;
		return this.blackCoin;
	}
	
	public int incrementBlack() {
		this.blackCoin++;
		return this.blackCoin;
	}
	
	public int decrementRed() {
		this.redCoin--;
		return this.redCoin;
	}
	
}
