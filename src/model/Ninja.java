package model;

import java.util.Comparator;

public class Ninja implements Comparable<Ninja>, Comparator<Ninja> {

	private String name;
	private String personality;
	private String creationDate;
	private int powerQuantity;
	private Jutsu firstJutsu;
	
	private Ninja nextNinja;
	private Ninja previousNinja;
	
	public Ninja(String name, String personality, String crationDate, int powerQuantity) {
		this.name = name;
		this.personality = personality;
		this.creationDate = crationDate;
		this.powerQuantity = powerQuantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPersonality() {
		return personality;
	}

	public void setPersonality(String personality) {
		this.personality = personality;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public int getPowerQuantity() {
		return powerQuantity;
	}

	public void setPowerQuantity(int powerQuantity) {
		this.powerQuantity = powerQuantity;
	}

	public Jutsu getJutsu() {
		return firstJutsu;
	}

	public void setJutsu(Jutsu jutsus) {
		this.firstJutsu = jutsus;
	}

	public Ninja getNextNinja() {
		return nextNinja;
	}

	public void setNextNinja(Ninja nextNinja) {
		this.nextNinja = nextNinja;
	}

	public Ninja getPreviousNinja() {
		return previousNinja;
	}

	public void setPreviousNinja(Ninja previousNinja) {
		this.previousNinja = previousNinja;
	}
	
	public void addNewJutsu(Jutsu j) {
		Jutsu jutsu = firstJutsu;
		while(jutsu != null) {
			if(!jutsu.getName().equals(j.getName())) {
				if(jutsu .getNextJutsu() == null) {
					jutsu.setNextJutsu(j);
				} else {
					jutsu = jutsu.getNextJutsu();
				}
			} else {
				jutsu = jutsu.getNextJutsu();
			}
		}
	}

	@Override
	public int compareTo(Ninja n) {
		return this.name.compareTo(n.getName());
	}

	@Override
	public int compare(Ninja n1, Ninja n2) {
		return n1.getPowerQuantity() - n2.getPowerQuantity();
	}
}
