
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.*;

public class WordsInFiles {
    private HashMap<String,ArrayList<String>> map;
    
    public WordsInFiles(){
        map = new HashMap<String,ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        String fname = f.getName();
        for (String word : fr.words()){
            if (map.containsKey(word)){
                if (!map.get(word).contains(fname)){
                    map.get(word).add(fname);
                }
            }
            else {
                ArrayList<String> fList = new ArrayList<String>();
                fList.add(fname);
                map.put(word, fList);
            }
        }
    }
    
    public void buildWordFileMap(){
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber(){
        int max = 0;
        for (String word : map.keySet()){
            if (map.get(word).size() > max){
                max = map.get(word).size();
            }
        }
        return max;
    }
    
    public void printFilesIn(String word){
        ArrayList<String> arr = map.get(word);
        for (String fname : arr){
            System.out.println("word exists in file: " + fname);
        }
    }
    
    public void tester(){
        buildWordFileMap();
        int max = maxNumber();
        int times = 4;
        int total = 0;
        for (String word : map.keySet()){
            if (map.get(word).size() == times){
                total ++;
                //System.out.println(word+" appears in the files: "+map.get(word));
            }
        }
        System.out.println(total+" words appear in "+times+" times.");
        String wordCheck = "tree";
        System.out.println(wordCheck+" appears in "+map.get(wordCheck));
    }
}
