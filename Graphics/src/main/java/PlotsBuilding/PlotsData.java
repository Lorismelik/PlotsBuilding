package PlotsBuilding;

import java.util.ArrayList;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import java.util.Optional;
import com.rits.cloning.Cloner;

public class PlotsData implements Cloneable {

    String function = "";
    double x1;
    double x2;
    double y1;
    double y2;
    boolean end = false;
    public PlotsData() {
    boolean end = false;
    }  
    public XYSeriesCollection createPlotdataset(int number, XYSeriesCollection col)
    { 
        double oldx1=x1;
        Optional<XYSeries> a = Optional.ofNullable(null);
        int i = 1;
        while (end == false)
        {
           a = createDataset(); 
            if(a.isPresent())
            {
                XYSeries s = a.get();
                if (i==1)
                {
                    s.setKey(Integer.toString(number+1)+".  " +function);
                }
                else
                {
                    s.setKey(Integer.toString(number+1)+". "+Integer.toString(i)+" "+function);
                }
                i++;
                col.addSeries(s);
            }   
        } 
        end = false;
        x1 = oldx1;
        return col;
    }
    
    @Override
    public PlotsData clone()
    {
        Cloner cloner = new Cloner();
        PlotsData clone = cloner.deepClone(this);
        return clone;
    }
    private Optional createDataset() {
        XYSeries series = new XYSeries("");
        Expression parser = new ExpressionBuilder(function)
                    .variables("x")
                    .build();
        double step = 0.0002;
        for (double i = x1; i <= x2; i += step) 
        {
            parser.setVariable("x", i);
            double result = 0;
            result = parser.evaluate();
            if ((result<y2)&(result>y1)) {
                series.add(i, result);
            } 
            else 
            {
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
