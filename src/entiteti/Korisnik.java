package entiteti;

public class Korisnik {
	protected String ime;
	protected String prezime;
	protected int telefon;
	protected String adresa;
	protected String korisnickoIme;
	protected String lozinka;
	
	public Korisnik(String ime, String prezime, int telefon, String adresa, String korisnickoIme, String lozinka) {
		//super(); 
		this.ime = ime;
		this.prezime = prezime;
		this.telefon = telefon;
		this.adresa = adresa;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
	}
	public String getIme() {
		return ime;
	} 
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public int getTelefon() {
		return telefon;
	}
	public void setTelefon(int telefon) {
		this.telefon = telefon;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	@Override
	public String toString() {
		return "ime=" + this.ime + ", prezime=" + this.prezime + ", telefon=" + this.telefon + ", adresa=" + this.adresa
				+ ", korisnickoIme=" + this.korisnickoIme + ", lozinka=" + this.lozinka+",";
	}
	 
	
}
