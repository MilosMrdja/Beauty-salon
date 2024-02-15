package utils;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import entiteti.TipTretmana;
import kontroleri.TipTretmanaKontroler;
	

class TipTretmanaKontrolerTest {
	static TipTretmanaKontroler tipTretmanaKontroler;


	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		tipTretmanaKontroler = new TipTretmanaKontroler("tipTretmanaTest.txt");

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		File[] fajlovi = {
                new File("tipTretmanaTest.txt")
        };

        for (File fajl : fajlovi) {
            fajl.delete();
        }
	}
	@Test
	void testVratiRandom() {
		tipTretmanaKontroler.getTipovi().clear();
		tipTretmanaKontroler.dodajTip(new TipTretmana(1, "Masaza"));
		tipTretmanaKontroler.dodajTip(new TipTretmana(2, "Masaza1"));
		tipTretmanaKontroler.dodajTip(new TipTretmana(3, "Masaza2"));
		
		TipTretmana tipTretmana = tipTretmanaKontroler.vratiRandom();
		
		assertNotEquals(tipTretmana, null);
	}

	@Test
	void testTipTretmanaFile() {
		tipTretmanaKontroler.getTipovi().clear();
		tipTretmanaKontroler.dodajTip(new TipTretmana(1, "Masaza"));
		tipTretmanaKontroler.dodajTip(new TipTretmana(2, "Masaza1"));
		tipTretmanaKontroler.dodajTip(new TipTretmana(3, "Masaza2"));
		tipTretmanaKontroler.azurirajFile();
		
		TipTretmanaKontroler t = new TipTretmanaKontroler("tipTretmanaTest.txt");
		t.ucitajTipove();
		assertEquals(t.getTipovi().size(), 3);

	}

	@Test
	void testPronadjiTipTretman() {
		tipTretmanaKontroler.getTipovi().clear();
		tipTretmanaKontroler.dodajTip(new TipTretmana(1, "Masaza"));
		tipTretmanaKontroler.dodajTip(new TipTretmana(2, "Masaza1"));
		tipTretmanaKontroler.dodajTip(new TipTretmana(3, "Masaza2"));
		
		int id=2;
		TipTretmana tipTretmana = tipTretmanaKontroler.pronadjiTipTretman(id);
		assertNotEquals(tipTretmana, null);

		
	}

	@Test
	void testPronadjiTipPoImenu() {
		tipTretmanaKontroler.getTipovi().clear();
		tipTretmanaKontroler.dodajTip(new TipTretmana(1, "Masaza"));
		tipTretmanaKontroler.dodajTip(new TipTretmana(2, "Masaza1"));
		tipTretmanaKontroler.dodajTip(new TipTretmana(3, "Masaza2"));
		
		String imeString = "Masaza2";
		TipTretmana tipTretmana = tipTretmanaKontroler.pronadjiTipPoImenu(imeString);
		assertNotEquals(tipTretmana, null);
	}

	@Test
	void testDodajTip() {
		tipTretmanaKontroler.getTipovi().clear();
		tipTretmanaKontroler.dodajTip(new TipTretmana(1, "Masaza"));
		tipTretmanaKontroler.dodajTip(new TipTretmana(2, "Masaza1"));
		tipTretmanaKontroler.dodajTip(new TipTretmana(3, "Masaza2"));
		
		assertEquals(tipTretmanaKontroler.getTipovi().size(), 3);
	}
	
	@Test
	void testIzmenaTipTretmana() {
		tipTretmanaKontroler.getTipovi().clear();
		tipTretmanaKontroler.dodajTip(new TipTretmana(1, "Masaza"));
		tipTretmanaKontroler.dodajTip(new TipTretmana(2, "Masaza1"));
		tipTretmanaKontroler.dodajTip(new TipTretmana(3, "Masaza2"));
		
		String izmena = "MASAZA";
		TipTretmana tipTretmana = tipTretmanaKontroler.editTipTretmana(1,izmena);
		if(tipTretmana!=null) {
			if(tipTretmana.getImeTip().compareTo(izmena)==0) {
				assertTrue(true);
			}
			else {
				assertTrue(false);
			}
		}
	}
	
	@Test
	void testIzbrisiTip() {
		tipTretmanaKontroler.getTipovi().clear();
		tipTretmanaKontroler.dodajTip(new TipTretmana(1, "Masaza"));
		tipTretmanaKontroler.dodajTip(new TipTretmana(2, "Masaza1"));
		tipTretmanaKontroler.dodajTip(new TipTretmana(3, "Masaza2"));
		
		tipTretmanaKontroler.izbrisiTip(tipTretmanaKontroler.pronadjiTipTretman(2));
		assertEquals(tipTretmanaKontroler.getTipovi().size(), 2);
	}

}
