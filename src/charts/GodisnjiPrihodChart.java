package charts;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import entiteti.TipTretmana;
import entiteti.Tretman;
import kontroleri.TipTretmanaKontroler;
import kontroleri.TretmanKontroler;

public class GodisnjiPrihodChart extends JFrame{
	
	private static final long serialVersionUID = -3101587007060109455L;
	

	public GodisnjiPrihodChart(TretmanKontroler tretmanKontroler, TipTretmanaKontroler tipTretmanaKontroler, JFrame p){
		
		this.setTitle("Prihod u poslednjih 12 meseci");
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				p.setVisible(true);
			}
		});
 
		
	    XYChart chart =
	            new XYChartBuilder()
                .width(1000)
                .height(400)
                .title("Prihod u poslednjih 12 meseci")
                .xAxisTitle("Mesec")
                .yAxisTitle("Prihod")
                .build();
		

        for(TipTretmana tipTretmana : tipTretmanaKontroler.getTipovi()) {
    		List<Double> prihodi = new ArrayList<Double>();
            List<java.util.Date> meseci = new ArrayList<java.util.Date>();
            LocalDate danas = LocalDate.now();
            double prihod = 0.0;
            for(int i =0;i < 12;i++) {
            	for(Tretman t:tretmanKontroler.getTretmani()) {
            		if(t.getVreme().getMonth().equals(danas.getMonth()) && t.getTipTretmana()==tipTretmana) {
            			prihod+=t.getCena();
            		}
            	}
            	prihodi.add(prihod);
            	ZoneId defaultZoneId = ZoneId.systemDefault();
            	java.util.Date date = (java.util.Date) Date.from(danas.atStartOfDay(defaultZoneId).toInstant());
            	meseci.add(date);
            	prihod = 0;
            	danas = danas.minusMonths(1);
            }
            
            chart.addSeries(tipTretmana.getImeTip(), meseci, prihodi);
        }

        XChartPanel<XYChart> chartPanel = new XChartPanel<>(chart);
		
		
		this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(chartPanel, BorderLayout.CENTER);
		
		this.pack();
		this.setLocationRelativeTo(null);

	}

}
