package izmene;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import GUI.KlijentTableFrame;
import entiteti.Klijent;
import entiteti.Korisnik;
import kontroleri.KorisniciKontroler;
import net.miginfocom.swing.MigLayout;

public class KlijentAddEditDialog extends JDialog{

	private static final long serialVersionUID = -9000266341104606724L;
	
	private KorisniciKontroler korisniciKontroler;
	private Klijent klijent;
	private JFrame pFrame;
	
	public KlijentAddEditDialog(JFrame p, Klijent k, KorisniciKontroler kr) {
		this.pFrame = p;
		this.klijent = k;
		this.korisniciKontroler = kr;
		if(this.klijent == null) {
			setTitle("Dodavanje klijenta");
		}
		else {
			setTitle("Izmena klijenta");
		}
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				p.setEnabled(true);
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		init(this, p);
		pack();
		setLocationRelativeTo(null);

	}
	
	private void init(JDialog r, JFrame p) {
		MigLayout migLayout = new MigLayout("", "[140.00,grow][140.00]", "[40.00][30.00]10[40.00][30.00]10[40][30]10[40][30]10[40][30]10[40][30]10[][35]");
		r.getContentPane().setLayout(migLayout);
		
		JTextField imefField = new JTextField(15);
		imefField.setHorizontalAlignment(SwingConstants.CENTER);
		imefField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton registracijaButton = new JButton("Registruj se");
		
		r.getRootPane().setDefaultButton(registracijaButton);
		
		JLabel imeJLabel = new JLabel("Ime:");
		imeJLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		imeJLabel.setHorizontalAlignment(SwingConstants.CENTER);
		r.getContentPane().add(imeJLabel,"cell 0 0 2 1,alignx center,growy");
		r.getContentPane().add(imefField,"cell 0 1 2 1,alignx center,growy");
		
		JLabel lblNewLabel = new JLabel("Prezime:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblNewLabel, "cell 0 2 2 1,alignx center");
		
		JTextField prezimeField = new JTextField();
		prezimeField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		prezimeField.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(prezimeField, "cell 0 3 2 1,alignx center,growy");
		prezimeField.setColumns(15);
		
		JLabel lblNewLabel_1 = new JLabel("Broj telefona:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblNewLabel_1, "cell 0 4 2 1,alignx center");
		
		JTextField telefonField = new JTextField();
		telefonField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		telefonField.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(telefonField, "cell 0 5 2 1,alignx center,growy");
		telefonField.setColumns(15);
		
		JLabel lblNewLabel_2 = new JLabel("Adresa:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblNewLabel_2, "cell 0 6 2 1,alignx center");
		
		JTextField adresaField = new JTextField();
		adresaField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		adresaField.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(adresaField, "cell 0 7 2 1,alignx center,growy");
		adresaField.setColumns(15);
		
		
		JLabel lblNewLabel_3 = new JLabel("Korisnicko ime:");
		if(klijent != null) {
			lblNewLabel_3.setText("Korisnicko ime: (Ne menja se)");
		}
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblNewLabel_3, "cell 0 8 2 1,alignx center");
		
		JTextField korImeField = new JTextField();
		korImeField.setHorizontalAlignment(SwingConstants.CENTER);
		korImeField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		if(klijent != null) {
			korImeField.setEditable(false);
		}
		
		getContentPane().add(korImeField, "cell 0 9 2 1,alignx center,growy");
		korImeField.setColumns(15);
		
		JLabel lblNewLabel_4 = new JLabel("Lozinka:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblNewLabel_4, "cell 0 10 2 1,alignx center");
		
		JTextField lozinkaField = new JTextField();
		lozinkaField.setHorizontalAlignment(SwingConstants.CENTER);
		lozinkaField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lozinkaField.setColumns(15);
		getContentPane().add(lozinkaField, "cell 0 11 2 1,alignx center,growy");
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(btnNewButton, "cell 0 12 1 2,grow");
		
		JButton btnRegistrujSe = new JButton("Dodaj");
		if(klijent != null) {
			btnRegistrujSe.setText("Izmeni");
		}
		btnRegistrujSe.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(btnRegistrujSe, "cell 1 12 1 2,grow");
		
		if(klijent != null) {
			imefField.setText(klijent.getIme());
			prezimeField.setText(klijent.getPrezime());
			telefonField.setText(String.valueOf(klijent.getTelefon()));
			adresaField.setText(klijent.getAdresa());
			korImeField.setText(klijent.getKorisnickoIme());
			lozinkaField.setText(klijent.getLozinka());
		}
		
		btnRegistrujSe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean flag;
				if(klijent == null) {
					flag = validacija(imefField, prezimeField, telefonField, adresaField, korImeField, lozinkaField, true);
				}
				else {
					flag = validacija(imefField, prezimeField, telefonField, adresaField, korImeField, lozinkaField, false);
				}
				
				
				if(flag) {
					String imeString = imefField.getText();
					String prezimeString = prezimeField.getText();
					int broj = Integer.parseInt(telefonField.getText());
					String adresaString = adresaField.getText();
					String korString = korImeField.getText();
					String passString = lozinkaField.getText();
					
					if(klijent == null) {
						korisniciKontroler.registrujKlijenta(new Klijent(imeString, prezimeString, broj, adresaString, korString, passString, false, 0.0));
					}
					else {
						klijent.setIme(imeString);
						klijent.setPrezime(prezimeString);
						klijent.setTelefon(broj);
						klijent.setAdresa(adresaString);
						klijent.setLozinka(passString);
						korisniciKontroler.azurirajFile();
						
					}
					((KlijentTableFrame)pFrame).refreshData();
					p.setEnabled(true);
					KlijentAddEditDialog.this.dispose();
				}
				

				
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				p.setEnabled(true);
				KlijentAddEditDialog.this.dispose();
				
			}
		});
	}
	
	private boolean validacija(JTextField ime, JTextField prezime, JTextField broj, JTextField adresa, JTextField kor, JTextField pass, boolean kl) {
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
		else {
			boolean prva = false;
			boolean druga = true;
			    try
			    {
			        Integer.parseInt(broj.getText());
			        prva = true;
			        if(kl == true) {
			        	for(Korisnik k:korisniciKontroler.getKorisnici()) {
							if(k.getKorisnickoIme().compareTo(kor.getText())==0) {
								druga = false;
								JOptionPane.showMessageDialog(null, "Koriscniko ime vec postoji.");
								return false;
							}
						}
			        }
			    	
			        
			    } catch (NumberFormatException ex)
			    {
			    	JOptionPane.showMessageDialog(null, "Broj telefona mora biti broj.");
			    	return false;
			    }
			
				
			if(prva == true && druga == true) {
				return true;
			}
			else {
				return false;
			}
		}
		
	}
}
