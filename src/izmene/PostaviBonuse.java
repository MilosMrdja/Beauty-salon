package izmene;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import kontroleri.KorisniciKontroler;
import kontroleri.TretmanKontroler;
import model.BonusiModel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

public class PostaviBonuse extends JDialog {

	private static final long serialVersionUID = 7372452177837995608L;
	private JTextField odradjeniTretmani;
	private JTextField prihod;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    
	public PostaviBonuse(JFrame p, KorisniciKontroler korisniciKontroler, TretmanKontroler tretmanKontroler) {
		
		
		
		setTitle("Postavljanje bonuse");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				p.setVisible(true);
			}
		});
		
		getContentPane().setLayout(new MigLayout("", "[165.00][165]", "[50][50][50][50][50][50][50][50][50][50][50][50]"));
		
		JLabel lblNewLabel = new JLabel("Minimalno odradjeni tretmani:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblNewLabel, "cell 0 0 2 1,alignx center");
		
		odradjeniTretmani = new JTextField();
		odradjeniTretmani.setHorizontalAlignment(SwingConstants.CENTER);
		odradjeniTretmani.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(odradjeniTretmani, "cell 0 1 2 1,alignx center,growy");
		odradjeniTretmani.setColumns(10);
		
		JLabel lblMinimalniPrihodZa = new JLabel("Minimalni prihod za salon:");
		lblMinimalniPrihodZa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblMinimalniPrihodZa, "cell 0 2 2 1,alignx center");
		
		prihod = new JTextField();
		prihod.setHorizontalAlignment(SwingConstants.CENTER);
		prihod.setFont(new Font("Tahoma", Font.PLAIN, 16));
		prihod.setColumns(10);
		getContentPane().add(prihod, "cell 0 3 2 1,alignx center,growy");
		
		JLabel lblPocetnoVreme = new JLabel("Pocetno vreme: (YYYY-MM-DD)");
		lblPocetnoVreme.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblPocetnoVreme, "cell 0 4 2 1,alignx center");
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_1.setColumns(10);
		getContentPane().add(textField_1, "cell 0 5 2 1,alignx center,growy");
		
		JLabel lblKrajnjeVreme = new JLabel("Krajnje vreme: (YYYY-MM-DD)");
		lblKrajnjeVreme.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblKrajnjeVreme, "cell 0 6 2 1,alignx center");
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_2.setColumns(10);
		getContentPane().add(textField_2, "cell 0 7 2 1,alignx center,growy");
		
		JLabel lblOsnovaBonusa = new JLabel("Osnova bonusa:");
		lblOsnovaBonusa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblOsnovaBonusa, "cell 0 8 2 1,alignx center");
		
		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_3.setColumns(10);
		getContentPane().add(textField_3, "cell 0 9 2 1,alignx center,growy");
		
		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane, "cell 0 10 1 2");
		
		
		JButton btnNewButton = new JButton("Vidi i postavi bonuse");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(btnNewButton, "cell 1 10,growx,aligny baseline");
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(btnCancel, "cell 1 11,growx,aligny top");
		
		btnNewButton.addActionListener(new ActionListener() {
			
			

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean flag = validacija(odradjeniTretmani, prihod, textField_1, textField_2, textField_3);
				if(flag) {
					LocalDate pDate = LocalDate.parse(textField_1.getText(), formatter);
					LocalDate kDate = LocalDate.parse(textField_2.getText(), formatter);
					korisniciKontroler.setBonuse(tretmanKontroler.getTretmani(), Integer.parseInt(odradjeniTretmani.getText()), Integer.parseInt(prihod.getText()), pDate, kDate, Integer.parseInt(textField_3.getText()));
					korisniciKontroler.azurirajFile();
					
					table = new JTable(new BonusiModel(korisniciKontroler.getKozmeticari()));
					table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					table.getTableHeader().setReorderingAllowed(false);
					
					
					scrollPane.getViewport().removeAll();
					scrollPane.setViewportView(table);
					getContentPane().add(scrollPane, "cell 0 10 1 2");
					revalidate();
				}
				
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PostaviBonuse.this.dispose();
				p.setVisible(true);
				
			}
		});
		
		pack();
		setLocationRelativeTo(null);

		
	}
	
	private boolean validacija(JTextField o,JTextField prihod,JTextField pocetak, JTextField kraj, JTextField osnova) {
		if(o.getText().compareTo("")==0) {
			JOptionPane.showMessageDialog(null, "Broj minimalno uradjenih tretmana ne sme biti prazno.");
			return false;
		}
		if(prihod.getText().compareTo("")==0) {
			JOptionPane.showMessageDialog(null, "Polje za prihod kozmeticara ne sme biti prazno.");
			return false;
		}
		if(pocetak.getText().compareTo("")==0) {
			JOptionPane.showMessageDialog(null, "Polje za pocetno vreme ne sme biti prazno.");
			return false;
		}
		if(kraj.getText().compareTo("")==0) {
			JOptionPane.showMessageDialog(null, "Polje za krajnje vreme ne sme biti prazno.");
			return false;
		}
		if(osnova.getText().compareTo("")==0) {
			JOptionPane.showMessageDialog(null, "Polje za osnovu bonusa ne sme biti prazno.");
			return false;
		}
		Pattern paterrn = Pattern.compile("\\d+");
		Pattern paterrn2 = Pattern.compile("\\d{4}-(0[1-9]|1[0-2])-([0-2][1-9]|3[0-1])");
		
		if(paterrn.matcher(o.getText()).matches()==false) {
			JOptionPane.showMessageDialog(null, "Broj minimalno uradjenih mora biti pozitivan broj.");
			return false;
		}
		if(paterrn.matcher(prihod.getText()).matches()==false) {
			JOptionPane.showMessageDialog(null, "Prihod kozmeticara salonu mora biti pozitivan broj.");
			return false;
		}
		
		
		if(paterrn2.matcher(pocetak.getText()).matches()==false) {
			JOptionPane.showMessageDialog(null, "Pocetno vreme nije u dobrom formatu.");
			return false;
		}
		
		if(paterrn2.matcher(kraj.getText()).matches()==false) {
			JOptionPane.showMessageDialog(null, "Krajnje vreme nije u dobrom formatu.");
			return false;
		}
		
		if(paterrn.matcher(osnova.getText()).matches()==false) {
			JOptionPane.showMessageDialog(null, "Osnova bonusa mora biti pozitivan broj.");
			return false;
		}
		return true;
	}
}
