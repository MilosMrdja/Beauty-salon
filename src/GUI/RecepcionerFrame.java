package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import entiteti.Klijent;
import entiteti.Recepcioner;
import izmene.zakaziTretman;
import kontroleri.Kontroler;
import kontroleri.KorisniciKontroler;
import kontroleri.TretmanKontroler;
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;

public class RecepcionerFrame extends JFrame {


	private static final long serialVersionUID = -8160904916120409196L;
	private Recepcioner recepcioner;
	private KorisniciKontroler kontrolerKorisnici;
	private TretmanKontroler kontroleTretmani;
	private Kontroler kontroler;

	public RecepcionerFrame(Recepcioner r,Kontroler k,JDialog main) {
		this.recepcioner = r;
		this.kontroler = k;
		this.kontrolerKorisnici = k.getKorisniciKontroler();
		this.kontroleTretmani = k.getTretmanKontroler();
		this.setTitle("Meni recepcioner");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		
		initMeni(this, main);
		this.pack();
		this.setLocationRelativeTo(null);

	}
	
	private void initMeni(JFrame f, JDialog jd) {
		MigLayout migLayout = new MigLayout("", "[130.00,grow][130.00,grow]", "[50,grow][40,grow][40,grow][40,grow]");
		f.getContentPane().setLayout(migLayout);
		
		JLabel lblNewLabel = new JLabel("Dobrodosli, " + recepcioner.getKorisnickoIme());
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblNewLabel, "cell 0 0 2 1,alignx center");
		
		JButton vidiTretmaneBtn = new JButton("Vidi tretmane");
		vidiTretmaneBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(vidiTretmaneBtn, "cell 0 1,grow");
		
		JButton zakaziTretmanBtn = new JButton("Zakazi tretman za:");
		zakaziTretmanBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(zakaziTretmanBtn, "cell 1 1,grow");
		
		ArrayList<String> klijentiArrayList = new ArrayList<String>();
		for(Klijent k:kontrolerKorisnici.getKlijenti()) {
			klijentiArrayList.add(k.getKorisnickoIme());
		}
		
		String[] kString = new String[klijentiArrayList.size()];
		for(int i = 0; i < klijentiArrayList.size();i++) {
			kString[i] = klijentiArrayList.get(i);
		}
		
		JComboBox<String> comboBox = new JComboBox<String>(kString);
		getContentPane().add(comboBox, "cell 1 2,growx");
		
		JButton odjavaBtn = new JButton("Odjava");
		odjavaBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(odjavaBtn, "cell 0 3,alignx center,growy");
		
		vidiTretmaneBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PregledZakazanihZaRecepcionera pr = new PregledZakazanihZaRecepcionera(f, kontroleTretmani, kontroler.getTipKontroler(),kontrolerKorisnici,kontroler);
				pr.setVisible(true);
				f.setVisible(false);
				
			}
		});
		
		zakaziTretmanBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Klijent klijent = (Klijent) kontrolerKorisnici.pronadjiKorisnika(comboBox.getSelectedItem().toString());
				zakaziTretman zakaziT = new zakaziTretman(f, kontroler.getUslugeKontroler(), kontroler.getKorisniciKontroler(), kontroler.getTipKontroler(),kontroler, klijent, null);
				zakaziT.setVisible(true);
				f.setEnabled(false);
				
			}
		});
		
		odjavaBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RecepcionerFrame.this.dispose();
				jd.setVisible(true);
				
			}
		});
	}

}
