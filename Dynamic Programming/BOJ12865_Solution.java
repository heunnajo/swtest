package ss;

import java.io.*;
import java.util.*;;

//평범한 배낭
public class BOJ12865_Solution {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[][] items = new int[n+1][2];
		int[][] dp = new int[n+1][k+1];
		
		for(int i=1;i<=n;i++) {
			st = new StringTokenizer(br.readLine());
			items[i][0] = Integer.parseInt(st.nextToken());
			items[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1;i<=n;i++) {
			for(int j=0;j<=k;j++) {
				dp[i][j] = dp[i-1][j];
				if(j-items[i][0]<0) continue;
				dp[i][j] = Math.max(dp[i][j], dp[i-1][j-items[i][0]]+items[i][1]);
			}
		}

		System.out.println(dp[n][k]);
	}

}

//		for(int i=1;i<=n;i++) {
//			System.out.print(items[i][0]+" "+items[i][1]);
//			System.out.println();
//			
//		}