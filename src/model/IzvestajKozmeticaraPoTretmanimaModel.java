package model;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import entiteti.Kozmeticar;
import entiteti.Tretman;

public class IzvestajKozmeticaraPoTretmanimaModel extends AbstractTableModel{
	
	private static final long serialVersionUID = -1122189573744930018L;
	private String[] kolone = {"Kozmeticar", "Izvrseni tretmani", "Prihod"};
	private ArrayList<Kozmeticar> kozmeticari;
	private LocalDate pocetak;
	private LocalDate kraj;
	private int izvrseni;
	private Double prihod;
	private ArrayList<Tretman> tretmani;

	public IzvestajKozmeticaraPoTretmanimaModel(ArrayList<Kozmeticar> kozmeticars, LocalDate pocetak,LocalDate kraj, ArrayList<Tretman> tretmans) {
		this.kozmeticari = kozmeticars;
		this.pocetak = pocetak;
		this.kraj = kraj;
		this.tretmani = tretmans;
		this.izvrseni = 0;
		this.prihod = 0.0;
	}
	
	@Override
	public int getRowCount() {
		return kozmeticari.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return kolone.length;
	}
	
	private void vratiIzvrsene(Kozmeticar k) {
		
		for(Tretman t:tretmani) {
			if(t.getVreme().toLocalDate().isAfter(pocetak) && t.getVreme().toLocalDate().isBefore(kraj) && k.getKorisnickoIme().compareTo(t.getKozmeticar().getKorisnickoIme())==0) {
				izvrseni++;
				prihod += t.getCena();
				
			}
		}
		
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Kozmeticar kozmeticar = kozmeticari.get(rowIndex);
		prihod = 0.0;
		izvrseni = 0;
		vratiIzvrsene(kozmeticar); 
		switch (columnIndex) {
		case 0:
			return kozmeticar.getKorisnickoIme();
		case 1:
			return izvrseni; 
		case 2:
			return prihod;
		

		default:
			return null;
		}
	}
	@Override
	public String getColumnName(int column) {
		return this.kolone[column];
	}
}
