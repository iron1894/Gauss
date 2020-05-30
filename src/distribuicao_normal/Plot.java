/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribuicao_normal;

import java.util.ArrayList;
import java.util.List;
import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Igor
 */
public class Plot {
    
    public Plot(){
        
    }
    
    public JFreeChart create_line_chart(String title, String nm_axis_X, String nm_axis_Y, String label, List<Double> x, List<Double> y){
                
        JFreeChart line = ChartFactory.createXYLineChart(title, nm_axis_X, nm_axis_Y, run_data_set(label, x, y));
        
        XYPlot plot = (XYPlot)line.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        
        return line;
    }
    
    public XYDataset run_data_set(String label, List<Double> x, List<Double> y){
        
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series1 = new XYSeries("Distribuição");
        
        for(int i = 0; i < x.size(); i++){
            
            series1.add(x.get(i), y.get(i));
        }
        
        dataset.addSeries(series1);
        
        return dataset;
    }
}
