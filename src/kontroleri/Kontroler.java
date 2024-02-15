package kontroleri;

import java.time.LocalTime;


public class Kontroler {
	private KorisniciKontroler korisniciKontroler;
	private TretmanKontroler tretmanKontroler;
	private UslugaTretmanaKontroler uslugeKontroler;
	private TipTretmanaKontroler tipKontroler;
	private Cenovnik cenovnik;
	
	private LocalTime pocetakRadnogVremena;
	private LocalTime krajRadnogVremena;
	
	//private static boolean flag;
	//private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

	
	public Kontroler(String korisniciFajl, String tretmaniFajl, String uslugeFajl, String tipFajl, String cenovnikFajl) {
		super();
		this.tipKontroler = new TipTretmanaKontroler(tipFajl);
		this.uslugeKontroler = new UslugaTretmanaKontroler(uslugeFajl, this.tipKontroler);
		this.korisniciKontroler = new KorisniciKontroler(korisniciFajl,this.tipKontroler);
		this.tretmanKontroler = new TretmanKontroler(tretmaniFajl, this.uslugeKontroler, this.korisniciKontroler);
		this.cenovnik = new Cenovnik(cenovnikFajl, this.uslugeKontroler);
		this.pocetakRadnogVremena = LocalTime.of(8, 0);
		this.krajRadnogVremena = LocalTime.of(20, 0);
				
 
		
		// this.flag = false;
	}
	
	public LocalTime getPocetakRadnogVremena() {
		return pocetakRadnogVremena;
	}

	public void setPocetakRadnogVremena(LocalTime pocetakRadnogVremena) {
		this.pocetakRadnogVremena = pocetakRadnogVremena;
	}

	public LocalTime getKrajRadnogVremena() {
		return krajRadnogVremena;
	}

	public void setKrajRadnogVremena(LocalTime krajRadnogVremena) {
		this.krajRadnogVremena = krajRadnogVremena;
	}

	public KorisniciKontroler getKorisniciKontroler() {
		return korisniciKontroler;
	}
	public void setKorisniciKontroler(KorisniciKontroler korisniciKontroler) {
		this.korisniciKontroler = korisniciKontroler;
	}
	public TretmanKontroler getTretmanKontroler() {
		return tretmanKontroler;
	}
	public void setTretmanKontroler(TretmanKontroler tretmanKontroler) {
		this.tretmanKontroler = tretmanKontroler;
	}
	public UslugaTretmanaKontroler getUslugeKontroler() {
		return uslugeKontroler;
	}
	public void setUslugeKontroler(UslugaTretmanaKontroler uslugeKontroler) {
		this.uslugeKontroler = uslugeKontroler;
	}
	public TipTretmanaKontroler getTipKontroler() {
		return tipKontroler;
	} 
	public void setTipKontroler(TipTretmanaKontroler tipKontroler) {
		this.tipKontroler = tipKontroler;
	}
	public Cenovnik getCenovnik() {
		return cenovnik;
	}
	public void setCenovnik(Cenovnik cenovnik) {
		this.cenovnik = cenovnik;
	} 
	public void ucitajSistem() {
		this.tipKontroler.ucitajTipove();
		this.uslugeKontroler.ucitajUslugeTretmana();
		this.korisniciKontroler.ucitajKorisnike();
		this.tretmanKontroler.ucitajTretmane();
		this.cenovnik.ucitajCenovnik();
		
		//da uvek sve izbrise iz fajla radi lakseg testiranja
		//verzija ja test scenario
		//gore je kod koji ce uvek ucitavati ono sto se nadje u fajlu

	}
}
