package model;

public class Clan implements Comparable<Clan> {
	
	private String name;
	
	private Ninja ninja;

	public Clan(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void createNewNinja(Ninja n) {
		
	}
	
	public void sortNinjasByName() {
		
	}
	
	public void sortNinjasByPower() {
		
	}

	@Override
	public int compareTo(Clan c) {
		return this.getName().compareTo(c.getName());
	}
}
