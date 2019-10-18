package ua.edu.ucu.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {

    private double[] temperSeries;
    int capacity;
    int size;

//     public TemperatureSeriesAnalysis() {

//     }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        for (int i = 0; i < temperatureSeries.length; i++) {
            if (temperatureSeries[i] < -273.0) {
                throw new InputMismatchException();
            }
        }
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("Temperature series can't be empty!");
        }

        this.temperSeries = Arrays.copyOf(temperatureSeries, temperatureSeries.length);
        this.size = temperatureSeries.length;
        this.capacity = size * 2;
    }

    public double average() {
        double aver = 0.0;
        for (int i = 0; i < temperSeries.length; i++) {
            aver += temperSeries[i];
        }
        aver = aver / temperSeries.length;
        return aver;

    }

    public double deviation() {
        double dev = 0.0;
        for (int i = 0; i < temperSeries.length; i++) {
            dev += Math.pow(temperSeries[i] - average(), 2.0);
        }
        dev = Math.sqrt(dev / temperSeries.length);
        return dev;

    }

    public double min() {
        double min_temp = temperSeries[0];
        for (int i = 0; i < temperSeries.length; i++) {
            if (temperSeries[i] < min_temp) {
                min_temp = temperSeries[i];
            }
        }
        return min_temp;
    }

    public double max() {
        double max_temp = temperSeries[0];
        for (int i = 0; i < temperSeries.length; i++) {
            if (temperSeries[i] > max_temp) {
                max_temp = temperSeries[i];
            }
        }
        return max_temp;
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        double difference = Math.abs(temperSeries[0] - tempValue);
        int idx = 0;
        for (int i = 0; i < temperSeries.length; i++) {
            double temp_differ = Math.abs(temperSeries[i] - tempValue);
            if (temp_differ < difference) {
                idx = i;
                difference = temp_differ;
            }
        }
        double closest = temperSeries[idx];
        return closest;

    }

    public double[] findTempsLessThen(double tempValue) {
        int s = 0;
        for (int i = 0; i < temperSeries.length; i++) {
            if (temperSeries[i] < tempValue) {
                s += 1;
            }
        }
        double less_arr[] = new double[s];
        int k = 0;
        for (int j = 0; j < temperSeries.length; j++) {
            if (temperSeries[j] < tempValue) {
                less_arr[k] = temperSeries[j];
                k++;
            }
        }
        return less_arr;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        int s = 0;
        for (int i = 0; i < temperSeries.length; i++) {
            if (temperSeries[i] >= tempValue) {
                s += 1;
            }
        }
        double greater_arr[] = new double[s];
        int k = 0;
        for (int j = 0; j < temperSeries.length; j++) {
            if (temperSeries[j] >= tempValue) {
                greater_arr[k] = temperSeries[j];
                k++;
            }
        }
        return greater_arr;
    }

    public TempSummaryStatistics summaryStatistics(double average,
                                                   double deviation,
                                                   double min,
                                                   double max) {

        return new TempSummaryStatistics(this.average(), this.deviation(), this.min(), this.max());
    }

    public int addTemps(double... temps) {
        double[] newArr = new double[temperSeries.length * 2];
        int sum = 0;
        int i = 0;
        for (double temp : temperSeries) {
            newArr[i] = temp;
            sum += temp;
            i++;
        }
        for (double t : temps) {
            if (t < -273.0) {
                throw new InputMismatchException();
            }
            newArr[i] = t;
            sum += t;
            i++;
        }
        this.temperSeries = newArr;
        return sum;
    }
}
