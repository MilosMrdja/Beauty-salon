package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import entiteti.Klijent;

public class KarticaLojalnostiModel extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4069079704734412064L;
	private String[] kolone = {"Klijent","Poseduje karticu"};
	private ArrayList<Klijent> klijenti;

	public KarticaLojalnostiModel(ArrayList<Klijent> klijents) {
		this.klijenti = klijents;
	}
	
	@Override
	public int getRowCount() {
		return klijenti.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return kolone.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Klijent klijent = klijenti.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return klijent.getKorisnickoIme();
		case 1:
			return klijent.isKarticaLojalnosti(); 
		

		default:
			return null;
		}
	}
	@Override
	public String getColumnName(int column) {
		return this.kolone[column];
	}
}
