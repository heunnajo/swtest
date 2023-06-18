package barkingAlgo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//애너그램
public class BOJ1919 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String a = br.readLine();
		String b = br.readLine();
			
		System.out.print(makeAnagram(a,b));
			
	}
	static int makeAnagram(String a,String b) {
		int sum = 0;
		int lenA = a.length(); int lenB = b.length();
		int[] cnt = new int[26];
		
		for(int i=0;i<lenA;i++) {
			cnt[a.charAt(i)-'a']++;
		}
//		System.out.println("========================");
//		for(int i=0;i<26;i++) {
//			System.out.print(cnt[i]+" ");
//		}
//		System.out.println();
		
		for(int i=0;i<lenB;i++) {
			cnt[b.charAt(i)-'a']--;
		}
//		for(int i=0;i<26;i++) {
//			System.out.print(cnt[i]+" ");
//		}
//		System.out.println();
//		System.out.println("========================");
		
		for(int i=0;i<26;i++) {
			if(cnt[i] != 0) sum += Math.abs(cnt[i]);
		}
		return sum;
	}
}
