import java.util.*;
import java.io.*;
//소수&팰린드롬
public class BOJ1747 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		int N = Integer.parseInt(input);
		if(N == 1) {
			System.out.println(2);
            System.exit(0);
		}
		for(int i=N;;i++) {
			if(isPrime(i) && isPalindrome(i+"")) {
				System.out.println(i);
				return;
			}
		}
	}
	static boolean isPrime(int x) {
		for(int i=2;i*i <= x;i++) {
			if(x % i == 0) return false;
		}
		return true;
	}
	
	static boolean isPalindrome(String x) {
		int len =  x.length();
		for(int i=0;i<len/2;i++) {
			if(x.charAt(i) != x.charAt(len-1-i)) return false;
		}
		return true;
	}

}
