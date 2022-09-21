package ss;

import java.io.*;
import java.util.*;
//팰린드롬?
public class BOJ10942 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=n;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		//dp
		//1.점화식 정의
		boolean[][] dp = new boolean[n+1][n+1];
		
		//2.초기값 셋팅
		for(int i=1;i<=n;i++) {
			dp[i][i] = true;
		}
		
		for(int i=1;i<=n-1;i++) {
			if(arr[i] == arr[i+1]) dp[i][i+1] = true;
		}
		
		//3.점화식 완성
		for(int i=n-1;i>=1;i--) {
			for(int j=i+2;j<=n;j++) {
				dp[i][j] = (arr[i] == arr[j]) && dp[i+1][j-1];
			}
		}
		
		int m = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			sb.append(dp[s][e] ? 1:0).append("\n");
		}
		
		System.out.print(sb.toString());
		
	}

}
