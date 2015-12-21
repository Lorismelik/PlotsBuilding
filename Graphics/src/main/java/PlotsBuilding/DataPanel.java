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
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class DataPanel  extends JPanel {
    private static final Font font = new Font("Calibri", Font.BOLD + Font.ITALIC, 16);
    JPanel topPanel;
    JPanel botPanel;
    public  DataPanel(ArrayList<PlotsData> plotscollection, PlotPanel plotPanel) {
                PlotPanel plotPanel1 = plotPanel;
                ArrayList<PlotsData> plotscollection1 = plotscollection;
                topPanel = new JPanel();
                topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
                topPanel.setPreferredSize(new Dimension(800, 40));
                botPanel = new JPanel();
                botPanel.setLayout(new BoxLayout(botPanel, BoxLayout.X_AXIS));
                setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(800, 70));
		setBorder(new EmptyBorder(10, 10, 10, 10));
		
		JLabel flabel = new JLabel("f(x) = ");
		flabel.setFont(font);          
		topPanel.add(flabel);
                
                JComboBox functionList = new JComboBox();
                functionList.setEditable(false);
                PlotsData plotdata = new PlotsData();
                for (int i = 0; i < plotscollection1.size(); i++) {
                    plotdata =  plotscollection1.get(i);
                    functionList.addItem(plotdata.function);
                }
                functionList.setPreferredSize(new Dimension(300, 40));
                topPanel.add(functionList);
                
                JButton button2 = new JButton();
                button2.setFont(font);
                button2.setText("Удалить");
                topPanel.add(button2);
		setMaximumSize(new Dimension(Integer.MAX_VALUE, button2.getWidth()));  
		
		JTextField functionText = new JTextField("",15);
		functionText.setFont(font);
		functionText.setBackground(Color.WHITE);
		topPanel.add(functionText);
                                           
                JButton button1 = new JButton();
                button1.setFont(font);
                button1.setText("Добавить");
                topPanel.add(button1);

                
                JLabel x1label = new JLabel("   x1 : ");
		x1label.setFont(font);          
		botPanel.add(x1label);
                
                
                JTextField x1Text = new JTextField(Double.toString(plotdata.x1),15);
		x1Text.setFont(font);
		x1Text.setBackground(Color.WHITE);
		botPanel.add(x1Text);
                
                JLabel x2label = new JLabel(" x2 : ");
		x2label.setFont(font);          
		botPanel.add(x2label);
                
                JTextField x2Text = new JTextField(Double.toString(plotdata.x2),15);
		x2Text.setFont(font);
		x2Text.setBackground(Color.WHITE);
		botPanel.add(x2Text);
                
                JLabel y1label = new JLabel(" y1 : ");
		y1label.setFont(font);          
		botPanel.add(y1label);
                
                JTextField y1Text = new JTextField(Double.toString(plotdata.y1),15);
		y1Text.setFont(font);
		y1Text.setBackground(Color.WHITE);
		botPanel.add(y1Text);
                
                JLabel y2label = new JLabel(" y2 : ");
		y2label.setFont(font);          
		botPanel.add(y2label);
                
                JTextField y2Text = new JTextField(Double.toString(plotdata.y2),15);
		y2Text.setFont(font);
		y2Text.setBackground(Color.WHITE);
		botPanel.add(y2Text);
                
                JButton button3 = new JButton();
                button3.setFont(font);
                button3.setText("Изменить");
                botPanel.add(button3);
                
                
                add(topPanel);
                add(botPanel);
                 
             
                button1.addActionListener(new ActionListener()
                {
			 public void actionPerformed(ActionEvent e)
                         {
                                if (e.getSource().equals(button1))
                                {
                                   PlotsData plotsData = new PlotsData();
                                   plotsData =  (PlotsData)plotscollection1.get(0);
                                   PlotsData plotsData1 = new PlotsData();
                                   plotsData1 = plotsData.clone();
                                   plotsData1.function = functionText.getText().trim();
                                   if (!Checkfunc(plotsData1.function)) return;
                                   functionList.addItem(plotsData1.function);
                                   plotscollection1.add(plotsData1);
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
                 
                  button3.addActionListener(new ActionListener()
                {
			 public void actionPerformed(ActionEvent e)
                         {
                                if (e.getSource().equals(button3))
                                {
                                    double x1, x2, y1, y2;
                                    try
                                    {
                                        x1 = Double.parseDouble(x1Text.getText(). trim());
                                        x2 = Double.parseDouble(x2Text.getText(). trim());
                                        y1 = Double.parseDouble(y1Text.getText(). trim());
                                        y2 = Double.parseDouble(y2Text.getText(). trim());
                                    } catch (NumberFormatException w2) {JOptionPane.showMessageDialog(null, " Введите числа для построения графика в нужном интервале");  return;}
                                     if (x1>=x2||y1>y2)
                                     {
                                        JOptionPane.showMessageDialog(null, " Неверно заполнены условия для построения графика");
                                        return;
                                     }
                                    int size = plotscollection1.size();
                                    plotscollection1.clear();
                                    for (int i = 0; i<size; i++)
                                    {
                                        PlotsData plotsData = new PlotsData ();
                                        plotsData.x1 = x1;
                                        plotsData.x2 = x2;
                                        plotsData.y1 = y1;
                                        plotsData.y2 = y2;
                                        plotsData.function = (String)functionList.getItemAt(i);
                                        plotscollection1.add(plotsData);
                                    }
                                    plotPanel1.updateChart(plotscollection1);
                                }   
                         }
                         
			
               });
    }
    private boolean Checkfunc (String s)
    {
        try
        {
            Expression parser = new ExpressionBuilder(s)
            .variables("x")
            .build();
            parser.setVariable("x", 1);
            double result = parser.evaluate();
        }  catch (IllegalArgumentException w4) { JOptionPane.showMessageDialog(null, " При вводе функции допущена ошибка");  return false;}
         catch (ArithmeticException w5) { JOptionPane.showMessageDialog(null, " Невозможно построить график при прямом делении на 0");  return false;}
        return true;
    }
    
}