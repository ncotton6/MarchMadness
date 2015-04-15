package util;
// imports
// standard imports
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
// JFreeChart imports
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.annotations.XYTextAnnotation;

/**
 * Uses JFreeChart-1.0.19 (http://sourceforge.net/projects/jfreechart/files/)
 *
 * @author Ryan Conrad
 */
public class GraphUtils
{
    /**
     * Creates a line chart of data and outputs
     * a graph to a jpg file.
     *
     * @param y List of y values
     * @param yLabel y axis label
     * @param fileName name of the file (without ".jpg" extension)
     */
    public static void createLineChart(
        ArrayList<Double> y, String yLabel, String fileName)
    {
        createLineChart(null, y, null, null, "N", yLabel, fileName);
    }
    
    /**
     * Creates a line chart of data and outputs
     * a graph to a jpg file.
     *
     * @param x List of x values
     * @param y List of y values
     * @param xLabel x axis label
     * @param yLabel y axis label
     * @param fileName name of the file (without ".jpg" extension)
     */
    public static void createLineChart(
        ArrayList<Double> x, ArrayList<Double> y,
        String xLabel, String yLabel, String fileName)
    {
        createLineChart(x, y, null, null, xLabel, yLabel, fileName);
    }
    
    /**
     * Creates a line chart of birthday probabilities and outputs
     * a graph to a jpg file.
     *
     * The x and y inputs are graphed as a line, and the critX
     * and critY inputs are graphed as points.
     *
     * @param x List of x values
     * @param y List of y values
     * @param critX List of x values to plot points
     * @param critY List of y values to plot points
     * @param xLabel x axis label
     * @param yLabel y axis label
     * @param fileName name of the file (without ".jpg" extension)
     */
    public static void createLineChart(
        ArrayList<Double> x, ArrayList<Double> y,
        ArrayList<Double> critX, ArrayList<Double> critY,
        String xLabel, String yLabel, String fileName)
    {
        if(x == null)
        {
            x = new ArrayList<Double>(y.size());
            for(int i = 0; i < y.size(); ++i)
            {
                x.add((double)i);
            }
        }
        // Put x and y data into an XYSeries
        XYSeries dataset = 
            new XYSeries("Dataset 1");
        for(int i = 0; i < x.size(); ++i)
        {
            dataset.add(x.get(i), y.get(i));
        }
        
        // Create a collection based off of the two data sets.
        final XYSeriesCollection data = new XYSeriesCollection();
        if(!(critX == null || critY == null))
        {
            // Put probability boundary x and y data into an XYSeries
            XYSeries datasetCrit = 
                new XYSeries("Probability Boundaries");
            for(int i = 0; i < critX.size(); ++i)
            {
                datasetCrit.add(critX.get(i), critY.get(i));
            }
            data.addSeries(datasetCrit);
        }
        data.addSeries(dataset);
        
        // Create the line chart
        JFreeChart lineChart = ChartFactory.createXYLineChart(
            yLabel + " vs. " + xLabel,
            xLabel,yLabel,
            data,
            PlotOrientation.VERTICAL,
            true,true,false);
        
        /*
         * Render the first series as a line and
         * the second series as points.
         */
        final XYPlot plot = lineChart.getXYPlot();
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesLinesVisible(1, false);
        plot.setRenderer(renderer);
        
        DecimalFormat df = new DecimalFormat("0");      // Format x points
        DecimalFormat df2 = new DecimalFormat("0.000"); // Format y points
        if(!(critX == null || critY == null))
        {
            // Create labels for the critical points
            for(int i = 0; i < critX.size(); ++i)
            {
                // Create string for annotation
                String d = "(" + df.format(critX.get(i)) 
                    + ", " +  df2.format(critY.get(i)) + ")";
                // Create annotation (x.size()/15 is the horizontal offset)
                XYTextAnnotation a = new XYTextAnnotation(
                    d, critX.get(i)+x.size()/15, critY.get(i));
                // Add the annotation
                plot.addAnnotation(a);
            }
        }
        
        // Set width, height, and file info for the image
        int width = 640;
        int height = 480;
        File XYChart = new File(fileName + ".jpg");
        
        // Export the image
        try
        {
            ChartUtilities.saveChartAsJPEG(XYChart, lineChart, width, height);
        }
        catch(IOException e)
        {
        
        }
    }
}