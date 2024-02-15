package kontroleri;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;


import entiteti.Klijent;
import entiteti.Korisnik;
import entiteti.Kozmeticar;
import entiteti.Menadzer;
import entiteti.Recepcioner;
import entiteti.STANJE_TERETMANA;
import entiteti.STRUCNA_SPREMA;
import entiteti.TipTretmana;
import entiteti.Tretman;
import entiteti.Zaposlen;

public class KorisniciKontroler {
	private ArrayList<Korisnik> korisnici;
	private String fajl;
	private TipTretmanaKontroler tipovi;
	
	private static boolean flag;
	
	public KorisniciKontroler(String s, TipTretmanaKontroler t) {
		this.fajl = s;
		korisnici = new ArrayList<Korisnik>();
		this.tipovi = t;
	}

	public ArrayList<Korisnik> getKorisnici() {
		return korisnici;
	}

	public void setKorisnici(ArrayList<Korisnik> korisnici) {
		this.korisnici = korisnici;
	} 
	
	public void ucitajKorisnike(){
		Scanner read = null;
		try {
			read = new Scanner (new File(this.fajl));
		} catch (FileNotFoundException e) {
			System.out.println("Fajl nije pronadjen.");
			e.printStackTrace();
		}
		   read.useDelimiter(",");
		   ArrayList<String> k = new ArrayList<String>();
		   ArrayList<TipTretmana> tipp = new ArrayList<TipTretmana>();
		   STRUCNA_SPREMA sprema = null;
		   boolean karL = false;
		   while(read.hasNext())
		   {
		       k.add(read.next()); //tip korisnika
		       k.add(read.next()); //ime
		       k.add(read.next()); //prezime
		       k.add(read.next()); // telefon
		       k.add(read.next());  //adres
		       k.add(read.next());  //kor ime
		       k.add(read.next()); // loz

		       
		       if(k.get(0).compareTo("\nKL")!=0 && k.get(0).compareTo("KL") != 0) {
		    	   k.add(read.next()); //strucna sprema
		    	   if(Double.parseDouble(k.get(7))==0.2) {
		    		   sprema = STRUCNA_SPREMA.NISKA;
		    	   }
		    	   else if(Double.parseDouble(k.get(7))==0.4) {
		    		   sprema = STRUCNA_SPREMA.SREDNJE_NISKA;
		    	   }
		    	   else if(Double.parseDouble(k.get(7))==0.6) {
		    		   sprema = STRUCNA_SPREMA.SREDNJA;
		    	   }
		    	   else if(Double.parseDouble(k.get(7))==0.8) {
		    		   sprema = STRUCNA_SPREMA.SREDNJE_VISOKA;
		    	   }
		    	   else { 
		    		   sprema = STRUCNA_SPREMA.VISOKA;
		    	   }
			       k.add(read.next()); // radni staz
			       k.add(read.next()); // bonus
			       k.add(read.next());	//osnova plate
			       k.add(read.next()); // plata
			       if(k.get(0).compareTo("\nK")==0 || k.get(0).compareTo("K") == 0) {
			    	   tipp = new ArrayList<TipTretmana>();
			    	   String tempString = "";
			    	   while(read.hasNext("kraj")==false) {
			    		   tempString = read.next(); // obucenost
			    		   for(TipTretmana t:this.tipovi.getTipovi()) {
			    			   if(tempString.compareTo(t.getImeTip())==0) {
			    				   tipp.add(t);
			    			   }
			    		   }
			    	   }  
			    	   read.next();  
			       }   
		       }
		       else {
		    	   k.add(read.next());
		    	   k.add(read.next());
		    	   if(k.get(7).compareToIgnoreCase("false")==0) {
		    		   karL = false;
		    	   }
		    	   else {
		    		   karL = true;
		    	   }
		       } 
		       
		       if(k.get(0).compareTo("\nM") == 0 || k.get(0).compareTo("M") == 0) {
					Menadzer m = new Menadzer(k.get(1), k.get(2),Integer.parseInt(k.get(3)), k.get(4), k.get(5),k.get(6), sprema, Integer.parseInt(k.get(8)), Double.parseDouble(k.get(10)),Double.parseDouble(k.get(9)));
					korisnici.add(m); 
				}
				else if((k.get(0)).compareTo("\nR") == 0 || k.get(0).compareTo("R") == 0) {
					Recepcioner r = new Recepcioner(k.get(1), k.get(2),Integer.parseInt(k.get(3)), k.get(4), k.get(5),k.get(6), sprema, Integer.parseInt(k.get(8)), Double.parseDouble(k.get(10)), Double.parseDouble(k.get(9)));
					korisnici.add(r);
				}
				else if(k.get(0).compareTo("\nK")==0 || k.get(0).compareTo("K") == 0) {
					
					Kozmeticar ko = new Kozmeticar(k.get(1), k.get(2),Integer.parseInt(k.get(3)), k.get(4), k.get(5),k.get(6),sprema, Integer.parseInt(k.get(8)), Double.parseDouble(k.get(10)), Double.parseDouble(k.get(9)),tipp);
					
					korisnici.add(ko);
				}
				else if(k.get(0).compareTo("\nKL")==0 || k.get(0).compareTo("KL") == 0){
					Klijent ki = new Klijent(k.get(1), k.get(2),Integer.parseInt(k.get(3)), k.get(4), k.get(5),k.get(6),karL, Double.parseDouble(k.get(8)));
					korisnici.add(ki);
				}
				
		       k.clear();
		       
		   }
		   read.close();
		   
	}
	
	public boolean upisKorisnike(String s, String t) { 
		try {
		      FileWriter myWriter = new FileWriter(this.fajl, true);
		      if(new File(this.fajl).length()==0){
			      myWriter.write(t+","+s);

		      }
		      else {
			      myWriter.write("\n"+t+","+s);

		      }
		      myWriter.close(); 
		      return true;
		    } catch (IOException e) {
		      System.out.println("Neuspesna registracija korisnika, problem u fajlu.");
		      return false;
		    }
	}
	
	public void upisiSveKorisnike() {
		for(Korisnik k:this.korisnici) {
			if(k instanceof Menadzer) {
				this.upisKorisnike(((Menadzer) k).toFileString(), "M");
				
			}
			else if(k instanceof Kozmeticar) {
				this.upisKorisnike(((Kozmeticar) k).toFileString(), "K");
				
			}
			else if(k instanceof Recepcioner) {
				this.upisKorisnike(((Recepcioner) k).toFileString(), "R");
				
			}
			else {
				this.upisKorisnike(((Klijent) k).toFileString(), "KL");
				
			}
			
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
	
	public void azurirajFile() {
		this.izbrisiSadrzajFajla();
		this.upisiSveKorisnike();
	}
	


	public void setKarticeLojalnosti(int limit) {
		for(Korisnik korisnik :this.korisnici) {
			if(korisnik instanceof Klijent) {
				((Klijent)korisnik).setKarticaLojalnosti(false);
				if (((Klijent) korisnik).getPotrosenNovac() >= limit) {
					((Klijent) korisnik).setKarticaLojalnosti(true);
				}
			}
		}
	}
	
	public double vratiRashode(LocalDateTime pocetak, LocalDateTime kraj) { 
		double rashodi = 0;
		if(pocetak.isAfter(kraj)) {
			return 0;
		}
		while(true) {
			if(pocetak.isBefore(kraj) || pocetak.isEqual(kraj)) {
				for(Korisnik k:this.korisnici) {
					if(k instanceof Zaposlen) {
						rashodi += ((Zaposlen) k).getPlata();
					}
				}
				
			}
			else {
				break;
			} 
			pocetak = pocetak.plusMonths(1);
			
		}
		 
		return rashodi; 
	}
  
	public void setBonuse(ArrayList<Tretman> tretmani, int odradjeniTretmani, int prihod, LocalDate pocetak,LocalDate kraj, int osnovaBonus) {
		int br = 0;
		double p = 0;
		
		for(Kozmeticar k:this.vratiKozmeticre()) {
			k.setBonus(0);
			for(Tretman t:tretmani) {
				if(t.getVreme().toLocalDate().isBefore(kraj) && t.getVreme().toLocalDate().isAfter(pocetak)) {
					if(t.getKozmeticar().equals(k)) {
						p += t.getCena();
						if(t.getStanje() == STANJE_TERETMANA.IZVRSEN || t.getStanje() == STANJE_TERETMANA.NIJE_SE_POJAVIO) {
							br++;
						}
					}
				}
				
				
			}
			if(br>odradjeniTretmani) {
				k.setBonus(osnovaBonus);
			}
			if(p>prihod) {
				k.setBonus(osnovaBonus * 1.5);
			}
			if(br > odradjeniTretmani && p > prihod) {
				k.setBonus(osnovaBonus * 2);
			}
			
			p = 0;
			br = 0;
		}
		
		
		
		
	}
	
	public ArrayList<Klijent> getKlijenti(){
		ArrayList<Klijent> klijents = new ArrayList<Klijent>();
		for(Korisnik k:this.korisnici) {
			if(k instanceof Klijent) {
				klijents.add((Klijent) k);
			}
		}
		return klijents;
	}
	public ArrayList<Recepcioner> getRecepcioner(){
		ArrayList<Recepcioner> recepcioners = new ArrayList<Recepcioner>();
		for(Korisnik k:this.korisnici) {
			if(k instanceof Recepcioner) {
				recepcioners.add((Recepcioner) k);
			}
		}
		return recepcioners;
	}
	public ArrayList<Menadzer> getMenadzeri(){
		ArrayList<Menadzer> menadzers = new ArrayList<Menadzer>();
		for(Korisnik k:this.korisnici) {
			if(k instanceof Menadzer) {
				menadzers.add((Menadzer) k);
			}
		}
		return menadzers;
	}
	public ArrayList<Kozmeticar> getKozmeticari(){
		ArrayList<Kozmeticar> kozmeticars = new ArrayList<Kozmeticar>();
		for(Korisnik k:this.korisnici) {
			if(k instanceof Kozmeticar) {
				kozmeticars.add((Kozmeticar) k);
			}
		}
		return kozmeticars;
	}
	
	public boolean registrujKlijenta(Klijent k) {
		flag = false;
		this.korisnici.add(k);
		flag = this.upisKorisnike(k.toFileString(), "KL");
		return flag;
	} 
	public boolean registrujMenadzera(Menadzer m) {
		flag = false;
		this.korisnici.add(m);
		flag = this.upisKorisnike(m.toFileString(), "M");
		return flag; 
	}
	public boolean registrujKozmeticara(Kozmeticar k) {
		flag = false;
		this.korisnici.add(k);
		flag = this.upisKorisnike(k.toFileString(),"K");
		return flag;
	}
	public boolean registrujRecepcionera(Recepcioner r) {
		flag = false;
		this.korisnici.add(r);
		flag = this.upisKorisnike(r.toFileString(),"R");
		return flag; 
	}
	
	public void vidiKorisnike() {
		for(Korisnik k:this.korisnici) {
			System.out.println(k.toString());
		}
	}
	
	public Korisnik pronadjiKorisnika(String s) {
		for(Korisnik k:this.korisnici) {
			if(s.compareTo(k.getKorisnickoIme())==0) {
				return k;
			}
		}
		return null;
	}
	
	public void izbrisiKorisnika(Korisnik k) {
		if(this.korisnici.size()>0) {
			this.korisnici.remove(k);
			this.azurirajFile();
		}

	}
	
	
	
	public void editObucenostZaKozmeticara(Kozmeticar k, TipTretmana t) {
		k.dodajObucenost(t);
		this.azurirajFile();
	}
	
	public Zaposlen editZaposlen(String ime, String prezime,int broj, String adresa, String korime, String pass,STRUCNA_SPREMA sprema,int staz, double osnova) {
		Zaposlen zaposlen = (Zaposlen) this.pronadjiKorisnika(korime);
		if(zaposlen!=null) {
			zaposlen.setIme(ime);
			zaposlen.setPrezime(prezime);
			zaposlen.setTelefon(broj);
			zaposlen.setAdresa(adresa);
			zaposlen.setLozinka(pass);
			Double sDouble = 0.0;
			if(STRUCNA_SPREMA.NISKA.equals(sprema)) {
				sDouble = 0.2;
			}
			else if(STRUCNA_SPREMA.SREDNJE_NISKA.equals(sprema)) {
				sDouble = 0.4;
			}
			else if(STRUCNA_SPREMA.SREDNJA.equals(sprema)) {
				sDouble = 0.6;
			}
			else if(STRUCNA_SPREMA.SREDNJE_VISOKA.equals(sprema)) {
				sDouble = 0.8;
			}
			else {
				sDouble = 1.0;
			}
			zaposlen.setStrucnaSprema(sDouble);
			zaposlen.setRadniStaz(staz);
			zaposlen.setOsnovaPlate(osnova);
		}
		return zaposlen;
	}
	
	public Klijent editKlijent(String ime, String prezime,int broj, String adresa, String korime, String pass) {
		Klijent klijent = (Klijent) this.pronadjiKorisnika(korime);
		if(klijent!=null) {
			klijent.setIme(ime);
			klijent.setPrezime(prezime);
			klijent.setTelefon(broj);
			klijent.setAdresa(adresa);
			klijent.setLozinka(pass);
			
		}
		return klijent;
	}
	
	
 

	public ArrayList<Kozmeticar> vratiKozmeticre(){
		ArrayList<Kozmeticar> kzm = new ArrayList<Kozmeticar>();
		for(Korisnik k:this.korisnici) {
			if(k instanceof Kozmeticar) {
				kzm.add((Kozmeticar)k);
			}
		}
		return kzm;
	}
	
}
