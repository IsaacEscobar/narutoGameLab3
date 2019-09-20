package model;

public class Clan implements Comparable<Clan> {
	
	private String name;
	
	private Clan nextClan;
	private Ninja firstNinja;
//	private Jutsu firstJutsu;

	public Clan(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Clan getNextClan() {
		return nextClan;
	}

	public void setNextClan(Clan c) {
		this.nextClan = c;
	}
	
	public Ninja getFirstNinja() {
		return firstNinja;
	}
	
	public void setFirstNinja(Ninja n) {
		this.firstNinja = n;
	}
	
//	public Jutsu getFirstJutsu() {
//		return firstJutsu;
//	}
//	
//	public void setFirstJutsu(Jutsu j) {
//		this.firstJutsu = j;
//	}

	public void addNewNinja(Ninja n) {
		Ninja ninja = firstNinja;
		while(ninja != null) {
			if(!ninja.getName().equals(n.getName())) {
				if(ninja.getNextNinja() == null) {
					ninja.setNextNinja(n);
				} else {
					ninja = ninja.getNextNinja();
				}
			} else {
				ninja = ninja.getNextNinja();
			}
		}
	}
	
	public void addNewJutsuToANinja(Ninja n, Jutsu j) {
		Ninja ninja = firstNinja;
		while(ninja != null) {
			if(ninja.compareTo(n) == 0) {
				n.addNewJutsu(j);
			} else {
				ninja = ninja.getNextNinja(); 
			}
		}
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
