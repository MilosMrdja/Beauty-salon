package entiteti;

public class Klijent extends Korisnik{

	private boolean karticaLojalnosti;
	private double potrosenNovac;
	
	public Klijent(String ime, String prezime, int telefon, String adresa, String korisnickoIme, String lozinka, boolean kl, Double n) {
		super(ime, prezime, telefon, adresa, korisnickoIme, lozinka);
		this.karticaLojalnosti = kl;
		this.potrosenNovac = n;
	}
	
	public boolean isKarticaLojalnosti() {
		return this.karticaLojalnosti;
	}

	public void setKarticaLojalnosti(boolean karticaLojalnosti) {
		this.karticaLojalnosti = karticaLojalnosti;
	}
	
	public void potrosiNovac(double novac) {
		this.potrosenNovac += novac; 
	}
	public void povratNovca(double novac) {
		this.potrosenNovac -= novac;
	}
	public double getPotrosenNovac() {
		return this.potrosenNovac;
	}

	public void setPotrosenNovac(double potrosenNovac) {
		this.potrosenNovac = potrosenNovac;
	}

	public void vracenNovac(double novac) {
		this.potrosenNovac -= novac;
	}
 
	@Override
	public String toString() {
		return "Klijent [ime=" + ime + ", prezime=" + prezime + ", telefon=" + telefon + ", adresa=" + adresa
				+ ", korisnickoIme=" + korisnickoIme + ", lozinka=" + lozinka + "]";
	}
	public String toFileString() {
		return this.ime + "," + this.prezime + "," + this.telefon + "," + this.adresa + "," + this.korisnickoIme + "," + this.lozinka+"," + this.karticaLojalnosti+","+this.potrosenNovac+",";
	}
}
