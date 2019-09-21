package ui;
import model.*;

public class Main {
	
	private Icesi icesi;
		
	public static void main(String[] args) {
		Main m = new Main();
	}
	
	public Main() {
		this.icesi = new Icesi();
		System.out.println("~~~~~~Bienvenido al sistema de Icesi~~~~~~");
		System.out.println("Por favor limitese de escoger entre las siguientes opciones: ");
	}
}
