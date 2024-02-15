package izmene;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import GUI.PregledZakazanihZaRecepcionera;
import entiteti.Kozmeticar;
import entiteti.Tretman;
import kontroleri.Kontroler;
import kontroleri.KorisniciKontroler;
import kontroleri.TretmanKontroler;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.JComboBox;

public class TretmanEdit extends JDialog {

	private static final long serialVersionUID = -1430592613780493855L;
	private TretmanKontroler tretmanKontroler;
	private JTextField textField;
	private JTextField textField_1;
	private KorisniciKontroler korisniciKontroler;

	public TretmanEdit(Kontroler kontroler,JFrame p, TretmanKontroler t,Tretman tretman, KorisniciKontroler k, PregledZakazanihZaRecepcionera temp) {
		setTitle("Izmena tretmana");
		this.tretmanKontroler = t;
		this.korisniciKontroler = k;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				p.setEnabled(true);
			}
		});
		
		getContentPane().setLayout(new MigLayout("", "[100,grow][100,grow]", "[50][50]15[50]15[50]"));
		
		JLabel kozmeticarLbl = new JLabel("Kozmeticar:");
		kozmeticarLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(kozmeticarLbl, "cell 0 0,alignx center");
		
		ArrayList<String> kozmeticariArrayList = new ArrayList<String>();
		for(Kozmeticar ko:korisniciKontroler.getKozmeticari()) {
			if(ko.proveraObucenost(tretman.getTipTretmana())) {
				kozmeticariArrayList.add(ko.getKorisnickoIme());
			}
		}
		String[] koStrings = new String[kozmeticariArrayList.size()+1];
		for(int i = 0;i < kozmeticariArrayList.size();i++) {
			koStrings[i] = kozmeticariArrayList.get(i);
		}
		koStrings[kozmeticariArrayList.size()]= " ";
		
		JComboBox<String> comboBox = new JComboBox<String>(koStrings);
		comboBox.setSelectedItem(tretman.getKozmeticar().getKorisnickoIme());
		getContentPane().add(comboBox, "cell 1 0,growx");

		
		JLabel lblDatum = new JLabel("Datum (YYYY-MM-DD):");
		lblDatum.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblDatum, "cell 0 2,alignx center");
		
		textField = new JTextField();
		getContentPane().add(textField, "cell 1 2,grow");
		textField.setColumns(10);
		String datumString = tretman.getVreme().toLocalDate().toString();
		textField.setText(datumString);
		
		
		JLabel lblVremehhmm = new JLabel("Vreme(HH:MM):");
		lblVremehhmm.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblVremehhmm, "cell 0 3,alignx center");
		
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		getContentPane().add(textField_1, "cell 1 3,grow");
		String vremeString = tretman.getVreme().toLocalTime().toString();
		textField_1.setText(vremeString);
		
		JButton btnNewButton_1 = new JButton("Odustani");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(btnNewButton_1, "cell 0 4,alignx center,growy");
		
		JButton btnNewButton = new JButton("Izmeni");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(btnNewButton, "cell 1 4,alignx center,growy");
		
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				p.setEnabled(true);
				TretmanEdit.this.dispose();
				
			}
		});
		
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedIndex()!=comboBox.getItemCount()-1) {
					textField.setEditable(true);
					textField_1.setEditable(true);
				}
				else {
					textField.setEditable(false);
					textField_1.setEditable(false);
					textField.setText("");
					textField_1.setText("");
				}
				
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedIndex()==comboBox.getItemCount()-1) {
					JOptionPane.showMessageDialog(null, "Morate izabrati kozmeticara.");
				}
				else {
					Kozmeticar kozmeticar = (Kozmeticar) korisniciKontroler.pronadjiKorisnika(comboBox.getSelectedItem().toString());
					boolean flagIzmena = provera_datum(kontroler,kozmeticar, textField.getText(), textField_1.getText(), t, tretman);
					if(flagIzmena) {
						LocalDate datumDate = LocalDate.parse(textField.getText()); 
						LocalTime vremeLocalTime = LocalTime.parse(textField_1.getText());
						
						LocalDateTime pocetakTretmanaDateTime = datumDate.atTime(vremeLocalTime); 
						tretmanKontroler.izmeniTretman(pocetakTretmanaDateTime, kozmeticar, tretman);
						JOptionPane.showMessageDialog(null, "Uspesno izmenjen tretman");
						temp.refreshData();
						p.setEnabled(true);
						TretmanEdit.this.dispose();
						
					}
				}
			
				
			}
		});
		
		pack();
		setLocationRelativeTo(null);

	}
	
	private boolean provera_datum(Kontroler kontroler,Kozmeticar kozmeticar, String datum,String vreme, TretmanKontroler tKontroler, Tretman tretman) {
		if(datum.compareTo("")==0) {
			JOptionPane.showMessageDialog(null, "Datum ne sme biti prazan.");
			return false;
		}
		else {
			Pattern regex = Pattern.compile("\\d{4}-(0[1-9]|1[0-2])-([0-2][1-9]|3[0-1])");
			if(regex.matcher(datum).matches()==false) {
				JOptionPane.showMessageDialog(null, "Datum nije u dobrom obliku.");
				return false;
			}
		}
		if(vreme.compareTo("")==0) {
			JOptionPane.showMessageDialog(null, "Vreme ne sme biti prazno.");
			return false;
		}
		else {
			Pattern regex = Pattern.compile("([01]\\d|2[0-3]):[0-5]\\d");
			if(regex.matcher(vreme).matches()==false) {
				JOptionPane.showMessageDialog(null, "Vreme nije u dobrom obliku.");
				return false;
			}
		}
		LocalDate datumDate = LocalDate.parse(datum); //datum kada pocinje
		LocalTime vremeLocalTime = LocalTime.parse(vreme);//vreme kada pocinje
		
		LocalDateTime pocetakTretmanaDateTime = datumDate.atTime(vremeLocalTime); //kada je tretman zakazan
		LocalDateTime zavrsetakTretmanaDateTime = pocetakTretmanaDateTime.plusHours(tretman.getUsluga().getTrajanjeUsluge().getHour()).plusMinutes(tretman.getUsluga().getTrajanjeUsluge().getMinute());
		
		 if(!pocetakTretmanaDateTime.isAfter(LocalDateTime.now())){
			 JOptionPane.showMessageDialog(null, "Vreme ne sme biti u proslosti.");
			 return false;
		 }
		 if(vremeLocalTime.isBefore(kontroler.getPocetakRadnogVremena())) {
			 JOptionPane.showMessageDialog(null, "Vreme je pre radnog vremena");
			 return false;
		 }
		 if(kontroler.getKrajRadnogVremena().isBefore(zavrsetakTretmanaDateTime.toLocalTime())) {
			 JOptionPane.showMessageDialog(null, "Vreme, odnosno dok bi se zvrsio tretman je posle radnog vremena");
			 return false;
		 }
		
		for(Tretman t:tretmanKontroler.getTretmani()) {
			 if(t.getKozmeticar() == kozmeticar && t.getVreme().toLocalDate().equals(datumDate)) {
				 LocalDateTime vremeZavrsetkaDateTime = t.getVreme().plusHours(t.getUsluga().getTrajanjeUsluge().getHour()).plusMinutes(t.getUsluga().getTrajanjeUsluge().getMinute());
				 if(t.getVreme().equals(pocetakTretmanaDateTime)) {
					 JOptionPane.showMessageDialog(null, "U ovo vreme kozmeticar je zauzet.");
					 return false;
				 }
				 if(pocetakTretmanaDateTime.isAfter(t.getVreme()) && pocetakTretmanaDateTime.isBefore(vremeZavrsetkaDateTime)) {
					 JOptionPane.showMessageDialog(null, "U ovo vreme kozmeticar je zauzet drugim tretmanom.");

					 return false;
				 }
				 if(zavrsetakTretmanaDateTime.isAfter(t.getVreme()) && zavrsetakTretmanaDateTime.isBefore(vremeZavrsetkaDateTime)) {
					 JOptionPane.showMessageDialog(null, "U ovo vreme kozmeticar je zauzet drugim tretmanom.");

					 return false;
				 }
				
			 }
		 }
		return true;
	}

}
