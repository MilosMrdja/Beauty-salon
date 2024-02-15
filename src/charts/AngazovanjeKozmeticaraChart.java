package charts;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;

import javax.swing.JFrame;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XChartPanel;

import entiteti.Kozmeticar;
import entiteti.STANJE_TERETMANA;
import entiteti.Tretman;
import kontroleri.KorisniciKontroler;
import kontroleri.TretmanKontroler;

public class AngazovanjeKozmeticaraChart extends JFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = 587440087136118516L;

	public AngazovanjeKozmeticaraChart(KorisniciKontroler korisniciKontroler, TretmanKontroler tretmanKontroler,JFrame p) {
		this.setTitle("Angazovanje kozmeticara po odradjenim tretmanima u 30 dana");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				p.setVisible(true);
			}
		});
 
		
	    PieChart chart = new PieChartBuilder().width(800).height(600).title("Angazovanje kozmeticara za odradjene tretmane u 30 dana").build();
	    
	    LocalDate danas = LocalDate.now();
	    LocalDate trideset_dana = danas.minusDays(30);
	    for(Kozmeticar k:korisniciKontroler.getKozmeticari()) {
	    	int brojac = 0;
	    	for(Tretman t:tretmanKontroler.getTretmani()) {
	    		if(t.getKozmeticar() == k && (t.getStanje().equals(STANJE_TERETMANA.IZVRSEN) || t.getStanje().equals(STANJE_TERETMANA.OTKAZAO_KLIJENT))) {
	    			if(t.getVreme().toLocalDate().isAfter(trideset_dana)) {
	    				brojac += 1;
	    			}
	    		}
	    	}
	    	chart.addSeries(k.getKorisnickoIme(), brojac);
	    	brojac = 0;
	    }
	    
	    XChartPanel<PieChart> chartPanel = new XChartPanel<>(chart);
		
		this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(chartPanel, BorderLayout.CENTER);
		
		this.pack();
		this.setLocationRelativeTo(null);
	}

}
