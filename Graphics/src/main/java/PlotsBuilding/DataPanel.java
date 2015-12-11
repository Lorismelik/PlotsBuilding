/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlotsBuilding;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class DataPanel  extends JPanel {
    private static final Font font = new Font("Calibri", Font.BOLD + Font.ITALIC, 16);
    JTextField functionText;
    int funcCount = 1;
    
 
    public  DataPanel(ArrayList<PlotsData> plotscollection, int Accept, PlotPanel plotPanel, ArrayList <Double> points) {
                setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setPreferredSize(new Dimension(800, 50));
		setBorder(new EmptyBorder(10, 10, 10, 10));
		
		JLabel fNlabel = new JLabel("Изменить функцию: ");
		fNlabel.setFont(font);          
		add(fNlabel);
                
                PlotsData plotsData1 = new PlotsData();
                plotsData1 = (PlotsData) plotscollection.get(0);
                JLabel func = new JLabel(Integer.toString(funcCount)+") f(x)="+plotsData1.function);
                func.setPreferredSize(new Dimension(100, 50));
		func.setFont(font);          
		add(func);		
		functionText = new JTextField("",15);
		functionText.setFont(font);
		functionText.setBackground(Color.WHITE);
		add(functionText);
                
                JButton button = new JButton();
                button.setFont(font);
                button.setText("Перестроить");
                add(button);
		setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getWidth()));
                
                button.addActionListener(new ActionListener()
                {
			 public void actionPerformed(ActionEvent e)
                         {
                              if (e.getActionCommand().equals("Перестроить"))
                              {
                      
                              PlotsData plotsData = new PlotsData();
                              plotsData.x1 = points.get(0);
                              plotsData.x2 = points.get(1);
                              plotsData.y1 = points.get(2);
                              plotsData.y2 = points.get(3);
                              plotsData.function = functionText.getText().trim();
                              plotscollection.set(funcCount-1, plotsData);
                              if (funcCount<plotscollection.size())
                              {
                              plotsData = (PlotsData) plotscollection.get(funcCount);
                              funcCount++;
                              func.setText(Integer.toString(funcCount)+") f(x)="+plotsData.function+"   ");
                              }
                              else
                              {
                                     plotPanel.updateChart(plotscollection, Accept);
                              }
                              }
                         }
                         
			
               });


		
    }
    
}
