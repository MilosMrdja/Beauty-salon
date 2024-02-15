package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumn;

import entiteti.Klijent;
import entiteti.STANJE_TERETMANA;
import entiteti.Tretman;
import kontroleri.TretmanKontroler;
import model.TretmanZaKlijentaModel;

public class TretmanZaKlijenteFrame extends JFrame {

	private static final long serialVersionUID = -4508988792259851903L;
	private JFrame parentF;
	private TretmanKontroler tretmanKontroler;
	private JButton btnOtkazi;
	private JTable table;
	private Klijent klijent;
	private JLabel novac;
	private JButton realizovan;
	private JButton nerealizovani;

	public TretmanZaKlijenteFrame(JFrame p, TretmanKontroler t,Klijent k,JLabel n) {
		this.parentF = p;
		this.tretmanKontroler = t;
		this.klijent = k;
		this.novac = n;
		setTitle("Zakazani tretmani");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(true);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				parentF.setVisible(true);
			}
		});
		
		init(this);
		pack();
		setLocationRelativeTo(null);

	}
	
	private void init(JFrame t) {
		BorderLayout borderLayout = new BorderLayout();
		t.getContentPane().setLayout(borderLayout);
		JToolBar mainToolbar = new JToolBar();
		
		btnOtkazi = new JButton("Otkazi tretman");
		mainToolbar.add(btnOtkazi);
		
		realizovan = new JButton("Realizovani tretmani");
		mainToolbar.add(realizovan);
		
		nerealizovani = new JButton("Nerealizovani tretmani");
		mainToolbar.add(nerealizovani);
		
		getContentPane().add(mainToolbar, BorderLayout.NORTH);
		ArrayList<Tretman> nerealizovaniArrayList = new ArrayList<Tretman>();
		for(Tretman ttTretman:tretmanKontroler.vidiTretmaneZaKlijenta(klijent)) {
			if(ttTretman.getStanje().equals(STANJE_TERETMANA.ZAKAZAN)) {
				nerealizovaniArrayList.add(ttTretman);
			}
		}
		
		
		table = new JTable(new TretmanZaKlijentaModel(nerealizovaniArrayList));
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		TableColumn koloneColumn= table.getColumnModel().getColumn(4);
		koloneColumn.setMinWidth(0);
		koloneColumn.setMaxWidth(0);
		koloneColumn.setPreferredWidth(0);
		koloneColumn.setResizable(false);
		
		
		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		nerealizovani.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Tretman> nerealizovaniArrayList = new ArrayList<Tretman>();
				for(Tretman t:tretmanKontroler.vidiTretmaneZaKlijenta(klijent)) {
					if(t.getStanje().equals(STANJE_TERETMANA.ZAKAZAN)) {
						nerealizovaniArrayList.add(t);
					}
				}
				table = new JTable(new TretmanZaKlijentaModel(nerealizovaniArrayList));
				table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				table.getTableHeader().setReorderingAllowed(false);
				TableColumn koloneColumn= table.getColumnModel().getColumn(4);
				koloneColumn.setMinWidth(0);
				koloneColumn.setMaxWidth(0);
				koloneColumn.setPreferredWidth(0);
				koloneColumn.setResizable(false);
				
				
				scrollPane.getViewport().removeAll();
				scrollPane.setViewportView(table);
				getContentPane().add(scrollPane, BorderLayout.CENTER);
				revalidate();
				
			}
		});
		
		realizovan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Tretman> realizovaniArrayList = new ArrayList<Tretman>();
				for(Tretman t:tretmanKontroler.vidiTretmaneZaKlijenta(klijent)) {
					if(!t.getStanje().equals(STANJE_TERETMANA.ZAKAZAN)) {
						realizovaniArrayList.add(t);
					}
				}
				table = new JTable(new TretmanZaKlijentaModel(realizovaniArrayList));
				table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				table.getTableHeader().setReorderingAllowed(false);
				TableColumn koloneColumn= table.getColumnModel().getColumn(4);
				koloneColumn.setMinWidth(0);
				koloneColumn.setMaxWidth(0);
				koloneColumn.setPreferredWidth(0);
				koloneColumn.setResizable(false);
				
				
				scrollPane.getViewport().removeAll();
				scrollPane.setViewportView(table);
				getContentPane().add(scrollPane, BorderLayout.CENTER);
				revalidate();
				
			}
		});
		
		btnOtkazi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = table.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Niste izabrali ni jedno polje");
				}
				else {
					Tretman tretman = tretmanKontroler.pronadjiTretman((int) table.getValueAt(red, 4));
							
					if(tretman!=null) {
						if(!tretman.isObrisan()) {
							boolean tempBoolean = tretmanKontroler.obradiTretman(tretman, STANJE_TERETMANA.OTKAZAO_KLIJENT);
							novac.setText("Potrosne novac: " + klijent.getPotrosenNovac());
							if(tempBoolean) {
								JOptionPane.showMessageDialog(null, "Uspesno otkazan tretman.");
								refreshData();
							}
							else {
								JOptionPane.showMessageDialog(null, "Tretman je vez obradjen.");
							}

						}
						else {
							JOptionPane.showMessageDialog(null, "Tretman je obrisan.");
						}
						
					}				
				}
				
			}
		});
	}
	
	public void refreshData() {
		TretmanZaKlijentaModel kl = (TretmanZaKlijentaModel)this.table.getModel();
		kl.fireTableDataChanged();
	}
}
