package utils;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import entiteti.Klijent;
import entiteti.Korisnik;
import entiteti.Kozmeticar;
import entiteti.Menadzer;
import entiteti.Recepcioner;
import entiteti.STRUCNA_SPREMA;
import entiteti.TipTretmana;
import entiteti.Zaposlen;
import kontroleri.KorisniciKontroler;
import kontroleri.TipTretmanaKontroler;

class KorisniciKontrolerTest {

	static TipTretmanaKontroler tipTretmanaKontroler;
	static KorisniciKontroler korisniciKontroler;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		tipTretmanaKontroler = new TipTretmanaKontroler("tipTretmanaTest.txt");
		
		korisniciKontroler = new KorisniciKontroler("korisniciTest.txt",tipTretmanaKontroler);
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		File[] fajlovi = {
                new File("korisniciTest.txt"),
                new File("tipTretmanaTest.txt")
        };

        for (File fajl : fajlovi) {
            fajl.delete();
        }
	}


	@Test
	void testFajl() {
		korisniciKontroler.getKorisnici().clear();
		
		korisniciKontroler.registrujKlijenta(new Klijent("Karlo", "Karlic", 061123123, "Srbobran", "klijent", "klijent123", false, (double) 0));
		korisniciKontroler.registrujMenadzera(new Menadzer("Marko", "Markovic", 061123123, "Srbobran", "menadzer", "menadzer123", STRUCNA_SPREMA.VISOKA, 10, 10000, 0));
		tipTretmanaKontroler.dodajTip(new TipTretmana(1, "Masaza"));
		ArrayList<TipTretmana> tipovi = new ArrayList<TipTretmana>();
		tipovi.add(tipTretmanaKontroler.pronadjiTipTretman(1));
		korisniciKontroler.registrujKozmeticara(new Kozmeticar("Ilija", "Il", 01321233, "NS", "kozmeticar", "kozmeticar123", STRUCNA_SPREMA.SREDNJA, 5, 9000, 0, tipovi));
		korisniciKontroler.registrujRecepcionera(new Recepcioner("Toma", "Tomic", 021232343, "SU", "recepcioner", "recepcioner123", STRUCNA_SPREMA.SREDNJA, 12, 8000, 0));

		korisniciKontroler.azurirajFile();
		
		TipTretmanaKontroler tipTretmanaKontroler = new TipTretmanaKontroler("tipTretmanaTest.txt");
		KorisniciKontroler korisniciKontroler2 = new KorisniciKontroler("korisniciTest.txt", tipTretmanaKontroler);
		korisniciKontroler2.ucitajKorisnike();
		
		assertEquals(korisniciKontroler2.getKorisnici().size(),4);
	}

	
	@Test
	void testIzmenaZaposleni() {
		korisniciKontroler.getKorisnici().clear();
		korisniciKontroler.registrujMenadzera(new Menadzer("Marko", "Markovic", 061123123, "Srbobran", "menadzer", "menadzer123", STRUCNA_SPREMA.VISOKA, 10, 10000, 0));
		Zaposlen zaposlenTemp = (Zaposlen) korisniciKontroler.pronadjiKorisnika("menadzer");
		String ime = "MARKO";
		String prezime = "MARKOVIC";
		int broj = 061432423;
		String adresa = "SRB";
		String passString = "123";
		STRUCNA_SPREMA sprema = STRUCNA_SPREMA.SREDNJA;
		double spremaBroj =0.0;
		if(STRUCNA_SPREMA.NISKA.equals(sprema)) {
			spremaBroj = 0.2;
		}
		else if(STRUCNA_SPREMA.SREDNJE_NISKA.equals(sprema)) {
			spremaBroj = 0.4;
		}
		else if(STRUCNA_SPREMA.SREDNJA.equals(sprema)) {
			spremaBroj = 0.6;
		}
		else if(STRUCNA_SPREMA.SREDNJE_VISOKA.equals(sprema)) {
			spremaBroj = 0.8;
		}
		else {
			spremaBroj = 1.0;
		}
		int s = 11;
		int o = 110000;
		zaposlenTemp = korisniciKontroler.editZaposlen(ime, prezime, broj, adresa, "menadzer", passString, sprema, s, o);
		if(zaposlenTemp != null) {
			if(ime.compareTo(zaposlenTemp.getIme())==0 && zaposlenTemp.getPrezime().compareTo(prezime)==0 &&zaposlenTemp.getAdresa().compareTo(adresa)==0 && zaposlenTemp.getTelefon() == broj && zaposlenTemp.getLozinka().compareTo(passString)==0 && zaposlenTemp.getStrucnaSprema()==spremaBroj && zaposlenTemp.getRadniStaz() == s && zaposlenTemp.getOsnovaPlate() == o) {
				assertTrue(true);
			}
			else {
				assertTrue(false);
			}
		}

	}
	
	@Test
	void testIzmenaKlijent() {
		korisniciKontroler.getKorisnici().clear();
		korisniciKontroler.registrujKlijenta(new Klijent("Marko", "Markovic", 061123123, "Srbobran", "klijent", "klijent123",false,0.0));
		Klijent klijent = (Klijent) korisniciKontroler.pronadjiKorisnika("klijent");
		String ime = "MARKO";
		String prezime = "MARKOVIC";
		int broj = 061432423;
		String adresa = "SRB";
		String passString = "123";
		
		klijent = korisniciKontroler.editKlijent(ime, prezime, broj, adresa, "klijent", passString);
		if(klijent != null) {
			if(ime.compareTo(klijent.getIme())==0 && klijent.getPrezime().compareTo(prezime)==0 &&klijent.getAdresa().compareTo(adresa)==0 && klijent.getTelefon() == broj && klijent.getLozinka().compareTo(passString)==0) {
				assertTrue(true);
			}
			else {
				assertTrue(false);
			}
		}
	}

	@Test
	void testGetKlijenti() {
		korisniciKontroler.getKorisnici().clear();
		korisniciKontroler.registrujKlijenta(new Klijent("Karlo", "Karlic", 061123123, "Srbobran", "klijent", "klijent123", false, (double) 0));
		ArrayList<Klijent> klijentiArrayList = korisniciKontroler.getKlijenti();
		assertEquals(klijentiArrayList.size(),1);
	}

	@Test
	void testGetRecepcioner() {
		korisniciKontroler.getKorisnici().clear();
		korisniciKontroler.registrujRecepcionera(new Recepcioner("Toma", "Tomic", 021232343, "SU", "recepcioner", "recepcioner123", STRUCNA_SPREMA.SREDNJA, 12, 8000, 0));
		ArrayList<Recepcioner> recepcioners = korisniciKontroler.getRecepcioner();
		assertEquals(recepcioners.size(), 1);
	}

	@Test
	void testGetMenadzeri() {
		korisniciKontroler.getKorisnici().clear();
		korisniciKontroler.registrujMenadzera(new Menadzer("Marko", "Markovic", 061123123, "Srbobran", "menadzer", "menadzer123", STRUCNA_SPREMA.VISOKA, 10, 10000, 0));
		ArrayList<Menadzer> menadzers = korisniciKontroler.getMenadzeri();
		assertEquals(menadzers.size(), 1);
	}

	@Test
	void testGetKozmeticari() {
		korisniciKontroler.getKorisnici().clear();
		tipTretmanaKontroler.dodajTip(new TipTretmana(1, "Masaza"));
		ArrayList<TipTretmana> tipovi = new ArrayList<TipTretmana>();
		tipovi.add(tipTretmanaKontroler.pronadjiTipTretman(1));
		korisniciKontroler.registrujKozmeticara(new Kozmeticar("Ilija", "Il", 01321233, "NS", "kozmeticar", "kozmeticar123", STRUCNA_SPREMA.SREDNJA, 5, 9000, 0, tipovi));
		ArrayList<Kozmeticar> kozmeticars = korisniciKontroler.getKozmeticari();
		assertEquals(kozmeticars.size(), 1);
	}

	@Test
	void testRegistrujKlijenta() {
		korisniciKontroler.registrujKlijenta(new Klijent("Karlo", "Karlic", 061123123, "Srbobran", "klijent", "klijent123", false, (double) 0));
		Klijent klijent = (Klijent) korisniciKontroler.pronadjiKorisnika("klijent");
		assertTrue(klijent != null);
	}

	@Test
	void testRegistrujMenadzera() {
		korisniciKontroler.registrujMenadzera(new Menadzer("Marko", "Markovic", 061123123, "Srbobran", "menadzer", "menadzer123", STRUCNA_SPREMA.VISOKA, 10, 10000, 0));
		Menadzer menadzer = (Menadzer) korisniciKontroler.pronadjiKorisnika("menadzer");
		assertTrue(menadzer != null);
	}

	@Test
	void testRegistrujKozmeticara() {
		tipTretmanaKontroler.dodajTip(new TipTretmana(1, "Masaza"));
		ArrayList<TipTretmana> tipovi = new ArrayList<TipTretmana>();
		tipovi.add(tipTretmanaKontroler.pronadjiTipTretman(1));
		korisniciKontroler.registrujKozmeticara(new Kozmeticar("Ilija", "Il", 01321233, "NS", "kozmeticar", "kozmeticar123", STRUCNA_SPREMA.SREDNJA, 5, 9000, 0, tipovi));
		Kozmeticar kozmeticar = (Kozmeticar) korisniciKontroler.pronadjiKorisnika("kozmeticar");
		assertTrue(kozmeticar != null);
	}

	@Test
	void testRegistrujRecepcionera() {
		korisniciKontroler.registrujRecepcionera(new Recepcioner("Toma", "Tomic", 021232343, "SU", "recepcioner", "recepcioner123", STRUCNA_SPREMA.SREDNJA, 12, 8000, 0));
		Recepcioner recepcioner = (Recepcioner) korisniciKontroler.pronadjiKorisnika("recepcioner");
		assertTrue(recepcioner!=null);
	}

	@Test
	void testIzbrisiKorisnika() {
		int broj = korisniciKontroler.getKorisnici().size();
		
		if(broj == 0) {
			korisniciKontroler.registrujKlijenta(new Klijent("Karlo", "Karlic", 061123123, "Srbobran", "klijent", "klijent123", false, (double) 0));

		}
		korisniciKontroler.izbrisiKorisnika(korisniciKontroler.getKorisnici().get(0));
		assertEquals(korisniciKontroler.getKorisnici().size(), broj-1);
		

	}

	@Test
	void testEditObucenostZaKozmeticara() {
		TipTretmana tipTretmana = new TipTretmana(tipTretmanaKontroler.postaviIdTip(), "Masiranje");
		ArrayList<Kozmeticar> kozmeticars = korisniciKontroler.getKozmeticari();
		
		if(kozmeticars == null) {
			tipTretmanaKontroler.dodajTip(new TipTretmana(1, "Masaza"));
			ArrayList<TipTretmana> tipovi = new ArrayList<TipTretmana>();
			tipovi.add(tipTretmanaKontroler.pronadjiTipTretman(1));
			korisniciKontroler.registrujKozmeticara(new Kozmeticar("Ilija", "Il", 01321233, "NS", "kozmeticar", "kozmeticar123", STRUCNA_SPREMA.SREDNJA, 5, 9000, 0, tipovi));
			
		}
		
		Kozmeticar kozmeticar = kozmeticars.get(0);
		int check = kozmeticar.getObucenost().size();
		korisniciKontroler.editObucenostZaKozmeticara(kozmeticar, tipTretmana);
		assertEquals(kozmeticar.getObucenost().size(),check+1 );
		
	}
	
	@Test
	void uspesanLogin() {
		korisniciKontroler.getKorisnici().clear();
		String korimeString = "klijent";
		String passString = "klijent123";
		korisniciKontroler.registrujKlijenta(new Klijent("Karlo", "Karlic", 061123123, "Srbobran", "klijent", "klijent123", false, (double) 0));
		Korisnik korisnik = korisniciKontroler.pronadjiKorisnika(korimeString);
		if(korisnik != null) {
			if(korisnik.getLozinka().compareTo(passString)==0) {
				assertTrue(true);
			}
			else {
				assertTrue(false);
			}
		}
		else {
			assertTrue(false);
		}
	}
	
	@Test
	void neuspesanLoginKorisnickoIme() {
		korisniciKontroler.getKorisnici().clear();
		String korimeString = "klijent";
		Korisnik korisnik = korisniciKontroler.pronadjiKorisnika(korimeString);
		if(korisnik == null) {
			assertTrue(true);
		}
		else {
			assertTrue(false);
		}
	}

	@Test
	void neuspesanLoginLozinka() {
		korisniciKontroler.getKorisnici().clear();
		String korimeString = "klijent";
		String passString = "klijent";
		korisniciKontroler.registrujKlijenta(new Klijent("Karlo", "Karlic", 061123123, "Srbobran", "klijent", "klijent123", false, (double) 0));
		Korisnik korisnik = korisniciKontroler.pronadjiKorisnika(korimeString);
		if(korisnik != null) {
			if(korisnik.getLozinka().compareTo(passString)==0) {
				assertTrue(false);
			}
			else {
				assertTrue(true);
			}
		}
		else {
			assertTrue(false);
		}
	}

}
