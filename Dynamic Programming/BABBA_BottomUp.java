//BOJ #9625 BABBA, Bottom-up : 164ms 
import java.io.*;
import java.util.*;
public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[][] dp = new int[n+1][2];
		
		dp[1][0] = 0; dp[1][1] = 1;
		
		for(int i=2;i<=n;i++) {
			dp[i][0] = dp[i-1][1];
			dp[i][1] = dp[i-1][0]+dp[i-1][1];
		}
		System.out.println(dp[n][0] + " " + dp[n][1]);
//		for(int i=1;i<=n;i++) {
//			System.out.println(dp[i][0]+" "+dp[i][1]);
//		}
	}

}