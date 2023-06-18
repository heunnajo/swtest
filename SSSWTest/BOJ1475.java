package barkingAlgo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
//방 번호
public class BOJ1475 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		int[] cnt = new int[10];
		for(int i=0;i<input.length();i++) {
			cnt[input.charAt(i)-'0']++;
		}
		
		//6,9에 대한 처리
		int max = -1;
		for(int i=0;i<10;i++) {
			if(i == 6 || i==9) continue;
			if(max < cnt[i]) max = cnt[i];
		}
		
		int a = max;
		int sum = cnt[6] + cnt[9];
		int b = sum%2 == 0 ? sum/2 : (sum/2)+1;
		int ans = Math.max(a, b);
		
		System.out.println(ans);
		
	}

}
