package ui;

import java.util.Scanner;

import model.*;

public class Main {
	
	private Icesi icesi;
	private Scanner reader;
		
	public static void main(String[] args) {
		Main m = new Main();
		m.Menu();
	}
	
	public Main() {
		reader = new Scanner(System.in);
		init();
	}
	
	public void Menu() {
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
					icesi.showInfo();
					System.out.println();
					break;
			}
			
		} while(chosenOption != 0);
	}
	
	public void init() {
		icesi = new Icesi();
	}
}
