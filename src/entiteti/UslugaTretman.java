package entiteti;

import java.time.LocalTime;

public class UslugaTretman {
	private TipTretmana tipTretmana;
	private String imeTretmana;
	private double cena;
	private int idUsluga;
	private LocalTime trajanjeUsluge;
	private boolean obrisan;
	public UslugaTretman(TipTretmana tip, String uslugaTretmana, double cena, int i, LocalTime l,boolean obr) {
		super();
		this.tipTretmana = tip;
		this.imeTretmana = uslugaTretmana; 
		this.cena = cena;
		this.idUsluga = i;
		this.trajanjeUsluge = l;
		this.obrisan = obr;
		
	}
	
	
	public boolean isObrisan() {
		return obrisan;
	}


	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}


	public LocalTime getTrajanjeUsluge() {
		return trajanjeUsluge;
	}


	public void setTrajanjeUsluge(LocalTime trajanjeUsluge) {
		this.trajanjeUsluge = trajanjeUsluge;
	}


	public int getIdUsluga() {
		return idUsluga;
	}


	public int getIdusluga() {
		return this.idUsluga;
	}
	
	public void setIdUsluga(int i) {
		this.idUsluga = i;
	}
	
	public String getImeTretmana() {
		return imeTretmana;
	}
	public void setImeTretmana(String uslugaTretmana) {
		this.imeTretmana = uslugaTretmana;
	}
	
	public TipTretmana getTipTretmana() {
		return tipTretmana;
	}

	public void setTipTretmana(TipTretmana tipTretmana) {
		this.tipTretmana = tipTretmana;
	}

	
	public String getUslugaITip() {
		return this.imeTretmana + "-" + this.tipTretmana.getImeTip();
	}
	
	
	public double getCena() { 
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}


	


	@Override
	public String toString() {
		return "UslugaTretman [tipTretmana=" + tipTretmana + ", imeTretmana=" + imeTretmana + ", cena=" + cena
				+ ", idUsluga=" + idUsluga + ", trajanjeUsluge=" + trajanjeUsluge + ", obrisan=" + obrisan + "]";
	}


	public String toFileString() {
		
		return this.idUsluga+","+this.tipTretmana.getImeTip()+"," + this.imeTretmana + "," + this.cena +","+this.trajanjeUsluge.toString()+","+this.obrisan+",";
	}
	
}
