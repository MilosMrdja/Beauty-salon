package charts;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XChartPanel;

import entiteti.STANJE_TERETMANA;
import entiteti.Tretman;
import kontroleri.TretmanKontroler;

public class PrikazStatusaTretmanaChart extends JFrame {


	private static final long serialVersionUID = -4289997176683463677L;

	public PrikazStatusaTretmanaChart(TretmanKontroler tretmanKontroler, JFrame p) {
		this.setTitle("Prikaz statusa tretmana");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				p.setVisible(true);
			}
		});
 
		
	    PieChart chart = new PieChartBuilder().width(800).height(600).title("Statusi tretmana").build();
	    
	    int zakazan = 0;
	    int nije_se_pojavio = 0;
	    int otkazao_salon = 0;
	    int otkazao_klijent = 0;
	    int izvrsen = 0;
	    for(Tretman t:tretmanKontroler.getTretmani()) {
	    	if(t.getStanje().equals(STANJE_TERETMANA.ZAKAZAN)) {
	    		zakazan += 1;
	    	}
	    	else if(t.getStanje().equals(STANJE_TERETMANA.NIJE_SE_POJAVIO)) {
	    		nije_se_pojavio += 1;
	    	}
	    	else if(t.getStanje().equals(STANJE_TERETMANA.OTKAZAO_KLIJENT)) {
	    		otkazao_klijent += 1;
	    	}
	    	else if(t.getStanje().equals(STANJE_TERETMANA.OTKAZAO_SALON)) {
	    		otkazao_salon += 1;
	    	}
	    	else if(t.getStanje().equals(STANJE_TERETMANA.IZVRSEN)) {
	    		izvrsen += 1;
	    	}
	    }
	    
	    chart.addSeries("ZAKAZAN", zakazan);
	    chart.addSeries("KLIJENT_SE_NIJE_POJAVIO", nije_se_pojavio);
	    chart.addSeries("OTKAZAO_SALON", otkazao_salon);
	    chart.addSeries("OTKAZAO_KLIJENT", otkazao_klijent);
	    chart.addSeries("IZVRSEN", izvrsen);
	    
	    XChartPanel<PieChart> chartPanel = new XChartPanel<>(chart);
		
		this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(chartPanel, BorderLayout.CENTER);
		
		this.pack();

		this.setLocationRelativeTo(null);
	}

}
