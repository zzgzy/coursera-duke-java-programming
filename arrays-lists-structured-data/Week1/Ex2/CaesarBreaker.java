
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarBreaker{

    public int[] countLetters(String message) {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int k = 0; k < message.length(); k++) {
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alph.indexOf(ch);
            if (dex != -1) {
                counts[dex] += 1;
            }
        }
        return counts;
    }
    
    public int maxIndex(int[] values) {
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

    public String halfOfString(String message, int start) {
        String answer = "";
        for (int i = start; i < message.length(); i += 2) {
            answer = answer + message.charAt(i);
        }
        return answer;
    }

    public void testHalfString() {
        String ans = halfOfString("Qbkm Zgis", 1);
        System.out.println("half of string is: "+ans);
    }
    public int getKey(String s) {
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return dkey;
    }

    public String decrypt(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        String messageF = halfOfString(encrypted, 0);
        String messageS = halfOfString(encrypted, 1);
        int key1 = getKey(messageF);
        int key2 = getKey(messageS);
        //int key1 = 14;
        //int key2 = 24;
        System.out.println(key1 + " " + key2);
        return cc.encryptTwoKeys(encrypted, 26 - key1, 26 - key2);
    }

    public void testDecrypt() {
        FileResource resource = new FileResource();
        String contents = resource.asString();
        System.out.println(decrypt(contents));
        //System.out.println(decrypt("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!"));
    }
}