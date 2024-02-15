package izmene;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import entiteti.TipTretmana;
import entiteti.UslugaTretman;
import kontroleri.TipTretmanaKontroler;
import kontroleri.UslugaTretmanaKontroler;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import GUI.UslugaTableFrame;

import javax.swing.JComboBox;

public class UslugaAddEdit extends JDialog {

	private static final long serialVersionUID = -3618964818127306882L;
	private UslugaTretmanaKontroler uslugaKontroler;
	private JTextField nazivTxt;
	private JTextField cenaTxt;
	private JTextField trajanjeTxt;

	public UslugaAddEdit(JFrame p, UslugaTretmanaKontroler u, UslugaTretman uslugaTretman, TipTretmanaKontroler t) {
		if(uslugaTretman == null) {
			setTitle("Dodavanje usluge");
		}
		else {
			setTitle("Izmena usluge");
		}
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				p.setEnabled(true);
			}
		});
		this.uslugaKontroler = u;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		
		getContentPane().setLayout(new MigLayout("", "[100,grow][100,grow]", "[50][50][50][50][50][50][50][50][70]"));
		
		JLabel lblNewLabel = new JLabel("Naziv:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblNewLabel, "cell 0 0 2 1,alignx center");
		
		nazivTxt = new JTextField();
		nazivTxt.setHorizontalAlignment(SwingConstants.CENTER);
		nazivTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(nazivTxt, "cell 0 1 2 1,alignx center,growy");
		nazivTxt.setColumns(10);
		
		JLabel lblTipTretmana = new JLabel("Tip tretmana:");
		lblTipTretmana.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblTipTretmana, "cell 0 2 2 1,alignx center");
		
		
		ArrayList<String> tipoviArrayList = new ArrayList<String>();
		for(TipTretmana tip:t.getTipovi()) {
			tipoviArrayList.add(tip.getImeTip());
		}
		String[] aaaStrings = new String[tipoviArrayList.size()];
		for(int i = 0;i < tipoviArrayList.size();i++) {
			aaaStrings[i] = tipoviArrayList.get(i);
		}
		JComboBox<String> comboBox = new JComboBox<String>(aaaStrings);
		getContentPane().add(comboBox, "cell 0 3 2 1,alignx center,growy");
		
		JLabel lblCena = new JLabel("Cena:");
		lblCena.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblCena, "cell 0 4 2 1,alignx center");
		
		cenaTxt = new JTextField();
		cenaTxt.setHorizontalAlignment(SwingConstants.CENTER);
		cenaTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(cenaTxt, "cell 0 5 2 1,alignx center,growy");
		cenaTxt.setColumns(10);
		
		JLabel lblTrajanje = new JLabel("Trajanje (HH:MM):");
		lblTrajanje.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblTrajanje, "cell 0 6 2 1,alignx center");
		
		trajanjeTxt = new JTextField();
		trajanjeTxt.setHorizontalAlignment(SwingConstants.CENTER);
		trajanjeTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(trajanjeTxt, "cell 0 7 2 1,alignx center,growy");
		trajanjeTxt.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(btnCancel, "cell 0 8,alignx center,growy");
		
		JButton btnNewButton = new JButton("Dodaj");
		if(uslugaTretman != null) {
			btnNewButton.setText("Izmeni");
			nazivTxt.setText(uslugaTretman.getImeTretmana());
			cenaTxt.setText(String.valueOf(uslugaTretman.getCena()));
			trajanjeTxt.setText(uslugaTretman.getTrajanjeUsluge().toString());
			comboBox.setSelectedItem(uslugaTretman.getTipTretmana().getImeTip());
		}
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(btnNewButton, "cell 1 8,alignx center,growy");
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean flag = validacija(nazivTxt, cenaTxt, trajanjeTxt);
				if(flag==true) {
					String nazivString = nazivTxt.getText();
					int cena = (int) Double.parseDouble(cenaTxt.getText());
					DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
					TipTretmana tippTipTretmana = t.getTipovi().get(0);
					LocalTime vreme = LocalTime.parse(trajanjeTxt.getText(), dateTimeFormatter);
					for(TipTretmana tp:t.getTipovi()) {
						if(comboBox.getSelectedItem().toString().compareTo(tp.getImeTip())==0) {
							tippTipTretmana = tp;
						}
					}
					if(uslugaTretman == null) {
						int id = uslugaKontroler.postaviIdUsluga();
						uslugaKontroler.dodajUslugu(new UslugaTretman(tippTipTretmana, nazivString, cena, id, vreme, false));
					}
					else {
						uslugaTretman.setImeTretmana(nazivString);
						uslugaTretman.setCena(cena);
						uslugaTretman.setTipTretmana(tippTipTretmana);
						uslugaTretman.setTrajanjeUsluge(vreme);
						u.azurirajFile();
					}
					((UslugaTableFrame)p).refreshData();
					p.setEnabled(true);
					UslugaAddEdit.this.dispose();
				}
				
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				p.setEnabled(true);
				UslugaAddEdit.this.dispose();
				
			}
		});
		
		pack();
		setLocationRelativeTo(null);

		
	}
	
	private boolean validacija(JTextField naziv, JTextField cena, JTextField datum) {
		if(naziv.getText().compareTo("")==0) {
			JOptionPane.showMessageDialog(null, "Niste uneli naziv");
			return false;
		}
		else if(cena.getText().compareTo("")==0) {
			JOptionPane.showMessageDialog(null, "Niste uneli cenu");
			return false;
		}
		else if(datum.getText().compareTo("")==0) {
			JOptionPane.showMessageDialog(null, "Niste uneli vreme trajanja");
			return false;
		}
		Pattern paterrn = Pattern.compile("-?\\d*\\.?\\d+");
		
		if(paterrn.matcher(cena.getText()).matches()==false) {
			JOptionPane.showMessageDialog(null, "Cena mora biti broj");
			return false;
		}
		Pattern paterrn1 = Pattern.compile("^([01]?\\d|2[0-3]):[0-5]\\d$");
		
		if(!paterrn1.matcher(datum.getText()).matches()) {
			JOptionPane.showMessageDialog(null, "Vreme trajanja nije u dobrom formatu");
			return false;
		}
		
		return true;
	}

}
