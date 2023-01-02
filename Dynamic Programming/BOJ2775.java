package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//부녀회장이 될테야
public class BOJ2775 {

	public static void main(String... args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int t = Integer.parseInt(br.readLine());
        int k = 0, n = 0;
        int[][] dp;
        StringBuilder sb = new StringBuilder();
        
        while(t-- >0) {
        	k = Integer.parseInt(br.readLine());
        	n = Integer.parseInt(br.readLine());
        	
        	dp = new int[k+1][n+1];
        	for(int i=1;i<=n;i++) {
        		dp[0][i] = i;
        	}
        	
        	for(int i=1;i<=k;i++) {
        		for(int j=1;j<=n;j++) {
        			for(int idx=1;idx<=j;idx++) {
        				dp[i][j] += dp[i-1][idx];
        			}
        		}
        	}
        	//print(dp);
        	sb.append(dp[k][n]).append("\n");
        }
		System.out.print(sb.toString());
	}
//	static void print(int[][] dp) {
//		int k = dp.length;
//		int n = dp[0].length;
//		
//		for(int i=0;i<k;i++) {
//			for(int j=1;j<=n;j++) {
//				
//			}
//		}
//	}

}
