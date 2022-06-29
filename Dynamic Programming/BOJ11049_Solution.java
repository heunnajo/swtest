import java.io.*;
import java.util.*;
//행렬 곱셈 순서
public class BOJ11049_Solution {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[N+1][2];
		int[][] dp = new int[N+1][N+1];
		
		int INF = Integer.MAX_VALUE;
		
		StringTokenizer st;
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for(int n=1;n<N;n++) {
			for(int i=1;i+n<=N;i++) {
				dp[i][i+n] = INF;
				for(int j=i;j<=i+n;j++) {
					if(j+1>N) continue;
					dp[i][i+n] = Math.min(dp[i][i+n], dp[i][j]+dp[j+1][i+n]+arr[i][0]*arr[j][1]*arr[i+n][1]);
				}
			}
		}
		System.out.println(dp[1][N]);
	}

}