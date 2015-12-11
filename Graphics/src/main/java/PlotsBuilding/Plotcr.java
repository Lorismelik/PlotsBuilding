package PlotsBuilding;

import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import org.jfree.ui.ApplicationFrame;

public class Plotcr extends ApplicationFrame {
   PlotPanel chartPanel;
    public Plotcr(String title, ArrayList<PlotsData> plotscollection, int Accept, ArrayList <Double> points) {
        super(title);
        chartPanel = new PlotPanel (plotscollection, Accept);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        DataPanel dataPanel = new DataPanel(plotscollection, Accept, chartPanel, points);
        getContentPane().add(dataPanel);
        getContentPane().add(chartPanel);
    }
}
