package izmene;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import GUI.MenadzerTabelFrame;
import entiteti.Menadzer;
import entiteti.STRUCNA_SPREMA;
import kontroleri.KorisniciKontroler;
import net.miginfocom.swing.MigLayout;

public class MenadzerAddEdit extends JDialog {

	private static final long serialVersionUID = 7890853115697779788L;
	private JTextField bonusTxt;
	private JTextField plataTxt;
	private KorisniciKontroler korisniciKontroler;

	public MenadzerAddEdit(JFrame p, KorisniciKontroler k, Menadzer m) {
		
		if(m == null) {
			setTitle("Dodavanje menadzera");
		}
		else {
			setTitle("Izmena menadzera");
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
		
		JTextField imeTxt = new JTextField();
		imeTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		imeTxt.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(imeTxt, "cell 0 1 2 1,alignx center,growy");
		imeTxt.setColumns(10);
		
		JTextField prezimeTxt = new JTextField();
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
		
		JTextField telefonTxt = new JTextField();
		telefonTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		telefonTxt.setHorizontalAlignment(SwingConstants.CENTER);
		telefonTxt.setColumns(10);
		getContentPane().add(telefonTxt, "cell 0 3 2 1,alignx center,growy");
		
		JTextField adresaTxt = new JTextField();
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
		
		JTextField korImeTxt = new JTextField();
		korImeTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		korImeTxt.setHorizontalAlignment(SwingConstants.CENTER);
		korImeTxt.setColumns(10);
		getContentPane().add(korImeTxt, "cell 0 5 2 1,alignx center,growy");
		
		JTextField lozinkaTxt = new JTextField();
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
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"NISKA", "SREDNJE NISKA", "SREDNJA", "SREDNJE VISOKA", "VISOKA"}));
		comboBox.setMaximumRowCount(10);
		getContentPane().add(comboBox, "cell 0 7 2 1,alignx center,growy");
		
		JTextField stazTxt = new JTextField();
		stazTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		stazTxt.setHorizontalAlignment(SwingConstants.CENTER);
		stazTxt.setColumns(10);
		getContentPane().add(stazTxt, "cell 2 7 2 1,alignx center,growy");
		
		JLabel lblOsnovaPlate = new JLabel("Osnova plate:");
		lblOsnovaPlate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblOsnovaPlate, "cell 2 8 2 1,alignx center");
		
		JTextField osnovaPlateTxt = new JTextField();
		osnovaPlateTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		osnovaPlateTxt.setHorizontalAlignment(SwingConstants.CENTER);
		osnovaPlateTxt.setColumns(10);
		getContentPane().add(osnovaPlateTxt, "cell 2 9 2 1,alignx center,growy");
		
		if(m != null) {
			JLabel lblNewLabel = new JLabel("Bonus:");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
			getContentPane().add(lblNewLabel, "cell 0 10,alignx center");
			
			JLabel lblPlata = new JLabel("Plata:");
			lblPlata.setFont(new Font("Tahoma", Font.PLAIN, 16));
			getContentPane().add(lblPlata, "cell 1 10,alignx center");
			
			bonusTxt = new JTextField();
			bonusTxt.setHorizontalAlignment(SwingConstants.CENTER);
			bonusTxt.setColumns(10);
			bonusTxt.setEditable(false);
			getContentPane().add(bonusTxt, "cell 0 11,grow");
			
			plataTxt = new JTextField();
			plataTxt.setHorizontalAlignment(SwingConstants.CENTER);
			plataTxt.setColumns(10);
			plataTxt.setEditable(false);
			getContentPane().add(plataTxt, "cell 1 11,grow");
			
			imeTxt.setText(m.getIme());
			prezimeTxt.setText(m.getPrezime());
			telefonTxt.setText(String.valueOf(m.getTelefon()));
			adresaTxt.setText(m.getAdresa());
			korImeTxt.setText(m.getKorisnickoIme());
			korImeTxt.setEditable(false);
			lblKorisnickoIme.setText("Korisnicko ime: (Ne menja se)");
			lozinkaTxt.setText(m.getLozinka());
			stazTxt.setText(String.valueOf(m.getRadniStaz()));
			osnovaPlateTxt.setText(String.valueOf(m.getOsnovaPlate()));
			bonusTxt.setText(String.valueOf(m.getBonus()));
			plataTxt.setText(String.valueOf(m.getPlata()));
			if(m.getStrucnaSprema() == 0.2) {
				comboBox.setSelectedIndex(0);
			}
			else if(m.getStrucnaSprema() == 0.4) {
				comboBox.setSelectedIndex(1);
			}
			else if(m.getStrucnaSprema() == 0.6) {
				comboBox.setSelectedIndex(2);
			}
			else if(m.getStrucnaSprema() == 0.8) {
				comboBox.setSelectedIndex(3);
			}
			else {
				comboBox.setSelectedIndex(4);
				
			}
			
		}

		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(btnNewButton, "cell 2 11,grow");
		
		JButton btnDodaj = new JButton("Dodaj");
		if(m!=null) {
			btnDodaj.setText("Izmeni");
		}
		btnDodaj.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(btnDodaj, "cell 3 11,grow");
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				p.setEnabled(true);
				MenadzerAddEdit.this.dispose();
				
			}
		});
		
		btnDodaj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean flag;
				if(m ==null) {
					flag = validacija(imeTxt, prezimeTxt, telefonTxt, adresaTxt, korImeTxt, lozinkaTxt, stazTxt, osnovaPlateTxt, true);
				}
				else {
					flag = validacija(imeTxt, prezimeTxt, telefonTxt, adresaTxt, korImeTxt, lozinkaTxt, stazTxt, osnovaPlateTxt, false);
				}
				if(flag == true) {
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
					
					if(m == null) {
						k.registrujMenadzera(new Menadzer(imString, preString, broj, adresaString, korString, passString, sprema, rs, op, 0));
					}
					else {
						m.setIme(imString);
						m.setPrezime(preString);
						m.setTelefon(broj);
						m.setAdresa(adresaString);
						m.setKorisnickoIme(korString);
						m.setLozinka(passString);
						m.setRadniStaz(rs);
						m.setOsnovaPlate(op);
						m.setStrucnaSprema(ss);
						korisniciKontroler.azurirajFile();
					}
					((MenadzerTabelFrame)p).refreshData();
					p.setEnabled(true);
					MenadzerAddEdit.this.dispose();
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
			    if(add== true) {
			    	for(Menadzer mn:korisniciKontroler.getMenadzeri()) {
			    		if(mn.getKorisnickoIme().compareTo(kor.getText())==0) {
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
