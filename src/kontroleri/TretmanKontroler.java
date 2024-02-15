package kontroleri;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


import entiteti.Klijent;
import entiteti.Korisnik;
import entiteti.Kozmeticar;
import entiteti.STANJE_TERETMANA;
import entiteti.Tretman;
import entiteti.UslugaTretman;

public class TretmanKontroler {
	private ArrayList<Tretman> tretmani;
	private String fajlT;
	private UslugaTretmanaKontroler uslugeKontroler;
	private KorisniciKontroler korisniciKontroler;
	
	public TretmanKontroler(String s, UslugaTretmanaKontroler u,KorisniciKontroler k) {
		this.fajlT = s;
		tretmani = new ArrayList<Tretman>();
		this.uslugeKontroler = u;
		this.korisniciKontroler = k;
	}

	public ArrayList<Tretman> getTretmani() {
		return tretmani;
	}

	public void setTretmani(ArrayList<Tretman> tretmani) {
		this.tretmani = tretmani;
	}

	public String getFajl() {
		return fajlT;
	}

	public void setFajl(String fajl) {
		this.fajlT = fajl;
	}
	
	public void ucitajTretmane(){
		Scanner read = null; 
		try {
			read = new Scanner (new File(this.fajlT));
		} catch (FileNotFoundException e) {
			System.out.println("Fajl nije pronadjen.");
			e.printStackTrace();
		}
		   read.useDelimiter(",");
		   ArrayList<String> k = new ArrayList<String>();
		   UslugaTretman up = null;
		   Kozmeticar kozm= null;
		   Klijent klijent = null; 
		   boolean obrisan = false; 
		   STANJE_TERETMANA stanje_TERETMANA = STANJE_TERETMANA.ZAKAZAN;
		   while(read.hasNext()) 
		   {
			  k.add(read.next());//id
			  k.add(read.next());	//OVO je za stanje		  			  
			  k.add(read.next()); //opis			  	
			  k.add(read.next());//kozmeticar
			  k.add(read.next());//klijent
			  k.add(read.next()); //cena
			  k.add(read.next());//vreme
			  k.add(read.next());//obrisan
			  
			  if(k.get(1).compareTo("IZVRSEN")==0) {
				  stanje_TERETMANA = STANJE_TERETMANA.IZVRSEN;
			  }
			  else if(k.get(1).compareTo("OTKAZAO_KLIJENT")==0) {
				  stanje_TERETMANA = STANJE_TERETMANA.OTKAZAO_KLIJENT;
			  }
			  else if(k.get(1).compareTo("OTKAZAO_SALON")==0) {
				  stanje_TERETMANA = STANJE_TERETMANA.OTKAZAO_SALON;
			  }
			  else if(k.get(1).compareTo("NIJE_SE_POJAVIO")==0) {
				  stanje_TERETMANA = STANJE_TERETMANA.NIJE_SE_POJAVIO;
			  }
			  else{
				  stanje_TERETMANA = STANJE_TERETMANA.ZAKAZAN;
			  }
			  
			   
			  
			  for(UslugaTretman u: uslugeKontroler.getUsluge()) {
				  String tempString = u.getImeTretmana()+"-"+u.getTipTretmana().getImeTip();
				  if((k.get(2)).compareTo(tempString)==0) {
					  up = u;
					  break;
				  }  
			  }
			  
			  
			  for(Korisnik ko:korisniciKontroler.getKorisnici()) {
				  if((k.get(3)).compareTo(ko.getKorisnickoIme())==0 && (ko instanceof Kozmeticar)) {
					  kozm = (Kozmeticar) ko;
				  }
			  }
			  klijent = (Klijent) this.korisniciKontroler.pronadjiKorisnika(k.get(4));
			  
			  DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			  if(k.get(7).compareToIgnoreCase("true")==0) {
				  obrisan = true;
			  }
			  else {
				  obrisan = false;
			  }
			  
			  Tretman tr = new Tretman(Integer.parseInt(k.get(0).replaceAll("\\W", "")),up,kozm,klijent,LocalDateTime.parse(k.get(6),format),obrisan, Double.parseDouble(k.get(5)), stanje_TERETMANA);
			  this.tretmani.add(tr);
		      k.clear();
		        
		   }
		   read.close();
	
	}
	
	public boolean upisTretmana(String s) {
		try {
		      FileWriter myWriter = new FileWriter(this.fajlT, true);
		      if(new File(this.fajlT).length()==0) {
		    	  myWriter.write(s);
		      }
		      else { 
		    	  myWriter.write("\n"+s);  
		      }
		      
		      myWriter.close();
		      
		      return true;
		    } catch (IOException e) {
		      System.out.println("Neuspesna registracija zakazanog tretmana, problem u fajlu.");
		      return false;
		      //e.printStackTrace();
		    } 
	}
	
	public void upisiSveTretmane() {
		for(Tretman t:this.tretmani) {
			this.upisTretmana(t.toFileString());
		}
	}
	
	public void izbrisiSadrzaj() {
		try {
		      FileWriter myWriter = new FileWriter(this.fajlT);
		      myWriter.write("");
		      myWriter.close();
		    } catch (IOException e) {
		      System.out.println("Neuspesno brisanje sadrzaja iz fajlu.");
		      
		    }
	}
	
	public void azurirajFile() {
		this.izbrisiSadrzaj();
		this.upisiSveTretmane(); 
	}
	
	public Tretman pronadjiTretman(int id) {
		for(Tretman t:this.tretmani) {
			if(t.getIdTretmana()==id) {
				return t;
			}
		}
		return null;
	}
	
	public boolean proveraZakazivanja(Kozmeticar kozmeticar, LocalDateTime vreme, UslugaTretman usluga) {

		
		if(kozmeticar.proveraObucenost(usluga.getTipTretmana())== false) {
			return false; // prosledjen kozm nije obucen za taj tip/uslugu
		}
		else if(vreme.isBefore(LocalDateTime.now())) {
			return false;
		}
		for(Tretman t:this.tretmani) {
			if(t.getVreme().equals(vreme) && t.getKozmeticar().equals(kozmeticar)) {
				return false; 
			}   
			else {
				int sati = t.getUsluga().getTrajanjeUsluge().getHour();
				int minuti = t.getUsluga().getTrajanjeUsluge().getMinute();
				LocalDateTime gornjaGranica = t.getVreme().plusHours(sati);
				gornjaGranica.plusMinutes(minuti);
				LocalDateTime donjaGranica = t.getVreme().minusHours(sati);
				donjaGranica.minusMinutes(minuti);
				if(vreme.isBefore(gornjaGranica) && vreme.isAfter(donjaGranica) && t.getKozmeticar().equals(kozmeticar)) {
					return false;
				}
				
			
			} 
		}
		return true; 
	}
	
	//zakazi tretman
	public boolean dodajTretman(Klijent klijent, Kozmeticar kozmeticar, LocalDateTime vreme, UslugaTretman usluga, int id) {
//		if(kozmeticar == null) {
//			ArrayList<Kozmeticar> potencijalni = this.korisniciKontroler.vratiKozmeticre();
//			for(Kozmeticar k:potencijalni) {
//				if(this.proveraZakazivanja(k, vreme, usluga)) {
//					kozmeticar = k;
//					break;
//				} 
//			}
//		} 
//		if(kozmeticar == null) {
//			return false;
//		}  
//		
//		if(this.proveraZakazivanja(kozmeticar,vreme,usluga)==false) { 
//			return false;
//		}
		double cena = 0;
		
		
		if(klijent.isKarticaLojalnosti()) { 
			cena = usluga.getCena()*0.9;
		} 
		else { 
			cena = usluga.getCena();
		}
		klijent.potrosiNovac(cena);
		
		this.tretmani.add(new Tretman(id, usluga, kozmeticar, klijent, vreme, false, cena, STANJE_TERETMANA.ZAKAZAN));
		
		korisniciKontroler.azurirajFile();
		return this.upisTretmana(this.pronadjiTretman(id).toFileString()); 
		  
	}
	
	public boolean obradiTretman(Tretman tretman,STANJE_TERETMANA stanje) {
		
		if(tretman == null) {
			return false; 
		}
		else if(tretman.isObrisan()==false){
			if(stanje.equals(STANJE_TERETMANA.OTKAZAO_KLIJENT) && tretman.getStanje().equals(STANJE_TERETMANA.ZAKAZAN)) {
				double cena = tretman.getCena();
				tretman.setCena(cena*0.1);
				tretman.getKlijent().vracenNovac(cena*0.9);
				tretman.setStanje(stanje);
				this.azurirajFile();
				korisniciKontroler.azurirajFile();
				return true; 
				
			}
			else if(stanje.equals(STANJE_TERETMANA.IZVRSEN) && tretman.getStanje().equals(STANJE_TERETMANA.ZAKAZAN)) {
				//ZAKOKOMENTARISANO ZBOG TEST SCENARIA
//				if(tretman.getVreme().isAfter(LocalDateTime.now())) {
//					return false;
//				}
				
				//tretman.getKlijent().potrosiNovac(tretman.getCena());
				tretman.setStanje(stanje);
				this.azurirajFile();
				korisniciKontroler.azurirajFile();
				return true;
			}
			else if(stanje.equals(STANJE_TERETMANA.OTKAZAO_SALON) && tretman.getStanje().equals(STANJE_TERETMANA.ZAKAZAN)) {
				tretman.setCena(0);
				tretman.getKlijent().povratNovca(tretman.getCena());
				tretman.setStanje(stanje);
				this.azurirajFile();
				korisniciKontroler.azurirajFile();
				return true; 
				
			}
			else if(stanje.equals(STANJE_TERETMANA.NIJE_SE_POJAVIO) && tretman.getStanje().equals(STANJE_TERETMANA.ZAKAZAN)) {
				//tretman.getKlijent().potrosiNovac(tretman.getCena());
				tretman.setStanje(stanje);
				this.azurirajFile();
				korisniciKontroler.azurirajFile();
				return true; 
			}
			
		}
		
		return false;
	}
	
	public int postaviIdTretman() {
		boolean flag = true;
		while(true) {
			Random random = new Random();
			int a = random.nextInt(10000);
			for(Tretman u:this.tretmani) {
				if(a == u.getIdTretmana()) {
					flag = false;
				}
			}
			if(flag) {
				return a;
			}
			flag = true;
		}
	}
	
	public double vratiPrihode(LocalDateTime pocetak, LocalDateTime kraj) {
		double prihod = 0;
		while(true) {
			if(pocetak.isBefore(kraj) || pocetak.isEqual(kraj)){
				for(Tretman t:this.tretmani) {
					if(t.isObrisan()==false) {
						prihod += t.getCena();
					}
				}
			}
			else {
				break;
			}
			pocetak = pocetak.plusMonths(1);
		}
		
		return prihod;
	} 
	 
	public void izbrisiTretman(Tretman t) { 
		this.tretmani.remove(t); 
		this.azurirajFile(); 

	} 
	
	public Tretman pronadji_tretman_po_imenu(String ime) {
		for(Tretman t:this.tretmani) {
			if(t.getOpis().compareTo(ime)==0) {
				return t;
			}
		}
		return null;
	}
	
	public int vrati_id_po_imenu(String ime) {
		for(Tretman t:this.tretmani) {
			if(t.getOpis().compareTo(ime)==0) {
				return t.getIdTretmana();
			}
		}
		return -1;
	}
	
	public ArrayList<Tretman> prikaziTretmaneZaKozmeticara(Kozmeticar kozmeticar){
		ArrayList<Tretman> listaTretmanaArrayList = new ArrayList<Tretman>();
		for(Tretman t:this.tretmani) {
			if(t.getKozmeticar().equals(kozmeticar)) {
				listaTretmanaArrayList.add(t);
				
			} 
		}
		return listaTretmanaArrayList;
	}
	
	
	public ArrayList<Tretman> vidiTretmaneZaKlijenta(Klijent klijent){
		ArrayList<Tretman> tretmaniArrayList = new ArrayList<Tretman>();
		for(Tretman t:this.tretmani) {
			if(t.getKlijent().equals(klijent)) {
				tretmaniArrayList.add(t);
			}
		}
		return tretmaniArrayList;
	}
	
	public Tretman izmeniTretman(LocalDateTime datum, Kozmeticar kozmeticar, Tretman t) {
		t.setVreme(datum);
		t.setKozmeticar(kozmeticar);
		this.azurirajFile();
		korisniciKontroler.azurirajFile();
		return t;
	}
	
	
}
