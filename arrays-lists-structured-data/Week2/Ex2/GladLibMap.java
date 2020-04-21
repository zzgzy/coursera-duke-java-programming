
/**
 * Write a description of GladLibMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String,ArrayList<String>> map;
    String[] catArr;
    
    private ArrayList<String> usedList;
    private ArrayList<String> usedCat;
    private int count = 0;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        map = new HashMap<String,ArrayList<String>>();
        usedCat = new ArrayList<String>();
        catArr = new String[] {"adjective", "noun", "color", 
                                "country", "name", "animal", 
                                "timeframe", "verb", "fruit"};
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        ArrayList<String> arrList;
        for (String cat :  catArr){
            arrList = readIt(source+"/"+cat+".txt");
            map.put(cat, arrList);
        }
        usedList = new ArrayList<String>();
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        else if (map.containsKey(label)){
            return randomFrom(map.get(label));  
        }
        else {
            return "**UNKNOWN**";
        }
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        count ++;
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String cat = w.substring(first+1,last);
        if (!usedCat.contains(cat)){
            usedCat.add(cat);
        }
        String sub = getSubstitute(cat);
        while (true) {
            if (!usedList.contains(sub)){
                usedList.add(sub);
                break;
            }
            sub = getSubstitute(cat);
        }
     
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public void makeStory(){
        usedList.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("Number of words replaced: " + count);
        int total = totalWordsInMap();
        System.out.println("Total number of words: " + total);
        int totalConsidered = totalWordsConsidered();
        System.out.println("Total words considered: " + totalConsidered);
    }
    
    public int totalWordsInMap(){
       int total = 0;
       for (String cat :  map.keySet()){
           total += map.get(cat).size();
       }
       return total;
    }
    
    public int totalWordsConsidered(){
        int totalConsidered = 0;
        for (String word : usedCat){
            if (word.equals("number")) continue;
            totalConsidered += map.get(word).size();
        }
        return totalConsidered;
    }
}
