
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // complete method
        FileResource fr = new FileResource(filename);
        for (String line : fr.lines()){
             LogEntry le = WebLogParser.parseEntry(line);
             records.add(le);
        }
     }
     
     public HashMap<String,Integer> countVisitsPerIP() {
        HashMap<String,Integer> counts = new HashMap<String,Integer>();
        for (LogEntry le : records){
             String ip = le.getIpAddress();
             if (!counts.containsKey(ip)){
                 counts.put(ip,1);
            } 
            else {
                counts.put(ip,counts.get(ip)+1);
            }
        }
        return counts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> counts){
         int max = 0;
         for (String ip : counts.keySet()){
             if (counts.get(ip) > max){
                 max = counts.get(ip);
            }
         }
         return max;
     }
     
    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts){
         int max = mostNumberVisitsByIP(counts);
         ArrayList<String> arr = new ArrayList<String>();
         for (String ip : counts.keySet()){
            if (counts.get(ip) == max){
                arr.add(ip);
            }
         }
         return arr;
    }
    
    public HashMap<String, ArrayList<String>> iPsForDays(){
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        for (LogEntry le : records){
            String day = le.getAccessTime().toString().substring(4, 10);
            if (!map.containsKey(day)){
                ArrayList<String> arr = new ArrayList<String>();
                arr.add(le.getIpAddress());
                map.put(day, arr);
            }
            else{
                map.get(day).add(le.getIpAddress());
            }
        }
        return map;
    }
    
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> ipDays){
        String mostD = "";
         int mostS = 0;
         for( String current: ipDays.keySet()){
             int size = ipDays.get(current).size();
             if (size > mostS){
                 mostS = size;
                 mostD = current;
                }
            }
         return mostD;
    }
    
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> map, String day){
		ArrayList<String> mostVisitIp = new ArrayList<String>();
		for(String date: map.keySet()){
			if(date.equals(day)){
				HashMap<String, Integer> counts = new HashMap<String, Integer>();
				for(String sr : map.get(date)){
					if(!counts.containsKey(sr)){
						counts.put(sr,1);
					}
					else{
						counts.put(sr, counts.get(sr) + 1);
					}
				}
				return iPsMostVisits(counts);
			}
			else{
				continue;
			}
		}
		return null;
	}
     
     
     public int countUniqueIPs(){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records){
             String ipAdd = le.getIpAddress();
             if (!uniqueIPs.contains(ipAdd)){
                 uniqueIPs.add(ipAdd);
             }
         }
         return uniqueIPs.size();
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public void printAllHigherThanNum(int num){
         ArrayList<Integer> uniqueCode = new ArrayList<Integer>();
         for (LogEntry le : records){
             int statusCode = le.getStatusCode();
             if (!uniqueCode.contains(statusCode) && statusCode > num){
                 uniqueCode.add(statusCode);
                 System.out.println(statusCode);
             }
         }
     }
     
     public int uniqueIPVisitsOnDay(String someday){
         ArrayList<String> IPOnDay = new ArrayList<String>();
         for (LogEntry le : records){
             String str = le.getAccessTime().toString();
             if (str.substring(4, 10).equals(someday) && !IPOnDay.contains(le.getIpAddress())){
                 IPOnDay.add(le.getIpAddress());
             }
         }
         return IPOnDay.size();
     }
     
     public int countUniqueIPsInRange(int low, int high){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records){
             String ipAdd = le.getIpAddress();
             int statusCode = le.getStatusCode();
             if (!uniqueIPs.contains(ipAdd) && statusCode>=low && statusCode<=high){
                 uniqueIPs.add(ipAdd);
             }
         }
         return uniqueIPs.size();
     }
}
