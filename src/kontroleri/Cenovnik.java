package kontroleri;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import entiteti.UslugaTretman;

public class Cenovnik {
	private HashMap<Integer, Double> cenovnik;
	private UslugaTretmanaKontroler uslugeKontroler;
	private String fajl;
	
	public Cenovnik(String f, UslugaTretmanaKontroler u) {
		this.fajl = f;
		cenovnik = new HashMap<Integer, Double>();
		this.uslugeKontroler = u;
	}
	
	public void ucitajCenovnik(){
		Scanner read = null;
		try {
			read = new Scanner (new File(this.fajl));
		} catch (FileNotFoundException e) {
			System.out.println("Fajl nije pronadjen.");
			e.printStackTrace();
		}
		   read.useDelimiter(","); 
		   ArrayList<String> k = new ArrayList<String>();
		   while(read.hasNext())
		   {
			  
			   k.add(read.next());//ime
		       k.add(read.next());//cena
		       
		       
		       this.cenovnik.put(Integer.parseInt(k.get(0).replaceAll("\\W", "")),Double.parseDouble(k.get(1)));
		        
		       k.clear(); 
		       
		       
		   }
		   
		   read.close(); 
	}
	
	public boolean upisCenovnika(int i, Double cena) {
		try {
		      FileWriter myWriter = new FileWriter(this.fajl, true);
		      if(new File(this.fajl).length()==0) {
		    	  myWriter.write(i + "," + cena +",");
		      }
		      else {
		    	  myWriter.write("\n" + i + ","+ cena +",");  
		      }
		      
		      myWriter.close();
		      return true;
		    } catch (IOException e) {
		      System.out.println("Neuspesna registracija tipa tretmana, problem.");
		      return false;
		      //e.printStackTrace();
		    }
	}
	
	public void upisiSveUsugeUCenonvnik() {
		for(UslugaTretman u:this.uslugeKontroler.getUsluge()) {
			this.upisCenovnika(u.getIdusluga(),u.getCena());
		}
		
		
	}
	
	public void izbrisiSadrzajFajla() {
		try {
		      FileWriter myWriter = new FileWriter(this.fajl);
		      myWriter.write("");
		      myWriter.close();
		      
		    } catch (IOException e) {
		      System.out.println("Neuspesno brisanje sadrzaja iz fajla.");
		    }
	}
	
	public void azurirajCenovnik() {
		this.izbrisiSadrzajFajla();
		this.upisiSveUsugeUCenonvnik();
		this.ucitajCenovnik();
	}
	
	public void ispisiCenovnik() {
		for (int i : this.cenovnik.keySet()) {
			   System.out.print(this.uslugeKontroler.pronadjiUsluguTretmana(i).getUslugaITip() + " - ");
			   System.out.println(this.cenovnik.get(i));
			 }
	}
}
