import java.util.*;
import edu.duke.*;
import java.io.File;

public class VigenereBreaker2 {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        String res = "";
        for (int i=whichSlice; i < message.length(); i+=totalSlices){
            res += message.charAt(i);
        }
        return res;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for (int k=0; k<klength; k++){
            String s = sliceString(encrypted, k, klength);
            CaesarCracker cc = new CaesarCracker(mostCommon);
            key[k] = cc.getKey(s);
        }
        return key;
    }

    public void breakVigenere () {
        FileResource fr = new FileResource();
        String msg = fr.asString();
        
        DirectoryResource dr = new DirectoryResource();
        
        HashMap<String, HashSet<String>> dictionaryMap = new HashMap<>();
        
        for (File f : dr.selectedFiles()) {
            FileResource frd = new FileResource(f);
            HashSet<String> dictionary = readDictionary(frd);
            dictionaryMap.put(f.getName(), dictionary);
        }

        breakForAllLangs(msg, dictionaryMap);
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> dict = new HashSet<String>();
        for (String line : fr.lines()){
            line = line.toLowerCase();
            dict.add(line);
        }
        return dict;
    }
    
    public int countWords(String message, HashSet<String> dict){
        String[] arr = message.split("\\W+");
        int count = 0;
        for (String word : arr){
            word = word.toLowerCase();
            if (dict.contains(word)) count++;
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dict){
        int maxCount = 0;
        String res = "";
        char mostCommon = mostCommonCharIn(dict);
        
        for (int i=1; i <= 100; i++){
            int[] keys = tryKeyLength(encrypted, i, mostCommon);
            VigenereCipher vc = new VigenereCipher(keys);
            String decrypted = vc.decrypt(encrypted);
            int tmp = countWords(decrypted, dict);
            if (tmp > maxCount){
                maxCount = tmp;
                res = decrypted;
            }
        }
        System.out.println("Number of valid words is " + maxCount);
        return res;
    }
    
      public char mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<Character, Integer> count = new HashMap<>();
        char mostCommonChar = 'a';
        int max = 0;
        for (String character : dictionary) {
            for (int i = 0; i < character.length(); i++) {
                if (count.containsKey(character.charAt(i))) {
                    count.put(character.charAt(i), count.get(character.charAt(i)) + 1);
                }
                else {
                    count.put(character.charAt(i), 1);
                }
            }

        }

        for (Character character : count.keySet()) {
            if (count.get(character) > max) {
                max = count.get(character);
                mostCommonChar = character;
            }
        }
        System.out.println("The most common char is " + mostCommonChar);
        return mostCommonChar;
    }

    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        for (String languageName : languages.keySet()) {
            System.out.println("Using the language " + languageName);
            String decrypted = breakForLanguage(encrypted, languages.get(languageName));
            System.out.println(decrypted);
        }
    }
    
    public void tester(){
        breakVigenere();
    }
}
