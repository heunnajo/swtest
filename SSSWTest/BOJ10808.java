package barkingAlgo;
import java.util.*;
import java.io.*;

//알파벳 개수
public class BOJ10808 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] cnt = new int[26];
		String s = br.readLine();
		int len = s.length();
		
		for(int i=0;i<len;i++) {
			cnt[s.charAt(i)-'a']++;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<26;i++) {
			sb.append(cnt[i]).append(" ");
		}
		
		System.out.print(sb.toString());
	}

}
