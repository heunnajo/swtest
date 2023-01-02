package boj;

import java.io.*;
import java.util.*;

//RGB 거리
public class BOJ1149 {

	public static void main(String... args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[][] cost = new int[N+1][3];
        int[][] dp = new int[N+1][3];
        StringTokenizer st;
        
        for(int i=1;i<=N;i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0;j<3;j++) {
        		cost[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        dp[1][0] = cost[1][0];
        dp[1][1] = cost[1][1];
        dp[1][2] = cost[1][2];
        
        for(int i=2;i<=N;i++) {
        	dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + cost[i][0];
        	dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + cost[i][1];
        	dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + cost[i][2];
        }
        
        int ans = 0;//N최대 1000, 비용 최대 1000
        ans = Math.min(Math.min(dp[N][0], dp[N][1]), dp[N][2]);
        
        System.out.println(ans);
	}

}

//        for(int i=1;i<=N;i++) {
//        	for(int j=0;j<3;j++) {
//        		System.out.print(cost[i][j]+" ");
//        	}
//        	System.out.println();
//        }