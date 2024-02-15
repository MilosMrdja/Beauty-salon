package izmene;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import GUI.TipTretmanaTableFrame;
import entiteti.TipTretmana;
import kontroleri.TipTretmanaKontroler;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class TipTretmanaAddEdit extends JDialog {

	private static final long serialVersionUID = -5503958021092871687L;
	private JTextField textField;

	public TipTretmanaAddEdit(JFrame p, TipTretmanaKontroler tipTretmanaKontroler, TipTretmana tipTretmana) {
		if(tipTretmana== null) {
			setTitle("Dodavanje tipa tretmana");
		}
		else {
			setTitle("Izmena tipa tretmana");
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				p.setEnabled(true);
			}
		});
		
		
		
		getContentPane().setLayout(new MigLayout("", "[100,grow][100,grow]", "[50][50][30][50]"));
		
		JLabel lblNewLabel = new JLabel("Naziv:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblNewLabel, "cell 0 0 2 1,alignx center");
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(textField, "cell 0 1 2 1,alignx center,growy");
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(btnNewButton, "cell 0 3,alignx center,growy");
		
		JButton btnNewButton_1 = new JButton("Dodaj");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(btnNewButton_1, "cell 1 3,alignx center,growy");
		if(tipTretmana != null) {
			btnNewButton_1.setText("Izmeni");
			textField.setText(tipTretmana.getImeTip());
		}
		
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean flag = validacija(textField);
				if(flag) {
					String imeString = textField.getText();
					if(tipTretmana == null) {
						int id = tipTretmanaKontroler.postaviIdTip();
						tipTretmanaKontroler.dodajTip(new TipTretmana(id, imeString));
					}
					else {
						tipTretmana.setImeTip(imeString);
						tipTretmanaKontroler.azurirajFile();
					}
					((TipTretmanaTableFrame)p).refreshData();
					p.setEnabled(true);
					TipTretmanaAddEdit.this.dispose();
				}
				
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				p.setEnabled(true);
				TipTretmanaAddEdit.this.dispose();
				
			}
		});
		
		pack();
		setLocationRelativeTo(null);

	
	}
	
	private boolean validacija(JTextField ime) {
		if(ime.getText().compareTo("")==0) {
			JOptionPane.showMessageDialog(null, "Niste uneli naziv tipa");
			return false;
		}
		return true;
		
	}

}
