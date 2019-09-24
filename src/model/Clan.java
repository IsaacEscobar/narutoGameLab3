package model;

import exceptions.SameName;

public class Clan implements Comparable<Clan> {
	
	private String name;
	
	private Clan nextClan;
	private Ninja firstNinja;

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

	public void addNewNinja(Ninja n) throws SameName {
		Ninja ninja = firstNinja;
		if(firstNinja == null) {
			firstNinja = n;
		} else {
			while(ninja != null) {
				if(!ninja.getName().equalsIgnoreCase(n.getName())) {
					if(ninja.getNextNinja() == null) {
						ninja.setNextNinja(n);
						ninja = null;
					} else {
						ninja = ninja.getNextNinja();
					}
				} else {
					throw new SameName("El personaje que desea añadir ya existe en el sistema");
				}
			}
		}
	}
	
	public boolean addNewJutsuToANinja(Ninja n, Jutsu j) throws SameName {
		boolean thereIs = true;
		Ninja ninja = firstNinja;
		while(ninja != null) {
			if(ninja.compareTo(n) == 0) {
				n.addNewJutsu(j);
				ninja = null;
				thereIs = false;
			} else {
				ninja = ninja.getNextNinja();
			}
		}
		return thereIs;
	}
	
	public void sortNinjasByName() {
		
	}
	
	public void sortNinjasByPower() {
		
	}
	
	@Override
	public int compareTo(Clan c) {
		return this.getName().compareToIgnoreCase(c.getName());
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
