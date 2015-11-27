package PlotsBuilding;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author РўРёРјСѓСЂ
 */
public class App extends JFrame {

    public static void main(String[] args) {
        App j = new App();
    }
    TextField Count;
    TextField Tf;
    TextField Tx1;
    TextField Tx2;
    TextField Ty1;
    TextField Ty2;
    JFrame Window;
    JPanel MainDialog;
    JPanel SetCount;
    JButton Buildbutton;
    JButton AcceptCount;
    JLabel er;
    int Accept = 0;
    Vector plotscollection = new Vector();

    public App() {
        Window = new JFrame();
        Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RefineryUtilities.centerFrameOnScreen(Window);
        SetCount = new JPanel();
        Window.setResizable(false);
        Window.add(SetCount);
        SetCount.setBackground(Color.white);
        SetCount.setLayout(null);
        AcceptCount = new JButton("Принять");
        Window.setTitle("");
        Window.setSize(323, 200);
        Count = new TextField("", 40);
        Count.setLocation(97, 90);
        Count.setSize(90, 20);
        JLabel co = new JLabel("Введите количество графиков");
        JLabel Hello = new JLabel("Здравствуйте, Вас приветствует Графикатор-3000");
        Hello.setLocation(13, 20);
        Hello.setSize(300, 20);
        co.setLocation(60, 55);
        co.setSize(300, 20);
        AcceptCount.setLocation(93, 120);
        AcceptCount.setSize(100, 30);
        SetCount.add(AcceptCount);
        SetCount.add(co);
        SetCount.add(Hello);
        SetCount.add(Count);
        SetCount.setVisible(true);
        Window.setVisible(true);
        AcceptCount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SetCount.setVisible(false);
                dialogwindow();
            }
        });
    }

    public void dialogwindow() {
        MainDialog = new JPanel();
        Vector collect = new Vector();
        JCheckBox box = new JCheckBox();
        collect.addElement(box);
        JCheckBox box1 = (JCheckBox) collect.get(0);
        Window.add(MainDialog);
        MainDialog.setBackground(Color.white);
        MainDialog.setLayout(null);
        Buildbutton = new JButton("Построить");
        Window.setSize(370, 260);
        Window.setTitle("Ввод функций");
        Tf = new TextField();
        Tx1 = new TextField("", 40);
        Tx2 = new TextField("", 40);
        Ty1 = new TextField("", 40);
        Ty2 = new TextField("", 40);

        Tf.setLocation(100, 60);
        Tf.setSize(200, 20);

        Tx1.setLocation(110, 100);
        Tx2.setLocation(230, 100);

        Tx1.setSize(50, 20);
        Tx2.setSize(50, 20);

        MainDialog.add(Tx1);
        MainDialog.add(Tx2);

        Ty1.setLocation(110, 145);
        Ty2.setLocation(230, 145);

        Ty1.setSize(50, 20);
        Ty2.setSize(50, 20);

        MainDialog.add(Ty1);
        MainDialog.add(Ty2);

        MainDialog.add(Tf);

        JLabel Lf = new JLabel("Введите функцию:");
        JLabel Lfx = new JLabel("F(x) =");
        JLabel Lx1 = new JLabel("x1:");
        JLabel Lx2 = new JLabel("x2:");
        JLabel Ly1 = new JLabel("y1:");
        JLabel Ly2 = new JLabel("y2:");

        Lf.setLocation(130, 20);
        Lf.setSize(140, 20);
        MainDialog.add(Lf);

        Lfx.setLocation(60, 60);
        Lfx.setSize(40, 20);
        MainDialog.add(Lfx);

        Lx1.setLocation(60, 100);
        Lx1.setSize(40, 20);
        MainDialog.add(Lx1);

        Lx2.setLocation(200, 100);
        Lx2.setSize(40, 20);
        MainDialog.add(Lx2);

        Ly1.setLocation(60, 145);
        Ly1.setSize(40, 20);
        MainDialog.add(Ly1);

        Ly2.setLocation(200, 145);
        Ly2.setSize(40, 20);
        MainDialog.add(Ly2);

        Buildbutton.setLocation(127, 180);
        Buildbutton.setSize(120, 50);
        MainDialog.add(Buildbutton);
        er = new JLabel("");
        er.setLocation(10, 210);
        er.setSize(170, 20);
        MainDialog.add(er);
        MainDialog.setVisible(true);
        Window.setResizable(false);

        Buildbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PlotsData data = new PlotsData();
                data.function = Tf.getText();
                if (!data.function.equals("")) {
                    set("");
                    data.x1 = Double.parseDouble(Tx1.getText());
                    data.x2 = Double.parseDouble(Tx2.getText());
                    try {
                        if (data.x1 < data.x2) {
                            set("");
                            data.y1 = Double.parseDouble(Ty1.getText());
                            data.y2 = Double.parseDouble(Ty2.getText());
                            try {
                                if (data.y1 < data.y2) {
                                    set("");
                                    try {
                                        plotscollection.addElement(data);
                                        Accept++;
                                    } catch (Exception w3) {
                                        System.out.println(w3);
                                    }
                                } else {
                                    set("Ошибка");
                                }

                            } catch (Exception w1) {
                                set("Ошибка‹");
                            }
                        } else {
                            set("Ошибка");
                        }

                    } catch (Exception w2) {
                        set("Ошибка‹");
                    }
                } else {
                    set("Ошибка");
                }

                if (Accept >= Integer.parseInt(Count.getText())) {
                    Window.setVisible(false);
                    Plotcr demo = new Plotcr("", plotscollection, Accept, data.y1, data.y2);
                    demo.pack();
                    RefineryUtilities.centerFrameOnScreen(demo);
                    demo.setVisible(true);

                }

            }
        });
    }

    public void set(String s) {
        er.setText(s);
    }

}

/* public static void main(String[] args) {
 javax.swing.SwingUtilities.invokeLater(new Runnable() {
 public void run() {
 createAndShowGUI();
 }
 });
 }
 **/
