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
    
    final PlotPanel chartPanel;
   
    public Plotcr(String title, Vector plotscollection, int Accept) {
        super(title);
        chartPanel = new PlotPanel (plotscollection, Accept);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        DataPanel dataPanel = new DataPanel(plotscollection, Accept, chartPanel);
        getContentPane().add(dataPanel);
        getContentPane().add(chartPanel);
    }
}
