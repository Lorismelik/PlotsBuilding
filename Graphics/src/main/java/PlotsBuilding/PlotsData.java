package PlotsBuilding;

import static PlotsBuilding.Plotcr.col;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.jfree.data.xy.XYSeries;

public class PlotsData {

    String function = "";
    double x1;
    double x2;
    double y1;
    double y2;

    public PlotsData() {

    }

    public PlotsData(double a, double b, double c, double d) {
        this.x1 = a;
        this.x2 = b;
        this.y1 = c;
        this.y2 = d;
    }

    public boolean createDataset(int Accept, boolean end) {
        XYSeries series;

        series = new XYSeries(Integer.toString(Accept + 1) + " " + function);
        double j = Math.abs(x1);
        double l = Math.abs(x2);
        if (j < 1) {
            j = Math.pow(j, l);
        }
        if (l < 1) {
            l = Math.pow(l, l);
        }
        double step = (Math.abs(x1) + Math.abs(x2)) / ((j + l) * 200);
        for (double i = x1; i <= x2; i += step) {
            Expression parser = new ExpressionBuilder(function)
                    .variables("x")
                    .build()
                    .setVariable("x", i);
            double result = 0;
            double lastresult = 0;
            try {
                result = parser.evaluate();
            } catch (Exception e) {
            }
            if (Math.abs(result) < Math.abs(8 * 100000) & Math.abs(Math.abs(result) - Math.abs(lastresult)) < Math.abs(Math.abs(y1) + Math.abs(y2))) {
                series.add(i, result);
                lastresult = result;
            } else {
                if (Math.abs(Math.abs(i) - Math.abs(x1)) >= step) {
                    col.addSeries(series);
                }
                x1 = i + step;
                end = false;
                return end;
            }

        }
        col.addSeries(series);
        end = true;
        return end;
    }

}
