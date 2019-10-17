package ua.edu.ucu.tempseries;

import java.util.Arrays;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.InputMismatchException;

import org.junit.Ignore;

public class TemperatureSeriesAnalysisTest {
    private double[] temperatureSeries;
    private TemperatureSeriesAnalysis seriesAn;

    @Before
    public void setUp() throws Exception {
        temperatureSeries = new double[]{3.0, -5.0, 1.0, -1.0, 5.0};
        seriesAn = new TemperatureSeriesAnalysis(Arrays.copyOf(temperatureSeries, temperatureSeries.length));
    }

    @Test
    public void testAverageWithOneElementArray() {
        // setup input data and expected result
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysis.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.average();
    }

    @Test(expected = InputMismatchException.class)
    public void testAverageWithWrongTemperature() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, -274.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.average();
    }

    @Test
    public void testAverage() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;

        double actualResult = seriesAnalysis.average();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.min();
    }


    @Test
    public void testDeviation() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 3.7416573867739413;

        double actualResult = seriesAnalysis.deviation();

        assertEquals(expResult, actualResult, 0.00001);
    }


    @Test
    public void testMin() {
        assertEquals(seriesAn.min(), -5.0, 0.00001);
    }


    @Test
    public void testMax() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 5.0;

        double actualResult = seriesAnalysis.max();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToZero() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;

        double actualResult = seriesAnalysis.findTempClosestToZero();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToValue() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0, 4.5, 4.7};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 4.5;

        double actualResult = seriesAnalysis.findTempClosestToValue(4.0);

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsLessThen() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0, 4.5, 4.7};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {3.0, -5.0, 1.0};

        double[] actualResult = seriesAnalysis.findTempsLessThen(4.0);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsGreaterThen() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0, 4.5, 4.7};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {5.0, 4.5, 4.7};

        double[] actualResult = seriesAnalysis.findTempsGreaterThen(4.0);

        assertArrayEquals(expResult, actualResult, 0.00001);

    }

    @Test
    public void TestSummaryStatistics() {
        TemperatureSeriesAnalysis ser = seriesAn;
        TempSummaryStatistics st = seriesAn.summaryStatistics(seriesAn.average(), seriesAn.deviation(), seriesAn.min(), seriesAn.max());
        assertArrayEquals(new double[]{0.6, 3.4409, 5.0, -5.0}, new double[]{st.getAvgTemp(), st.getDevTemp(), st.getMaxTemp(), st.getMinTemp()}, 0.01);
    }

    @Test
    public void testAddTemps() {
        assertEquals(seriesAn.addTemps(6.0, -16.3), 3.0, 0.01);
    }

    @Test(expected = InputMismatchException.class)
    public void testAddTempsWrongTemperature() {
        seriesAn.addTemps(6.0, -288.3);

    }
}
