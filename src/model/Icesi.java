package model;

public class Icesi {
	
	private Clan firstClan;
	
	public Icesi() {
		Clan uzumakis = new Clan("Uzumaki");
		Ninja naruto = new Ninja("Naruto", "Activa", "20/09/19", 899);
		Jutsu kage_bunshin = new Jutsu("Kage-Bunshin", 0.69); 
		uzumakis.setFirstNinja(naruto);
		uzumakis.getFirstNinja().setFirstJutsu(kage_bunshin);
		Clan uchiha = new Clan("Uchiha");
		Ninja sasuke = new Ninja("Sasuke", "Introvertida", "20/09/19", 900);
		Jutsu katon = new Jutsu("Katon", 0.80);
		addNewClan(uchiha);
		addNinjaToAClan(sasuke, uchiha);
		sasuke.addNewJutsu(katon);
	}
	
	public Clan getFirstClan() {
		return firstClan;
	}
	
	public void setFirstClan(Clan c) {
		this.firstClan = c;
	}
	
	public void addNewClan(Clan c) {
		Clan clan = firstClan;
		while(clan != null) {
			if(!clan.getName().equals(c.getName())) {
				if(clan.getNextClan() == null) {
					clan.setNextClan(c);
				} else {
					clan = clan.getNextClan();
				}
			} else {
				clan = clan.getNextClan();
			}
		}
	}
	
	public void addNinjaToAClan(Ninja n, Clan c) {
		Clan clan = firstClan;
		while (clan != null) {
			if(c.compareTo(clan) == 0) {
				c.addNewNinja(n);
			} else {
				clan = clan.getNextClan();
			}
		}
	}
}
