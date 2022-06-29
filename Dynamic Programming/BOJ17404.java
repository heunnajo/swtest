import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

//RGB거리2
public class BOJ17404 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] dp = new int[N+1][3];
		int[][] p = new int[N+1][3];
		int maxVal = 10000000;
		int ans = maxVal;
		
		StringTokenizer st;
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			
			p[i][0] = Integer.parseInt(st.nextToken());
			p[i][1] = Integer.parseInt(st.nextToken());
			p[i][2] = Integer.parseInt(st.nextToken());
		}
		
		//bottom-up dp
		//0.첫번째 집 초기화!
		for(int k=0;k<3;k++) {
			for(int j=0;j<3;j++) {
				if(j==k) dp[1][j] = p[1][j];
				else dp[1][j] = maxVal;
			}
			
			for(int i=2;i<=N;i++) {
				dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2])+p[i][0];
				dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2])+p[i][1];
				dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1])+p[i][2];
			}
			
			for(int j=0;j<=2;j++) {
				if(j==k) continue;
				
				ans = Math.min(ans, dp[N][j]);
			}
		}
		System.out.println(ans);
	}

}

//		for(int i=1;i<=N;i++) {
//			for(int j=0;j<3;j++) {
//				System.out.print(p[i][j]+" ");
//			}
//			System.out.println();
//		}