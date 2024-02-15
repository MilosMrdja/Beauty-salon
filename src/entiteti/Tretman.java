package entiteti;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tretman {
	private int idTretmana;
	private STANJE_TERETMANA stanje;
	private UslugaTretman usluga;
	private Kozmeticar kozmeticar;
	private Klijent klijent;
	private String opis; //usluga-tip
	private double cena;//da li se cena racuna na osnovu usluge ili prosledjuje??
	private LocalDateTime vreme;
	private boolean obrisan;
	
	public Tretman(int idTretmana, UslugaTretman u, Kozmeticar kozmeticar, Klijent klijent, LocalDateTime vreme,boolean o, double cena, STANJE_TERETMANA stanje) {
		super();
		this.idTretmana = idTretmana;
		this.stanje =stanje;
		this.usluga = u;
		this.opis = u.getImeTretmana() + "-" + u.getTipTretmana().getImeTip();
		this.kozmeticar = kozmeticar;
		this.klijent = klijent; 
		this.cena = cena;
		this.vreme = vreme;
		this.obrisan = o;
	}
	




	public STANJE_TERETMANA getStanje() {
		return stanje;
	}





	public void setStanje(STANJE_TERETMANA stanje) {
		this.stanje = stanje;
	}





	public boolean isObrisan() {
		return obrisan;
	}



	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}



	public LocalDateTime getVreme() {
		return this.vreme;
	}



	public void setVreme(LocalDateTime v) {
		this.vreme = v;
	}



	public Klijent getKlijent() {
		return klijent;
	}
	public void setKlijent(Klijent klijent) {
		this.klijent = klijent;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public int getIdTretmana() {
		return idTretmana;
	}
	public void setIdTretmana(int idTretmana) {
		this.idTretmana = idTretmana;
	}
	public UslugaTretman getUsluga() {
		return usluga;
	}
	public void setUsluga(UslugaTretman usluga) {
		this.usluga = usluga;
	}
	public Kozmeticar getKozmeticar() {
		return kozmeticar;
	}
	public void setKozmeticar(Kozmeticar kozmeticar) {
		this.kozmeticar = kozmeticar; 
	}
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	
	public TipTretmana getTipTretmana() {
		return this.usluga.getTipTretmana();
	}
	public void setTip(TipTretmana t) {
		this.usluga.setTipTretmana(t); 
	}
	
	
	

	@Override
	public String toString() {
		return "Tretman [stanje=" + stanje + ", kozmeticar="
				+ kozmeticar.getKorisnickoIme() + ", klijent=" + klijent.korisnickoIme + ", opis=" + opis + ", cena=" + cena + ", vreme=" + vreme
				+ "]";
	}



	public String toFileString() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return this.idTretmana + "," + this.stanje + "," + this.opis + "," + this.kozmeticar.getKorisnickoIme() + ","+this.klijent.getKorisnickoIme()+"," + this.cena+ "," + this.vreme.format(dateTimeFormatter) +","+this.obrisan+",";
	}
	
	
	
	
	
	
	
}
