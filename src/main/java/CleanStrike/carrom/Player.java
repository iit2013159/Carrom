package CleanStrike.carrom;

import java.util.Comparator;
import java.util.Objects;

import CleanStrike.carrom.Player.Builder;

public class Player implements Comparable<Player> {
	private int points;
	private int foul;
	private String name;
	
	private Player(int points,String name,int foul) {
		this.name = name;
		this.points = points;
		this.foul =foul;
	}
	
	public int hashCode() {
		return Objects.hash(this.points,this.name,this.foul);
	}

	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}

	public int incrementFoul() {
		this.foul++;
		if(foul == 3) {
			this.points =this.points -2 ;
			this.foul = 0;
		}
		return this.foul;
	}
	public int resetFoul() {
		this.foul = 0;
		return 0;
	}
	public int incrementPoint() {
		this.points++;
		return this.points;
	}
	public int decrementPoint() {
		this.points--;
		return this.points;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == this) {return true;}
		if(obj instanceof Player) {
			Player other = (Player)obj;
			return Objects.equals(other, this);
		}
		return false;
	}
	private static final Comparator<Player> COMPARATOR = Comparator.comparingInt((Player c) -> c.points)
			.thenComparing(c -> c.name)
			.thenComparingInt(c -> c.foul);
	@Override
	public int compareTo(Player o) {
		// TODO Auto-generated method stub
		return 	COMPARATOR.compare(this, o);
	}

	public static class Builder{
		int point ;
		String name;
		int foul;
		private Builder() {
			
		}
		private Builder (int point) {
			this.point = point;			
		}
		public static Builder of(int point) {
			return new Builder(point) ;
		}
		public static Builder of() {
			return new Builder() ;
		}
		public Builder ofPoint(int size) {
			this.point = size;
			return this;
		}
		public Builder ofName(String name) {
			this.name = name;
			return this;
		}
		public Builder ofFoul(int foul) {
			this.foul = foul;
			return this;
		}
		public Player build() {
			return new Player(point,name,0);
		}
	}
	
}
