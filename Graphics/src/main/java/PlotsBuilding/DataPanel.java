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
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class DataPanel  extends JPanel {
    private static final Font font = new Font("Calibri", Font.BOLD + Font.ITALIC, 16);
    JTextField functionText;
    
 
    public  DataPanel(ArrayList<PlotsData> plotscollection, PlotPanel plotPanel, ArrayList <Double> points) {
                PlotPanel plotPanel1 = plotPanel;
                ArrayList<PlotsData> plotscollection1 = plotscollection;
                setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setPreferredSize(new Dimension(800, 50));
		setBorder(new EmptyBorder(10, 10, 10, 10));
		
		JLabel fNlabel = new JLabel("Изменить функцию: ");
		fNlabel.setFont(font);          
		add(fNlabel);
                
                JComboBox functionList = new JComboBox();
                functionList.setEditable(false);
                functionList.setPreferredSize(new Dimension(400, 130));
                PlotsData plotdata = new PlotsData();
                for (int i = 0; i < plotscollection1.size(); i++) {
                    plotdata =  plotscollection1.get(i);
                    functionList.addItem(plotdata.function);
                }
                add(functionList);
		
		functionText = new JTextField("",15);
		functionText.setFont(font);
		functionText.setBackground(Color.WHITE);
		add(functionText);
                
                JButton button = new JButton();
                button.setFont(font);
                button.setText("Редактировать");
                add(button);
		setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getWidth()));
                
                 
                JButton button1 = new JButton();
                button1.setFont(font);
                button1.setText("Добавить");
                add(button1);
		setMaximumSize(new Dimension(Integer.MAX_VALUE, button1.getWidth()));
                 
                JButton button2 = new JButton();
                button2.setFont(font);
                button2.setText("Удалить");
                add(button2);
		setMaximumSize(new Dimension(Integer.MAX_VALUE, button2.getWidth()));
                
                
                
                
                button.addActionListener(new ActionListener()
                {
			 public void actionPerformed(ActionEvent e)
                         {
                              if (e.getSource().equals(button))
                              {
                              int changedItem =functionList.getSelectedIndex(); 
                              PlotsData plotsData = new PlotsData();
                              plotsData.x1 = points.get(0);
                              plotsData.x2 = points.get(1);
                              plotsData.y1 = points.get(2);
                              plotsData.y2 = points.get(3);
                              plotsData.function = functionText.getText().trim();
                              functionList.removeItemAt(changedItem);
                              functionList.addItem(plotsData.function);
                              plotscollection1.set(changedItem,plotsData);
                              functionList.removeAllItems();
                                for (int i = 0; i< plotscollection1.size(); i++)
                                {
                                    plotsData = plotscollection1.get(i);
                                    functionList.addItem(plotsData.function);
                                }
                              plotPanel1.updateChart(plotscollection1);
                              }
                           
                         }
                         
			
               });
                
                 
                button1.addActionListener(new ActionListener()
                {
			 public void actionPerformed(ActionEvent e)
                         {
                                if (e.getSource().equals(button1))
                                {
                                   PlotsData plotsData = new PlotsData();
                                   plotsData.x1 = points.get(0);
                                   plotsData.x2 = points.get(1);
                                   plotsData.y1 = points.get(2);
                                   plotsData.y2 = points.get(3);
                                   plotsData.function = functionText.getText().trim();
                                   functionList.addItem(plotsData.function);
                                   plotscollection1.add(plotsData);
                                   plotPanel1.updateChart(plotscollection1);
                                }   
                         }
                         
			
               });
                
                 button2.addActionListener(new ActionListener()
                {
			 public void actionPerformed(ActionEvent e)
                         {
                                if (e.getSource().equals(button2))
                                {
                                    int changedItem =functionList.getSelectedIndex();
                                    functionList.removeItemAt(changedItem);
                                    plotscollection1.remove(changedItem);
                                    plotPanel1.updateChart(plotscollection1);
                                }   
                         }
                         
			
               });


		
    }
    
}