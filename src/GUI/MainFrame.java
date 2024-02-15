package GUI;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import entiteti.Klijent;
import entiteti.Korisnik;
import entiteti.Kozmeticar;
import entiteti.Menadzer;
import entiteti.Recepcioner;
import kontroleri.Kontroler;
import kontroleri.KorisniciKontroler;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			Login dialog = new Login();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	private Kontroler kontroler;
	private KorisniciKontroler korisniciKontroler;

	/**
	 * Create the dialog.
	 */
	public MainFrame(Kontroler kontroler) {
		this.kontroler = kontroler;
		this.korisniciKontroler = this.kontroler.getKorisniciKontroler();
		login();
	}
	
	private void login() {
		JDialog loginDialog = new JDialog();
		loginDialog.setTitle("Prijava korisnika");
		loginDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		loginDialog.setResizable(true);
		initLogin(loginDialog);
		loginDialog.pack();
		loginDialog.setLocationRelativeTo(null);
		loginDialog.setVisible(true);
	}
	
	private void initLogin(JDialog dialog) {
		MigLayout layout = new MigLayout("wrap", "[100]10[100]10[100]", "20[30][30]20[30][30]20[40]");
		dialog.getContentPane().setLayout(layout);
		
		JTextField korisnickoIme = new JTextField(15);
		korisnickoIme.setFont(new Font("Tahoma", Font.PLAIN, 14));
		korisnickoIme.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField lozinka = new JTextField(15);
		lozinka.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lozinka.setHorizontalAlignment(SwingConstants.CENTER);
		JButton prijavaButton = new JButton("Prijava");
		prijavaButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JButton registracijaButton = new JButton("Registracija");
		registracijaButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JButton exitButton = new JButton("Exit");
		exitButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		dialog.getRootPane().setDefaultButton(prijavaButton);
		
		JLabel label = new JLabel("Korisnicko ime:");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dialog.getContentPane().add(label,"cell 0 0 3 1, grow");
		dialog.getContentPane().add(korisnickoIme, "cell 0 1 3 1,alignx center,growy");
		
		JLabel label1 = new JLabel("Lozinka:");
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dialog.getContentPane().add(label1, "cell 0 2 3 1,grow");
		dialog.getContentPane().add(lozinka, "cell 0 3 3 1, growy, alignx center");
		
		
		dialog.getContentPane().add(exitButton, "cell 0 4,grow");
		dialog.getContentPane().add(prijavaButton, "cell 1 4,grow");
		dialog.getContentPane().add(registracijaButton, "cell 2 4,grow");
		
		prijavaButton.addActionListener(new ActionListener() {
			
			

			@Override
			public void actionPerformed(ActionEvent e) {
				if(korisnickoIme.getText().compareTo("")==0) {
					JOptionPane.showMessageDialog(null, "Niste uneli korisnicko ime.");
				}
				else if(lozinka.getText().compareTo("")==0) {
					JOptionPane.showMessageDialog(null, "Niste uneli lozinku.");
				}
				else {
					boolean flag = false;
					for(Korisnik k:korisniciKontroler.getKorisnici()) {
						if(k.getKorisnickoIme().compareTo(korisnickoIme.getText())==0 && k.getLozinka().compareTo(lozinka.getText())==0) {
							if(k instanceof Menadzer) {
								flag = true;
								JFrame menadzerFrame = new menadzerFrame((Menadzer) k, kontroler,dialog);
								menadzerFrame.setVisible(true);
								dialog.dispose();
							}
							else if(k instanceof Kozmeticar) {
								flag = true; 
								KozmeticarFrame kozmeticarFrame = new KozmeticarFrame(dialog, kontroler, (Kozmeticar) k);
								kozmeticarFrame.setVisible(true);
								dialog.dispose();
							}
							else if(k instanceof Recepcioner) {
								flag = true;
								RecepcionerFrame recepcionerFrame = new RecepcionerFrame((Recepcioner) k, kontroler, dialog);
								recepcionerFrame.setVisible(true);
								dialog.dispose();
							}
							else {
								flag = true;
								KlijentFrame klijentFrame = new KlijentFrame((Klijent) k, kontroler, dialog);
								klijentFrame.setVisible(true);
								dialog.dispose();
							}
						}
					}
					if(flag == false) {
						JOptionPane.showMessageDialog(null, "Ne postoji ovakav korisnik.");
					}
				}

				
			}
		});
		
		registracijaButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame registracija = new Registracija(korisniciKontroler, dialog);
				registracija.setVisible(true);
				dialog.setVisible(false);;
			}
		});
		
		exitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
				dialog.dispose();
				
			}
		});
	}
	


}
