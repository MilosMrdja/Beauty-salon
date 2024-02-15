package GUI;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;

import entiteti.Tretman;
import kontroleri.TretmanKontroler;
import model.TretmanModel;

public class TretmanTableFrame extends JFrame {

	private static final long serialVersionUID = -4307771324758047070L;
	private TretmanKontroler tretmanKontroler;
	private JFrame parentF;
	private JButton btnDelete;
	private JTable table;

	public TretmanTableFrame(JFrame p, TretmanKontroler t) {
		this.tretmanKontroler = t;
		this.parentF = p;
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
		t.setLayout(borderLayout);
		JToolBar mainToolbar = new JToolBar();
		
//		btnEdit = new JButton();
//		ImageIcon editIcon = new ImageIcon("slike/edit.png");
//		ImageIcon temp = new ImageIcon(editIcon.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH));
//		editIcon = temp;
//		btnEdit.setIcon(editIcon);
//		mainToolbar.add(btnEdit);
		
		btnDelete = new JButton();
		ImageIcon deleteIcon = new ImageIcon("slike/delete.png");
		ImageIcon temp = new ImageIcon(deleteIcon.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH));
		deleteIcon = temp;
		btnDelete.setIcon(deleteIcon);
		mainToolbar.add(btnDelete);
		
		getContentPane().add(mainToolbar, BorderLayout.NORTH);
		table = new JTable(new TretmanModel(tretmanKontroler));
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
	
		
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = table.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Niste izabrali ni jedno polje");

				}
				else {
					Tretman tretman = tretmanKontroler.pronadjiTretman((int) table.getValueAt(red, 0));
					if(tretman != null) {
						int a = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete tretman?", table.getValueAt(red, 0) + " "+ table.getValueAt(red, 1),JOptionPane.YES_NO_OPTION);
						if(a == JOptionPane.YES_OPTION) {
							tretman.setObrisan(true);
							tretmanKontroler.azurirajFile();
							refreshData();
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
		TretmanModel kl = (TretmanModel)this.table.getModel();
		kl.fireTableDataChanged();
	}
}
