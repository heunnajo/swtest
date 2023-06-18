package barkingAlgo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Strfry
public class BOJ11328 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		
		String a = ""; String b = "";
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		while(tc-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			a = st.nextToken();
			b = st.nextToken();
			
			if(isStrfry(a,b)) {sb.append("Possible");}
			else {sb.append("Impossible");}
			
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	static boolean isStrfry(String a,String b) {
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
			if(cnt[i] != 0) return false;
		}
		return true;
	}
}
