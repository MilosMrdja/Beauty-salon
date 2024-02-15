package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import entiteti.STANJE_TERETMANA;
import entiteti.Tretman;

public class TretmaniRecepcionerModel extends AbstractTableModel{
	
	
	private static final long serialVersionUID = 1470740463683122375L;
	private String[] kolone = {"ID","Stanje", "Usluga", "Tip", "Kozmeticar", "Klijent","Cena","Vreme","Obrisan"};
	private ArrayList<Tretman> tretmani;

	
	public TretmaniRecepcionerModel(ArrayList<Tretman> t) {
		this.tretmani = t;
	}
	
	@Override
	public int getRowCount() {
		
		return tretmani.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return kolone.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Tretman t = tretmani.get(rowIndex);
		
		switch (columnIndex) {
		case 0:
			return t.getIdTretmana();
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
			return t.getOpis(); 
		case 3:
			return t.getTipTretmana().getImeTip();
		case 4: 
			if(t.getKozmeticar()!=null) {
				return t.getKozmeticar().getKorisnickoIme();
			}
		case 5:
			return t.getKlijent().getKorisnickoIme();
		case 6:
			return t.getCena();
		case 7:
			return t.getVreme();
		case 8:
			return t.isObrisan();

		default:
			return null;
		}
	}
	@Override
	public String getColumnName(int column) {
		return this.kolone[column];
	}
}
