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

import entiteti.TipTretmana;
import izmene.TipTretmanaAddEdit;
import kontroleri.TipTretmanaKontroler;
import model.TipModel;

public class TipTretmanaTableFrame extends JFrame {


	private static final long serialVersionUID = -1769396170870967175L;
	private JFrame parentF;
	private TipTretmanaKontroler tipKontroler;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;
	private JTable table;

	public TipTretmanaTableFrame(JFrame p, TipTretmanaKontroler tipTretmanaKontroler) {
		this.parentF = p;
		this.tipKontroler = tipTretmanaKontroler;
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
		
		table = new JTable(new TipModel(tipKontroler));
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TipTretmanaAddEdit addEdit = new TipTretmanaAddEdit(t, tipKontroler, null);
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
					TipTretmana tipTretmana = tipKontroler.pronadjiTipTretman((int) table.getValueAt(red, 0));
					TipTretmanaAddEdit edit = new TipTretmanaAddEdit(t, tipKontroler, tipTretmana);
					edit.setVisible(true);
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
					TipTretmana tipTretmana = tipKontroler.pronadjiTipTretman((int) table.getValueAt(red, 0));
					if(tipTretmana != null) {
						int a = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete tip tretmana?", (String) table.getValueAt(red, 1),JOptionPane.YES_NO_OPTION);
						if(a == JOptionPane.YES_OPTION) {
							tipKontroler.izbrisiTip(tipTretmana);
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
		TipModel kl = (TipModel)this.table.getModel();
		kl.fireTableDataChanged();
	}

}
