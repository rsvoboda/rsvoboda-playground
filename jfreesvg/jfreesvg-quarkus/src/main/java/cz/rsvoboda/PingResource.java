package cz.rsvoboda;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYDrawableAnnotation;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.TickUnitSource;
import org.jfree.chart.axis.TickUnits;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYDataset;
import org.jfree.graphics2d.svg.SVGGraphics2D;


@Path("/ping")
public class PingResource {

    @GET
    @Produces("image/svg+xml")
    public String ping() {
        JFreeChart chart = createChart(createDataset());
        SVGGraphics2D g2 = new SVGGraphics2D(600, 400);
        Rectangle r = new Rectangle(0, 0, 600, 400);
        chart.draw(g2, r);
        return g2.getSVGElement();
    }


    private static JFreeChart createPieChart() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Engineering", 43.2);
        dataset.setValue("Research", 13.2);
        dataset.setValue("Advertising", 20.9);
        PiePlot plot = new PiePlot(dataset);
        plot.setBackgroundPaint(null);
        // plot.setBorderPainter(null);
        // plot.setBaseSectionOutlinePaint(Color.white);
        // plot.setBaseSectionOutlineStroke(new BasicStroke(2.0f));
        plot.setLabelFont(new Font("Dialog", Font.PLAIN, 18));
        plot.setMaximumLabelWidth(0.25);
        JFreeChart chart = new JFreeChart(plot);
        chart.setBackgroundPaint(null);
        chart.removeLegend();
        chart.setPadding(RectangleInsets.ZERO_INSETS);
        return chart;
    }

    private static JFreeChart createBarChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(10.0, "R1", "Q1");
        dataset.addValue(7.0, "R1", "Q2");
        dataset.addValue(8.0, "R1", "Q3");
        dataset.addValue(4.0, "R1", "Q4");
        dataset.addValue(10.6, "R2", "Q1");
        dataset.addValue(6.1, "R2", "Q2");
        dataset.addValue(8.5, "R2", "Q3");
        dataset.addValue(4.3, "R2", "Q4");
        JFreeChart chart = ChartFactory.createBarChart("Sales 2008", null, null, dataset);
        chart.removeLegend();
        chart.setBackgroundPaint(null);
        chart.getPlot().setBackgroundPaint(
                new Color(200, 200, 255, 60));
        return chart;
    }

    private static TickUnitSource createStandardDateTickUnits() {
        TickUnits units = new TickUnits();
        DateFormat df = new SimpleDateFormat("yyyy");
        units.add(new DateTickUnit(DateTickUnitType.YEAR, 1,
                DateTickUnitType.YEAR, 1, df));
        units.add(new DateTickUnit(DateTickUnitType.YEAR, 2,
                DateTickUnitType.YEAR, 1, df));
        units.add(new DateTickUnit(DateTickUnitType.YEAR, 5,
                DateTickUnitType.YEAR, 5, df));
        return units;
    }

    private static JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "XYDrawableAnnotationDemo1",
                null, "$ million", dataset);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        DateAxis xAxis = (DateAxis) plot.getDomainAxis();
        xAxis.setLowerMargin(0.2);
        xAxis.setUpperMargin(0.2);
        xAxis.setStandardTickUnits(createStandardDateTickUnits());

        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setLowerMargin(0.2);
        yAxis.setUpperMargin(0.2);

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        // renderer.setBaseShapesVisible(true);
        // renderer.setBaseLinesVisible(true);
        renderer.setSeriesShape(0, new Ellipse2D.Double(-5.0, -5.0, 10.0, 10.0));
        renderer.setSeriesShape(1, new Ellipse2D.Double(-5.0, -5.0, 10.0, 10.0));
        renderer.setSeriesStroke(0, new BasicStroke(3.0f));
        renderer.setSeriesStroke(1, new BasicStroke(3.0f, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND, 5.0f, new float[] {10.0f, 5.0f}, 0.0f));
        renderer.setSeriesFillPaint(0, Color.white);
        renderer.setSeriesFillPaint(1, Color.white);
        renderer.setUseFillPaint(true);

        renderer.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
        renderer.setDefaultEntityRadius(6);

        renderer.addAnnotation(new XYDrawableAnnotation(
                new Month(4, 2005).getFirstMillisecond(), 600, 180, 100, 3.0,
                createPieChart()));
        renderer.addAnnotation(new XYDrawableAnnotation(
                new Month(9, 2007).getFirstMillisecond(), 1250, 120, 100, 2.0,
                createBarChart()));
        plot.setRenderer(renderer);
        return chart;
    }

    private static XYDataset createDataset() {
        TimeSeries series1 = new TimeSeries("Division A");
        series1.add(new Year(2005), 1520);
        series1.add(new Year(2006), 1132);
        series1.add(new Year(2007), 450);
        series1.add(new Year(2008), 620);
        TimeSeries series2 = new TimeSeries("Division B");
        series2.add(new Year(2005), 1200);
        series2.add(new Year(2006), 1300);
        series2.add(new Year(2007), 640);
        series2.add(new Year(2008), 520);
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        return dataset;
    }
}
