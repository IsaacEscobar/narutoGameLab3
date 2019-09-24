package model;

import java.util.Comparator;

import exceptions.SameName;

public class Ninja implements Comparable<Ninja>, Comparator<Ninja> {

	private String name;
	private String personality;
	private String creationDate;
	private int powerQuantity;
	
	private Jutsu firstJutsu;
	private Ninja nextNinja;
	private Ninja previousNinja;
	
	public Ninja(String name, String personality, String creationDate, int powerQuantity) {
		this.name = name;
		this.personality = personality;
		this.creationDate = creationDate;
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

	public Jutsu getFirstJutsu() {
		return firstJutsu;
	}

	public void setFirstJutsu(Jutsu jutsu) {
		this.firstJutsu = jutsu;
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
	
	public void addNewJutsu(Jutsu j) throws SameName {
		Jutsu jutsu = firstJutsu;
		if(firstJutsu == null) {
			firstJutsu = j;
		} else {
			while(jutsu != null) {
				if(!jutsu.getName().equalsIgnoreCase(j.getName())) {
					if(jutsu.getNextJutsu() == null) {
						jutsu.setNextJutsu(j);
						jutsu = null;
					} else {
						jutsu = jutsu.getNextJutsu();
					}
				} else {
					throw new SameName("La tecnica que desea añadir ya existe en el sistema");
				}
			}
		}
	}
	
	public void sortJutsusByMultiplier() {
		
	}

	@Override
	public int compareTo(Ninja n) {
		return this.name.compareToIgnoreCase(n.getName());
	}

	@Override
	public int compare(Ninja n1, Ninja n2) {
		return n1.getPowerQuantity() - n2.getPowerQuantity();
	}
	
	@Override
	public String toString() {
		return getName() + ", personalidad: " + getPersonality() + ", fecha: " + getCreationDate() + ", poder:  " + getPowerQuantity();
	}
}
