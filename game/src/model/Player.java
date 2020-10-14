package model;

public class Player {
	
	String name;
	int chips;
	
	public Player(String name) {
		this.chips = 0;
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getChips() {
		return chips;
	}
	public void setChips(int chips) {
		this.chips = chips;
	}
	

}
