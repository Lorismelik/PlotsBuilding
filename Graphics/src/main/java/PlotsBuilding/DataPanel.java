/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlotsBuilding;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class DataPanel  extends JPanel {
    private static final Font font = new Font("Calibri", Font.BOLD + Font.ITALIC, 16);
    JTextField functionText;
    
 
    public  DataPanel(Vector plotscollection, int Accept, PlotPanel plotPanel) {
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
                for (int i = 0; i < Accept; i++) {
                    plotdata = (PlotsData) plotscollection.get(i);
                    functionList.addItem(plotdata.function);
                }
                add(functionList);
		
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
                              String functionNotation = functionText.getText().trim();
                              for (int i =0; i <Accept; i++)
                                    {
                                        PlotsData plotsData = new PlotsData(-10,10,-10,10,functionNotation);
                                        plotscollection.add(i, plotsData);    
                                    }
                              plotPanel.updateChart(plotscollection, Accept);
                              functionList.removeAllItems();
                              PlotsData plotdata = new PlotsData();
                              for (int i = 0; i < Accept; i++) {
                              plotdata = (PlotsData) plotscollection.get(i);
                              functionList.addItem(plotdata.function);
                              }
                              
                              }
                         }
                         
			
               });


		
    }
    
}
