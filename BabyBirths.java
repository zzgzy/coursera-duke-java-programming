/**
 * Print out the names for which 100 or fewer babies were born in a chosen CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;


public class BabyBirths {
    
    public void printNames () {
    FileResource fr = new FileResource();
    // pass in a false means that this csv file doesn't have a header.
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                           " Gender " + rec.get(1) +
                           " Num Born " + rec.get(2));
            }
        }
    }
    
    public void totalBirths (FileResource fr) {
        int totalBoys = 0;
        int totalGirls = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals("M")) {
                totalBoys ++;
            } 
            else {
                totalGirls ++;
            }
        }
        System.out.println("total girls = " + totalGirls);
        System.out.println("total boys = " + totalBoys);
    }
    
    public void testTotalBirths () {
        FileResource fr = new FileResource("../us_babynames_by_year/yob1905.csv");
        totalBirths(fr);
    }
    
    public int getRank(int year, String name, String gender) {
        String filePath = "../us_babynames_by_year/yob"+ Integer.toString(year) + ".csv";
        FileResource fr = new FileResource(filePath);
        int rank = 0;
        Boolean found = false;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (!rec.get(1).equals(gender)){ 
                continue;
            }
            if (name.equals(rec.get(0))) {
                found = true;
                break;
            }
            else {
                rank++;
            }
        }
        if (!found) return -1;
        return rank+1;
    }
    
    public void testGetRank() {
        int rank = getRank(1971, "Frank", "M");
        System.out.println("Rank is " + rank);
    }
    
    public String getName(int year, int rank, String gender) {
        String filePath = "../us_babynames_by_year/yob"+ Integer.toString(year) + ".csv";
        FileResource fr = new FileResource(filePath);
        String name = "NO NAME";
        int cnt = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (!rec.get(1).equals(gender)){ 
                continue;
            }
            cnt ++;
            if (cnt == rank) {
                name = rec.get(0);
            }
        }
        return name;
    }
    
    public void testGetName() {
        String name = getName(1982, 450, "M");
        System.out.println("Name with this rank is " + name);
    }
    
    public String whatIsNameInYear (String name, int year, int newYear, String gender) {
        int rankTY = getRank(year, name, gender);
        String newName = getName(newYear, rankTY, gender);
        return newName;
    }
    
    public void testwhatIsNameInYear() {
        String name = "Susan";
        int year = 1972;
        int newYear = 2014;
        String gender = "F";
        String newName = whatIsNameInYear(name, year, newYear, gender);
        System.out.println(name + " born in " + year + " would be " + newName + " if born in " + newYear);
    }
    
    public int yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int rank = Integer.MAX_VALUE;
        int yearWithHighestRank = -1;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            int currYr = Integer.parseInt(f.getName().substring(3, 7));
            int currRank = getRank(currYr, name, gender);
            if (currRank < rank && currRank != -1) {
                rank = currRank;
                yearWithHighestRank = currYr;
            }
        }
        return yearWithHighestRank;
    }
    
    public void testYearOfHighestRank() {
        int yearWithHighestRank = yearOfHighestRank("Mich", "M");
        System.out.println("Year with highest rank: " + yearWithHighestRank);
    }
    
    public double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int sum = 0;
        int cnt = 0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            int currYr = Integer.parseInt(f.getName().substring(3, 7));
            int currRank = getRank(currYr, name, gender);
            sum += currRank;
            cnt ++;
        }
        return (double)sum / cnt;
    }
    
    public void testgetAverageRank () {
        double avg = getAverageRank("Robert", "M");
        System.out.println("Average rank is: " + avg);
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int rank = getRank(year, name, gender);
        String filePath = "../us_babynames_by_year/yob"+ Integer.toString(year) + ".csv";
        FileResource fr = new FileResource(filePath);
        int total = 0;
        int curRank = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (!rec.get(1).equals(gender)) continue;
            curRank ++;
            if (curRank < rank) {
                total += Integer.parseInt(rec.get(2));
            }
        }
        return total;
    }
    
    public void testGetTotal() {
        int total = getTotalBirthsRankedHigher(1990, "Drew", "M");
        System.out.println("Total birth after rank is: " + total);
    }
}
