package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import entiteti.STANJE_TERETMANA;
import entiteti.Tretman;

public class TretmanZaKlijentaModel extends AbstractTableModel{
	
	private static final long serialVersionUID = -5769072025278932935L;
	private ArrayList<Tretman> tretmans;
	private String[] kolone = {"Naziv","Stanje", "Trosak","Obrisan","ID"};

	public TretmanZaKlijentaModel(ArrayList<Tretman> t) {
		this.tretmans = t;
	}
	@Override
	public int getRowCount() {
		
		return tretmans.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return kolone.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Tretman t = tretmans.get(rowIndex);
		
		switch (columnIndex) {
		
		case 0:
			return t.getOpis();
		case 1:
			if(t.getStanje()==STANJE_TERETMANA.IZVRSEN) {
				return "Izvrsen";
			}
			else if(t.getStanje() == STANJE_TERETMANA.ZAKAZAN) {
				return "Zakazan";
			}
			else if(t.getStanje() == STANJE_TERETMANA.OTKAZAO_KLIJENT) {
				return "Otkazao klijent";
			}
			else if(t.getStanje() == STANJE_TERETMANA.OTKAZAO_SALON) {
				return "Otkazao salon";
			}
			else {
				return "Nije se pojavio";
			} 
		case 2:
			return t.getCena();
		case 3:
			return t.isObrisan();
		case 4:
			return t.getIdTretmana();
		

		default:
			return null;
		}
	}
	@Override
	public String getColumnName(int column) {
		return this.kolone[column];
	}
}
