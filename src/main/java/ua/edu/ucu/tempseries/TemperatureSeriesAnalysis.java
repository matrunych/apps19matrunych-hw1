package ua.edu.ucu.tempseries;
import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {

    private double[] temperSeries;
//    private double avgTemp;
//    private double devTemp;
//    private double minTemp;
//    private double maxTemp;
    int capacity;
    int size;

    public TemperatureSeriesAnalysis() {

    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        for (int i = 0; i < temperatureSeries.length; i++) {
            if (temperatureSeries[i] < -273.0){
                throw new InputMismatchException();
            }
        }
        this.temperSeries = Arrays.copyOf(temperatureSeries, temperatureSeries.length);
        this.size = temperatureSeries.length;
        this.capacity = size * 2;
    }

    public double average() {
        if (temperSeries.length == 0){
            throw new IllegalArgumentException("Temperature series can't be empty!");
        }
        double aver = 0.0;
        for (int i = 0; i < temperSeries.length; i++){
            aver += temperSeries[i];
        }
        aver = aver / temperSeries.length;
//        this.avgTemp = aver;
        return aver;

    }

    public double deviation() {
        if (temperSeries.length == 0) {
            throw new IllegalArgumentException("Temperature series can't be empty!");
        }
        double dev = 0.0;
        for(int i = 0; i < temperSeries.length; i++) {
            dev += Math.pow(temperSeries[i] - average(), 2.0);
        }
        dev = Math.sqrt(dev/temperSeries.length);
        return dev;

    }

    public double min() {
        if (temperSeries.length == 0) {
            throw new IllegalArgumentException("Temperature series can't be empty!");
        }
        double min_temp = temperSeries[0];
        for(int i = 0; i < temperSeries.length; i++){
            if (temperSeries[i] < min_temp){
                min_temp = temperSeries[i];
            }
        }
//        this.minTemp = min_temp;
        return min_temp;
    }

    public double max() {
        if (temperSeries.length == 0) {
            throw new IllegalArgumentException("Temperature series can't be empty!");
        }
        double max_temp = temperSeries[0];
        for(int i = 0; i < temperSeries.length; i++){
            if (temperSeries[i] > max_temp){
                max_temp = temperSeries[i];
            }
        }
//        this.maxTemp = max_temp;
        return max_temp;
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        if (temperSeries.length == 0) {
            throw new IllegalArgumentException("Temperature series can't be empty!");
        }
        double difference = Math.abs(temperSeries[0] - tempValue);
        int idx = 0;
        for(int i = 0; i < temperSeries.length; i++) {
            double temp_differ = Math.abs(temperSeries[i] - tempValue);
            if(temp_differ < difference){
                idx = i;
                difference = temp_differ;
            }
        }
        double closest = temperSeries[idx];
        return closest;

    }

    public double[] findTempsLessThen(double tempValue) {
        if (temperSeries.length == 0) {
            throw new IllegalArgumentException("Temperature series can't be empty!");
        }
        int s = 0;
        for(int i = 0; i < temperSeries.length; i++){
            if(temperSeries[i] < tempValue){
                s += 1;
            }
        }
        double less_arr[] = new double[s];
        int k = 0;
        for(int j = 0; j < temperSeries.length; j++){
            if(temperSeries[j] < tempValue){
                less_arr[k] = temperSeries[j];
                k++;
            }
        }
        return less_arr;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        if (temperSeries.length == 0) {
            throw new IllegalArgumentException("Temperature series can't be empty!");
        }
        int s = 0;
        for(int i = 0; i < temperSeries.length; i++){
            if(temperSeries[i] >= tempValue){
                s += 1;
            }
        }
        double greater_arr[] = new double[s];
        int k = 0;
        for(int j = 0; j < temperSeries.length; j++){
            if(temperSeries[j] >= tempValue){
                greater_arr[k] = temperSeries[j];
                k++;
            }
        }
        return greater_arr;
    }

    public TempSummaryStatistics summaryStatistics() {
        if (temperSeries.length == 0) {
            throw new IllegalArgumentException("Temperature series can't be empty!");
        }
        return new TempSummaryStatistics(this.average(), this.deviation(), this.min(), this.max());
        }

    public int addTemps(double... temps) {
            for(double value: temps){
                if (value < -273){
                    throw new InputMismatchException();
            }
                if(capacity == size){
                    double[] new_arr = new double[capacity * 2];
                    System.arraycopy(temperSeries, 0, new_arr, 0, size);
                    temperSeries = new_arr;
                    capacity *= 2;
                    temperSeries[size] = value;
                    size++;
                }
        }
        return size;
    }
}
