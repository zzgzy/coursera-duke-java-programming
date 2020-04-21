
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    public boolean isVowel (char ch) {
        char chLower = Character.toLowerCase(ch);
        if (chLower == 'a' || chLower == 'e' || 
            chLower == 'i' || chLower == 'o' || chLower == 'u') {
                return true;
        }
        return false;
    }
    
    public void testIsVowel(){
        char ch = 'A';
        boolean ans = isVowel(ch);
        System.out.println(ch+" is vowel? "+ans);
    }
    
    public String replaceVowels (String phrase, char ch) {
        StringBuilder sb = new StringBuilder(phrase);
        for (int i=0; i < sb.length(); i++) {
            char currChar = sb.charAt(i);
            if (isVowel(currChar)) {
                sb.setCharAt(i, ch);
            }
        }
        return sb.toString();
    }
    
    public void testReplaceVowel() {
        String ans = replaceVowels("Hello World", '*');
        System.out.println("new string is: "+ans);
    }
    
    public String emphasize (String phrase, char ch) {
        StringBuilder sb = new StringBuilder(phrase);
        for (int i=0; i < sb.length(); i++) {
            char currChar = Character.toLowerCase(sb.charAt(i));
            if (currChar == Character.toLowerCase(ch)) {
                if (i%2==0) {
                    sb.setCharAt(i, '*');
                } else {
                    sb.setCharAt(i, '+');
                }
                
            }
        }
        return sb.toString();
    }
    
    public void testEmphasize() {
        String phrase = "Mary Bella Abracadabra";
        char ch = 'a';
        String ans = emphasize(phrase, ch);
        System.out.println("New string is: " + ans);
    }
}
