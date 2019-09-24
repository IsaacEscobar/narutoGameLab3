package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.NotSerializableException;
import java.util.Scanner;

import model.*;

public class Main {
	
	private Icesi icesi;
	private Scanner reader;
		
	public static void main(String[] args) throws IOException, ClassNotFoundException, NotSerializableException {
		Main m = new Main();
		try {
			m.Menu();
		} catch(IOException msg) {
			System.out.println(msg.getMessage());
		}
	}
	
	public Main() throws FileNotFoundException, ClassNotFoundException, IOException {
		reader = new Scanner(System.in);
		init();
	}
	
	public void Menu() throws IOException {
		int chosenOption = 10;
		do {
			icesi.menuInterface();
			chosenOption = reader.nextInt();
			System.out.println(" ");
			switch(chosenOption) {
				case 1:
					icesi.add();
					break;
				case 2:
					icesi.delete();
					System.out.println();
					break;
				case 3:
					icesi.update();
					System.out.println();
					break;
				case 4:
					try {
						icesi.showInfo();
					} catch(NullPointerException m) {
						System.out.println("No hay ningun clan en el sistema.");
					}
					System.out.println(" ");
					break;
				case 0:
						icesi.serialize();
					break;
			}
			
		} while(chosenOption != 0);
	}
	
	public void init() throws FileNotFoundException, ClassNotFoundException, IOException, NotSerializableException{
		icesi = new Icesi();
		icesi.serialize();
	}
}
