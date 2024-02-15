package utils;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.time.LocalTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import entiteti.TipTretmana;
import entiteti.UslugaTretman;
import kontroleri.TipTretmanaKontroler;
import kontroleri.UslugaTretmanaKontroler;

class UslugaTretmanaKontrolerTest {

	static UslugaTretmanaKontroler uslugaTretmanaKontroler;
	static TipTretmanaKontroler tipTretmanaKontroler;

	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		tipTretmanaKontroler = new TipTretmanaKontroler("tipTretmanaTest.txt");

		uslugaTretmanaKontroler = new UslugaTretmanaKontroler("uslugaTretmanaTest.txt", tipTretmanaKontroler);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		File[] fajlovi = {
                new File("uslugaTretmanaTest.txt"),
                new File("tipTretmanaTest.txt")
        };

        for (File fajl : fajlovi) {
            fajl.delete();
        }
	}


	@Test
	void testUslugaTretmanaFajl() {
		uslugaTretmanaKontroler.getUsluge().clear();
		uslugaTretmanaKontroler.dodajUslugu(new UslugaTretman(new TipTretmana(1, "masaza1"), "ledja", 10000, 23123,LocalTime.of(1, 0), false));
		uslugaTretmanaKontroler.dodajUslugu(new UslugaTretman(new TipTretmana(2, "masaza2"), "ledja2", 10000, 123123,LocalTime.of(1, 0), false));
		uslugaTretmanaKontroler.azurirajFile();
		
		UslugaTretmanaKontroler temp = new UslugaTretmanaKontroler("uslugaTretmanaTest.txt", tipTretmanaKontroler);
		temp.ucitajUslugeTretmana();
		
		assertEquals(temp.getUsluge().size(), 2);
	}



	@Test
	void testPronadjiUsluguTretmana() {
		uslugaTretmanaKontroler.getUsluge().clear();
		uslugaTretmanaKontroler.dodajUslugu(new UslugaTretman(new TipTretmana(1, "masaza1"), "ledja", 10000, 23123,LocalTime.of(1, 0), false));
		uslugaTretmanaKontroler.dodajUslugu(new UslugaTretman(new TipTretmana(2, "masaza2"), "ledja2", 10000, 123123,LocalTime.of(1, 0), false));
		
		UslugaTretman uslugaTretman = uslugaTretmanaKontroler.pronadjiUsluguTretmana(123123);
		
		assertNotEquals(uslugaTretman, null);
	}

	@Test
	void testVratiRandom() {
		uslugaTretmanaKontroler.getUsluge().clear();
		uslugaTretmanaKontroler.dodajUslugu(new UslugaTretman(new TipTretmana(1, "masaza1"), "ledja", 10000, 23123,LocalTime.of(1, 0), false));
		uslugaTretmanaKontroler.dodajUslugu(new UslugaTretman(new TipTretmana(2, "masaza2"), "ledja2", 10000, 123123,LocalTime.of(1, 0), false));
		
		UslugaTretman uslugaTretman = uslugaTretmanaKontroler.vratiRandom();
		
		assertNotEquals(uslugaTretman, null);
	}

	@Test
	void testDodajUslugu() {
		uslugaTretmanaKontroler.getUsluge().clear();
		uslugaTretmanaKontroler.dodajUslugu(new UslugaTretman(new TipTretmana(1, "masaza1"), "ledja", 10000, 23123,LocalTime.of(1, 0), false));
		uslugaTretmanaKontroler.dodajUslugu(new UslugaTretman(new TipTretmana(2, "masaza2"), "ledja2", 10000, 123123,LocalTime.of(1, 0), false));
		
		assertEquals(uslugaTretmanaKontroler.getUsluge().size(), 2);
	}

	@Test
	void testIzbrisiUsluguTretmana() {
		uslugaTretmanaKontroler.getUsluge().clear();
		uslugaTretmanaKontroler.dodajUslugu(new UslugaTretman(new TipTretmana(1, "masaza1"), "ledja", 10000, 23123,LocalTime.of(1, 0), false));
		uslugaTretmanaKontroler.dodajUslugu(new UslugaTretman(new TipTretmana(2, "masaza2"), "ledja2", 10000, 123123,LocalTime.of(1, 0), false));
		
		uslugaTretmanaKontroler.izbrisiUsluguTretmana(uslugaTretmanaKontroler.getUsluge().get(0));
		assertEquals(uslugaTretmanaKontroler.getNeObrisaneUsluge().size(), 1);
	}

	@Test
	void testIzmenaUslugeTretmana() {
		uslugaTretmanaKontroler.getUsluge().clear();
		uslugaTretmanaKontroler.dodajUslugu(new UslugaTretman(new TipTretmana(1, "masaza1"), "ledja", 10000, 23123,LocalTime.of(1, 0), false));
		uslugaTretmanaKontroler.dodajUslugu(new UslugaTretman(new TipTretmana(2, "masaza2"), "ledja2", 10000, 123123,LocalTime.of(1, 0), false));
		
		String imeString = "MASAZA LEDJA";
		int cena = 12000;
		UslugaTretman uslugaTretman = uslugaTretmanaKontroler.pronadjiUsluguTretmana(1);
		if(uslugaTretman != null) {
			if(uslugaTretman.getImeTretmana().compareTo(imeString)==0 && uslugaTretman.getCena()==cena) {
				assertTrue(true);
			}
			else {
				assertTrue(false);
			}
		}
	}
	

}
