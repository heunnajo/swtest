// BOJ #2671 잠수함 식별
import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.regex.Pattern;

public class CheckSubmarine {
    
	public static void main (String[] args) throws java.lang.Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String str = br.readLine().trim();
	    String ptn = "(100+1+|01)+";
	    System.out.println(str.matches(ptn) ? "SUBMARINE": "NOISE");
	}
}