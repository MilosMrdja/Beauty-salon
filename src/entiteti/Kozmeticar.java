package entiteti;

import java.util.ArrayList;

public class Kozmeticar extends Zaposlen{

	private ArrayList<TipTretmana> obucenost;
	private int uslovBonus; 
	
	public Kozmeticar(String ime, String prezime, int telefon, String adresa, String korisnickoIme, String lozinka,
			STRUCNA_SPREMA strucnaSprema, int radniStaz, double osnova,double bonus,  ArrayList<TipTretmana> obucen) {
		super(ime, prezime, telefon, adresa, korisnickoIme, lozinka, strucnaSprema, radniStaz,osnova, bonus);
		this.obucenost = obucen;
		
	}
	
	


	public int getUslovBonus() {
		return uslovBonus;
	}




	public void setUslovBonus(int uslovBonus) {
		this.uslovBonus = uslovBonus;
	}




	public ArrayList<TipTretmana> getObucenost() {
		return obucenost;
	} 



	public void setObucenost(ArrayList<TipTretmana> obucenost) {
		this.obucenost = obucenost;
	}
	
	public void dodajObucenost(TipTretmana t) {
		this.obucenost.add(t);
	}
	
	public boolean proveraObucenost(TipTretmana t) {
		for(TipTretmana tipTretmana : this.obucenost) {
			if(tipTretmana.equals(t)) {
				return true;
			}
		}
		return false;
	}



	@Override
	public String toString() {
		
		return "Kozmeticar [" + super.toString() + ", obucenost=" + obucenost + "]";
	}

 

	public String toFileString() {
		String s = "";
		for(TipTretmana t: this.obucenost) {
			s += t.getImeTip() + ",";
			
		}
		s = s.replaceAll("\\s", "");
		return this.ime + "," + this.prezime + "," + this.telefon + "," + this.adresa + "," + this.korisnickoIme + "," + this.lozinka+","+
		this.strucnaSprema +","+this.radniStaz+","+this.bonus+","+this.osnovaPlate+","+this.plata+","+s+"kraj"+",";
	}
	
	
}
