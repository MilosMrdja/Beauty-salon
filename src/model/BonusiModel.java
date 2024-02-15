package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import entiteti.Kozmeticar;

public class BonusiModel extends AbstractTableModel{

	private static final long serialVersionUID = 2693459430060856317L;
	private String[] kolone = {"Kozmeticar", "Bonus"};
	private ArrayList<Kozmeticar> kozmeticari;

	public BonusiModel(ArrayList<Kozmeticar> kozmeticars) {
		this.kozmeticari = kozmeticars;
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
	

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Kozmeticar kozmeticar = kozmeticari.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return kozmeticar.getKorisnickoIme();
		case 1:
			return kozmeticar.getBonus(); 
		

		default:
			return null;
		}
	}
	@Override
	public String getColumnName(int column) {
		return this.kolone[column];
	}
}
