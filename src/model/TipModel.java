package model;

import javax.swing.table.AbstractTableModel;

import entiteti.TipTretmana;
import kontroleri.TipTretmanaKontroler;

public class TipModel extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5115976303672986851L;
	private TipTretmanaKontroler tipTretmanaKontroler;
	private String[] kolone = {"ID","Ime"};

	public TipModel(TipTretmanaKontroler k) {
		this.tipTretmanaKontroler = k;
	}
	
	@Override
	public int getRowCount() {
		
		return tipTretmanaKontroler.getTipovi().size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return kolone.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		TipTretmana u = tipTretmanaKontroler.getTipovi().get(rowIndex);
		switch (columnIndex) {
		case 0:
			return u.getIdTip();
		case 1:
			return u.getImeTip();

		default:
			return null;
		}
	}
	@Override
	public String getColumnName(int column) {
		return this.kolone[column];
	}
}
