package PlotsBuilding;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.jfree.ui.RefineryUtilities;


public class App extends JFrame {

    public static void main(String args[]){
        SwingUtilities.invokeLater(new Runnable () {
            @Override
            public void run() {
                new App();
            }
        });
  }           
    TextField Tf;
    TextField Tx1;
    TextField Tx2;
    TextField Ty1;
    TextField Ty2;
    JFrame Window;
    JPanel MainDialog;
    JButton Buildbutton;
    JLabel Lfx;
    final Font font2 = new Font("",Font.PLAIN,18);
    public App() {
        Window = new JFrame();
        Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RefineryUtilities.centerFrameOnScreen(Window);
        Window.setTitle("");
        Window.setSize(323, 200);
        Window.pack();
        Window.setVisible(true);
        dialogwindow();
    }

    public void dialogwindow() {
       MainDialog = new JPanel();
        Window.add(MainDialog);
        MainDialog.setBackground(Color.white);
        MainDialog.setLayout(new GridBagLayout());
        Window.setTitle("Ввод функций");

        Tf = new TextField(10);
        Tx1 = new TextField("", 20);
        Tx2 = new TextField("", 20);
        Ty1 = new TextField("", 20);
        Ty2 = new TextField("", 20);
        Buildbutton = new JButton("Построить");
        JLabel Lf = new JLabel("Введите функцию для построения графика:");
        Lf.setFont(font2);

        MainDialog.add(Lf ,  new GridBagConstraints( 0,0,4,1,1,1, GridBagConstraints.NORTH, GridBagConstraints.CENTER,new Insets(10,1,1,1),0,0));

        MainDialog.add(new JLabel("F(x)=") , new GridBagConstraints( 0,1,1,1,1,1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,new Insets(5,10,1,1),0,0));
        MainDialog.add(Tf ,  new GridBagConstraints( 1,1,3,1,1,1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,new Insets(5,1,1,1),0,0));

        MainDialog.add(Tx1 , new GridBagConstraints( 1,2,1,1,1,1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,new Insets(5,0,1,1),0,0));
        MainDialog.add(Tx2 , new GridBagConstraints( 1,3,1,1,1,1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,new Insets(5,0,1,1),0,0));
        MainDialog.add(Ty1 , new GridBagConstraints( 3,2,1,1,1,1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,new Insets(5,0,1,1),0,0));
        MainDialog.add(Ty2 , new GridBagConstraints( 3,3,1,1,1,1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,new Insets(5,0,1,1),0,0));

        MainDialog.add(new JLabel("x1:") , new GridBagConstraints( 0,2,1,1,1,1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,new Insets(5,10,1,1),0,0));
        MainDialog.add(new JLabel("x2:") , new GridBagConstraints( 0,3,1,1,1,1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,new Insets(5,10,1,1),0,0));

        MainDialog.add(new JLabel("y1:") , new GridBagConstraints( 2,2,1,1,1,1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,new Insets(5,10,1,1),0,0));
        MainDialog.add(new JLabel("y2:") , new GridBagConstraints( 2,3,1,1,1,1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,new Insets(5,10,1,1),0,0));

        MainDialog.add(Buildbutton , new GridBagConstraints( 0,4,4,1,1,1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,new Insets(5,1,1,1),0,0));


        MainDialog.setVisible(true);
       Window.pack();
        
        ArrayList<PlotsData> plotscollection = new ArrayList<PlotsData>();
        Buildbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PlotsData data = new PlotsData();
                data.function = Tf.getText(). trim();
                try
                {
                data.x1 = Double.parseDouble(Tx1.getText(). trim());
                data.x2 = Double.parseDouble(Tx2.getText(). trim());
                data.y1 = Double.parseDouble(Ty1.getText(). trim());
                data.y2 = Double.parseDouble(Ty2.getText(). trim());  
                } catch (NumberFormatException w2) {JOptionPane.showMessageDialog(null, " Введите числа для построения графика в нужном интервале");  return;}
                if (data.x1>data.x2||data.y1>data.y2||data.function.equals(""))
                {
                     JOptionPane.showMessageDialog(null, " Неверно заполнены условия для построения графика");
                     return;
                }
                try {
                Expression parser = new ExpressionBuilder(data.function)
                    .variables("x")
                    .build();
                parser.setVariable("x", 1);
                double result = parser.evaluate();
                }  catch (IllegalArgumentException w4) { JOptionPane.showMessageDialog(null, " При вводе функции допущена ошибка");  return;
                }
                
                try {
                plotscollection.add(data);
                } catch (Exception w3) {} 
                
                ArrayList <Double> points = new ArrayList<Double>();
                points.add(data.x1);
                points.add(data.x2);
                points.add(data.y1);
                points.add(data.y2);
                Window.setVisible(false);
                
                Plotcr demo = new Plotcr("", plotscollection, points);
                demo.pack();
                RefineryUtilities.centerFrameOnScreen(demo);
                demo.setVisible(true);
                }
        });
    }
}
