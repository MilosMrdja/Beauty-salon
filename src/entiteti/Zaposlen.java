package entiteti;



public class Zaposlen extends Korisnik{
	
	protected double strucnaSprema;
	protected int radniStaz;
	protected double osnovaPlate;
	protected double bonus;
	protected double plata;
	public Zaposlen(String ime, String prezime, int telefon, String adresa, String korisnickoIme, String lozinka,
			STRUCNA_SPREMA strucnaSprema, int radniStaz,double osnova, double bonus) {
		super(ime, prezime, telefon, adresa, korisnickoIme, lozinka);
		this.strucnaSprema = this.dodeliKoeficijent(strucnaSprema);
		this.radniStaz = radniStaz;
		this.bonus = bonus;
		this.osnovaPlate = osnova;
		this.plata = (this.strucnaSprema * this.radniStaz * this.osnovaPlate) + this.bonus; 
	}
	public double getOsnovaPlate() {
		return osnovaPlate;
	}
	public void setOsnovaPlate(double osnovaPlate) {
		this.osnovaPlate = osnovaPlate;
		this.plata = (this.strucnaSprema * this.radniStaz * this.osnovaPlate) + this.bonus;
	}
	public double getPlata() {
		return plata;
	}
	public void setPlata(double plata) {
		this.plata = plata;
	}
	public double getStrucnaSprema() {
		return strucnaSprema;
	}
	public void setStrucnaSprema(double strucnaSprema) {
		this.strucnaSprema = strucnaSprema;
		this.plata = (this.strucnaSprema * this.radniStaz * this.osnovaPlate) + this.bonus;
	}
	public int getRadniStaz() {
		return radniStaz;
	}
	public void setRadniStaz(int radniStaz) {
		this.radniStaz = radniStaz;
		this.plata = (this.strucnaSprema * this.radniStaz * this.osnovaPlate) + this.bonus;
	}
	public double getBonus() {
		return bonus;
	}
	public void setBonus(double bonus) {
		this.bonus = bonus;
		this.plata = (this.strucnaSprema * this.radniStaz * this.osnovaPlate) + this.bonus;
	}
	public double dodeliKoeficijent(STRUCNA_SPREMA s) {
		if(s == STRUCNA_SPREMA.NISKA) {
			return 0.2;
		}
		else if(s == STRUCNA_SPREMA.SREDNJE_NISKA) { 
			return 0.4;
		}
		else if(s == STRUCNA_SPREMA.SREDNJA){
			return 0.6;
		}
		else if(s == STRUCNA_SPREMA.SREDNJE_VISOKA) {
			return 0.8;
		}
		else {
			return 1.0;
		}
	}
	@Override
	public String toString() {
		
		return super.toString() + " strucnaSprema=" + strucnaSprema + ", radniStaz=" + radniStaz + ", bonus=" + bonus + ", plata="
				+ plata;
	}
	
	
	

	
}
