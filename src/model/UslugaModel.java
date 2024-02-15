package model;

import javax.swing.table.AbstractTableModel;

import entiteti.UslugaTretman;
import kontroleri.UslugaTretmanaKontroler;

public class UslugaModel extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 225755300847040222L;
	private UslugaTretmanaKontroler uslugaTretmanaKontroler;
	private String[] kolone = {"ID","Tip", "Ime", "Cena","Trajanje","Obrisana"};

	public UslugaModel(UslugaTretmanaKontroler k) {
		this.uslugaTretmanaKontroler = k;
	}
	
	@Override
	public int getRowCount() {
		
		return uslugaTretmanaKontroler.getUsluge().size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return kolone.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		UslugaTretman u = uslugaTretmanaKontroler.getUsluge().get(rowIndex);
		switch (columnIndex) {
		case 0:
			return u.getIdusluga();
		case 1:
			return u.getTipTretmana().getImeTip();
		case 2:
			return u.getImeTretmana();
		case 3:
			return u.getCena();
		case 4: 
			return u.getTrajanjeUsluge();
		case 5:
			return u.isObrisan();

		default:
			return null;
		}
	}
	@Override
	public String getColumnName(int column) {
		return this.kolone[column];
	}
}
