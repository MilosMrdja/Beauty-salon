package model;

import javax.swing.table.AbstractTableModel;

import entiteti.Korisnik;
import entiteti.Menadzer;
import kontroleri.KorisniciKontroler;

public class MenadzerModel extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3661999829553623857L;
	private KorisniciKontroler korisniciKontroler;
	private String[] kolone = {"Ime", "Prezime", "Telefon", "Adresa", "Korisnicko ime","Lozinka","Strucna sprema","Radni staz","Bonus","Osnova plate","Plata"};

	public MenadzerModel(KorisniciKontroler k) {
		this.korisniciKontroler = k;
	}
	
	@Override
	public int getRowCount() {
		int br = 0;
		for(Korisnik k:korisniciKontroler.getKorisnici()) {
			if(k instanceof Menadzer) {
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
		Menadzer menadzer = korisniciKontroler.getMenadzeri().get(rowIndex);
		switch (columnIndex) {
		case 0:
			return menadzer.getIme();
		case 1:
			return menadzer.getPrezime(); 
		case 2:
			return menadzer.getTelefon();
		case 3: 
			return menadzer.getAdresa();
		case 4:
			return menadzer.getKorisnickoIme();
		case 5:
			return menadzer.getLozinka();
		case 6:
			return menadzer.getStrucnaSprema();
		case 7:
			return menadzer.getRadniStaz();
		case 8:
			return menadzer.getBonus();
		case 9:
			return menadzer.getOsnovaPlate();
		case 10:
			return menadzer.getPlata();

		default:
			return null;
		}
	}
	@Override
	public String getColumnName(int column) {
		return this.kolone[column];
	}
}
