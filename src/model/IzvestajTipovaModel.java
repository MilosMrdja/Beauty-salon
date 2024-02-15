package model;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import entiteti.STANJE_TERETMANA;
import entiteti.Tretman;

public class IzvestajTipovaModel extends AbstractTableModel{

	private static final long serialVersionUID = -5753957024994693127L;

	private String[] kolone = {"Odradjeni", "Otkazao klijent", "Otkazao salon","Nije se pojavio"};
	
	private int odradjeni;
	private int otkazaoKlijent;
	private int otkazaoSalon;
	private int nijeSePojavio;

	public IzvestajTipovaModel(LocalDate pocetak,LocalDate kraj, ArrayList<Tretman> tretmans) {
	
		this.odradjeni = 0;
		this.otkazaoKlijent = 0;
		this.otkazaoSalon = 0;
		this.nijeSePojavio = 0;
		for(Tretman t:tretmans) {
			if(t.getVreme().toLocalDate().isBefore(kraj) && t.getVreme().toLocalDate().isAfter(pocetak)) {
				if(t.getStanje() == STANJE_TERETMANA.IZVRSEN) {
					this.odradjeni++;
				}
				else if(t.getStanje() == STANJE_TERETMANA.OTKAZAO_KLIJENT) {
					this.otkazaoKlijent++;
				}
				else if(t.getStanje() == STANJE_TERETMANA.OTKAZAO_SALON) {
					this.otkazaoSalon++;
				}
				else if(t.getStanje()==STANJE_TERETMANA.NIJE_SE_POJAVIO) {
					this.nijeSePojavio++;
				}
			}
			
		}
		
	}
	
	@Override
	public int getRowCount() {
		return 1;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return kolone.length;
	}
	
	
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return this.odradjeni;
		case 1:
			return this.otkazaoKlijent; 
		case 2:
			return this.otkazaoSalon;
		case 3:
			return this.nijeSePojavio;
		

		default:
			return null;
		}
	}
	@Override
	public String getColumnName(int column) {
		return this.kolone[column];
	}
}
