package main;

import GUI.MainFrame;
import kontroleri.Kontroler;

public class Main {

		
	public static void main(String[] args) {
		Kontroler kozmetickiSalon = new Kontroler("./fajlovi/korisnici.txt","./fajlovi/tretmani.txt","./fajlovi/usluge.txt", "./fajlovi/tipovi.txt","./fajlovi/cenovnik.txt");
		kozmetickiSalon.ucitajSistem(); 
		kozmetickiSalon.getCenovnik().azurirajCenovnik();
		new MainFrame(kozmetickiSalon);
	
	}

}
