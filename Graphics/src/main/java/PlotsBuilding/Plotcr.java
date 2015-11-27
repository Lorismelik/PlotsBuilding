package PlotsBuilding;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import net.objecthunter.exp4j.*;
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

    static XYSeriesCollection col = new XYSeriesCollection();

    private static JFreeChart createChart(XYDataset xyDataset, ArrayList PlotSeries) {

        NumberAxis domainAxis = new NumberAxis("x");
        domainAxis.setAutoRangeIncludesZero(false);
        domainAxis.setPositiveArrowVisible(true);
        NumberAxis rangeAxis = new NumberAxis("y");
        rangeAxis.setRange(-10, 10);
        rangeAxis.setPositiveArrowVisible(true);
        XYItemRenderer renderer = new XYLineAndShapeRenderer(true, false);
        int start = 0;
        for (int j = 0; j < PlotSeries.size(); j++) {
            for (int i = start; i < (int) PlotSeries.get(j) - 1; i++) {
                Color s = new Color(j + 2000);
                renderer.setSeriesPaint(i, s);

                renderer.setSeriesVisibleInLegend(start, true);

            }
            start = (int) PlotSeries.get(j);
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

    public Plotcr(String title, Vector plotscollection, int Accept, double y1, double y2) {
        super(title);
        ArrayList<Integer> PlotSeries = new ArrayList<>();
        for (int i = 0; i < Accept; i++) {
            int SeriesCount = 0;
            boolean end = false;
            PlotsData plotdata = (PlotsData) plotscollection.get(i);

            while (end == false) {
                end = plotdata.createDataset(SeriesCount + i, end);
                SeriesCount++;
            }
            PlotSeries.add(SeriesCount);

        }

        XYDataset xydataset = col;
        JFreeChart chart = createChart(xydataset, PlotSeries);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(450, 450));
        JScrollPane sp = new JScrollPane(chartPanel);
        sp.setPreferredSize(new Dimension(500, 500));
        setContentPane(sp);
    }

}