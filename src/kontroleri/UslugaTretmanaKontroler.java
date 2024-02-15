package kontroleri;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import entiteti.TipTretmana;
import entiteti.UslugaTretman;

public class UslugaTretmanaKontroler {
	private ArrayList<UslugaTretman> usluge;
	private String fajl;
	private TipTretmanaKontroler tipKontroler;
	
	public UslugaTretmanaKontroler(String s, TipTretmanaKontroler t) {
		this.fajl = s;
		this.usluge = new ArrayList<UslugaTretman>();
		this.tipKontroler = t;
	}

	public ArrayList<UslugaTretman> getUsluge() {
		return this.usluge;
	}

	public void setUsluge(ArrayList<UslugaTretman> usluge) {
		this.usluge = usluge;
	}

	public String getFajl() {
		return fajl;
	}

	public void setFajl(String fajl) {
		this.fajl = fajl;
	}
	
	public void ucitajUslugeTretmana(){
		Scanner read = null;
		try {
			read = new Scanner (new File(this.fajl));
		} catch (FileNotFoundException e) {
			System.out.println("Fajl nije pronadjen.");
			e.printStackTrace();
		}
		   read.useDelimiter(","); 
		   ArrayList<String> k = new ArrayList<String>();
		   String temp = "";
		   TipTretmana tipTretmana = null;
		   DateTimeFormatter format = null;
		   boolean obrisan = false;
		   while(read.hasNext())
		   {
			  
			   k.add(read.next());//id usluge
		       k.add(read.next());//tip  
		       k.add(read.next());///usluga
		       k.add(read.next());//cena
		       k.add(read.next());//trajanje
		       k.add(read.next());//obrisan
		       
		       temp = k.get(1);
		       for(TipTretmana t:this.tipKontroler.getTipovi()) {
		    	   if(temp.compareTo(t.getImeTip())== 0) {
		    		   tipTretmana = t;
		    		    
		    	   }
		       }
		       
		       format = DateTimeFormatter.ofPattern("HH:mm");
		       if(k.get(5).compareToIgnoreCase("true")==0) {
		    	   obrisan = true;
		       }
		       else {
		    	   obrisan = false;
		       }
		       
		       this.usluge.add(new UslugaTretman(tipTretmana, k.get(2), Double.parseDouble(k.get(3)),Integer.parseInt(k.get(0).replaceAll("\\W", "")),LocalTime.parse(k.get(4), format),obrisan));
		       
		       k.clear();
		       
		       
		   }
		   
		   read.close(); 
	}
	
	public boolean upisUslugaTretmana(String s) {
		try {
		      FileWriter myWriter = new FileWriter(this.fajl, true);
		      if(new File(this.fajl).length()==0) {
		    	  myWriter.write(s);
		      }
		      else {
		    	  myWriter.write("\n"+s);  
		      }
		      myWriter.close();
		      
		      return true;
		    } catch (IOException e) {
		      System.out.println("Neuspesna registracija usluga tretmana, problem u fajlu.");
		      return false;
		      //e.printStackTrace();
		    }
	}
	
	public void upisSveTretmane() {
		for(UslugaTretman u:this.usluge) {
			this.upisUslugaTretmana(u.toFileString());
		}
	}
	public void izbrisiSadrzaj() {
		try {
		      FileWriter myWriter = new FileWriter(this.fajl);
		      myWriter.write("");
		      myWriter.close();
		      
		    } catch (IOException e) {
		      System.out.println("Neuspesno brisanje sadrzaja iz fajla.");
		      
		    }
	}
	public void azurirajFile() {
		this.izbrisiSadrzaj();
		this.upisSveTretmane();
	}
	
	public UslugaTretman pronadjiUsluguTretmana(int i) {
		for(UslugaTretman u:this.usluge) {
			if(i ==u.getIdusluga()) {
				return u;
			}
		}
		return null;
	}
	
	public UslugaTretman vratiRandom() {
		int i = 0;
		i = (int) (Math.random() * this.usluge.size());
		return this.usluge.get(i);
	}
	
	public boolean dodajUslugu(UslugaTretman u) {
		this.usluge.add(u);
		return this.upisUslugaTretmana(u.toFileString());
	}
	
	public ArrayList<UslugaTretman> getNeObrisaneUsluge(){
		ArrayList<UslugaTretman> uslugaTretmans = new ArrayList<UslugaTretman>();
		for(UslugaTretman u:this.usluge) {
			if(!u.isObrisan()) {
				uslugaTretmans.add(u);
			}
		}
		return uslugaTretmans;
	}
	
	public void izbrisiUsluguTretmana(UslugaTretman u) {
		u.setObrisan(true);
		this.azurirajFile();
	}
	
	public UslugaTretman pronadjiUsluguIme(String ime) {
		for(UslugaTretman u:this.usluge) {
			if(u.getImeTretmana().compareTo(ime)==0) {
				return u;
			}
		}
		return null;
	}
	
	public UslugaTretman izmenaUslugaTretman(int id,String ime,int cena) {
		UslugaTretman uslugaTretman = this.pronadjiUsluguTretmana(id);
		if(uslugaTretman!=null) {
			uslugaTretman.setImeTretmana(ime);
			uslugaTretman.setCena(cena);
		}
		return uslugaTretman;
	}
	
	public int postaviIdUsluga() {
		boolean flag = true;
		while(true) {
			Random random = new Random();
			int a = random.nextInt(10000);
			for(UslugaTretman u:this.usluge) {
				if(a == u.getIdusluga()) {
					flag = false;
				}
			}
			if(flag) {
				return a;
			}
			flag = true;
		}
	}
}
