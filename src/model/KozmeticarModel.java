package model;

import javax.swing.table.AbstractTableModel;

import entiteti.Korisnik;
import entiteti.Kozmeticar;
import entiteti.TipTretmana;
import kontroleri.KorisniciKontroler;

public class KozmeticarModel extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3127480540386929732L;
	private KorisniciKontroler korisniciKontroler;
	private String[] kolone = {"Ime", "Prezime", "Telefon", "Adresa", "Korisnicko ime","Lozinka","Strucna sprema","Radni staz","Bonus","Osnova plate","Plata","Obucenost"};

	public KozmeticarModel(KorisniciKontroler k) {
		this.korisniciKontroler = k;
	}
	
	@Override
	public int getRowCount() {
		int br = 0;
		for(Korisnik k:korisniciKontroler.getKorisnici()) {
			if(k instanceof Kozmeticar) {
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
		Kozmeticar k = korisniciKontroler.getKozmeticari().get(rowIndex);
		switch (columnIndex) {
		case 0:
			return k.getIme();
		case 1:
			return k.getPrezime(); 
		case 2:
			return k.getTelefon();
		case 3: 
			return k.getAdresa();
		case 4:
			return k.getKorisnickoIme();
		case 5:
			return k.getLozinka();
		case 6:
			return k.getStrucnaSprema();
		case 7:
			return k.getRadniStaz();
		case 8:
			return k.getBonus();
		case 9:
			return k.getOsnovaPlate();
		case 10:
			return k.getPlata();
		case 11:
			String s = "";
			for(TipTretmana t:k.getObucenost()) {
				s += t.getImeTip() + ", ";
			}
			return s;

		default:
			return null;
		}
	}
	@Override
	public String getColumnName(int column) {
		return this.kolone[column];
	}
}
