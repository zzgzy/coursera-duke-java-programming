
/**
 * Write a description of UniqueIPTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UniqueIPTester {
    public void testUniqIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("There are "+uniqueIPs+" IPs");
    }
}
