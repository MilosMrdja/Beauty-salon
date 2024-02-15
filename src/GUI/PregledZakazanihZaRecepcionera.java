package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;

import entiteti.STANJE_TERETMANA;
import entiteti.TipTretmana;
import entiteti.Tretman;
import izmene.TretmanEdit;
import kontroleri.Kontroler;
import kontroleri.KorisniciKontroler;
import kontroleri.TipTretmanaKontroler;
import kontroleri.TretmanKontroler;
import model.TretmaniRecepcionerModel;

public class PregledZakazanihZaRecepcionera extends JFrame {


	private static final long serialVersionUID = -69870075823227992L;
	private TretmanKontroler tretmanKontroler;
	private JTable table;
	private TipTretmanaKontroler tipKontroler;
	private KorisniciKontroler korisniciKontroler;
	private Kontroler kontroler;

	public PregledZakazanihZaRecepcionera(JFrame p,TretmanKontroler t, TipTretmanaKontroler tk, KorisniciKontroler kor, Kontroler kontroler) {
		this.tretmanKontroler = t;
		this.tipKontroler = tk;
		this.korisniciKontroler = kor;
		this.kontroler = kontroler;
		setTitle("Zakazani tretmani");
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
		
		JButton btnOtkaziZaSalon = new JButton("Otkazi za salon");
		JButton btnNijeSePojavio = new JButton("Klijent se nije pojavio");
		JButton btnIzmeni = new JButton("Izmeni tretman");
		
		mainToolbar.add(btnOtkaziZaSalon);
		mainToolbar.add(btnNijeSePojavio);
		mainToolbar.add(btnIzmeni);
		
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
		
		JPanel filter = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		JButton filterButton = new JButton("Filtriraj");
		filter.add(filterButton);
		
		filter.add(new JLabel("Tretman: "));
		ArrayList<String> tretmaniArrayList = new ArrayList<String>();
		for(Tretman k:tretmanKontroler.getTretmani()) {
			tretmaniArrayList.add(k.getOpis());
			
		}
		String[] skStrings = new String[tretmaniArrayList.size()+1];
		for(int i =0; i< tretmaniArrayList.size();i++) {
			skStrings[i] = tretmaniArrayList.get(i);
		}
		skStrings[tretmaniArrayList.size()] = " ";
		
		JComboBox<String> comboBox1 = new JComboBox<String>(skStrings);
		comboBox1.setSelectedItem(" ");
		filter.add(comboBox1);
		
		filter.add(new JLabel("Tip: "));
		ArrayList<String> tipoviArrayList = new ArrayList<String>();
		for(TipTretmana tp:tipKontroler.getTipovi()) {
			tipoviArrayList.add(tp.getImeTip());
			
		}
		String[] tpStrings = new String[tipoviArrayList.size()+1];
		for(int i =0; i< tipoviArrayList.size();i++) {
			tpStrings[i] = tipoviArrayList.get(i);
		}
		tpStrings[tipoviArrayList.size()] = " ";
		
		JComboBox<String> comboBox2 = new JComboBox<String>(tpStrings);
		comboBox2.setSelectedItem(" ");
		filter.add(comboBox2);
		
		filter.add(new JLabel("Cena: "));
		JTextField cena1 = new JTextField(10);
		filter.add(cena1);
		filter.add(new JLabel(" - "));
		JTextField cena2 = new JTextField(10);
		filter.add(cena2);
		
		
		add(filter, BorderLayout.SOUTH);
		
		btnIzmeni.addActionListener(new ActionListener() {
			
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
							TretmanEdit tretmanEdit = new TretmanEdit(kontroler,t, tretmanKontroler, tretman, korisniciKontroler, PregledZakazanihZaRecepcionera.this);
							tretmanEdit.setVisible(true);
							t.setEnabled(false);
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
		
		filterButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cena1.getText().compareTo("")!=0 && cena2.getText().compareTo("")==0) {
					JOptionPane.showMessageDialog(null, "Cena mora biti u opsegu. (OD - DO)");
				}
				else if(cena1.getText().compareTo("")==0 && cena2.getText().compareTo("")!=0) {
					JOptionPane.showMessageDialog(null, "Cena mora biti u opsegu. (OD - DO)");
				}
				else {
					boolean flagCena = true;
					if(cena1.getText().compareTo("")!=0 && cena2.getText().compareTo("")!=0) {
						flagCena = proveraCena(cena1.getText(), cena2.getText());
					}
					if(flagCena) {
						String tretmanString = comboBox1.getSelectedItem().toString();
						String tipString = comboBox2.getSelectedItem().toString();
						String cena_1 = cena1.getText();
						String cena_2 = cena2.getText();
						tretmans.clear();
						if(tretmanString.compareTo(" ")!=0 && tipString.compareTo(" ")!=0 && cena_1.compareTo("")!=0 && cena_2.compareTo("")!=0) {
							for(Tretman t:tretmanKontroler.getTretmani()) {
								if(t.getOpis().compareTo(tretmanString)==0 && t.getTipTretmana().getImeTip().compareTo(tipString)==0 && t.getCena() >= Double.parseDouble(cena_1) && t.getCena() <= Double.parseDouble(cena_2)) {
									if(t.getStanje().equals(STANJE_TERETMANA.ZAKAZAN)) {
										tretmans.add(t);
									}	
								}
							}
						}
						else if(tretmanString.compareTo(" ")!=0 && tipString.compareTo(" ")==0 && cena_1.compareTo("")==0 && cena_2.compareTo("")==0) {
							for(Tretman t:tretmanKontroler.getTretmani()) {
								if(t.getOpis().compareTo(tretmanString)==0) {
									if(t.getStanje().equals(STANJE_TERETMANA.ZAKAZAN)) {
										tretmans.add(t);
									}
								}
							}
						}
						else if(tretmanString.compareTo(" ")==0 && tipString.compareTo(" ")!=0 && cena_1.compareTo("")==0 && cena_2.compareTo("")==0) {
							for(Tretman t:tretmanKontroler.getTretmani()) {
								if(t.getTipTretmana().getImeTip().compareTo(tipString)==0) {
									if(t.getStanje().equals(STANJE_TERETMANA.ZAKAZAN)) {
										tretmans.add(t);
									}
								}
							}
						}
						else if(tretmanString.compareTo(" ")==0 && tipString.compareTo(" ")==0 && cena_1.compareTo("")!=0 && cena_2.compareTo("")!=0) {
							for(Tretman t:tretmanKontroler.getTretmani()) {
								if(t.getCena() >= Double.parseDouble(cena_1) && t.getCena() <= Double.parseDouble(cena_2)) {
									if(t.getStanje().equals(STANJE_TERETMANA.ZAKAZAN)) {
										tretmans.add(t);
									}
								}
							}
						}
						else if(tretmanString.compareTo(" ")!=0 && tipString.compareTo(" ")!=0 && cena_1.compareTo("")==0 && cena_2.compareTo("")==0) {
							for(Tretman t:tretmanKontroler.getTretmani()) {
								if(t.getOpis().compareTo(tretmanString)==0 && t.getTipTretmana().getImeTip().compareTo(tipString)==0) {
									if(t.getStanje().equals(STANJE_TERETMANA.ZAKAZAN)) {
										tretmans.add(t);
									}
								}
							}
						}
						else if(tretmanString.compareTo(" ")!=0 && tipString.compareTo(" ")==0 && cena_1.compareTo("")!=0 && cena_2.compareTo("")!=0) {
							for(Tretman t:tretmanKontroler.getTretmani()) {
								if(t.getOpis().compareTo(tretmanString)==0 && t.getCena() >= Double.parseDouble(cena_1) && t.getCena() <= Double.parseDouble(cena_2)) {
									if(t.getStanje().equals(STANJE_TERETMANA.ZAKAZAN)) {
										tretmans.add(t);
									}
								}
							}
						}
						else if(tretmanString.compareTo(" ")==0 && tipString.compareTo(" ")!=0 && cena_1.compareTo("")!=0 && cena_2.compareTo("")!=0) {
							for(Tretman t:tretmanKontroler.getTretmani()) {
								if(t.getTipTretmana().getImeTip().compareTo(tipString)==0 && t.getCena() >= Double.parseDouble(cena_1) && t.getCena() <= Double.parseDouble(cena_2)) {
									if(t.getStanje().equals(STANJE_TERETMANA.ZAKAZAN)) {
										tretmans.add(t);
									}
								}
							}
						}
						else {
							for(Tretman t:tretmanKontroler.getTretmani()) {
								if(t.getStanje().equals(STANJE_TERETMANA.ZAKAZAN)) {
									tretmans.add(t);
								}
							}
						}
						table.setModel(new TretmaniRecepcionerModel(tretmans));
					}
				}
				
			}
		});
		
		btnOtkaziZaSalon.addActionListener(new ActionListener() {
			
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
							boolean flagTBoolean = tretmanKontroler.obradiTretman(tretman, STANJE_TERETMANA.OTKAZAO_SALON);
							if(flagTBoolean) {
								JOptionPane.showMessageDialog(null, "Salon je otkazao tretman.");
								tretmanKontroler.azurirajFile();
								korisniciKontroler.azurirajFile();
								refreshData();
							}
							else {
								JOptionPane.showMessageDialog(null, "Tretman je vec obradjen.");

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
		
		btnNijeSePojavio.addActionListener(new ActionListener() {
			
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
							boolean flagB=tretmanKontroler.obradiTretman(tretman, STANJE_TERETMANA.NIJE_SE_POJAVIO);
							if(flagB) {
								JOptionPane.showMessageDialog(null, "Salon je obavesten da se klijent nije pojavio.");
								tretmanKontroler.azurirajFile();
								korisniciKontroler.azurirajFile();
								refreshData();
							}
							else {
								JOptionPane.showMessageDialog(null, "Tretman je vec obradjen.");

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
	
	private boolean proveraCena(String cena1, String cena2) {
		Pattern regex = Pattern.compile("(?:[1-9]\\d*|0)(?:\\.\\d+)?");
		if(regex.matcher(cena1).matches()==false) {
			JOptionPane.showMessageDialog(null, "Cena mora biti broj");
			return false;
		}
		if(regex.matcher(cena2).matches()==false) {
			JOptionPane.showMessageDialog(null, "Cena mora biti broj");
			return false;
		}
		return true;
	}

	
	public void refreshData() {
		TretmaniRecepcionerModel kr = (TretmaniRecepcionerModel)this.table.getModel();
		kr.fireTableDataChanged();
	}
	
}
