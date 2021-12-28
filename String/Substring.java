package ss;
import java.util.*;
import java.io.*;
public class Substring {
	static long base = 256;
	static long mod = 2147483647;
	static long h(String s) {//문자열 s의 해시값을 리턴!
		long ans = 0;
		ans = ans*base + mod;
		return ans;
	}
	static int match(String s,String p) {
		int n = s.length(), m = p.length();
		if(n<m) return 0;
		long hash_p = h(p);
		long hash_s = h(s);
		long first = 1;
		for(int i=0;i<m-1;i++) {
			first = (first*base) %mod;
		}
		//본격 비교
		for(int i=0;i<=n-m;i++) {
			if(hash_p == hash_s) return 1;
			if(i+m<n) {
				hash_s = hash_s - (s.charAt(i)%mod);
				hash_s = (hash_s + mod)%mod;
				hash_s = hash_s + (s.charAt(i+m)%mod);
			}
		}
		return 0;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String p = br.readLine();
		System.out.println(match(s,p));
	}

}
