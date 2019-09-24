package model;

import java.util.Comparator;

public class Jutsu implements Comparable<Jutsu>, Comparator<Jutsu> {

	private String name;
	private double multiplier;
	
	private Jutsu nextJutsu;

	public Jutsu(String name, double multiplier) {
		this.name = name;
		this.multiplier = multiplier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(int multiplier) {
		this.multiplier = multiplier;
	}

	public Jutsu getNextJutsu() {
		return nextJutsu;
	}

	public void setNextJutsu(Jutsu nextJutsu) {
		this.nextJutsu = nextJutsu;
	}

	@Override
	public int compare(Jutsu j1, Jutsu j2) {
		return (int) (j1.getMultiplier() - j2.getMultiplier());
	}
	
	@Override
	public int compareTo(Jutsu j) {
		return this.getName().compareToIgnoreCase(j.getName());
	}
	
	@Override
	public String toString() {
		return getName() + ", factor: " + getMultiplier();
	}
}
