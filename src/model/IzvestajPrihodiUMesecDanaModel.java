package model;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import entiteti.STANJE_TERETMANA;
import entiteti.Tretman;
import entiteti.UslugaTretman;

public class IzvestajPrihodiUMesecDanaModel extends AbstractTableModel{


	private static final long serialVersionUID = -8506047906989859567L;
	private String[] kolone = {"Usluga", "Tip", "Zakazani tretmani", "Prihod"};
	private ArrayList<UslugaTretman> uslugaTretmans;
	private LocalDate pocetak;
	private LocalDate kraj;
	private int izvrseni;
	private Double prihod;
	private ArrayList<Tretman> tretmani;

	public IzvestajPrihodiUMesecDanaModel(ArrayList<UslugaTretman> u,LocalDate pocetak,LocalDate kraj, ArrayList<Tretman> tretmans) {
		this.uslugaTretmans = u;
		this.pocetak = pocetak;
		this.kraj = kraj;
		this.tretmani = tretmans;
		this.izvrseni = 0;
		this.prihod = 0.0;
	}
	
	@Override
	public int getRowCount() {
		return uslugaTretmans.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return kolone.length;
	}
	
	private void vratiIzvrsene(UslugaTretman uslugaTretman) {
		
		for(Tretman t:tretmani) {
			if(t.getVreme().toLocalDate().isBefore(kraj) && t.getVreme().toLocalDate().isAfter(pocetak) && t.getUsluga().getIdUsluga() == uslugaTretman.getIdusluga() && t.getStanje()==STANJE_TERETMANA.IZVRSEN) {
				izvrseni++;
				prihod += t.getCena();
			}
		}
		
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		UslugaTretman uslugaTretman = uslugaTretmans.get(rowIndex);
		prihod = 0.0;
		izvrseni = 0;
		vratiIzvrsene(uslugaTretman); 
		switch (columnIndex) {
		case 0:
			return uslugaTretman.getImeTretmana();
		case 1:
			return uslugaTretman.getTipTretmana().getImeTip();
		case 2:
			return izvrseni; 
		case 3:
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
