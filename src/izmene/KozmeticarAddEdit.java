package izmene;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import GUI.KozmeticarTableFrame;
import entiteti.Kozmeticar;
import entiteti.STRUCNA_SPREMA;
import entiteti.TipTretmana;
import kontroleri.KorisniciKontroler;
import kontroleri.TipTretmanaKontroler;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

public class KozmeticarAddEdit extends JDialog {

	private static final long serialVersionUID = 1182435404157575009L;

	private JTextField imeTxt;
	private JTextField prezimeTxt;
	private JTextField telefonTxt;
	private JTextField adresaTxt;
	private JTextField korImeTxt;
	private JTextField lozinkaTxt;
	private JTextField stazTxt;
	private JTextField bonusTxt;
	private JTextField osnovaPlateTxt;
	private JLabel lblObucenost;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JList<String> list;
	private JComboBox<String> comboBox;
	private JLabel lblPlata_1;
	private JTextField plataTxt;
	private KorisniciKontroler korisniciKontroler;
	public KozmeticarAddEdit(JFrame p, KorisniciKontroler k,Kozmeticar kzm, TipTretmanaKontroler tk) {
		if(kzm == null) {
			setTitle("Dodavanje kozmeticara");
		}
		else {
			setTitle("Izmena kozmeticara");
		}
		this.korisniciKontroler = k;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				p.setEnabled(true);
			}
		});
		
		
		
		getContentPane().setLayout(new MigLayout("", "[100,grow][100,grow][100,grow][100,grow]", "[50][40][30][40][30][40][30][40.00][30][40][50][50,grow]30[62.00]"));
		
		JLabel ime = new JLabel("Ime:");
		ime.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(ime, "cell 0 0 2 1,alignx center");
		
		JLabel prezime = new JLabel("Prezime:");
		prezime.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(prezime, "cell 2 0 2 1,alignx center");
		
		imeTxt = new JTextField();
		imeTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		imeTxt.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(imeTxt, "cell 0 1 2 1,alignx center,growy");
		imeTxt.setColumns(10);
		
		prezimeTxt = new JTextField();
		prezimeTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		prezimeTxt.setHorizontalAlignment(SwingConstants.CENTER);
		prezimeTxt.setColumns(10);
		getContentPane().add(prezimeTxt, "cell 2 1 2 1,alignx center,growy");
		
		JLabel broj = new JLabel("Broj telefona:");
		broj.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(broj, "cell 0 2 2 1,alignx center");
		
		JLabel adresa = new JLabel("Adresa:");
		adresa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(adresa, "cell 2 2 2 1,alignx center");
		
		telefonTxt = new JTextField();
		telefonTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		telefonTxt.setHorizontalAlignment(SwingConstants.CENTER);
		telefonTxt.setColumns(10);
		getContentPane().add(telefonTxt, "cell 0 3 2 1,alignx center,growy");
		
		adresaTxt = new JTextField();
		adresaTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		adresaTxt.setHorizontalAlignment(SwingConstants.CENTER);
		adresaTxt.setColumns(10);
		getContentPane().add(adresaTxt, "cell 2 3 2 1,alignx center,growy");
		
		JLabel lblKorisnickoIme = new JLabel("Korisnicko ime:");
		lblKorisnickoIme.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblKorisnickoIme, "cell 0 4 2 1,alignx center");
		
		JLabel lblLozinka = new JLabel("Lozinka:");
		lblLozinka.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblLozinka, "cell 2 4 2 1,alignx center");
		
		korImeTxt = new JTextField();
		korImeTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		korImeTxt.setHorizontalAlignment(SwingConstants.CENTER);
		korImeTxt.setColumns(10);
		getContentPane().add(korImeTxt, "cell 0 5 2 1,alignx center,growy");
		
		lozinkaTxt = new JTextField();
		lozinkaTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lozinkaTxt.setHorizontalAlignment(SwingConstants.CENTER);
		lozinkaTxt.setColumns(10);
		getContentPane().add(lozinkaTxt, "cell 2 5 2 1,alignx center,growy");
		
		JLabel lblKoeficijentStrucneSpreme = new JLabel("Koeficijent strucne spreme:");
		lblKoeficijentStrucneSpreme.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblKoeficijentStrucneSpreme, "cell 0 6 2 1,alignx center");
		
		JLabel lblRadniStaz = new JLabel("Radni staz:");
		lblRadniStaz.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblRadniStaz, "cell 2 6 2 1,alignx center");
		
		comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"NISKA", "SREDNJE NISKA", "SREDNJA", "SREDNJE VISOKA", "VISOKA"}));
		comboBox.setMaximumRowCount(10);
		getContentPane().add(comboBox, "cell 0 7 2 1,alignx center,growy");
		
		stazTxt = new JTextField();
		stazTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		stazTxt.setHorizontalAlignment(SwingConstants.CENTER);
		stazTxt.setColumns(10);
		getContentPane().add(stazTxt, "cell 2 7 2 1,alignx center,growy");
		
		lblObucenost = new JLabel("Obucenost: (Ctrl/Shift + click)");
		lblObucenost.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblObucenost, "cell 0 8 2 1,alignx center");
		
		JLabel lblOsnovaPlate = new JLabel("Osnova plate:");
		lblOsnovaPlate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblOsnovaPlate, "cell 2 8 2 1,alignx center");
		
		osnovaPlateTxt = new JTextField();
		osnovaPlateTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		osnovaPlateTxt.setHorizontalAlignment(SwingConstants.CENTER);
		osnovaPlateTxt.setColumns(10);
		getContentPane().add(osnovaPlateTxt, "cell 2 9 2 1,alignx center,growy");
	
		
		DefaultListModel<String> model = new DefaultListModel<>();
		list = new JList<>( model );
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		for ( TipTretmana t:tk.getTipovi() ){
		  model.addElement( t.getImeTip() );
		}
		if(kzm != null) {
			int br = 0;
			ArrayList<Integer> indexi = new ArrayList<Integer>();
			for(TipTretmana t:tk.getTipovi()) {
				if(kzm.getObucenost().contains(t)) {
					indexi.add(br);
				}
				br++;
			}
			int[] aInteger = new int[indexi.size()];
			for(int i = 0; i<indexi.size();i++) {
				aInteger[i] = indexi.get(i);
			}
			list.setSelectedIndices(aInteger);
		}
		
		if(kzm != null) {
			JLabel lblBonus = new JLabel("Bonus:");
			lblBonus.setFont(new Font("Tahoma", Font.PLAIN, 16));
			getContentPane().add(lblBonus, "cell 2 10,alignx center");
		}

		
		JScrollPane pane = new JScrollPane(list);
		
		
		getContentPane().add(pane, "cell 0 9 2 3,grow");
		
		
		
		
		btnNewButton = new JButton("Dodaj");
		if(kzm != null) {
			btnNewButton.setText("Izmeni");
		}
		if(kzm != null) {
			lblPlata_1 = new JLabel("Plata:");
			lblPlata_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
			getContentPane().add(lblPlata_1, "cell 3 10,alignx center");
			
			bonusTxt = new JTextField();
			bonusTxt.setEditable(false);
			bonusTxt.setHorizontalAlignment(SwingConstants.CENTER);
			bonusTxt.setColumns(10);
			getContentPane().add(bonusTxt, "cell 2 11,alignx center,growy");
			
			plataTxt = new JTextField();
			plataTxt.setEditable(false);
			plataTxt.setHorizontalAlignment(SwingConstants.CENTER);
			plataTxt.setColumns(10);
			getContentPane().add(plataTxt, "cell 3 11,grow");
		}

		
		btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(btnNewButton_1, "cell 0 12,grow");
		
		if(kzm!= null) {
			imeTxt.setText(kzm.getIme());
			prezimeTxt.setText(kzm.getPrezime());
			telefonTxt.setText(String.valueOf(kzm.getTelefon()));
			adresaTxt.setText(kzm.getAdresa());
			korImeTxt.setText(kzm.getKorisnickoIme());
			korImeTxt.setEditable(false);
			lblKorisnickoIme.setText("Korisnicko ime: (Ne menja se)");
			lozinkaTxt.setText(kzm.getLozinka());
			stazTxt.setText(String.valueOf(kzm.getRadniStaz()));
			osnovaPlateTxt.setText(String.valueOf(kzm.getOsnovaPlate()));
			bonusTxt.setText(String.valueOf(kzm.getBonus()));
			plataTxt.setText(String.valueOf(kzm.getPlata()));
			if(kzm.getStrucnaSprema() == 0.2) {
				comboBox.setSelectedIndex(0);
			}
			else if(kzm.getStrucnaSprema() == 0.4) {
				comboBox.setSelectedIndex(1);
			}
			else if(kzm.getStrucnaSprema() == 0.6) {
				comboBox.setSelectedIndex(2);
			}
			else if(kzm.getStrucnaSprema() == 0.8) {
				comboBox.setSelectedIndex(3);
			}
			else {
				comboBox.setSelectedIndex(4);
				
			}
		}
		
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				p.setEnabled(true);
				KozmeticarAddEdit.this.dispose();
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(btnNewButton, "cell 3 12,grow");
		
		
		//add,edit
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean flag;
				if(kzm == null) {
					flag= validacija(imeTxt, prezimeTxt, telefonTxt, adresaTxt, korImeTxt, lozinkaTxt, stazTxt, osnovaPlateTxt,true);

				}
				else {
					flag = validacija(imeTxt, prezimeTxt, telefonTxt, adresaTxt, korImeTxt, lozinkaTxt, stazTxt, osnovaPlateTxt,false);

				}
				
				if(flag==true) {
					String imString = imeTxt.getText();
					String preString = prezimeTxt.getText();
					int broj = Integer.parseInt(telefonTxt.getText());
					String adresaString = adresaTxt.getText();
					String korString = korImeTxt.getText();
					String passString = lozinkaTxt.getText();
					int rs = Integer.parseInt(stazTxt.getText());
					double op = Double.parseDouble(osnovaPlateTxt.getText());
					STRUCNA_SPREMA sprema;
					double ss = 0.0;
					if(comboBox.getSelectedIndex() == 0) {
						sprema = STRUCNA_SPREMA.NISKA;
						ss = 0.2;
					}
					else if(comboBox.getSelectedIndex() == 1) {
						sprema = STRUCNA_SPREMA.SREDNJE_NISKA;
						ss = 0.4;
					}
					else if(comboBox.getSelectedIndex() == 2) {
						sprema = STRUCNA_SPREMA.SREDNJA;
						ss = 0.6;
					}
					else if(comboBox.getSelectedIndex() == 3) {
						sprema = STRUCNA_SPREMA.SREDNJE_VISOKA;
						ss = 0.8;
					}
					else if(comboBox.getSelectedIndex() == 4) {
						sprema = STRUCNA_SPREMA.VISOKA;
						ss = 1;
					}
					else {
						sprema = STRUCNA_SPREMA.NISKA;
						ss = 0.2;
					}
					ArrayList<String> selektovani = (ArrayList<String>) list.getSelectedValuesList();
					ArrayList<TipTretmana> tipoviArrayList = new ArrayList<TipTretmana>();
					for(TipTretmana t:tk.getTipovi()) {
						for(String s:selektovani) {
							if(s.compareTo(t.getImeTip())==0) {
								tipoviArrayList.add(t);
							}
						}
					}
					if(kzm == null) {
						k.registrujKozmeticara(new Kozmeticar(imString, preString, broj, adresaString, korString, passString, sprema, rs, op, 0, tipoviArrayList));
					}
					else {
						kzm.setIme(imString);
						kzm.setPrezime(preString);
						kzm.setTelefon(broj);
						kzm.setAdresa(adresaString);
						kzm.setKorisnickoIme(korString);
						kzm.setLozinka(passString);
						kzm.setRadniStaz(rs);
						kzm.setOsnovaPlate(op);
						kzm.setStrucnaSprema(ss);
						kzm.setObucenost(tipoviArrayList);
						korisniciKontroler.azurirajFile();
						
					}
					((KozmeticarTableFrame)p).refreshData();
					p.setEnabled(true);
					KozmeticarAddEdit.this.dispose();
					
					
				}
			}
		});
		
		pack();
		setLocationRelativeTo(null);

	}


	private boolean validacija(JTextField ime, JTextField prezime, JTextField broj, JTextField adresa, JTextField kor, JTextField pass, JTextField rs, JTextField op, boolean add) {
		if(ime.getText().compareTo("")==0) {
			JOptionPane.showMessageDialog(null, "Niste uneli ime.");
			return false;
		}
		else if(prezime.getText().compareTo("")==0) {
			JOptionPane.showMessageDialog(null, "Niste uneli prezime.");
			return false;
		}
		else if(broj.getText().compareTo("")==0) {
			JOptionPane.showMessageDialog(null, "Niste uneli broj telefona.");
			return false;
		}
		
		else if(adresa.getText().compareTo("")==0) {
			JOptionPane.showMessageDialog(null, "Niste uneli adresu.");
			return false;
		}
		else if(kor.getText().compareTo("")==0) {
			JOptionPane.showMessageDialog(null, "Niste uneli koriscniko ime.");
			return false;
		}
		else if(pass.getText().compareTo("")==0) {
			JOptionPane.showMessageDialog(null, "Niste uneli lozinku.");
			return false;
		}
		else if(rs.getText().compareTo("")==0) {
			JOptionPane.showMessageDialog(null, "Niste uneli radni staz.");
			return false;
		}
		else if(op.getText().compareTo("")==0) {
			JOptionPane.showMessageDialog(null, "Niste uneli osnovu plate.");
			return false;
		}
		else {
			boolean prva = false;
			boolean druga = false;
			boolean treca = false;
			    try
			    {
			        Integer.parseInt(broj.getText());
			        prva = true;
			        
			    } catch (NumberFormatException ex)
			    {
			    	JOptionPane.showMessageDialog(null, "Broj telefona mora biti broj.");
			    	return false;
			    }
			    try
			    {
			        Integer.parseInt(rs.getText());
			        druga = true;
			        
			    } catch (NumberFormatException ex)
			    {
			    	JOptionPane.showMessageDialog(null, "Radni staz mora biti broj.");
			    	return false;
			    }
			    try
			    {
			        Double.parseDouble(op.getText());
			        treca = true;
			        
			    } catch (NumberFormatException ex)
			    {
			    	JOptionPane.showMessageDialog(null, "Osnova plate mora biti broj.");
			    	return false;
			    }
			    if(add==true) {
			    	for(Kozmeticar kz:korisniciKontroler.getKozmeticari()) {
			    		if(kz.getKorisnickoIme().compareTo(kor.getText())==0) {
			    			return false;
			    		}
			    	}
			    }
			
				
			if(prva == true && druga == true && treca == true) {
				return true;
			}
			else {
				return false;
			}
		}
		
	}
	


}
