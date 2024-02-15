package utils;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import entiteti.Klijent;
import entiteti.Kozmeticar;
import entiteti.STANJE_TERETMANA;
import entiteti.STRUCNA_SPREMA;
import entiteti.TipTretmana;
import entiteti.Tretman;
import entiteti.UslugaTretman;
import kontroleri.KorisniciKontroler;
import kontroleri.TipTretmanaKontroler;
import kontroleri.TretmanKontroler;
import kontroleri.UslugaTretmanaKontroler;

class TretmanKontrolerTest {
	
	static TretmanKontroler tretmanKontroler;
	static KorisniciKontroler korisniciKontroler;
	static TipTretmanaKontroler tipTretmanaKontroler;
	static UslugaTretmanaKontroler uslugaTretmanaKontroler;



	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		tipTretmanaKontroler = new TipTretmanaKontroler("tipTretmanaTest.txt");
		korisniciKontroler = new KorisniciKontroler("korisniciTest.txt",tipTretmanaKontroler);
		uslugaTretmanaKontroler = new UslugaTretmanaKontroler("uslugaTretmanaTest.txt", tipTretmanaKontroler);
		tretmanKontroler = new TretmanKontroler("tretmanTest.txt", uslugaTretmanaKontroler, korisniciKontroler);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		File[] fajlovi = {
                new File("tipTretmanaTest.txt"),
                new File("korisniciTest.txt"),
                new File("uslugaTretmanaTest.txt"),
                new File("tretmanTest.txt")
                
        };

        for (File fajl : fajlovi) {
            fajl.delete(); // ili samo obrisi
        }
	}


	@Test
	void testTretmaniFajl() {
		tretmanKontroler.getTretmani().remove(0);
		korisniciKontroler.getKorisnici().remove(0);
		Klijent klijent = new Klijent("Karlo", "Karlic", 061123123, "Srbobran", "karlo1", "karlo_1_123", false, (double) 0);
		tipTretmanaKontroler.dodajTip(new TipTretmana(1, "Masaza"));
		ArrayList<TipTretmana> tipovi = new ArrayList<TipTretmana>();
		tipovi.add(tipTretmanaKontroler.pronadjiTipTretman(1));
		Kozmeticar kozmeticar = new Kozmeticar("Ilija", "Il", 01321233, "NS", "ilija", "ilija123", STRUCNA_SPREMA.SREDNJA, 5, 9000, 0, tipovi);
		UslugaTretman uslugaTretman = new UslugaTretman(tipTretmanaKontroler.pronadjiTipTretman(1),"Ledja", 1200, 2234, LocalTime.of(1, 0), false);
		uslugaTretmanaKontroler.dodajUslugu(uslugaTretman);
		LocalDate date = LocalDate.of(2024, 6, 30);
		LocalTime time = LocalTime.of(10, 30, 0);
		korisniciKontroler.registrujKlijenta(klijent);

		LocalDateTime vreme = LocalDateTime.of(date, time);
		tretmanKontroler.dodajTretman(klijent, kozmeticar, vreme,uslugaTretman, 2453);
		tretmanKontroler.azurirajFile();
		
		TretmanKontroler tempKontroler = new TretmanKontroler("tretmanTest.txt", uslugaTretmanaKontroler, korisniciKontroler);
		tempKontroler.ucitajTretmane();
		assertEquals(tempKontroler.getTretmani().size(), 2);
	}

	@Test
	void testPronadjiTretman() {
		
		Tretman tretman = tretmanKontroler.pronadjiTretman(2453);
		assertNotEquals(tretman, null);
	}

	@Test
	void testDodajTretman() {
		Klijent klijent = new Klijent("Mirko", "Mirkovic", 061123123, "Novi Sad", "mire", "mire123", false, (double) 0);
		tipTretmanaKontroler.dodajTip(new TipTretmana(2, "Manikir"));
		ArrayList<TipTretmana> tipovi = new ArrayList<TipTretmana>();
		tipovi.add(tipTretmanaKontroler.pronadjiTipTretman(2));
		Kozmeticar kozmeticar = new Kozmeticar("Marija", "Maric", 61862874, "Subotica", "marija", "marija123", STRUCNA_SPREMA.VISOKA, 5, 9000, 0, tipovi);
		UslugaTretman uslugaTretman = new UslugaTretman(tipTretmanaKontroler.pronadjiTipTretman(2),"Nokti", 1200, 2222, LocalTime.of(1, 30), false);
		uslugaTretmanaKontroler.dodajUslugu(uslugaTretman);
		LocalDate date = LocalDate.of(2024, 5, 30);
		LocalTime time = LocalTime.of(12, 0, 0);
		korisniciKontroler.registrujKlijenta(klijent);
		korisniciKontroler.registrujKozmeticara(kozmeticar);

		LocalDateTime vreme = LocalDateTime.of(date, time);
		tretmanKontroler.dodajTretman(klijent, kozmeticar, vreme,uslugaTretman, 3333);
		
		assertEquals(tretmanKontroler.getTretmani().size(), 2);
	}

	@Test
	void testOtkazaoKlijent() {
		if(tretmanKontroler.getTretmani().size()>0) {
			Tretman tretman = tretmanKontroler.getTretmani().get(0);
			double cena = tretman.getCena();
			tretmanKontroler.obradiTretman(tretman,STANJE_TERETMANA.OTKAZAO_KLIJENT);
			assertEquals(cena*0.1, tretman.getCena());
		}

	}
	@Test
	void testOtkazaoSalon() {
		if(tretmanKontroler.getTretmani().size()>0) {
			Tretman tretman = tretmanKontroler.getTretmani().get(0);
			tretman.setStanje(STANJE_TERETMANA.ZAKAZAN);
			tretmanKontroler.obradiTretman(tretman,STANJE_TERETMANA.OTKAZAO_SALON);
			assertEquals(0, tretman.getCena());
		}
	}
	@Test
	void testIzvrsen() {
		if(tretmanKontroler.getTretmani().size()>0) {
			Tretman tretman = tretmanKontroler.getTretmani().get(0);
			tretman.setStanje(STANJE_TERETMANA.ZAKAZAN);
			double cena = tretman.getCena();
			tretmanKontroler.obradiTretman(tretman,STANJE_TERETMANA.IZVRSEN);
			assertEquals(cena, tretman.getCena());
		}
	}
	@Test
	void testKlijentSeNijePojavio() {
		if(tretmanKontroler.getTretmani().size()>0) {
			Tretman tretman = tretmanKontroler.getTretmani().get(0);
			tretman.setStanje(STANJE_TERETMANA.ZAKAZAN);
			double cena = tretman.getCena();
			tretmanKontroler.obradiTretman(tretman,STANJE_TERETMANA.NIJE_SE_POJAVIO);
			assertEquals(cena, tretman.getCena());
		}

	}


	@Test
	void testVratiPrihode() {
		double prihod = 0.0;
		for(Tretman t:tretmanKontroler.getTretmani()) {
			prihod += t.getCena();
		}
		assertEquals(prihod, 1200);
	}

	@Test
	void testIzbrisiTretman() {
		if(tretmanKontroler.getTretmani().size()>0) {
			tretmanKontroler.izbrisiTretman(tretmanKontroler.getTretmani().get(0));
			assertEquals(tretmanKontroler.getTretmani().size(), 1);
		}
		
	}


	@Test
	void testPrikaziTretmaneZaKozmeticara() {
		
		ArrayList<Kozmeticar> kozmeticari = korisniciKontroler.getKozmeticari();
		
		if(kozmeticari.size()>0) {
			Kozmeticar kozmeticar = kozmeticari.get(0);
			ArrayList<Tretman> tretmani = tretmanKontroler.prikaziTretmaneZaKozmeticara(kozmeticar);

			assertEquals(tretmani.size(), 1);
		}
	}

	@Test
	void testIzmenaTretmana() {
		ArrayList<TipTretmana> tipovi1 = new ArrayList<TipTretmana>();
		tipovi1.add(new TipTretmana(123, "Spa"));
		Kozmeticar kozmeticar1 = new Kozmeticar("Andrea", "Andric", 61678556, "Beograd", "andrea", "andrea123", STRUCNA_SPREMA.SREDNJE_VISOKA, 20, 15000, 0, tipovi1);
		LocalDate date1 = LocalDate.of(2023, 10, 20);
		LocalTime time1 = LocalTime.of(16, 0, 0);

		LocalDateTime dan1 = LocalDateTime.of(date1, time1);
		
		Klijent klijent = new Klijent("Karlo", "Karlic", 061123123, "Srbobran", "karlo", "karlo123", false, (double) 0);
		tipTretmanaKontroler.dodajTip(new TipTretmana(1, "Masaza"));
		ArrayList<TipTretmana> tipovi = new ArrayList<TipTretmana>();
		tipovi.add(tipTretmanaKontroler.pronadjiTipTretman(1));
		Kozmeticar kozmeticar = new Kozmeticar("Ilija", "Il", 01321233, "NS", "kozmeticar", "kozmeticar123", STRUCNA_SPREMA.SREDNJA, 5, 9000, 0, tipovi);
		UslugaTretman uslugaTretman = new UslugaTretman(tipTretmanaKontroler.pronadjiTipTretman(1),"Ledja", 1200, 2234, LocalTime.of(1, 0), false);
		uslugaTretmanaKontroler.dodajUslugu(uslugaTretman);
		LocalDate date = LocalDate.of(2024, 6, 30);
		LocalTime time = LocalTime.of(10, 30, 0);
		korisniciKontroler.registrujKozmeticara(kozmeticar);
		korisniciKontroler.registrujKlijenta(klijent);

		LocalDateTime dan = LocalDateTime.of(date, time);
		tretmanKontroler.dodajTretman(klijent, kozmeticar1, dan1,uslugaTretman, 2453);
		Tretman tretman = tretmanKontroler.getTretmani().get(0);
		tretmanKontroler.izmeniTretman(dan, kozmeticar, tretman);
		if(tretman.getKozmeticar().equals(kozmeticar) && tretman.getVreme().equals(dan)) {
			assertTrue(true);
		}
		else {
			assertTrue(false);
		}
	}
	
	@Test
	void testVidiTretmaneZaKlijenta() {
		ArrayList<Klijent> klijents = korisniciKontroler.getKlijenti();
		
		if(klijents.size()>0) {
			Klijent klijent = klijents.get(klijents.size()-1);
			ArrayList<Tretman> tretmani = tretmanKontroler.vidiTretmaneZaKlijenta(klijent);
			
			
			assertEquals(tretmani.size(), 1);
		}
	}

}
