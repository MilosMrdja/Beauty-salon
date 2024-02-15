package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;

import entiteti.STANJE_TERETMANA;
import entiteti.Tretman;
import kontroleri.Kontroler;
import kontroleri.TretmanKontroler;
import model.TretmaniRecepcionerModel;

public class PregledZakazanihZaKozmeticara extends JFrame {


	private static final long serialVersionUID = 790161844889249796L;
	
	private JTable table;
	private TretmanKontroler tretmanKontroler;

	public PregledZakazanihZaKozmeticara(JFrame p, Kontroler k) {
		this.tretmanKontroler = k.getTretmanKontroler();
		setTitle("Raspored");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(true);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				p.setVisible(true);
			}
		});
		
		init(this);
		pack();
		setLocationRelativeTo(null);

	}
	
	private void init(JFrame t) {
		BorderLayout borderLayout = new BorderLayout();
		t.setLayout(borderLayout);
		JToolBar mainToolbar = new JToolBar();
		
		JButton izvrsiButton = new JButton("Izvrsi tretman");
		mainToolbar.add(izvrsiButton);
		getContentPane().add(mainToolbar, BorderLayout.NORTH);

		ArrayList<Tretman> tretmans = new ArrayList<Tretman>();
		for(Tretman tr:tretmanKontroler.getTretmani()) {
			if(tr.getStanje().equals(STANJE_TERETMANA.ZAKAZAN)) {
				tretmans.add(tr);
			}
		}
		
		table = new JTable(new TretmaniRecepcionerModel(tretmans));
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		izvrsiButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = table.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Niste izabrali ni jedno polje");
					
				}
				else {
					Tretman tretman = tretmanKontroler.pronadjiTretman((int) table.getValueAt(red, 0));
					if(tretman != null) {
						if(!tretman.isObrisan()) {
							boolean  tempB= tretmanKontroler.obradiTretman(tretman, STANJE_TERETMANA.IZVRSEN);
							if(tempB) {
								JOptionPane.showMessageDialog(null, "Uspesno izvrsen tretman.");
								refreshData();
							}
							else {
								JOptionPane.showMessageDialog(null, "Tretman je vec izvrsen.");

							}

						}
						else {
							JOptionPane.showMessageDialog(null, "Tretman je obrisan.");
						}
						
					}
					else {
						JOptionPane.showMessageDialog(null, "GRESKA");

					}
				}
				
			}
		});
		
	}
	
	public void refreshData() {
		TretmaniRecepcionerModel kr = (TretmaniRecepcionerModel)this.table.getModel();
		kr.fireTableDataChanged();
	}

}
