
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class CodonCount {
    private HashMap<String,Integer> map;
    
    public CodonCount() {
        map = new HashMap<String,Integer>();
    }
    
    public void buildCodonMap(int start, String dna){
        map.clear();
        for(int i=start; i < dna.length(); i+=3){
            if (i+3 >= dna.length()) continue;
            String codon = dna.substring(i, i+3).toUpperCase();
            if (!map.containsKey(codon)){
        	map.put(codon,1);
            }
            else {
                map.put(codon, map.get(codon)+1);
            }
        }
    }
    
    public String getMostCommonCodon(){
        int largestCnt = 0;
        String codonLargest = "";
        for (String codon : map.keySet()){
            int cnt = map.get(codon);
            if (cnt > largestCnt){
                largestCnt = cnt;
                codonLargest = codon;
            }
        }
        return codonLargest;
    }
    
    public void printCodonCounts(int start, int end){
        for (String codon : map.keySet()){
            int cnt = map.get(codon);
            if (cnt <= end && cnt >= start){
                System.out.println(codon+"\t"+cnt);
            }
        }
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        String dna = fr.asString().trim();
        for (int start=0; start < 3; start++){
            buildCodonMap(start, dna);
            System.out.println("Reading frame starting with "+start+" results in "+map.size()+" unique codons");
            String codonLargest = getMostCommonCodon();
            System.out.println("and most common codon is "+codonLargest+" with count "+map.get(codonLargest));
            int begin = 1;
            int end = 9;
            System.out.println("Counts of codons between "+begin+" and "+end+" inclusive are:");
            printCodonCounts(begin, end);
        }
    }
}
