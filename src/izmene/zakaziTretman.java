package izmene;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import entiteti.Klijent;
import entiteti.Korisnik;
import entiteti.Kozmeticar;
import entiteti.TipTretmana;
import entiteti.Tretman;
import entiteti.UslugaTretman;
import kontroleri.Kontroler;
import kontroleri.KorisniciKontroler;
import kontroleri.TipTretmanaKontroler;
import kontroleri.UslugaTretmanaKontroler;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class zakaziTretman extends JDialog {

	private static final long serialVersionUID = 4834174530742092647L;
	private JTextField DatumTxt;
	private JTextField VremeTxt;
	private JTextField trajanjeTxt;
	private JTextField cenaTxt;

	
	public zakaziTretman(JFrame f, UslugaTretmanaKontroler uslugaTretmanaKontroler, KorisniciKontroler korisniciKontroler, TipTretmanaKontroler tipTretmanaKontroler, Kontroler kontroler, Klijent klijent, JLabel novac) {
		setTitle("Zakazivanje tretmana");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f.setEnabled(true);
			}
		});
		
		getContentPane().setLayout(new MigLayout("", "[130,grow][130,grow][130,grow][130,grow]", "[60][60][60][60][60][60]10[60]15[60]"));
		
		JLabel lblNewLabel = new JLabel("Usluga tretmana:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblNewLabel, "cell 0 0 2 1,alignx center");
		
		JButton filtrirajBtn = new JButton("Filtriraj (click)");
		filtrirajBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(filtrirajBtn, "cell 2 0 2 1,alignx center,growy");
		
		JLabel lblNewLabel_2 = new JLabel("Tip tretmana:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblNewLabel_2, "cell 2 1 2 1,alignx center");
		
		ArrayList<String> usluge = new ArrayList<String>();
		for(UslugaTretman u:uslugaTretmanaKontroler.getUsluge()) {
			usluge.add(u.getImeTretmana());
		}
		String[] aaaStrings = new String[usluge.size()+1];
		for(int i = 0;i < usluge.size();i++) {
			aaaStrings[i] = usluge.get(i);
		}
		aaaStrings[usluge.size()] = " ";
		JComboBox<String> comboBox = new JComboBox<String>(aaaStrings);
		getContentPane().add(comboBox, "cell 0 1 2 1,alignx center");
		comboBox.setSelectedItem(" ");

		
		
		JLabel lblKozmeticar = new JLabel("Kozmeticar:");
		lblKozmeticar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblKozmeticar, "cell 0 2 2 1,alignx center");
		
		
		ArrayList<String> tipoviArrayList = new ArrayList<String>();
		for(TipTretmana t:tipTretmanaKontroler.getTipovi()) {
			tipoviArrayList.add(t.getImeTip());
		}
		
		String[] tsStrings = new String[tipoviArrayList.size()+1];
		for(int i = 0;i < tipoviArrayList.size();i++) {
			tsStrings[i]=tipoviArrayList.get(i);
		}
		tsStrings[tipoviArrayList.size()] = " ";
		
		JComboBox<String> comboBox2 = new JComboBox<String>(tsStrings);
		comboBox2.setEditable(false);
		getContentPane().add(comboBox2, "cell 2 2 2 1,alignx center");
		comboBox2.setSelectedItem(" ");
		comboBox2.setEnabled(false);
		
		JLabel lblNewLabel_2_1 = new JLabel("Trajanje (HH:MM):");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblNewLabel_2_1, "cell 2 3 2 1,alignx center");
		
		ArrayList<String> kozmeticariArrayList = new ArrayList<String>();
		for(Kozmeticar k:korisniciKontroler.getKozmeticari()) {
			kozmeticariArrayList.add(k.getKorisnickoIme());
			
		}
		String[] skStrings = new String[kozmeticariArrayList.size()+1];
		for(int i =0; i< kozmeticariArrayList.size();i++) {
			skStrings[i] = kozmeticariArrayList.get(i);
		}
		skStrings[kozmeticariArrayList.size()] = " ";
		
		JComboBox<String> comboBox1 = new JComboBox<String>(skStrings);
		getContentPane().add(comboBox1, "cell 0 3 2 1,alignx center");
		comboBox1.setSelectedItem(" ");
		comboBox1.setEnabled(false);
		
		JLabel lblDatum = new JLabel("Datum (YYYY-MM-DD):");
		lblDatum.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblDatum, "cell 0 4,alignx center");
		
		JLabel lblVreme = new JLabel("Vreme (HH:MM):");
		lblVreme.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblVreme, "cell 1 4,alignx center");
		
		trajanjeTxt = new JTextField();
		trajanjeTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		trajanjeTxt.setHorizontalAlignment(SwingConstants.CENTER);
		trajanjeTxt.setEditable(false);
		trajanjeTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(trajanjeTxt, "cell 2 4 2 1,alignx center,growy");
		trajanjeTxt.setColumns(10);
		
		DatumTxt = new JTextField();
		DatumTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		DatumTxt.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(DatumTxt, "cell 0 5,grow");
		DatumTxt.setColumns(10);
		DatumTxt.setEditable(false);
		
		VremeTxt = new JTextField();
		VremeTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		VremeTxt.setHorizontalAlignment(SwingConstants.CENTER);
		VremeTxt.setColumns(10);
		VremeTxt.setEditable(false);
		getContentPane().add(VremeTxt, "cell 1 5,grow");
		
		JLabel lblNewLabel_2_2 = new JLabel("Cena:");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblNewLabel_2_2, "cell 2 5 2 1,alignx center");
		
		cenaTxt = new JTextField();
		cenaTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cenaTxt.setHorizontalAlignment(SwingConstants.CENTER);
		cenaTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cenaTxt.setColumns(10);
		cenaTxt.setEditable(false);
		getContentPane().add(cenaTxt, "cell 2 6 2 1,alignx center,growy");
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(btnCancel, "cell 0 7,grow");
		
		JButton btnNewButton = new JButton("Zakazi tretman");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(btnNewButton, "cell 3 7,grow");
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UslugaTretman uslugaTretman = uslugaTretmanaKontroler.pronadjiUsluguIme(comboBox.getSelectedItem().toString());
				Korisnik korisnik = korisniciKontroler.pronadjiKorisnika(comboBox1.getSelectedItem().toString());
				boolean flag = false;
				if(comboBox1.getItemCount()==1) {
					JOptionPane.showMessageDialog(null, "Za ovu vrstu tretmana ne postoji kozmeticar");
					flag = false;
					return;
				}
				else if(comboBox1.getSelectedIndex()==comboBox1.getItemCount()-1) {
					comboBox1.setSelectedIndex(0);
				}
				flag = provera_za_zakazivanje(comboBox.getSelectedItem().toString(), comboBox1.getSelectedItem().toString(), DatumTxt.getText(), VremeTxt.getText(), kontroler, uslugaTretman, (Kozmeticar)korisnik,true);
				

				
				if(flag) {
					LocalDate datum = LocalDate.parse(DatumTxt.getText());
					LocalTime vremeLocalTime = LocalTime.parse(VremeTxt.getText());
					kontroler.getTretmanKontroler().dodajTretman(klijent, (Kozmeticar)korisnik, datum.atTime(vremeLocalTime), uslugaTretman, kontroler.getTretmanKontroler().postaviIdTretman());
					JOptionPane.showMessageDialog(null, "Uspesno zakazan tretman!");
					f.setEnabled(true);
					zakaziTretman.this.dispose();
					if(novac!=null) {
						novac.setText("Potrosen novac: " + klijent.getPotrosenNovac());

					}
					
				}
				
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				f.setEnabled(true);
				zakaziTretman.this.dispose();
				
			}
		});
		
		
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedIndex()!=comboBox.getItemCount()-1) {
					comboBox1.setEnabled(true);
					DatumTxt.setEditable(true);
					VremeTxt.setEditable(true);
					kozmeticariArrayList.clear();
					UslugaTretman uTemp = uslugaTretmanaKontroler.pronadjiUsluguIme(comboBox.getSelectedItem().toString());
					if(uTemp != null) {
						
						for(Kozmeticar k:korisniciKontroler.getKozmeticari()) {
							
							if(k.proveraObucenost(uTemp.getTipTretmana())) {
								kozmeticariArrayList.add(k.getKorisnickoIme());
							}
					
						}
	
					}
					String[] skStrings = new String[kozmeticariArrayList.size()+1];
					for(int i =0; i< kozmeticariArrayList.size();i++) {
						skStrings[i] = kozmeticariArrayList.get(i);
					}
					skStrings[kozmeticariArrayList.size()] = " ";
					DefaultComboBoxModel<String> model1 = new DefaultComboBoxModel<>(skStrings);
					
					comboBox1.removeAllItems();
					comboBox1.setModel(model1);
				}
				else {
					comboBox1.setEnabled(false);
					DatumTxt.setEditable(false);
					VremeTxt.setEditable(false);
				}
				
			}
		});
		
		filtrirajBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				if(filtrirajBtn.getText().compareTo("Zavrsi filtriranje")==0) {


					
					String trajanjeString = "";
					String cenaString = "";
					TipTretmana tp = null;
					if(comboBox2.getSelectedIndex()!=comboBox2.getItemCount()-1) {
						tp = tipTretmanaKontroler.pronadjiTipPoImenu(comboBox2.getSelectedItem().toString());
						
					}
					if(trajanjeTxt.getText().compareTo("")!=0) {
						trajanjeString = trajanjeTxt.getText(); 
					}
					if(cenaTxt.getText().compareTo("")!=0) {
						cenaString = cenaTxt.getText();
					}
					boolean flagBoolean = provera_cena_trajanje(cenaString, trajanjeString);
					if(flagBoolean) {
						comboBox2.setEnabled(false);
						trajanjeTxt.setEditable(false);
						cenaTxt.setEditable(false);
						
						comboBox.setEnabled(true);
						comboBox.setSelectedItem(" ");
						comboBox1.setSelectedItem(" ");
						DatumTxt.setText("");
						VremeTxt.setText("");
						filtrirajBtn.setText("Filtriraj (click)");
						LocalTime localTime = null;
						if(trajanjeString.compareTo("")!=0) {
							localTime = LocalTime.parse(trajanjeString);
							System.out.println(localTime);
						}
						
						usluge.clear();
						for(UslugaTretman u:uslugaTretmanaKontroler.getUsluge()) {
							
							if((tp == null || u.getTipTretmana() == tp) && (localTime == null || localTime.equals(u.getTrajanjeUsluge())) &&(cenaString.compareTo("")==0 || u.getCena() == Double.parseDouble(cenaString))) {
								usluge.add(u.getImeTretmana());
							}
						}
						String[] usStrings = new String[usluge.size()+1]; 
						for(int i =0; i< usluge.size();i++) {
							usStrings[i] = usluge.get(i);
						}
						usStrings[usluge.size()] = " ";
						DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(usStrings);
						
						comboBox.removeAllItems();
						comboBox.setModel(model);
						comboBox.setSelectedItem(" "); 
					}

					
				}
				else {
					comboBox.setEnabled(false);
					comboBox1.setEnabled(false);
					DatumTxt.setEditable(false);
					VremeTxt.setEditable(false);
					
					comboBox2.setEnabled(true);
					comboBox2.setSelectedItem(" ");
					trajanjeTxt.setText("");
					cenaTxt.setText("");
					trajanjeTxt.setEditable(true);
					cenaTxt.setEditable(true);
					filtrirajBtn.setText("Zavrsi filtriranje");
				}
				
				
			}
		});
		
		
		
		this.pack();
		setLocationRelativeTo(null);

		
		
	}
	
	private boolean provera_cena_trajanje(String cena, String trajanje) {
		if(trajanje.compareTo("")!=0) {
			Pattern regexPattern = Pattern.compile("^([01]\\d|2[0-3]):([0-5]\\d)$");

			if(regexPattern.matcher(trajanje).matches()==false) {
				JOptionPane.showMessageDialog(null, "Format za trajanje mora biti HH:MM");
				return false;
			}
		}
		if(cena.compareTo("")!=0) {
			Pattern regexPattern = Pattern.compile("(?:[1-9]\\d*|0)(\\.\\d+)?");

			if(regexPattern.matcher(cena).matches()==false) {
				JOptionPane.showMessageDialog(null, "Cena mora biti broj");
				return false;
			}
		}
		return true;
	}
	
	public boolean provera_za_zakazivanje(String usluga, String kozmeticar,String datum,String vreme,Kontroler kontroler, UslugaTretman uslgaTretman, Kozmeticar kozmeticarK, boolean pokazi_por) {
		
		
		if(usluga.compareTo("")==0 || usluga.compareTo(" ")==0) {
			if(pokazi_por) {
				JOptionPane.showMessageDialog(null, "Niste uneli uslugu.");

			}
			return false;
		}
		
		if(datum.compareTo("")==0) {
			if(pokazi_por) {
				JOptionPane.showMessageDialog(null, "Niste uneli datum.");
			}
			return false;
		}
		if(vreme.compareTo("")==0) {
			if(pokazi_por) {
				JOptionPane.showMessageDialog(null, "Niste uneli vreme.");
			}
			return false;
		}
		if(vreme.compareTo("")!=0) {
			Pattern regexPattern = Pattern.compile("^([01]\\d|2[0-3]):([0-5]\\d)$");

			if(regexPattern.matcher(vreme).matches()==false) {
				if(pokazi_por) {
					JOptionPane.showMessageDialog(null, "Format za vreme mora biti HH:MM");
				}
				return false;
			}
		}
		if(datum.compareTo("")!=0) {
			Pattern regexPattern = Pattern.compile("\\d{4}-(0[1-9]|1[0-2])-([0-2][1-9]|3[0-1])");

			if(regexPattern.matcher(datum).matches()==false) {
				if(pokazi_por) {
					JOptionPane.showMessageDialog(null, "Format za datum mora biti YYYY-MM-DD");
				}
				return false;
			}
		}
		 LocalDate localDate = LocalDate.parse(datum);
		 if(!localDate.isAfter(LocalDate.now())){
			 if(pokazi_por) {
				 JOptionPane.showMessageDialog(null, "Vreme ne sme biti u proslosti.");
			 }
			 return false;
		 }
		 LocalTime vremeLocalTime = LocalTime.parse(vreme);
		 if(vremeLocalTime.isBefore(kontroler.getPocetakRadnogVremena())) {
			if(pokazi_por) {
				 JOptionPane.showMessageDialog(null, "Vreme je pre radnog vremena");
			 } 
			return false;
		 }
		 LocalTime sumTime = vremeLocalTime.plusHours(uslgaTretman.getTrajanjeUsluge().getHour()).plusMinutes(uslgaTretman.getTrajanjeUsluge().getMinute());
		 if(kontroler.getKrajRadnogVremena().isBefore(sumTime)) {
			if(pokazi_por) {
				 JOptionPane.showMessageDialog(null, "Vreme, odnosno dok bi se zvrsio tretman je posle radnog vremena");
			 } 
			return false;
		 }
		 
		 if(!kozmeticarK.proveraObucenost(uslgaTretman.getTipTretmana())) {
			 if(pokazi_por) {
				 JOptionPane.showMessageDialog(null, "Kozmeticar nije obucen za ovaj tip.");
			 }
			 return false;
		 }
		 LocalDateTime tempVremeDateTime = localDate.atTime(vremeLocalTime);
		 

		 for(Tretman t:kontroler.getTretmanKontroler().getTretmani()) {
			 if(t.getKozmeticar() == kozmeticarK && t.getVreme().toLocalDate().equals(localDate)) {
				 LocalDateTime vremeZavrsetkaDateTime = t.getVreme().plusHours(uslgaTretman.getTrajanjeUsluge().getHour()).plusMinutes(uslgaTretman.getTrajanjeUsluge().getMinute());
				 if(t.getVreme().equals(tempVremeDateTime)) {
					 if(pokazi_por) {
						 JOptionPane.showMessageDialog(null, "U ovo vreme kozmeticar je zauzet.");
					 }
					 return false;
				 }
				 if(localDate.atTime(sumTime).isAfter(t.getVreme()) && localDate.atTime(sumTime).isBefore(vremeZavrsetkaDateTime)) {
					 if(pokazi_por) {
						 JOptionPane.showMessageDialog(null, "U ovo vreme kozmeticar je zauzet drugim tretmanom.");
					 }
					 return false;
				 }
				 if(localDate.atTime(vremeLocalTime).isAfter(t.getVreme()) && localDate.atTime(vremeLocalTime).isBefore(vremeZavrsetkaDateTime)) {
					 if(pokazi_por) {
						 JOptionPane.showMessageDialog(null, "U ovo vreme kozmeticar je zauzet drugim tretmanom.");
					 }
					 return false;
				 }
				
			 }
		 }
		 return true;
	}

}
