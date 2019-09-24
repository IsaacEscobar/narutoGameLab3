package model;

import java.util.Scanner;

import exceptions.SameName;

public class Icesi {
	
	private Clan firstClan;
	private Scanner reader;
	
	public Icesi() {
		reader = new Scanner(System.in);
		Clan uzumaki = new Clan("Uzumaki");
		Ninja naruto = new Ninja("Naruto", "Activa", "20/09/19", 899);
		Jutsu kage_bunshin = new Jutsu("Kage-Bunshin", 0.69);
		setFirstClan(uzumaki);
		uzumaki.setFirstNinja(naruto);
		uzumaki.getFirstNinja().setFirstJutsu(kage_bunshin);
		Clan uchiha = new Clan("Uchiha");
		Ninja sasuke = new Ninja("Sasuke", "Introvertida", "20/09/19", 900);
		Jutsu katon = new Jutsu("Katon", 0.80);
		try {
			addNewClan(uchiha);
		} catch(SameName m) {
			System.out.println(m.getMessage());
		}
		try {
			addNinjaToAClan(sasuke, uchiha);
		} catch(SameName m) {
			System.out.println(m.getMessage());
		}
		try {
			sasuke.addNewJutsu(katon);
		} catch(SameName m) {
			System.out.println(m.getMessage());
		}
		Ninja karin = new Ninja("Karin", "Pasiva", "23/09/19", 290);
		Jutsu nose = new Jutsu("Clones", 0.1);
		try {
			addNinjaToAClan(karin, uzumaki);
		} catch(SameName m) {
			System.out.println(m.getMessage());
		}
		try {
			uzumaki.addNewJutsuToANinja(karin, nose);
		} catch(SameName m) {
			System.out.println(m.getMessage());
		}
	}
	
	public Clan getFirstClan() {
		return firstClan;
	}
	
	public void setFirstClan(Clan c) {
		this.firstClan = c;
	}
	
	public boolean addNewClan(Clan c) throws SameName {
		boolean thereIs = true;
		Clan clan = firstClan;
		if(firstClan == null) {
			firstClan = c;
		} else {
			while(clan != null) {
				if(!clan.getName().equalsIgnoreCase(c.getName())) {
					if(clan.getNextClan() == null) {
						clan.setNextClan(c);
						clan = null;
						thereIs = false;
					} else {
						clan = clan.getNextClan();
					}
				} else {
					throw new SameName("El clan que desea añadir ya existe en el sistema.");
				}
			}
		}
		return thereIs;
	}
	
	public boolean addNinjaToAClan(Ninja n, Clan c) throws SameName{
		boolean thereIs = true;
		Clan clan = firstClan;
		while (clan != null) {
			if(c.compareTo(clan) == 0) {
				c.addNewNinja(n);
				clan = null;
				thereIs = false;
			} else {
				clan = clan.getNextClan();
			}
		}
		return thereIs;
	}
	
	public void menuInterface() {
		System.out.println("~~~~~~~ Bienvenido al sistema ~~~~~~~~");
		System.out.println("Escoja entre las siguientes opciones: ");
		System.out.println("1- Añadir.");
		System.out.println("2- Eliminar.");
		System.out.println("3- Modificar.");
		System.out.println("4- Ordenar y mostrar.");
		System.out.println("0- Salir del sistema.");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(" ");
	}
	
	public void add() {
		int option = 0;
		do {
			System.out.println(" ");
			System.out.println("Seleccione: ");
			System.out.println("1- Clan nuevo con un personaje y una tecnica.");
			System.out.println("2- Personaje con tecnica.");
			System.out.println("3- Tecnica nueva.");
			System.out.println("4- Volver al menu principal.");
			System.out.println(" ");
			option = reader.nextInt();
			System.out.println(" ");
			switch (option) {
			case 1:
				addClan();
				break;
			case 2:
				System.out.println("Digite el nombre del clan al cual desea añadir el personaje: ");
				reader.nextLine();
				String cName = reader.nextLine();
				Clan aux = searchClan(cName);
				if(aux != null) {
					addNinja(aux);
					System.out.println(" ");
				} else {
					System.out.println(" ");
				}
				break;
			case 3:
				System.out.println("Digite el nombre del clan al que pertenece el personaje: ");
				reader.nextLine();
				String cName2 = reader.nextLine();
				Clan aux1 = searchClan(cName2);
				if(aux1 != null) {
					System.out.println("Digite el nombre del personaje al cual desea añadir la tecnica: ");
					//reader.nextLine();
					String nName = reader.nextLine();
					Ninja aux2 = searchNinjaInAClan(cName2, nName);
					if(aux2 != null) {
						addJutsu(aux1, aux2);
						System.out.println(" ");
					} else {
						System.out.println(" ");
					}
					break;
				}
			}	
		} while(option != 4);
	}
	
	public void delete() {
		int option = 0;
		do {
			System.out.println(" ");
			System.out.println("Seleccione: ");
			System.out.println("1- Clan.");
			System.out.println("2- Personaje.");
			System.out.println("3- Tecnica.");
			System.out.println("4- Volver al menu principal.");
			System.out.println(" ");
			option = reader.nextInt();
			System.out.println(" ");
			switch(option) {
				case 1:
					deleteClan();
					System.out.println(" ");
					break;
				case 2:
					deleteNinja();
					System.out.println(" ");
					break;
				case 3:
					deleteJutsu();
					System.out.println(" ");
					break;
			}
		} while(option != 4);
	}
	
	public void update() {
		
	}
	
	public void showInfo() {
		System.out.println(toString());
	}
	
	public void addClan() {
		System.out.println("Digite el nombre del clan que desea crear: ");
		reader.nextLine();
		String newClanName = reader.nextLine();
		Clan newClan = new Clan(newClanName);
		try {
		//if(!addNewClan(newClan)) {
			addNewClan(newClan);
			System.out.println(" ");
			System.out.println("Personaje: ");
			System.out.println("Digite el nombre del personaje que desea crear: ");
			String newNinjaName = reader.nextLine();
			System.out.println("Digite la personalidad que tiene: ");
			String newPersonality = reader.nextLine();
			System.out.println("Digite el dia actual en formato dd/mm/aa: ");
			String newCreationDate = reader.nextLine();
			System.out.println("Digite la cantidad de poder que tiene: ");
			int newPowerQuantity = reader.nextInt();
			Ninja newNinja = new Ninja(newNinjaName, newPersonality, newCreationDate, newPowerQuantity);
			if(!addNinjaToAClan(newNinja, newClan)) {
				//addNinjaToAClan(newNinja, newClan);
				System.out.println(" ");
				System.out.println("Tecnica: ");
				System.out.println("Digite el nombre de la tecnica que desea crear: ");
				reader.nextLine();
				String newJutsuName = reader.nextLine();
				System.out.println("Digite el factor de poder de la tecnica: ");
				String auxDouble = reader.nextLine();
				double newMultiplier = Double.valueOf(auxDouble);
				Jutsu newJutsu = new Jutsu(newJutsuName, newMultiplier);
				if(!newClan.addNewJutsuToANinja(newNinja, newJutsu)) {
					//newClan.addNewJutsuToANinja(newNinja, newJutsu);
					System.out.println(" ");
				}
			}
		} catch(SameName m) {
			System.out.println(m.getMessage());
		}
	}
	
	
	public void addNinja(Clan c) {
		System.out.println(" ");
		System.out.println("Personaje: ");
		System.out.println("Digite el nombre del personaje que desea crear: ");
		String newNinjaName = reader.nextLine();
		System.out.println("Digite la personalidad que tiene: ");
		String newPersonality = reader.nextLine();
		System.out.println("Digite el dia actual en formato dd/mm/aa: ");
		String newCreationDate = reader.nextLine();
		System.out.println("Digite la cantidad de poder que tiene: ");
		int newPowerQuantity = reader.nextInt();
		Ninja newNinja = new Ninja(newNinjaName, newPersonality, newCreationDate, newPowerQuantity);
		try {
			addNinjaToAClan(newNinja, c);
			System.out.println(" ");
			System.out.println("Tecnica: ");
			System.out.println("Digite el nombre de la tecnica que desea crear: ");
			reader.nextLine();
			String newJutsuName = reader.nextLine();
			System.out.println("Digite el factor de poder de la tecnica: ");
			String auxDouble = reader.nextLine();
			double newMultiplier = Double.valueOf(auxDouble);
			Jutsu newJutsu = new Jutsu(newJutsuName, newMultiplier);
			if(!c.addNewJutsuToANinja(newNinja, newJutsu)) {
				//newClan.addNewJutsuToANinja(newNinja, newJutsu);
				System.out.println(" ");
			}
		} catch(SameName m) {
			System.out.println(m.getMessage());
		}
	}
	
	public void addJutsu(Clan c, Ninja n) {
		System.out.println(" ");
		System.out.println("Tecnica: ");
		System.out.println("Digite el nombre de la tecnica que desea crear: ");
		reader.nextLine();
		String newJutsuName = reader.nextLine();
		System.out.println("Digite el factor de poder de la tecnica: ");
		String auxDouble = reader.nextLine();
		double newMultiplier = Double.valueOf(auxDouble);
		Jutsu newJutsu = new Jutsu(newJutsuName, newMultiplier);
		try {
			c.addNewJutsuToANinja(n, newJutsu);
			System.out.println(" ");
		} catch(SameName m) {
			System.out.println(m.getMessage());
		}
	}
	
	public void deleteClan() {
		
	}
	
	public void deleteNinja() {
		
	}
	
	public void deleteJutsu() {
		
	}
	
	@Override
	public String toString() {
		String msg = "";
		Clan auxC = getFirstClan();
		Ninja auxN = auxC.getFirstNinja();
		Jutsu auxJ = auxN.getFirstJutsu();
		while(auxC != null) {
			msg += auxC.toString() + ":\n";
			while(auxN != null) {
				msg += auxN.toString() + " || Tecnicas: ";
				while(auxJ != null) {
					msg += auxJ.toString() + "\n";
					auxJ = auxJ.getNextJutsu();
				}
				auxN = auxN.getNextNinja();
				if(auxN != null) {
					auxJ = auxN.getFirstJutsu();
				}
			}
			if(auxN == null) {
				auxC = auxC.getNextClan();
				if(auxC != null) {
					auxN = auxC.getFirstNinja();
					auxJ = auxN.getFirstJutsu();
				}
			}
		}
		return msg;
	}
	
	private Clan searchClan(String cName) {
		Clan searched = null;
		Clan aux = firstClan;
		while(aux != null) {
			if(cName.equalsIgnoreCase(aux.getName())) {
				searched = aux;
				aux = null;
			} else {
				aux = aux.getNextClan();
			}
		}
		if(searched == null) {
			System.out.println("El clan buscado no existe.");
		}
		return searched;
	}
	
	public Ninja searchNinjaInAClan(String cName, String nName) {
		Clan cSearched = searchClan(cName);
		Ninja nSearched = null;
		Clan aux1 = firstClan;
		Ninja aux2 = cSearched.getFirstNinja();
		while(aux1 != null) {
			if(cName.equalsIgnoreCase(aux1.getName())) {
				cSearched = aux1;
				aux1 = null;
				while(aux2 != null) {
					if(nName.equalsIgnoreCase(aux2.getName())) {
						nSearched = aux2;
						aux2 = null;
					} else {
						aux2 = aux2.getNextNinja(); 
					}
				}
			} else {
				aux1 = aux1.getNextClan();
			}
		}
		if(nSearched == null) {
			System.out.println("El personaje buscado no existe.");
			nSearched = null;
		}
		return nSearched;
	}
}
