package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Logger;
import java.util.logging.Level;

import exceptions.NotFound;
import exceptions.SameName;

public class Icesi {
	
	private Clan firstClan;
	private Scanner reader;
	
	public Icesi() throws FileNotFoundException, ClassNotFoundException, IOException {
		reader = new Scanner(System.in);
		serialize();
//		Clan uzumaki = new Clan("Uzumaki");
//		Ninja naruto = new Ninja("Naruto", "Activa", "20/09/19", 899);
//		Jutsu kage_bunshin = new Jutsu("Kage-Bunshin", 0.69);
//		setFirstClan(uzumaki);
//		uzumaki.setFirstNinja(naruto);
//		uzumaki.getFirstNinja().setFirstJutsu(kage_bunshin);
//		Clan uchiha = new Clan("Uchiha");
//		Ninja sasuke = new Ninja("Sasuke", "Introvertida", "20/09/19", 900);
//		Jutsu katon = new Jutsu("Katon", 0.80);
//		try {
//			addNewClan(uchiha);
//		} catch(SameName m) {
//			System.out.println(m.getMessage());
//		}
//		try {
//			addNinjaToAClan(sasuke, uchiha);
//		} catch(SameName m) {
//			System.out.println(m.getMessage());
//		}
//		try {
//			sasuke.addNewJutsu(katon);
//		} catch(SameName m) {
//			System.out.println(m.getMessage());
//		}
//		Ninja karin = new Ninja("Karin", "Pasiva", "23/09/19", 290);
//		Jutsu nose = new Jutsu("Clones", 0.1);
////		try {
////			addNinjaToAClan(karin, uzumaki);
////		} catch(SameName m) {
////			System.out.println(m.getMessage());
////		}
////		try {
////			uzumaki.addNewJutsuToANinja(karin, nose);
////		} catch(SameName m) {
////			System.out.println(m.getMessage());
////		}
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

	public void deleteAClan(Clan c) {
		Clan aux = firstClan;
		if(aux.compareTo(c) == 0) {
			setFirstClan(aux.getNextClan());
		} else {
			while(aux != null) {
				if(aux.getNextClan().compareTo(c) == 0) {
					aux.setNextClan(null);
					aux = null;
				} else {
					aux = aux.getNextClan();
				}
			}
		}
	}

	public void deleteANinjaFromAClan(Clan c, Ninja n) {
		Clan aux1 = firstClan;
		Ninja aux2 = aux1.getFirstNinja();
		if(aux1.getFirstNinja() == null) {
			setFirstClan(aux1.getNextClan());
		} else {
			while(aux1 != null) {
				if(aux2.compareTo(n) == 0) {
					aux1.setFirstNinja(aux2.getNextNinja());
					aux1 = null;
				} else {
					while(aux2 != null) {
						if(aux2.getNextNinja().compareTo(n) == 0) {
							aux2.setNextNinja(n);
							aux2.getNextNinja().setPreviousNinja(aux2);
							aux2 = null;
						} else {
							aux2 = aux2.getNextNinja();
						}
					}
				}
			}
		}
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
		System.out.println("0- Guardar y salir del sistema.");
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
					try {
						Clan aux = searchClan(cName);
						if(aux != null) {
							addNinja(aux);
							System.out.println(" ");
						} else {
							System.out.println(" ");
						}
					} catch(NotFound m) {
						System.out.println(m.getMessage());
					}
					break;
				case 3:
					System.out.println("Digite el nombre del clan al que pertenece el personaje: ");
					reader.nextLine();
					String cName2 = reader.nextLine();
					try {
						Clan aux1 = searchClan(cName2);
						if(aux1 != null) {
							System.out.println("Digite el nombre del personaje al cual desea añadir la tecnica: ");
							//reader.nextLine();
							String nName = reader.nextLine();
							try {
								Ninja aux2 = searchNinjaInAClan(cName2, nName);
								if(aux2 != null) {
									addJutsu(aux1, aux2);
									System.out.println(" ");
								} else {
									System.out.println(" ");
								}
							}catch(NotFound m) {
								System.out.println(m.getMessage());
							} 
						}
					} catch(NotFound m) {
					System.out.println(m.getMessage());
					}
					break;
			} 
		}while(option != 4);
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
					try {
						deleteClan();
					} catch(NotFound m) {
						System.out.println(m.getMessage());
					}
					System.out.println(" ");
					break;
				case 2:
					try {
						deleteNinja();
					} catch(NotFound m) {
						System.out.println(m.getMessage());
					}
					System.out.println(" ");
					break;
				case 3:
					//deleteJutsu();
					System.out.println("NO IMPLEMENTADO :/");
					break;
			}
		} while(option != 4);
	}
	
	public void update() {
		System.out.println("NO IMPLEMENTADO :/");
	}
	
	public void showInfo() throws NullPointerException {
		Clan aux = firstClan;
		if(firstClan.getFirstNinja() == null) {
			while(aux != null) {
				Ninja auxN = aux.getFirstNinja();
				if(auxN != null) {
					firstClan = aux;
					aux = null;
				} else {
					aux = aux.getNextClan();
				}
			}
		} else {
			if(firstClan != null) {
				System.out.println(toString());
			} else {
				throw new NullPointerException();
			}
		}
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
	
	public void deleteClan() throws NotFound {
		System.out.println(" ");
		System.out.println("Digite el nombre del clan que desea eliminar: ");
		reader.nextLine();
		String cName = reader.nextLine();
		try {
			Clan c = searchClan(cName);
			if(c != null) {
				deleteAClan(c);
			} else {
			System.out.println(" ");	
			}
		} catch(NotFound m) {
			System.out.println(m.getMessage());
		}
	}
	
	public void deleteNinja() throws NotFound {
		System.out.println(" ");
		System.out.println("Digite el nombre del clan al cual pertenece el personaje.");
		reader.nextLine();
		String cName = reader.nextLine();
		try {
			Clan c = searchClan(cName);
			if(c != null) {
				System.out.println(" ");
				System.out.println("Digite el nombre del ninja que desea eliminar.");
				String nName = reader.nextLine();
				try {
					Ninja n = searchNinjaInAClan(cName, nName);
					deleteANinjaFromAClan(c, n);
				} catch(NotFound m) {
					System.out.println(m.getMessage());
				}
			}
		} catch(NotFound m) {
			System.out.println(m.getMessage());
		}
	}
	
	public void deleteJutsu() {
		
	}
	
	@Override
	public String toString() {
		String msg = "";
		Clan auxC = getFirstClan();
		Ninja auxN = auxC.getFirstNinja();
		Jutsu auxJ = auxN.getFirstJutsu();
//		if(auxC == null) {
//			auxC = auxC.getNextClan();
//		} else {	
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
//		}
		return msg;
	}
	
	public Clan searchClan(String cName) throws NotFound {
		Clan searched = null;
		Clan aux = firstClan;
		if(firstClan.getFirstNinja() == null) {
			while(aux != null) {
				Ninja auxN = aux.getFirstNinja();
				if(auxN != null) {
					firstClan = aux;
					aux = null;
				} else {
					aux = aux.getNextClan();
				}
			}
		}else {
			while(aux != null) {
				if(cName.equalsIgnoreCase(aux.getName())) {
					searched = aux;
					aux = null;
				} else {
					aux = aux.getNextClan();
				}
			}
			if(searched == null) {
				throw new NotFound("El clan buscado no existe.");
			}
		}
		return searched;
	}
	
	public Ninja searchNinjaInAClan(String cName, String nName) throws NotFound {
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
			throw new NotFound("El personaje buscado no existe.");
		}
		return nSearched;
	}

	public void serialize() throws FileNotFoundException, IOException {
		File sFile = new File("files/Datos");
		Vector save = new Vector();
		save.add(firstClan);
		try {
			ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(sFile));
			obj.writeObject(getFirstClan());
			obj.close();
		} catch(IOException m) {
		}
		try {
			ObjectInputStream obj2 = new ObjectInputStream(new FileInputStream(sFile));
			save = (Vector)obj2.readObject();
			obj2.close();
		} catch(ClassNotFoundException m2) {
			Logger.getLogger(Clan.class.getName()).log(Level.SEVERE, null, m2);
		} catch(IOException m3) {
		} catch(ArrayIndexOutOfBoundsException m4) {
			System.out.println(m4.getMessage());
		}
	}
}
