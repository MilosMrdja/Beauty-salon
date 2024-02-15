package GUI;

import javax.swing.JFrame;

import entiteti.Menadzer;
import izmene.KarticaLojalnosti;
import izmene.PostaviBonuse;
import kontroleri.Kontroler;
import model.IzvestajTipovaModel;
import model.IzvestajKozmeticaraPoTretmanimaModel;
import model.IzvestajPrihodiUMesecDanaModel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.SwingConstants;

import charts.AngazovanjeKozmeticaraChart;
import charts.GodisnjiPrihodChart;
import charts.PrikazStatusaTretmanaChart;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class menadzerFrame extends JFrame {

	private static final long serialVersionUID = -4101827531821328454L;
	private Menadzer menadzer;
	private Kontroler kontroler;
	private JTextField pocetak;
	private JTextField kraj;
	
	private JTable table;
	private LocalDate pDateTime;
	private LocalDate kDateTime;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private JScrollPane scrollPane;
	

	public menadzerFrame(Menadzer m, Kontroler kontroler, JDialog main) {
		this.menadzer = m;
		this.kontroler = kontroler;
		this.setTitle("Meni menadzer");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		
		initMeni(this, main);
		this.pack();
		this.setLocationRelativeTo(null);

	}
	
	private void initMeni(JFrame f, JDialog dd) {
		MigLayout migLayout = new MigLayout("", "[120,grow]30[120,grow][100,center][120,grow]", "[50,grow][50][40][40][40][40][40]20[40][50][50][50,grow][50]");
		f.getContentPane().setLayout(migLayout);
		
		JLabel naslov = new JLabel("Dobrodosli, ");
		naslov.setFont(new Font("Tahoma", Font.PLAIN, 18));
		naslov.setText("Dobrodosli, " + menadzer.getKorisnickoIme());
		getContentPane().add(naslov, "cell 0 0 4 1,alignx center");
		
		JLabel lblNewLabel = new JLabel("Pogledaj: ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblNewLabel, "cell 0 1 2 1,alignx center");
		
		JLabel lblInfo = new JLabel("Info:");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblInfo, "cell 3 1,alignx center");
		
		JButton btnTretmani = new JButton("Tretmani");
		btnTretmani.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(btnTretmani, "cell 0 2,grow");
		
		JButton btnMenadzeri = new JButton("Menadzeri");
		btnMenadzeri.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(btnMenadzeri, "cell 1 2,grow");
		

		
		JLabel lblNewLabel_1 = new JLabel(menadzer.getIme() + " "+ menadzer.getPrezime());
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblNewLabel_1, "cell 3 2,alignx center");
		
		JButton btnUslugeTretmana = new JButton("Usluge tretmana");
		btnUslugeTretmana.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(btnUslugeTretmana, "cell 0 3,grow");
		
		JButton btnKozmeticari = new JButton("Kozmeticari");
		btnKozmeticari.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(btnKozmeticari, "cell 1 3,grow");
		
		JLabel lblNewLabel_1_1 = new JLabel("Plata: " + String.valueOf(menadzer.getPlata()));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblNewLabel_1_1, "cell 3 3,alignx center");
		
		JButton btnTipoviTretmana = new JButton("Tipovi tretmana");
		btnTipoviTretmana.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(btnTipoviTretmana, "cell 0 4,grow");
		
		JButton btnRecepcioneri = new JButton("Recepcioneri");
		btnRecepcioneri.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(btnRecepcioneri, "cell 1 4,grow");
		
		JButton btnGodisnjiPrihod = new JButton("Godisnji prihod");
		btnGodisnjiPrihod.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(btnGodisnjiPrihod, "cell 0 5,grow");
		
		JButton btnKlijenti = new JButton("Klijenti");
		btnKlijenti.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(btnKlijenti, "cell 1 5,grow");
		
		JButton btnOdjava = new JButton("Odjava ");
		btnOdjava.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(btnOdjava, "cell 3 5,grow");
		
		JButton btnAngozovanjaKozmeticara = new JButton("<html>Angazovanja <br />kozmeticara</html>");
		btnAngozovanjaKozmeticara.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(btnAngozovanjaKozmeticara, "cell 0 6,grow");
		
		JButton btnStatusiTretmana = new JButton("<html>Statusi<br />tretmana</html>");
		btnStatusiTretmana.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(btnStatusiTretmana, "cell 1 6,grow");
		
		JLabel lblNewLabel_2 = new JLabel("Izvestaji:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblNewLabel_2, "cell 0 7 2 1,alignx center");
		
		JButton btnNewButton = new JButton("Postavi bonuse");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(btnNewButton, "cell 3 7,grow");
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PostaviBonuse postaviBonuse = new PostaviBonuse(f, kontroler.getKorisniciKontroler(),kontroler.getTretmanKontroler());
				postaviBonuse.setVisible(true);
				menadzerFrame.this.dispose();
				
			}
		});
		
		JLabel lblNewLabel_3 = new JLabel("<html>Pocetno vreme:<br />(YYYY-MM-DD)</html>");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblNewLabel_3, "cell 0 8,alignx center");
		
		pocetak = new JTextField();
		pocetak.setHorizontalAlignment(SwingConstants.CENTER);
		pocetak.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(pocetak, "cell 1 8,alignx center,growy");
		pocetak.setColumns(10);
		
		JButton btnNewButton_1_1_1_1 = new JButton();
		btnNewButton_1_1_1_1.setText("<html>Postavi kartice<br />lojalnosti</html>");
		btnNewButton_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		getContentPane().add(btnNewButton_1_1_1_1, "cell 3 8,grow");
		
		
				btnNewButton_1_1_1_1.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						KarticaLojalnosti krKarticaLojalnosti = new KarticaLojalnosti(f, kontroler.getKorisniciKontroler());
						krKarticaLojalnosti.setVisible(true);
						menadzerFrame.this.dispose();
						
					}
				});
		
		JLabel lblNewLabel_3_1 = new JLabel("<html>Krajnje vreme:<br />(YYYY-MM-DD)</html>");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblNewLabel_3_1, "cell 0 9,alignx center");
		
		kraj = new JTextField();
		kraj.setHorizontalAlignment(SwingConstants.CENTER);
		kraj.setFont(new Font("Tahoma", Font.PLAIN, 16));
		kraj.setColumns(10);
		getContentPane().add(kraj, "cell 1 9,alignx center,growy");
		
		JButton btnNewButton_1 = new JButton();
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_1.setText("<html>Kozmeticari i njihovi<br />Izvrseni tretmani</html>");
		getContentPane().add(btnNewButton_1, "cell 3 9,grow");
		
				btnNewButton_1.addActionListener(new ActionListener() {
		
					@Override
					public void actionPerformed(ActionEvent e) {
						boolean flag = validacija(pocetak, kraj);
						if(flag) {
							pDateTime = LocalDate.parse(pocetak.getText(), formatter);
							kDateTime = LocalDate.parse(kraj.getText(), formatter);
							table = new JTable(new IzvestajKozmeticaraPoTretmanimaModel(kontroler.getKorisniciKontroler().getKozmeticari(),pDateTime,kDateTime,kontroler.getTretmanKontroler().getTretmani()));
							table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
							table.getTableHeader().setReorderingAllowed(false);
							
							
							scrollPane.getViewport().removeAll();
							scrollPane.setViewportView(table);
							getContentPane().add(scrollPane, "cell 0 10 2 2,grow");
							revalidate();
						}
						
		
						
					}
				});
		
		JButton btnNewButton_1_1 = new JButton();
		btnNewButton_1_1.setText("<html>Stanja kozmetickih<br />tretmana</html>");
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		getContentPane().add(btnNewButton_1_1, "cell 3 10,grow");
		
		btnNewButton_1_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean flag = validacija(pocetak, kraj);
				if(flag) {
					pDateTime = LocalDate.parse(pocetak.getText(), formatter);
					kDateTime = LocalDate.parse(kraj.getText(), formatter);
					table = new JTable(new IzvestajTipovaModel(pDateTime,kDateTime,kontroler.getTretmanKontroler().getTretmani()));
					table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					table.getTableHeader().setReorderingAllowed(false);
					
					
					scrollPane.getViewport().removeAll();
					scrollPane.setViewportView(table);
					getContentPane().add(scrollPane, "cell 0 10 2 2,grow");
					revalidate();
				}
				
			}
		});
		

		
		JButton btnNewButton_1_1_1 = new JButton();
		btnNewButton_1_1_1.setText("<html>Info o uslugama<br />tretmana</html>");
		btnNewButton_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		getContentPane().add(btnNewButton_1_1_1, "cell 3 11,grow");
		
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean flag = validacija(pocetak, kraj);
				if(flag) {
					pDateTime = LocalDate.parse(pocetak.getText(), formatter);
					kDateTime = LocalDate.parse(kraj.getText(), formatter);
					table = new JTable(new IzvestajPrihodiUMesecDanaModel(kontroler.getUslugeKontroler().getUsluge(), pDateTime,kDateTime,kontroler.getTretmanKontroler().getTretmani()));
					table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					table.getTableHeader().setReorderingAllowed(false);
					
					
					scrollPane.getViewport().removeAll();
					scrollPane.setViewportView(table);
					getContentPane().add(scrollPane, "cell 0 10 2 2,grow");
					revalidate();
				}
				
			}
		});
		
		scrollPane = new JScrollPane(table);

		
		btnOdjava.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				menadzerFrame.this.dispose();
				dd.setVisible(true);
				
				
			}
		});
		
		btnKlijenti.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				KlijentTableFrame kt = new KlijentTableFrame(f, kontroler.getKorisniciKontroler()); 
				kt.setVisible(true);
				menadzerFrame.this.dispose();
				
			}
		});
		
		btnKozmeticari.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				KozmeticarTableFrame k = new KozmeticarTableFrame(f, kontroler.getKorisniciKontroler(), kontroler.getTipKontroler());
				k.setVisible(true);
				menadzerFrame.this.dispose();
			}
		});
		
		btnMenadzeri.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MenadzerTabelFrame men = new MenadzerTabelFrame(f, kontroler.getKorisniciKontroler());
				men.setVisible(true);
				menadzerFrame.this.dispose();
				
			}
		});
		
		btnRecepcioneri.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RecepcionerTableFrame men = new RecepcionerTableFrame(f, kontroler.getKorisniciKontroler());
				men.setVisible(true);
				menadzerFrame.this.dispose();
				
			}
		});
		
		btnTretmani.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TretmanTableFrame tableFrame = new TretmanTableFrame(f,kontroler.getTretmanKontroler());
				tableFrame.setVisible(true);
				menadzerFrame.this.dispose();
				
			}
		});
		
		btnUslugeTretmana.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UslugaTableFrame uslugaTableFrame = new UslugaTableFrame(f, kontroler.getUslugeKontroler(), kontroler.getTipKontroler());
				uslugaTableFrame.setVisible(true);
				menadzerFrame.this.dispose();
				
			}
		});
		
		btnTipoviTretmana.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TipTretmanaTableFrame tipTretmanaTableFrame = new TipTretmanaTableFrame(f, kontroler.getTipKontroler());
				tipTretmanaTableFrame.setVisible(true);
				menadzerFrame.this.dispose();
				
			}
		});
		
		btnGodisnjiPrihod.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GodisnjiPrihodChart gdChart = new GodisnjiPrihodChart(kontroler.getTretmanKontroler(),kontroler.getTipKontroler(),f);
				gdChart.setVisible(true);
				menadzerFrame.this.dispose();
			}
		});
		
		btnAngozovanjaKozmeticara.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AngazovanjeKozmeticaraChart angazovanjeKozmeticaraChart = new AngazovanjeKozmeticaraChart(kontroler.getKorisniciKontroler(),kontroler.getTretmanKontroler(),f);
				angazovanjeKozmeticaraChart.setVisible(true);
				menadzerFrame.this.dispose();
				
			}
		});
		
		btnStatusiTretmana.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PrikazStatusaTretmanaChart prikazStatusaTretmanaChart = new PrikazStatusaTretmanaChart(kontroler.getTretmanKontroler(),f);
				prikazStatusaTretmanaChart.setVisible(true);
				menadzerFrame.this.dispose();
				
			}
		});
		
		
	}
	
	private boolean validacija(JTextField p, JTextField k) {
		if(p.getText().compareTo("")==0) {
			JOptionPane.showMessageDialog(null, "Niste uneli pocetno vreme.");
			return false;
		}
		else if(k.getText().compareTo("")==0) {
			JOptionPane.showMessageDialog(null, "Niste uneli krajnje vreme.");
			return false;
		}
		Pattern paterrn = Pattern.compile("\\d{4}-(0[1-9]|1[0-2])-([0-2][1-9]|3[0-1])");
		
		if(paterrn.matcher(p.getText()).matches()==false) {
			JOptionPane.showMessageDialog(null, "Format za pocetno vreme mora biti YYYY-MM-DD");
			return false;
		}
		Pattern paterrn1 = Pattern.compile("\\d{4}-(0[1-9]|1[0-2])-([0-2][1-9]|3[0-1])");
		
		if(paterrn1.matcher(k.getText()).matches()==false) {
			JOptionPane.showMessageDialog(null, "Format za krajnje vreme mora biti YYYY-MM-DD");
			return false;
		}
		return true;
	}

}
