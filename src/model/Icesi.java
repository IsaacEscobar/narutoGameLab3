package model;

public class Icesi {
	
	private Clan firstClan;
	
	public Clan getClan() {
		return firstClan;
	}
	
	public void setClan(Clan c) {
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

}
