package CleanStrike.carrom.model;

import java.util.Comparator;
import java.util.Objects;

import CleanStrike.carrom.model.Player.Builder;

public class Player implements Comparable<Player> {
	private int points;
	private int foul;
	final private String name;
	
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

	public int incrementFoul() {
		this.foul++;
		if(foul == 3) {
			this.points =this.points -2 ;
			resetFoul();
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
			.thenComparingInt(c -> c.foul);
	@Override
	public int compareTo(Player o) {
		// TODO Auto-generated method stub
		return 	COMPARATOR.compare(o,this);
	}

	public static class Builder{
		private int point ;
		private String name;
		private int foul;
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
			return new Player(point,name,foul);
		}
	}

	@Override
	public String toString() {
		return "Player [points=" + points + ", foul=" + foul + ", name=" + name + "]";
	}
	
	
}
