/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlotsBuilding;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
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
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.VerticalAlignment;


public  class PlotPanel extends JPanel implements Cloneable {
    
    ChartPanel chartPanel;
    public PlotPanel (ArrayList<PlotsData> plotscollection, Plotcr frame)
    {
       
        this.chartPanel = new ChartPanel(fillCollection(plotscollection));
        this.chartPanel.setPreferredSize(new Dimension(800, 450));
        this.chartPanel.setBackground(Color.WHITE);    
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(this.chartPanel);
    }
    
    private JFreeChart fillCollection ( ArrayList<PlotsData> plotscollection)
            {
                XYSeriesCollection col = new XYSeriesCollection();
                ArrayList<Integer> seriesCount = new ArrayList<>();
                int SeriesCount = 0;
                PlotsData plotdata = new PlotsData();
                if (plotscollection.isEmpty())
                {
                    XYSeries series = new XYSeries("1. ");
                    series.add(0, 0);
                    plotdata.y1=-10;
                    plotdata.y2=10;
                    col.addSeries(series);
                }
                for (int i = 0; i < plotscollection.size(); i++) 
                {
                    plotdata = (PlotsData) plotscollection.get(i);
                    col=plotdata.createPlotdataset(i, col);
                    seriesCount.add(col.getSeriesCount() - SeriesCount);
                    SeriesCount = col.getSeriesCount();
                }
                double y1 = plotdata.y1;
                double y2 = plotdata.y2;
                JFreeChart chart = createChart(col, seriesCount, y1,  y2);
                return chart;
            }
    private JFreeChart createChart(XYDataset xyDataset, ArrayList<Integer> seriesCount,  double y1, double y2) {

        NumberAxis domainAxis = new NumberAxis("x");
        domainAxis.setAutoRangeIncludesZero(false);
        domainAxis.setPositiveArrowVisible(true);
        NumberAxis rangeAxis = new NumberAxis("y");
        rangeAxis.setRange(y1, y2);
        rangeAxis.setPositiveArrowVisible(true);
        XYItemRenderer renderer = new XYLineAndShapeRenderer(true, false);
        int start = 0;
        int color = 50;
        for (Object series : seriesCount) {
            for (int i = start; i < start + (int) series; i++) {
                Color s = new Color(color);
                renderer.setSeriesPaint(i, s);
                renderer.setSeriesVisibleInLegend(i, false);
                renderer.setSeriesVisibleInLegend(start, true);
            }
            start = start + (int) series;
            color *=100;
        }
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
    
     public void updateChart ( ArrayList<PlotsData> plotscollection)
     {
        ArrayList<PlotsData> plotscollection1 = plotscollection;
        remove(this.chartPanel);
        this.chartPanel = new ChartPanel(fillCollection(plotscollection1));
        this.chartPanel.setPreferredSize(new Dimension(800, 450));
        this.chartPanel.setBackground(Color.WHITE);
        add(this.chartPanel);
        repaint();
        validate();
     }
    
}
