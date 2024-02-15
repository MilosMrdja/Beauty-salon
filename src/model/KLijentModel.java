package model;

import javax.swing.table.AbstractTableModel;

import entiteti.Klijent;
import entiteti.Korisnik;
import kontroleri.KorisniciKontroler;

public class KLijentModel extends AbstractTableModel{

	
	private static final long serialVersionUID = 1L;
	private KorisniciKontroler korisniciKontroler;
	private String[] kolone = {"Ime", "Prezime", "Telefon", "Adresa", "Korisnicko ime","Lozinka","Karitica lojalnosti","Potrosen novac"};

	public KLijentModel(KorisniciKontroler k) {
		this.korisniciKontroler = k;
	}
	
	@Override
	public int getRowCount() {
		int br = 0;
		for(Korisnik k:korisniciKontroler.getKorisnici()) {
			if(k instanceof Klijent) {
				br++;
			}
		}
		return br;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return kolone.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Klijent klijent = korisniciKontroler.getKlijenti().get(rowIndex);
		switch (columnIndex) {
		case 0:
			return klijent.getIme();
		case 1:
			return klijent.getPrezime(); 
		case 2:
			return klijent.getTelefon();
		case 3: 
			return klijent.getAdresa();
		case 4:
			return klijent.getKorisnickoIme();
		case 5:
			return klijent.getLozinka();
		case 6:
			return klijent.isKarticaLojalnosti();
		case 7:
			return klijent.getPotrosenNovac();

		default:
			return null;
		}
	}
	@Override
	public String getColumnName(int column) {
		return this.kolone[column];
	}

}
