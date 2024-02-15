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
import entiteti.Kozmeticar;
import izmene.KozmeticarAddEdit;
import kontroleri.KorisniciKontroler;
import kontroleri.TipTretmanaKontroler;
import model.KozmeticarModel;

public class KozmeticarTableFrame extends JFrame {

	private static final long serialVersionUID = 8196789931440385611L;
	private JFrame parentFrame;
	private KorisniciKontroler korisniciKontroler;
	private TipTretmanaKontroler tipTretmanaKontroler;
	
	private JToolBar mainToolbar;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;
	private JTable table;

	
	public KozmeticarTableFrame(JFrame p, KorisniciKontroler korisniciKontroler, TipTretmanaKontroler tk) {
		this.parentFrame = p;
		this.korisniciKontroler = korisniciKontroler;
		this.tipTretmanaKontroler = tk;
		
		this.setTitle("Kozmeticari");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.setResizable(true);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				parentFrame.setVisible(true);
			}
		});
		
		init(this);
		this.pack();
		this.setLocationRelativeTo(null);
		
		
	}
	
	private void init(JFrame t) {
		BorderLayout borderLayout = new BorderLayout();
		t.setLayout(borderLayout);
		mainToolbar = new JToolBar();
		
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
		
		table = new JTable(new KozmeticarModel(korisniciKontroler));
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				KozmeticarAddEdit add = new KozmeticarAddEdit(t, korisniciKontroler, null,tipTretmanaKontroler);
				add.setVisible(true);
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
					KozmeticarAddEdit editDialog = new KozmeticarAddEdit(t, korisniciKontroler,(Kozmeticar) temp, tipTretmanaKontroler);
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
						int a = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete kozmeticara?", table.getValueAt(red, 0) + " "+ table.getValueAt(red, 1),JOptionPane.YES_NO_OPTION);
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
		KozmeticarModel kl = (KozmeticarModel)this.table.getModel();
		kl.fireTableDataChanged();
	}

}
