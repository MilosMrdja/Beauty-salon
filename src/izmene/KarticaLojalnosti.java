package izmene;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import kontroleri.KorisniciKontroler;
import model.KarticaLojalnostiModel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public class KarticaLojalnosti extends JDialog {

	private static final long serialVersionUID = -5023579773114465089L;
	private JTextField limitTxt;
	private JTable table;


	public KarticaLojalnosti(JFrame p, KorisniciKontroler korisniciKontroler) {
		
		setTitle("Limit za karticu lojalnosti");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				p.setVisible(true);
			}
		});
		
		getContentPane().setLayout(new MigLayout("", "[60,grow][60,grow]", "[50][50]20[50,grow]20[50]20[50]"));
		
		JLabel lblNewLabel = new JLabel("Postavi limit:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblNewLabel, "cell 0 0 2 1,alignx center");
		
		limitTxt = new JTextField();
		limitTxt.setHorizontalAlignment(SwingConstants.CENTER);
		limitTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(limitTxt, "cell 0 1 2 1,alignx center,growy");
		limitTxt.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(btnCancel, "cell 0 4,grow");
		
		JButton btnNewButton = new JButton("Postavi i vidi klijente");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(btnNewButton, "cell 1 4,alignx trailing,growy");
		
		btnNewButton.addActionListener(new ActionListener() {
			
			

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean flag = validacija(limitTxt);
				if(flag) {
					korisniciKontroler.setKarticeLojalnosti(Integer.parseInt(limitTxt.getText()));
					korisniciKontroler.azurirajFile();
					table = new JTable(new KarticaLojalnostiModel(korisniciKontroler.getKlijenti()));
					table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					table.getTableHeader().setReorderingAllowed(false);
					
					scrollPane.getViewport().removeAll();
					scrollPane.setViewportView(table);
					getContentPane().add(scrollPane, "cell 0 2 2 2,grow");
					revalidate();
				}
				
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				KarticaLojalnosti.this.dispose();
				p.setVisible(true);
			}
		});
		
		pack();
		setLocationRelativeTo(null);

		
	}
	


	private boolean validacija(JTextField i) {
		if(i.getText().compareTo("")==0) {
			JOptionPane.showMessageDialog(null, "Niste uneli limit.");
			return false;
		}
		Pattern paterrn = Pattern.compile("-?\\d*\\.?\\d+");
		
		if(paterrn.matcher(i.getText()).matches()==false) {
			JOptionPane.showMessageDialog(null, "Limit mora biti broj");
			return false;
		}
		return true;
	}
	

}
