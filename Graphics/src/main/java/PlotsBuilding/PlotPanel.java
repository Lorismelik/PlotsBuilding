/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlotsBuilding;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.awt.geom.Rectangle2D;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
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
    
    private ChartPanel chartPanel;
    private JPanel pointsPanel;
    private JTextArea cursorInfo;
    JFreeChart chart;
    
    public PlotPanel (ArrayList<PlotsData> plotscollection, Plotcr frame)
    {
        pointsPanel = new JPanel();
        pointsPanel.setLayout(new BoxLayout(pointsPanel, BoxLayout.X_AXIS));
        cursorInfo = new JTextArea(2, 10);
        cursorInfo.setVisible(true);
        chartPanel = new ChartPanel(fillCollection(plotscollection));
        chartPanel.addChartMouseListener(new MyChartMouseListener());
        chartPanel.setPreferredSize(new Dimension(800, 450));
        pointsPanel.setMaximumSize(new Dimension(chartPanel.getWidth(), 70));
        pointsPanel.add(cursorInfo);
        chartPanel.setBackground(Color.WHITE);    
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(chartPanel);
        add(pointsPanel);
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
        chart = new JFreeChart(plot);
        chart.setBackgroundPaint(Color.white);
        chart.getLegend().setPosition(RectangleEdge.RIGHT);
        chart.getLegend().setVerticalAlignment(VerticalAlignment.TOP);
        return chart;
    }
    
     public void updateChart ( ArrayList<PlotsData> plotscollection)
     {
        ArrayList<PlotsData> plotscollection1 = plotscollection;
        remove(chartPanel);
        remove(pointsPanel);
        chartPanel = new ChartPanel(fillCollection(plotscollection1));
        chartPanel.addChartMouseListener(new MyChartMouseListener());
        chartPanel.setPreferredSize(new Dimension(800, 450));
        chartPanel.setBackground(Color.WHITE);
        pointsPanel = new JPanel();
        pointsPanel.setLayout(new BoxLayout(pointsPanel, BoxLayout.X_AXIS));
        cursorInfo = new JTextArea(2, 10);
        cursorInfo.setVisible(true);
        pointsPanel.add(cursorInfo);
        add(chartPanel);
        add(pointsPanel);
        repaint();
        revalidate();
     }
     
      protected class MyChartMouseListener implements ChartMouseListener {
        int j = 1;
        public void chartMouseMoved (ChartMouseEvent e) {
            cursorInfo.setText("");
            Point2D p = chartPanel.translateScreenToJava2D(e.getTrigger().getPoint());
            Rectangle2D plotArea = chartPanel.getScreenDataArea();
            XYPlot plot = (XYPlot) chart.getPlot();
            double chartY = plot.getRangeAxis().java2DToValue(p.getY(), plotArea, plot.getRangeAxisEdge());
            double chartX = plot.getDomainAxis().java2DToValue(p.getX(), plotArea, plot.getDomainAxisEdge());
            double newChartY = new BigDecimal(chartY).setScale(4, RoundingMode.UP).doubleValue();
            double newChartX = new BigDecimal(chartX).setScale(4, RoundingMode.UP).doubleValue();
            cursorInfo.append("Расположение курсора:");
            cursorInfo.append("\n");
            cursorInfo.append("x: "+String.valueOf(newChartX)+" ");
            cursorInfo.append("y: "+String.valueOf(newChartY));
        }
        public void chartMouseClicked (ChartMouseEvent e) {
        }
      }
    
    
}
