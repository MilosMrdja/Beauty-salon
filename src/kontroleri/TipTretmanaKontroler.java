package kontroleri;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import entiteti.TipTretmana;

public class TipTretmanaKontroler {
	private ArrayList<TipTretmana> tipovi;
	private String fajl;
	
	public TipTretmanaKontroler(String f) {
		this.fajl = f;
		this.tipovi = new ArrayList<TipTretmana>();
	}

	public ArrayList<TipTretmana> getTipovi() {
		return this.tipovi;
	}

	public void setTipovi(ArrayList<TipTretmana> tipovi) {
		this.tipovi = tipovi;
	}
	
	public TipTretmana vratiRandom() {
		int i = 0;
		i = (int) (Math.random() * this.tipovi.size());
		return this.tipovi.get(i);
	}
	
	public void ucitajTipove(){
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
			  k.add(read.next());
			  k.add(read.next());
			  this.tipovi.add(new TipTretmana(Integer.parseInt(k.get(0).replaceAll("\\W", "")), k.get(1)));
			  
		      k.clear();
		       
		   }
		   read.close();
	
	}
	
	public boolean upisTipova(String s) {
		try {
		      FileWriter myWriter = new FileWriter(this.fajl, true);
		      if(new File(this.fajl).length()==0) {
		    	  myWriter.write(s);
		      }
		      else {
		    	  myWriter.write("\n" + s);  
		      }
		      
		      myWriter.close();
		      
		      return true;
		    } catch (IOException e) {
		      System.out.println("Neuspesna registracija tipa tretmana, problem.");
		      return false;
		      //e.printStackTrace();
		    }
	}
	
	public void upisiSveTipove() {
		for(TipTretmana t:this.tipovi) {
			this.upisTipova(t.toFileString());
		}
	} 
	
	
	public void izbrisiSadrzajaTipova() {
		try {
		      FileWriter myWriter = new FileWriter(this.fajl);
		      myWriter.write("");
		      myWriter.close();
		      
		    } catch (IOException e) {
		      System.out.println("Neuspesno brisanje sadrzaja iz tipova.");
		     
		    }
	}
	
	public TipTretmana pronadjiTipTretman(int id) {
		for(TipTretmana t:this.tipovi) {
			if(id == t.getIdTip()) {
				return t;
			}
		}
		return null; 
	}
	
	public TipTretmana pronadjiTipPoImenu(String ime) {
		for(TipTretmana t:this.tipovi) {
			if(ime.compareTo(t.getImeTip())==0) {
				return t;
			}
		}
		return null; 
	}
	
	public boolean dodajTip(TipTretmana t) {
		this.tipovi.add(t);
		return this.upisTipova(t.toFileString());
	}
	
	public void azurirajFile() {
		this.izbrisiSadrzajaTipova();
		this.upisiSveTipove();
	}
	
	public void izbrisiTip(TipTretmana t) {
		this.tipovi.remove(t);	
		this.azurirajFile();

	}
	
	public TipTretmana editTipTretmana(Integer id, String ime) {
		TipTretmana tipTretmana = this.pronadjiTipTretman(id);
		if(tipTretmana != null) {
			tipTretmana.setImeTip(ime);
		}
		return tipTretmana;
	}
	
	public int postaviIdTip() {
		boolean flag = true;
		while(true) {
			Random random = new Random();
			int a = random.nextInt(10000);
			for(TipTretmana u:this.tipovi) {
				if(a == u.getIdTip()) {
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
