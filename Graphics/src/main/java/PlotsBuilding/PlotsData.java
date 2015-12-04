package PlotsBuilding;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import java.util.Optional;

public class PlotsData {

    String function = "";
    double x1;
    double x2;
    double y1;
    double y2;
    boolean end = false;
    public PlotsData() {

    }

    public PlotsData(double a, double b, double c, double d) {
        this.x1 = a;
        this.x2 = b;
        this.y1 = c;
        this.y2 = d;
    }
    
    public XYSeriesCollection createPlotdataset(int Accept, XYSeriesCollection col)
    { 
        Optional<XYSeries> a = Optional.ofNullable(null);
        int i = 1;
        while (end == false)
        {
           a = createDataset(i); 
            if(a.isPresent()==true)
        {
            XYSeries s = a.get();
            s.setKey(Integer.toString(Accept+i)+". "+function);
            i++;
            col.addSeries(s);
        }   
        }  
        return col;
    }

    public Optional createDataset(int Accept) {
        XYSeries series = new XYSeries(Double.toString(x1 + 1) + " " + function);
        double j = Math.abs(x1);
        double l = Math.abs(x2);
        if (j < 1) {
            j = Math.pow(j, l);
        }
        if (l < 1) {
            l = Math.pow(l, l);
        }
        Expression parser = new ExpressionBuilder(function)
                    .variables("x")
                    .build();
        double step = (Math.abs(x1) + Math.abs(x2)) / ((j + l) * 200);
        for (double i = x1; i <= x2; i += step) {
            parser.setVariable("x", i);
            double result = 0;
            double lastresult = 0;
            try {
                result = parser.evaluate();
            } catch (Exception e) {
            }
            if ((Math.abs(Math.abs(result) - Math.abs(lastresult)) < Math.abs(Math.abs(y1) + Math.abs(y2)/2))&(result<y2)&(result>y1)) {
                series.add(i, result);
                lastresult = result;
            } else {
                x1 = i + step;
                if (series.getItemCount()<2)
                { series = null; }
                Optional<XYSeries> a = Optional.ofNullable(series);
                return a;
            }

        }
        end =true;
        if (series.getItemCount()<2)
                { series = null; }
        Optional<XYSeries> a = Optional.ofNullable(series);
        return a;
    }

}
