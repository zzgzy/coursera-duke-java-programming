import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
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
        
        FileResource frDict = new FileResource();
        HashSet<String> dict = readDictionary(frDict);

        String decryted = breakForLanguage(msg, dict);
        System.out.println("Valid words in decrypted String: " + countWords(decryted,dict));
        System.out.println("Decryted msg is: "+decryted.substring(0, 100));
        
        int[] keys = tryKeyLength(msg,38,'e');
        VigenereCipher vc = new VigenereCipher(keys);
        String deWithKey = vc.decrypt(msg);
        System.out.println("Valid words are in the \"decrypted\" is " + countWords(deWithKey,dict));
        
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
        int keyLength = 0;
        for (int i=1; i <= 100; i++){
            int[] keys = tryKeyLength(encrypted, i, 'e');
            VigenereCipher vc = new VigenereCipher(keys);
            String decrypted = vc.decrypt(encrypted);
            int tmp = countWords(decrypted, dict);
            if (tmp > maxCount){
                maxCount = tmp;
                keyLength = i;
            }
        }
        System.out.println("the total number of words is " + maxCount);
        int[] keys = tryKeyLength(encrypted, keyLength, 'e');
        System.out.println("the key length is " + keys.length);
        VigenereCipher vc = new VigenereCipher(keys);
        String message = vc.decrypt(encrypted);
        return message;
    }
    
    public void tester(){
        breakVigenere();
    }
}
