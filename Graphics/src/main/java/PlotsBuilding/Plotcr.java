
package PlotsBuilding;

import java.awt.*;
import java.util.Vector;
import javax.swing.*;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.VerticalAlignment;

public class Plotcr extends ApplicationFrame {
   static XYSeriesCollection col = new XYSeriesCollection ();
    
    public Plotcr (String title, Vector plotscollection, int Accept ) {
        super(title);
        Vector PlotSeries = new Vector ();
        for (int i=0;i<Accept;i++)
        {
         int SeriesCount=0;
         boolean end = false;
        PlotsData plotdata=(PlotsData)plotscollection.get(i);
        
        while (end == false)
        {
            end = createDataset(plotdata, SeriesCount+i, end);
            SeriesCount++;
        }
        PlotSeries.addElement(SeriesCount);

        }
       
        XYDataset xydataset = col;
        JFreeChart chart = createChart(xydataset, PlotSeries);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(450, 450));
        JScrollPane sp = new JScrollPane(chartPanel);
        sp.setPreferredSize(new Dimension(500, 500));
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setContentPane(sp);
        
    }
    
    private static boolean createDataset(PlotsData plotdata, int Accept, boolean end) {
     XYSeries series;
     
      series = new XYSeries(Integer.toString(Accept+1)+" "+plotdata.function);
     
   MatchParser p = new MatchParser();
   double j = Math.abs(plotdata.x1);
   double l = Math.abs(plotdata.x2);
   if (j<1) {j=Math.pow(j, l);}
   if (l<1) {l=Math.pow(l, l);}
   double step = (Math.abs(plotdata.x1)+Math.abs(plotdata.x2))/((j+l)*200);
    for(double i = plotdata.x1; i <= plotdata.x2; i+=step){
        
         p.setVariable("x", i );
   double c = 0;
   double lastc = 0;
    try
            {
                c = p.Parse(plotdata.function);
                }catch(Exception e) {}  
              if (Math.abs(c)<Math.abs(8*100000)&Math.abs(Math.abs(c)-Math.abs(lastc))<Math.abs(Math.abs(plotdata.y1)+Math.abs(plotdata.y2)))
              {
                series.add(i, c);
                lastc=c;
              }
              else
              {
                  plotdata.x1 = i+step;
                  col.addSeries(series);
                  end = false;
                  return end;
              }
          
                               
            }
    col.addSeries(series);
    end = true;
    return end;
    }
    
    private static JFreeChart createChart(XYDataset xyDataset, Vector PlotSeries) {
        
        NumberAxis domainAxis = new NumberAxis ("x");
        domainAxis.setAutoRangeIncludesZero(false);
        domainAxis.setPositiveArrowVisible(true);
        NumberAxis rangeAxis = new NumberAxis("y");
        rangeAxis.setRange(-10, 10);
        rangeAxis.setPositiveArrowVisible(true);
        XYItemRenderer renderer = new XYLineAndShapeRenderer(true, false);
        for (int j=1; j<xyDataset.getSeriesCount();j++)
        {
            
                renderer.setSeriesPaint(j, Color.RED);
                renderer.setSeriesVisibleInLegend(j,false);
        }
        
        
        
      /*
      *  for (int j=0; j<PlotSeries.size();j++)
      * {
      *      for (int i=0; i<(int)PlotSeries.get(j) ;i++) 
      *      {
      *          Color s = new Color(j);
      *          renderer.setSeriesPaint(i, s);
      *          renderer.setSeriesVisibleInLegend(i,false);
      *          renderer.setSeriesVisibleInLegend(0,true);
      *      }
      *  }
      */
        renderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator());
        XYPlot plot = new XYPlot(xyDataset, domainAxis, rangeAxis, renderer);
         PlotOrientation orientation = PlotOrientation.VERTICAL;
        plot.setOrientation(orientation);
        plot.setBackgroundPaint(Color.white);
        JFreeChart chart = new JFreeChart(plot);
        chart.setBackgroundPaint(Color.white);
        chart.getLegend().setPosition(RectangleEdge.RIGHT);
        chart.getLegend().setVerticalAlignment(VerticalAlignment.TOP);
        return chart;
    }
    
}
