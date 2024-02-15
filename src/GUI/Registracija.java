package GUI;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import entiteti.Klijent;
import entiteti.Korisnik;
import kontroleri.KorisniciKontroler;
import net.miginfocom.swing.MigLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Registracija extends JFrame {


	private static final long serialVersionUID = 4485065536499568216L;
	private KorisniciKontroler korisnici;
	private JTextField prezimeField;
	private JTextField telefonField;
	private JTextField adresaField;
	private JTextField korImeField;
	private JTextField lozinkaField;
	private JDialog login;

	public Registracija(KorisniciKontroler korisniciKontroler, JDialog dialog) {
		this.korisnici = korisniciKontroler;
		this.login = dialog;
		this.setTitle("Registracija korisnika");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocation(500, 50);
		this.setResizable(false);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dialog.setVisible(true);
			}
		});
		initRegistracija(this,dialog);
		this.pack();
		this.setLocationRelativeTo(null);


	}
	
	private void initRegistracija(JFrame r,JDialog d) {
		MigLayout migLayout = new MigLayout("", "[140.00,grow][140.00]", "[40.00][30.00]10[40.00][30.00]10[40][30]10[40][30]10[40][30]10[40][30]10[][35]");
		r.getContentPane().setLayout(migLayout);
		
		JTextField imefField = new JTextField(15);
		imefField.setHorizontalAlignment(SwingConstants.CENTER);
		imefField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton registracijaButton = new JButton("Registruj se");
		
		r.getRootPane().setDefaultButton(registracijaButton);
		
		JLabel imeJLabel = new JLabel("Ime:");
		imeJLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		imeJLabel.setHorizontalAlignment(SwingConstants.CENTER);
		r.getContentPane().add(imeJLabel,"cell 0 0 2 1,alignx center,growy");
		r.getContentPane().add(imefField,"cell 0 1 2 1,alignx center,growy");
		
		JLabel lblNewLabel = new JLabel("Prezime:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblNewLabel, "cell 0 2 2 1,alignx center");
		
		prezimeField = new JTextField();
		prezimeField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		prezimeField.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(prezimeField, "cell 0 3 2 1,alignx center,growy");
		prezimeField.setColumns(15);
		
		JLabel lblNewLabel_1 = new JLabel("Broj telefona:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblNewLabel_1, "cell 0 4 2 1,alignx center");
		
		telefonField = new JTextField();
		telefonField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		telefonField.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(telefonField, "cell 0 5 2 1,alignx center,growy");
		telefonField.setColumns(15);
		
		JLabel lblNewLabel_2 = new JLabel("Adresa:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblNewLabel_2, "cell 0 6 2 1,alignx center");
		
		adresaField = new JTextField();
		adresaField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		adresaField.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(adresaField, "cell 0 7 2 1,alignx center,growy");
		adresaField.setColumns(15);
		
		JLabel lblNewLabel_3 = new JLabel("Korisnicko ime:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblNewLabel_3, "cell 0 8 2 1,alignx center");
		
		korImeField = new JTextField();
		korImeField.setHorizontalAlignment(SwingConstants.CENTER);
		korImeField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(korImeField, "cell 0 9 2 1,alignx center,growy");
		korImeField.setColumns(15);
		
		JLabel lblNewLabel_4 = new JLabel("Lozinka:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblNewLabel_4, "cell 0 10 2 1,alignx center");
		
		lozinkaField = new JTextField();
		lozinkaField.setHorizontalAlignment(SwingConstants.CENTER);
		lozinkaField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lozinkaField.setColumns(15);
		getContentPane().add(lozinkaField, "cell 0 11 2 1,alignx center,growy");
		
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(btnNewButton, "cell 0 12 1 2,grow");
		
		JButton btnRegistrujSe = new JButton("Registruj se");
		btnRegistrujSe.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(btnRegistrujSe, "cell 1 12 1 2,grow");
		
		btnRegistrujSe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(imefField.getText().compareTo("")==0) {
					JOptionPane.showMessageDialog(null, "Niste uneli ime.");
				}
				else if(prezimeField.getText().compareTo("")==0) {
					JOptionPane.showMessageDialog(null, "Niste uneli prezime.");
				}
				else if(telefonField.getText().compareTo("")==0) {
					JOptionPane.showMessageDialog(null, "Niste uneli broj telefona.");
				}
				
				else if(adresaField.getText().compareTo("")==0) {
					JOptionPane.showMessageDialog(null, "Niste uneli adresu.");
				}
				else if(korImeField.getText().compareTo("")==0) {
					JOptionPane.showMessageDialog(null, "Niste uneli koriscniko ime.");
				}
				else if(lozinkaField.getText().compareTo("")==0) {
					JOptionPane.showMessageDialog(null, "Niste uneli lozinku.");
				}
				else {
					boolean prva = false;
					boolean druga = true;
					    try
					    {
					        Integer.parseInt(telefonField.getText());
					        prva = true;
					    	for(Korisnik k:korisnici.getKorisnici()) {
								if(k.getKorisnickoIme().compareTo(korImeField.getText())==0) {
									druga = false;
									JOptionPane.showMessageDialog(null, "Koriscniko ime vec postoji.");
								}
							}
					        
					    } catch (NumberFormatException ex)
					    {
					    	JOptionPane.showMessageDialog(null, "Broj telefona mora biti broj.");
					    }
					
						
					if(prva == true && druga == true) {
						korisnici.registrujKlijenta(new Klijent(imefField.getText(), prezimeField.getText(), Integer.parseInt(telefonField.getText()), adresaField.getText(), korImeField.getText(), lozinkaField.getText(), false, 0.0));
						JOptionPane.showMessageDialog(null, "Uspesno ste se registrovali.");
						login.setVisible(true);
						r.dispose();
					}
				}

				
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				d.setVisible(true);
				r.dispose();
				
			}
		});
	}

}
