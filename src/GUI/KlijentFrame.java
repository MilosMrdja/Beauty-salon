package GUI;


import javax.swing.JDialog;
import javax.swing.JFrame;

import entiteti.Klijent;
import izmene.zakaziTretman;
import kontroleri.Kontroler;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class KlijentFrame extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2980750419890563186L;
	private Klijent klijent;
	private Kontroler kontroler;

	public KlijentFrame(Klijent klijent, Kontroler kontroler, JDialog main) {

		this.klijent = klijent;
		this.kontroler = kontroler;
		this.setTitle("Meni klijent");
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		
		initMeni(this, main);
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	private void initMeni(JFrame f, JDialog jd) {
		MigLayout migLayout = new MigLayout("", "[130.00,grow][130.00,grow]", "[50,grow][40,grow][40,grow][40,grow][40,grow]");
		f.getContentPane().setLayout(migLayout);
		
		JLabel lblNewLabel = new JLabel("Dobrodosli, " + klijent.getKorisnickoIme());
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblNewLabel, "cell 0 0 2 1,alignx center");
		
		JButton vidiTretmaneBtn = new JButton("Vidi tretmane");
		vidiTretmaneBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(vidiTretmaneBtn, "cell 0 1,grow");
		
		JButton zakaziTretmanBtn = new JButton("Zakazi tretman");
		zakaziTretmanBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(zakaziTretmanBtn, "cell 1 1,grow");
		
		JLabel lblNewLabel_1 = new JLabel("Potrosen novac:");
		lblNewLabel_1.setText("Potrosen novac: " + klijent.getPotrosenNovac());
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblNewLabel_1, "cell 0 2 2 1,alignx center");
		
		JLabel lblNewLabel_2 = new JLabel("Kartica lojalnosti");
		if(klijent.isKarticaLojalnosti()) {
			lblNewLabel_2.setText("Kartica lojanosti: poseduje");

		}
		else {
			lblNewLabel_2.setText("Kartica lojanosti: ne poseduje");

		}
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblNewLabel_2, "cell 0 3 2 1,alignx center");
		
		JButton odjavaBtn = new JButton("Odjava");
		odjavaBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(odjavaBtn, "cell 0 4 2 1,alignx center,growy");
		
		vidiTretmaneBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TretmanZaKlijenteFrame tretmanZaKlijenteFrame = new TretmanZaKlijenteFrame(f, kontroler.getTretmanKontroler(), klijent,lblNewLabel_1);
				tretmanZaKlijenteFrame.setVisible(true);
				f.setVisible(false);
				
			}
		});
		
		zakaziTretmanBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				zakaziTretman zakaziT = new zakaziTretman(f, kontroler.getUslugeKontroler(), kontroler.getKorisniciKontroler(), kontroler.getTipKontroler(),kontroler, klijent, lblNewLabel_1);
				zakaziT.setVisible(true);
				f.setEnabled(false);
				
			}
		});
		
		
		
		odjavaBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				KlijentFrame.this.dispose();
				jd.setVisible(true);
				
			}
		});
	}

}
