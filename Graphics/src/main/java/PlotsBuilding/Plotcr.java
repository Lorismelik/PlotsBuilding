package PlotsBuilding;

import java.util.ArrayList;
import javax.swing.*;
import org.jfree.ui.ApplicationFrame;

public class Plotcr extends ApplicationFrame {
   PlotPanel chartPanel;
    public Plotcr(String title, ArrayList<PlotsData> plotscollection, ArrayList <Double> points) {
        super(title);
        ArrayList<PlotsData> plotscollection1 =  plotscollection;
        chartPanel = new PlotPanel (plotscollection1, this);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        DataPanel dataPanel = new DataPanel(plotscollection, chartPanel, points);
        getContentPane().add(dataPanel);
        getContentPane().add(chartPanel);
    }
}