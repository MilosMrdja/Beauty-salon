package entiteti;

public class Recepcioner extends Zaposlen{

	public Recepcioner(String ime, String prezime, int telefon, String adresa, String korisnickoIme, String lozinka,
			STRUCNA_SPREMA strucnaSprema, int radniStaz, double osnova, double bonus) {
		super(ime, prezime, telefon, adresa, korisnickoIme, lozinka, strucnaSprema, radniStaz, osnova, bonus);
		
	}
	
	
	@Override
	public String toString() {
		return "Recepcioner [" + super.toString() + "]";
	}


	public String toFileString() {
		return this.ime + "," + this.prezime + "," + this.telefon + "," + this.adresa + "," + this.korisnickoIme + "," + this.lozinka+","+
				this.strucnaSprema +","+this.radniStaz+","+this.bonus+","+this.osnovaPlate+","+this.plata+",";
	}
	
}
