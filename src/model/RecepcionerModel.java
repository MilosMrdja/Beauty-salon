package model;

import javax.swing.table.AbstractTableModel;

import entiteti.Korisnik;
import entiteti.Recepcioner;
import kontroleri.KorisniciKontroler;

public class RecepcionerModel extends AbstractTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4965386333106851382L;
	private KorisniciKontroler korisniciKontroler;
	private String[] kolone = {"Ime", "Prezime", "Telefon", "Adresa", "Korisnicko ime","Lozinka","Strucna sprema","Radni staz","Bonus","Osnova plate","Plata"};

	public RecepcionerModel(KorisniciKontroler k) {
		this.korisniciKontroler = k;
	}
	
	@Override
	public int getRowCount() {
		int br = 0;
		for(Korisnik k:korisniciKontroler.getKorisnici()) {
			if(k instanceof Recepcioner) {
				br++;
			}
		}
		return br;
	}

	@Override
	public int getColumnCount() {
		return kolone.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Recepcioner r = korisniciKontroler.getRecepcioner().get(rowIndex);
		switch (columnIndex) {
		case 0:
			return r.getIme();
		case 1:
			return r.getPrezime(); 
		case 2:
			return r.getTelefon();
		case 3: 
			return r.getAdresa();
		case 4:
			return r.getKorisnickoIme();
		case 5:
			return r.getLozinka();
		case 6:
			return r.getStrucnaSprema();
		case 7:
			return r.getRadniStaz();
		case 8:
			return r.getBonus();
		case 9:
			return r.getOsnovaPlate();
		case 10:
			return r.getPlata();

		default:
			return null;
		}
	}
	@Override
	public String getColumnName(int column) {
		return this.kolone[column];
	}

}
