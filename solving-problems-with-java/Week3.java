/**
 * Write a description of CSVMax here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Week3 {
    public CSVRecord coldestHourInFile(CSVParser parser, String metric) {
        CSVRecord coldestSoFar = null;
        for (CSVRecord currRow : parser) {
            coldestSoFar = getSmallestOfTwo(currRow, coldestSoFar, metric);
        }
        return coldestSoFar;
    }
    
    public void testColdestHourInFile () {
        FileResource fr = new FileResource("nc_weather/2014/weather-2014-05-01.csv");
        String metric = "TemperatureF";
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser(), metric);
        System.out.println("lowest " + metric + " " + "was " + smallest.get(metric) +
                " at " + smallest.get("DateUTC"));
    }
    
    public String fileWithColdestTemperature(String metric) {
        CSVRecord coldestSoFar = null;
        String fileName = "";
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currRow = coldestHourInFile(fr.getCSVParser(), metric);
            if (coldestSoFar == null) {
                coldestSoFar = currRow;
            }
            else {
                double currTemp = Double.parseDouble(currRow.get(metric));
                double smallestTemp = Double.parseDouble(coldestSoFar.get(metric));
                if (currTemp < smallestTemp && currTemp > -9999) {
                    coldestSoFar = currRow;
                    fileName = f.getName();
                }
            }
            
        }
        return fileName;
    }
    
    public CSVRecord getSmallestOfTwo(CSVRecord currRow, CSVRecord coldestSoFar, String metric) {
        if (coldestSoFar == null) {
                coldestSoFar = currRow;
            }
            else {
                if (currRow.get(metric).equals("N/A")) return coldestSoFar;
                double currTemp = Double.parseDouble(currRow.get(metric));
                double smallestTemp = Double.parseDouble(coldestSoFar.get(metric));
                if (currTemp < smallestTemp && currTemp > -9999) {
                    coldestSoFar = currRow;
                }
            }
        return coldestSoFar;
    }
    
    public void testColdestInManyDays () {
        String metric = "TemperatureF";
        String fileName = fileWithColdestTemperature(metric);
        System.out.println("Coldest day was in file " + fileName);
        FileResource fr = new FileResource("nc_weather/2013/"+fileName);
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser(), metric);
        System.out.println("coldest " + metric + " was " + smallest.get(metric) +
                " at " + smallest.get("DateUTC"));
    }
    
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestHumSoFar = null;
        for (CSVRecord currRow : parser) {
            lowestHumSoFar = getSmallestOfTwo(currRow, lowestHumSoFar, "Humidity");
        }
        return lowestHumSoFar;
    }
    
    public void testLowestHumidityInFile () {
        FileResource fr = new FileResource("nc_weather/2014/weather-2014-07-22.csv");
        CSVRecord smallest = lowestHumidityInFile(fr.getCSVParser());
        System.out.println("lowest humidity was " + smallest.get("Humidity") +
                " at " + smallest.get("DateUTC"));
    }
    
    public double averageTemperatureInFile(CSVParser parser) {
        CSVRecord coldestSoFar = null;
        double sum = 0;
        int count = 0;
        for (CSVRecord currRow : parser) {
            sum = sum + Double.parseDouble(currRow.get("TemperatureF"));
            count++;
            
        }
        return sum / count;
    }
    
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource("nc_weather/2013/weather-2013-08-10.csv");
        double avg = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average temperature in file is " + avg);
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        CSVRecord coldestSoFar = null;
        double sum = 0;
        int count = 0;
        for (CSVRecord currRow : parser) {
            double hum = Double.parseDouble(currRow.get("Humidity"));
            if (hum >= value) {
                sum = sum + Double.parseDouble(currRow.get("TemperatureF"));
                count++;
            }
        }
        return sum / count;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource("nc_weather/2013/weather-2013-09-02.csv");
        double avg = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
        System.out.println("Average temp when high humidity in file is " + avg);
    }
}
