import java.util.*;
import java.io.*;
//타일 채우기
public class BOJ2718_Solution {
	static int N;
	static int[][] dp;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		
		while(TC-- >0) {
			N = Integer.parseInt(br.readLine());
			solve();
		}
		System.out.print(sb.toString());
	}
	
	static void solve() {
		dp = new int[N+2][5];
		
		dp[1][0] = 1;
		for(int i=2;i<N+2;i++) {
			dp[i][0] = dp[i-2][0] + dp[i-1][0] + dp[i-1][1] + dp[i-1][4] + dp[i-1][3];//빵꾸X
			dp[i][1] = dp[i-1][0] + dp[i-1][4];//위의 두개 빵꾸
			dp[i][2] = dp[i-1][3];//맨위랑 맨 아래가 빵꾸
			dp[i][3] = dp[i-1][0] + dp[i-1][2];//중간 두개가 빵꾸
			dp[i][4] = dp[i-1][0] + dp[i-1][1];//밑에 두개가 빵꾸
		}
		
		sb.append(dp[N+1][0]).append("\n");
	}

}