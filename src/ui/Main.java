package ui;
import model.*;

public class Main {
	
	private Menu menu;
	
	public static void main(String[] args) {
		Main m = new Main();
	}
	
	public Main() {
		menu.menu();
	}
	
	public Menu getMenu() {
		return menu;
	}

}
