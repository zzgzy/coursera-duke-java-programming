
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.ArrayList;

public class CharactersInPlay {
    
    private ArrayList<String> names = new ArrayList<String>();
    private ArrayList<Integer> counts = new ArrayList<Integer>();
    
    public void update(String person){
        int idx = names.indexOf(person);
        if ( idx == -1){
            names.add(person);
            counts.add(1);
        }
        else {
            int value = counts.get(idx);
            counts.set(idx, value+1);
        }
    }
    
    public void findAllChars() {
        FileResource fr = new FileResource();
        for (String line : fr.lines()){
            int start = 0;
            if (line.length() == 0) continue;
            while (start < line.length() && line.charAt(start) == ' ') {
                start ++;
            }
            if (start == line.length()) continue;
            
            int end = start;
            while (end < line.length() && line.charAt(end) != '.'){
                end ++;
            }
            if (end == line.length() && line.charAt(end-1) == '.'){
                end = end-1;
            }
            else if (end == line.length()) {
                continue;
            }
            String person = line.substring(start, end+1);
            update(person);
        }
        
    }
    
    public void tester(){
       names.clear();
       counts.clear();
       findAllChars();
       int limit = 3;
       for (int k=0; k<names.size(); k++){
           if (counts.get(k) > limit){
               System.out.println("Name is " + names.get(k) + " count is " + counts.get(k));
           }
       }
       System.out.println("Start testing charactersWithNumParts-------");
       charactersWithNumParts(10,15);
    }
    
    public void charactersWithNumParts(int num1, int num2) {
        for (int k=0; k<names.size(); k++){
           if (counts.get(k) >= num1 && counts.get(k) <= num2){
               System.out.println("Name is " + names.get(k) + " count is " + counts.get(k));
           }
       }
    }
}
