package utils;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import entiteti.Klijent;
import entiteti.Kozmeticar;
import entiteti.STRUCNA_SPREMA;
import entiteti.TipTretmana;
import entiteti.UslugaTretman;
import izmene.zakaziTretman;
import kontroleri.Kontroler;
import kontroleri.KorisniciKontroler;
import kontroleri.TipTretmanaKontroler;
import kontroleri.TretmanKontroler;
import kontroleri.UslugaTretmanaKontroler;

class ZakazivanjeTretmanaTest {
	
	static UslugaTretmanaKontroler uslugaTretmanaKontroler;
	static KorisniciKontroler korisniciKontroler;
	static Kontroler kontroler;
	static TipTretmanaKontroler tipTretmanaKontroler;
	static TretmanKontroler tretmanKontroler;
	static zakaziTretman zakaziTretman;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		kontroler = new Kontroler("korisniciTest.txt","tretmaniTest.txt","uslugeTest.txt", "tipoviTest.txt","cenovnikTest.txt");
		tipTretmanaKontroler = kontroler.getTipKontroler();
		uslugaTretmanaKontroler = kontroler.getUslugeKontroler();
		korisniciKontroler = kontroler.getKorisniciKontroler();
		tretmanKontroler = kontroler.getTretmanKontroler();
		//kozmeticar
		tipTretmanaKontroler.dodajTip(new TipTretmana(1, "Masaza"));
		tipTretmanaKontroler.dodajTip(new TipTretmana(2, "Manikir"));
		ArrayList<TipTretmana> tipovi = new ArrayList<TipTretmana>();
		tipovi.add(tipTretmanaKontroler.pronadjiTipTretman(1));
		Kozmeticar kozmeticar1 = new Kozmeticar("Ilija", "Il", 01321233, "NS", "ilija", "ilija123", STRUCNA_SPREMA.SREDNJA, 5, 9000, 0, tipovi);
		korisniciKontroler.registrujKozmeticara(kozmeticar1);
		tipovi.add(tipTretmanaKontroler.pronadjiTipTretman(2));
		Kozmeticar kozmeticar2 = new Kozmeticar("Marija", "Marijanovic", 013345533, "NS", "marija", "marija123", STRUCNA_SPREMA.VISOKA, 10, 15000, 0, tipovi);
		korisniciKontroler.registrujKozmeticara(kozmeticar2);

		
		//klijent
		Klijent klijent = new Klijent("Marko", "Markovic", 44433322, "Zrenjanin", "marko", "marko123", false, 0.0);
		korisniciKontroler.registrujKlijenta(klijent);
		
		//usluge
		UslugaTretman uslugaTretman = new UslugaTretman(tipTretmanaKontroler.pronadjiTipTretman(1), "Ledja", 3000, 1, LocalTime.of(2, 0), false);
		uslugaTretmanaKontroler.dodajUslugu(uslugaTretman);
		
		kontroler.setPocetakRadnogVremena(LocalTime.of(8, 0));
		kontroler.setKrajRadnogVremena(LocalTime.of(20, 0));
		
		tretmanKontroler.dodajTretman(klijent, kozmeticar1, LocalDateTime.of(2023, 7, 10, 12, 0),uslugaTretman, 1);
		tretmanKontroler.dodajTretman(klijent, kozmeticar2, LocalDateTime.of(2023, 12, 12, 12, 0),uslugaTretman, 2);
		
		zakaziTretman = new zakaziTretman(new JFrame(), uslugaTretmanaKontroler, korisniciKontroler, tipTretmanaKontroler, kontroler, klijent, new JLabel());
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		File[] fajlovi = {
                new File("korisniciTest.txt"),
                new File("tretmaniTest.txt"),
                new File("uslugeTest.txt"),
                new File("tipoviTest.txt"),
                new File("cenovnikTest.txt")
        };

        for (File fajl : fajlovi) {
            fajl.delete();
        }
	}
	
	@Test
	void testNijeUnesenaUsluga() {
		boolean flag = zakaziTretman.provera_za_zakazivanje("", null, "2023-07-10", "8:50", kontroler, null, null,false);
		assertFalse(flag);
	}
	@Test
	void testNijeUnesenDatum() {
		boolean flag = zakaziTretman.provera_za_zakazivanje("potrebno_da_stoji", null, "", "8:50", kontroler, null, null,false);
		assertFalse(flag);
	}
	@Test
	void testNijeUnesenoVreme() {
		boolean flag = zakaziTretman.provera_za_zakazivanje("potrebno_da_stoji", null, "2023-07-10", "", kontroler, null, null,false);
		assertFalse(flag);
	}
	
	@Test
	void testNijeUnesenoProsloVreme() {
		boolean flag = zakaziTretman.provera_za_zakazivanje("potrebno_da_stoji", null, "2022-07-10", "8:50", kontroler, null, null,false);
		assertFalse(flag);
	}
	
	@Test
	void testUspesnoZakazivanje() {
		String uslugaTretmanIme = uslugaTretmanaKontroler.getUsluge().get(0).getImeTretmana();
		String kozmeticarIme = korisniciKontroler.pronadjiKorisnika("ilija").getKorisnickoIme();
		UslugaTretman uslugaTretman = uslugaTretmanaKontroler.getUsluge().get(0);
		Kozmeticar kozmeticar = (Kozmeticar) korisniciKontroler.pronadjiKorisnika("ilija");
		boolean flag = zakaziTretman.provera_za_zakazivanje(uslugaTretmanIme, kozmeticarIme, "2023-07-10", "8:50", kontroler, uslugaTretman, kozmeticar,false);
		assertFalse(flag);
	}
	
	
	@Test
	void testNesupesnoVremeRadnoVreme() {
		String uslugaTretmanIme = uslugaTretmanaKontroler.getUsluge().get(0).getImeTretmana();
		String kozmeticarIme = korisniciKontroler.pronadjiKorisnika("ilija").getKorisnickoIme();
		UslugaTretman uslugaTretman = uslugaTretmanaKontroler.getUsluge().get(0);
		Kozmeticar kozmeticar = (Kozmeticar) korisniciKontroler.pronadjiKorisnika("ilija");
		boolean flag = zakaziTretman.provera_za_zakazivanje(uslugaTretmanIme, kozmeticarIme, "2023-07-10", "7:50", kontroler, uslugaTretman, kozmeticar,false);
		assertFalse(flag);
	}
	
	@Test
	void testNesuspesnoKozmeticarZauzet() {
		String uslugaTretmanIme = uslugaTretmanaKontroler.getUsluge().get(0).getImeTretmana();
		String kozmeticarIme = korisniciKontroler.pronadjiKorisnika("ilija").getKorisnickoIme();
		UslugaTretman uslugaTretman = uslugaTretmanaKontroler.getUsluge().get(0);
		Kozmeticar kozmeticar = (Kozmeticar) korisniciKontroler.pronadjiKorisnika("ilija");
		boolean flag = zakaziTretman.provera_za_zakazivanje(uslugaTretmanIme, kozmeticarIme, "2023-07-10", "11:50", kontroler, uslugaTretman, kozmeticar,false);
		assertFalse(flag);
	}
	
	@Test
	void testNeuspesnoKozmeticarNijeObucen() {
		String uslugaTretmanIme = uslugaTretmanaKontroler.getUsluge().get(0).getImeTretmana();
		String kozmeticarIme = korisniciKontroler.pronadjiKorisnika("ilija").getKorisnickoIme();
		UslugaTretman uslugaTretman = uslugaTretmanaKontroler.getUsluge().get(0);
		uslugaTretman.setTipTretmana(tipTretmanaKontroler.getTipovi().get(1));
		Kozmeticar kozmeticar = (Kozmeticar) korisniciKontroler.pronadjiKorisnika("ilija");
		boolean flag = zakaziTretman.provera_za_zakazivanje(uslugaTretmanIme, kozmeticarIme, "2023-07-10", "7:50", kontroler, uslugaTretman, kozmeticar,false);
		assertFalse(flag);
	}

}
