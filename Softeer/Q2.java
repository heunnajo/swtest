//성적 평균
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] Scores = new int[N+1];
		int[] dp = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=1;i<=N;i++) {
			Scores[i] = Integer.parseInt(st.nextToken());
		}
		
		//bottom-up dp 수행 : O(N)
		for(int i=1;i<=N;i++) {
			dp[i] = dp[i-1] + Scores[i];
		}
		
		StringBuilder sb = new StringBuilder();
		while(K-- >0) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			//평균 = 구간의 성적 합 sum / 구간의 학생 수 cnt
			int cnt = B-A+1;
			int sum = dp[B] - dp[A-1];
			double ans = (double)sum/cnt;
			
			ans = (double)Math.round(ans*100)/100.0;//12.345=>1234.5=>1235.0=>12.35
			String str = String.format("%.2f",ans);
			sb.append(str).append("\n");
			
 		}
		System.out.print(sb);
	}

}