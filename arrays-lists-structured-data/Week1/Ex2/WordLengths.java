
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts) {
        for(String s : resource.words()){
           //System.out.println(s);
           int len = s.length();
           if (!Character.isLetter(s.charAt(0))) {
                len--;
           }
           if (len > 0 && !Character.isLetter(s.charAt(len-1))) {
                len--;
           }
           if (len <= 0) {
               continue;
           }
           if (len >= counts.length){
               counts[counts.length-1] ++;
           }
           else {
               counts[len] ++;
           }
        }
    
    for (int i=0; i < counts.length; i++){
        if (counts[i] > 0) {
            System.out.println(i + " has count: " + counts[i]);
        }
    }
    }
    
    public void testCountWordLengths() {
        int len = 31;
        int[] counts = new int[31];
        FileResource resource = new FileResource();
        countWordLengths(resource, counts);
        int maxIndex = indexOfMax(counts);
        System.out.println("max index is: " + maxIndex);
    }
    
    public int indexOfMax(int[] values) {
        int max = 0;
        int index = 0;
        for (int i=0; i<values.length; i++){
            if (values[i] > max) {
                max = values[i];
                index = i;
            }
        }
        return index;
    }
    
}
