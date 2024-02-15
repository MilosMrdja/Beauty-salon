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

import entiteti.Korisnik;
import entiteti.Menadzer;
import izmene.MenadzerAddEdit;
import kontroleri.KorisniciKontroler;
import model.MenadzerModel;

public class MenadzerTabelFrame extends JFrame {


	private static final long serialVersionUID = 144850359181877772L;
	private JFrame parentF;
	private KorisniciKontroler korisniciKontroler;
	private JTable table;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;

	public MenadzerTabelFrame(JFrame p, KorisniciKontroler k) {
		this.parentF = p;
		this.korisniciKontroler = k;
		this.setTitle("Menadzeri");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(true);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				parentF.setVisible(true);
			}
		});
		
		init(this);
		this.pack();
		this.setLocationRelativeTo(null);

	}
	
	private void init(JFrame t) {
		BorderLayout borderLayout = new BorderLayout();
		t.setLayout(borderLayout);
		JToolBar mainToolbar = new JToolBar();
		
		btnAdd = new JButton();
		ImageIcon addIcon = new ImageIcon("slike/add.jpg");
		ImageIcon temp = new ImageIcon(addIcon.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH));
		addIcon = temp;
		btnAdd.setIcon(addIcon);
		mainToolbar.add(btnAdd);
		
		btnEdit = new JButton();
		ImageIcon editIcon = new ImageIcon("slike/edit.png");
		temp = new ImageIcon(editIcon.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH));
		editIcon = temp;
		btnEdit.setIcon(editIcon);
		mainToolbar.add(btnEdit);
		
		btnDelete = new JButton();
		ImageIcon deleteIcon = new ImageIcon("slike/delete.png");
		temp = new ImageIcon(deleteIcon.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH));
		deleteIcon = temp;
		btnDelete.setIcon(deleteIcon);
		mainToolbar.add(btnDelete);
		
		getContentPane().add(mainToolbar, BorderLayout.NORTH);
		
		table = new JTable(new MenadzerModel(korisniciKontroler));
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MenadzerAddEdit addEdit = new MenadzerAddEdit(t, korisniciKontroler, null);
				addEdit.setVisible(true);
				t.setEnabled(false);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = table.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Niste izabrali ni jedno polje");
				}
				else {
					Korisnik temp = korisniciKontroler.pronadjiKorisnika(table.getValueAt(red, 4).toString());
					MenadzerAddEdit editDialog = new MenadzerAddEdit(t, korisniciKontroler,(Menadzer) temp);
					editDialog.setVisible(true);
					t.setEnabled(false);
				}
				
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = table.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Niste izabrali ni jedno polje");

				}
				else {
					Korisnik korisnik = korisniciKontroler.pronadjiKorisnika(table.getValueAt(red, 4).toString());
					if(korisnik != null) {
						int a = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete menadzera?", table.getValueAt(red, 0) + " "+ table.getValueAt(red, 1),JOptionPane.YES_NO_OPTION);
						if(a == JOptionPane.YES_OPTION) {
							korisniciKontroler.izbrisiKorisnika(korisnik);
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
		MenadzerModel kl = (MenadzerModel)this.table.getModel();
		kl.fireTableDataChanged();
	}

}
