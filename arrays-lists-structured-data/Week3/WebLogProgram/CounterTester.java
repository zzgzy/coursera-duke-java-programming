
/**
 * Write a description of CounterTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class CounterTester {
    public void testCounts(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        //System.out.println(counts);
        int max = la.mostNumberVisitsByIP(counts);
        System.out.println("most number visits per IP is: "+max);
        
        ArrayList<String> arr = la.iPsMostVisits(counts);
        System.out.println("IP has max visits: " + arr);
        
        HashMap<String, ArrayList<String>> arrDay = la.iPsForDays();
        System.out.println("IP for days: " + arrDay);
        
        String day = la.dayWithMostIPVisits(arrDay);
        System.out.println("day most visited is: " + day);
        
        ArrayList<String> arrList = la.iPsWithMostVisitsOnDay(arrDay, "Sep 30");
        System.out.println("IPs had most accesses on a given day: "+arrList);
    }
}
