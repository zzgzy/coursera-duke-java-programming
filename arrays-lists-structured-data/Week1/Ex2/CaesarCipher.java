
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarCipher {
    public String encrypt (String input, int key, int start, int step) {
        StringBuilder encrypted = new StringBuilder(input);
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String shiftedUpper = upper.substring(key)+upper.substring(0,key);
        String shiftedLower = lower.substring(key)+lower.substring(0,key);
        for(int i = start; i < encrypted.length(); i=i+step) {
            char currChar = encrypted.charAt(i);
            int idxUpper = upper.indexOf(currChar);
            int idxLower = lower.indexOf(currChar);
            if(idxUpper != -1){
                char newChar = shiftedUpper.charAt(idxUpper);
                encrypted.setCharAt(i, newChar);
            }
            else if (idxLower != -1) {
                char newChar = shiftedLower.charAt(idxLower);
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }
    
    public void testCaesar() {
        int key = 15;
        //FileResource fr = new FileResource();
        //String msg = fr.asString();
        String msg = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        String encrypted = encrypt(msg, key, 0, 1);
        System.out.println("key is " + key + "\n" + "encrypted: " + encrypted);
        String decrypted = encrypt(encrypted, 26-key, 0, 1);
        System.out.println(decrypted);
    }
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        String temp = "";
        temp = encrypt(input, key1, 0, 2);
        String out = "";
        out = encrypt(temp, key2, 1, 2);
        return out;
    }
    
    public void testEncryptTwoKey() {
        String input = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        int key1 = 21;
        int key2 = 8;
        String ans = encryptTwoKeys(input, key1, key2);
        System.out.println("Output string with two keys: "+ans);
    }
}
