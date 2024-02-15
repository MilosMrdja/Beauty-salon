package GUI;


import javax.swing.JDialog;
import javax.swing.JFrame;

import entiteti.Kozmeticar;
import kontroleri.Kontroler;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class KozmeticarFrame extends JFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7070598499327648456L;
	private Kontroler kontroler;
	private Kozmeticar kozmeticar;

	public KozmeticarFrame(JDialog main, Kontroler k, Kozmeticar kozm) {
		this.kozmeticar = kozm;
		this.kontroler = k;
		this.setTitle("Meni Kozmeticar");
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		
		initMeni(this, main);
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	private void initMeni(JFrame f,JDialog jd) {
		MigLayout migLayout = new MigLayout("", "[130.00,grow][130.00,grow]", "[50,grow][40,grow]15[40,grow][40,grow]");
		f.getContentPane().setLayout(migLayout);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setText("Dobrodosli, " + kozmeticar.getKorisnickoIme());
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblNewLabel, "cell 0 0 2 1,alignx center");
		
		JButton btnNewButton = new JButton("Vidi raspored");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(btnNewButton, "cell 0 1 2 1,alignx center,growy");
		
		JButton btnOdjava = new JButton("Odjava");
		btnOdjava.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(btnOdjava, "cell 0 2 2 1,alignx center,growy");
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PregledZakazanihZaKozmeticara pr = new PregledZakazanihZaKozmeticara(f, kontroler);
				pr.setVisible(true);
				f.setVisible(false);
				
			}
		});
		
		btnOdjava.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				KozmeticarFrame.this.dispose();
				jd.setVisible(true);
			}
		});
		
	}

}
