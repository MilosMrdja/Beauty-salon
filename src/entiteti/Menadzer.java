package entiteti;


public class Menadzer extends Zaposlen{

	
	
	public Menadzer(String ime, String prezime, int telefon, String adresa, String korisnickoIme, String lozinka,
			STRUCNA_SPREMA strucnaSprema, int radniStaz, double osnova, double bonus) {
		super(ime, prezime, telefon, adresa, korisnickoIme, lozinka, strucnaSprema, radniStaz, osnova, bonus);
		
	}

	@Override 
	public String toString() {
		
		return "Menadzer [" + super.toString() + "]";
	}
	
	public String toFileString() {
		return this.ime + "," + this.prezime + "," + this.telefon + "," + this.adresa + "," + this.korisnickoIme + "," + this.lozinka+","+
				this.strucnaSprema +","+this.radniStaz+","+this.bonus+","+this.osnovaPlate+","+this.plata+",";
	} 
	
//	public void meniMenadzer() {
//		System.out.println("\n ***MENI MENADZEER*** \n");
//		System.out.println("1. Kreiraj korisnika - registracija");
//		System.out.println("2. Pogledaj korisnike");
//		System.out.println("3. Izmeni korisnika");
//		System.out.println("4. Obrisi korisnika");
//		System.out.println("5. Kreiraj tretman");
//		System.out.println("6. Pogledaj tretmane");
//		System.out.println("7. Izmeni tretman");
//		System.out.println("8. Obrisi tretman");
//		System.out.println("9. Kreiraj uslugu salona");
//		System.out.println("10. Pogledaj usluge salona");
//		System.out.println("11. Izmeni uslugu salona");
//		System.out.println("12. Obrisi uslugu salona");
//		System.out.println("13. Dodaj tip tretmana za salon");
//		System.out.println("14. Vidi tipove tretmana za salon");
//		System.out.println("15. Izmeni tip tretmana za salon");
//		System.out.println("16. Obrisi tip tretmana za salon");
//		System.out.println("17. Pogledaj cene usluga salona");
//		System.out.println("x. Odjava"); 
//	}
	
//	public void dodajMenadzera(KorisniciKontroler ks, Menadzer m) {
//		if(ks.registrujMenadzera(m)) {
//			System.out.println("Uspesno ste registrovali menadzera. ");
//		}
//	}
//	public void dodajKozmeticara(KorisniciKontroler ks, Kozmeticar k) {
//		if(ks.registrujKozmeticara(k)) {
//			System.out.println("Uspesno ste registrovali kozmeticara. ");
//		}
//	}
//	public void dodajRecepcionara(KorisniciKontroler ks, Recepcioner r) {
//		if(ks.registrujRecepcionera(r)) {
//			System.out.println("Uspesno ste registrovali recepcionara. ");
//		}
//	}
//	public void vidiKorisnike(KorisniciKontroler ks) {
//		ks.vidiKorisnike(); 
//	}
//	
//	public void izmeniKorisnika(KorisniciKontroler ks, Korisnik kor,String ime,String prezime, String adresa, Integer telefon, String pass) {
//		kor.setIme(ime);
//		kor.setPrezime(prezime);
//		kor.setAdresa(adresa);
//		kor.setTelefon(telefon);
//		kor.setLozinka(pass);
//				
//		
//		ks.izbrisiSadrzajFajla();
//		ks.upisiSveKorisnike();
//		System.out.println("Uspesna izmena korisnika.");
//	}
//	
//	public void izmeniKozmeticara(KorisniciKontroler ks, Kozmeticar kor,String ime,String prezime, String adresa, Integer telefon, String pass, ArrayList<TipTretmana> t) {
//		kor.setIme(ime);
//		kor.setPrezime(prezime);
//		kor.setAdresa(adresa); 
//		kor.setTelefon(telefon);
//		kor.setLozinka(pass);
//		kor.setObucenost(t);
//				
//		
//		ks.izbrisiSadrzajFajla();
//		ks.upisiSveKorisnike();
//		System.out.println("Uspesna izmena korisnika.");
//	}
//	
//	public void izbrisiKorisnika(KorisniciKontroler ks, Korisnik korisnik) {
//		ks.izbrisiKorisnika(korisnik);
//		System.out.println("Uspesno izbrisan korisnik.");
//	}
//	
//	public void napraviTretman(TretmanKontroler tr, Tretman t) {
//		if(tr.dodajTretman(t)) {
//			System.out.println("Uspesno ste dodali tretman u ponudu.");
//		}
//	}
//	public void vidiTretmane(TretmanKontroler tr) {
//		for(Tretman t:tr.getTretmani()) {
//			System.out.println(t.toString());
//		}
//		
//	}
//	
//	public void izmeniTretman(TretmanKontroler tr, Tretman t, UslugaTretman u, Kozmeticar k, TipTretmana tip, Klijent kl, LocalDateTime v) {
//		t.setUsluga(u);
//		t.setTip(tip);
//		t.setKozmeticar(k);
//		t.setKlijent(kl);
//		t.setVreme(v);
//		
//		tr.izbrisiSadrzaj();
//		tr.upisiSveTretmane();
//		System.out.println("Uspesna izmena tretmana.");
//	}
//	
//	public void izbrisiTretman(TretmanKontroler tr, Tretman t) {
//		tr.izbrisiTretman(t);
//		System.out.println("Uspesno izbrisan tretman.");
//	}
//	public void napraviUslugu(UslugeKontroler us, UslugaTretman u) {
//		if(us.dodajUslugu(u)) {
//			System.out.println("Uspesno ste napravili uslugu.");
//		}
//	}
//	public void vidiUsluge(UslugeKontroler us) {
//		for(UslugaTretman u:us.getUsluge()) {
//			System.out.println(u.toString());
//		}
//	}
//	public void izmeniUslugu(UslugeKontroler us,UslugaTretman u, TipTretmana t, Double cena, LocalTime l) {
//		u.setTipTretmana(t);
//		u.setCena(cena);
//		u.setTrajanjeUsluge(l);
//		
//		us.izbrisiSadrzaj(); 
//		us.upisSveTretmane();
//		System.out.println("Uspesna izmena usluge tretmana.");
//	}
//	
//	public void izbrisiUslugu(UslugeKontroler us, UslugaTretman u, ArrayList<Tretman> tretmani, TretmanKontroler tk) {
//		us.izbrisiUsluguTretmana(u);
//		for(Tretman t:tretmani) {
//			t.setObrisan(true);
//		}
//		
//		tk.izbrisiSadrzaj();
//		tk.upisiSveTretmane();
//		
//		System.out.println("Uspesno izbrisan tretman.");
//	}
// 
//	public void napraviTip(TipKontroler tk, TipTretmana t) {
//		if(tk.dodajTip(t)) {
//			System.out.println("Uspesno dodati tip tretmana.");
//		}
//	}
//	public void vidiTipove(TipKontroler tk) {
//		for(TipTretmana t:tk.getTipovi()) {
//			System.out.println(t.toString());
//		}
//	}
//	public void izmeniTip(TipKontroler tk, TipTretmana t, String ime) {
//		t.setImeTip(ime);
//		
//		tk.izbrisiSadrzajaTipova();
//		tk.upisiSveTipove();
//		System.out.println("Uspesna izmena tipa.");
//	}
//	public void izbrisiTip(TipKontroler tk, TipTretmana t, ArrayList<UslugaTretman> usluge, ArrayList<Tretman> tretmani, UslugeKontroler uk, TretmanKontroler tkk) {
//		tk.izbrisiTip(t);
//		for(UslugaTretman u:usluge) {
//			u.setObrisan(true);
//		}
//		for(Tretman tr:tretmani) {
//			tr.setObrisan(true);
//		}
//		
//		//azuriranje zbog atributa obrisan
//		uk.izbrisiSadrzaj();
//		uk.upisSveTretmane();
//		
//		tkk.izbrisiSadrzaj();
//		tkk.upisiSveTretmane();
//		
//		System.out.println("Uspesno izbrisan tip.");
//	}
	
	
	

}
