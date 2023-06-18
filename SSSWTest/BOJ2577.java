package barkingAlgo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//숫자의 개수
public class BOJ2577 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int a = Integer.parseInt(br.readLine());
		int b = Integer.parseInt(br.readLine());
		int c = Integer.parseInt(br.readLine());
		int ans =  a*b*c;
		
		String answer = ans + "";
		int[] cnt = new int[10];
		for(int i=0;i<answer.length();i++) {
			cnt[answer.charAt(i)-'0']++;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<10;i++) {
			sb.append(cnt[i]).append("\n");
		}
		
		System.out.print(sb.toString());
	}

}
