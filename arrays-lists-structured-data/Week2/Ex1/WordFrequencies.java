
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.ArrayList;

public class WordFrequencies {
    
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource fs = new FileResource();
        for (String s : fs.words()) {
            s = s.toLowerCase();
            int idx = myWords.indexOf(s);
            if (idx != -1) {
                int freq = myFreqs.get(idx);
                myFreqs.set(idx, freq + 1);
            }
            else {
                myWords.add(s);
                myFreqs.add(1);
            }
        }
    }
    
    public void tester(){
        findUnique();
        System.out.println("# unique words: "+myWords.size());
        for (int k=0; k<myWords.size(); k++){
            //System.out.println("Word " + myWords.get(k) + " has freq " + myFreqs.get(k));
        }
        int maxIdx = findIndexOfMax();
        System.out.println("Word " + myWords.get(maxIdx) + 
                           " occurs most often for " + myFreqs.get(maxIdx) + " times.");
    }
    
    public int findIndexOfMax(){
        int maxIdx = 0;
        int maxCnt = 0;
        for (int k=0; k<myFreqs.size(); k++){
            if (myFreqs.get(k) > maxCnt){
                maxIdx = k;
                maxCnt = myFreqs.get(k);
            }
        }
        return maxIdx;
    }
}
