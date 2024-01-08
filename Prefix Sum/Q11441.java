import java.util.*;
import java.io.*;
//합 구하기
public class Q11441 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		int[] dp = new int[N+1];
		for(int i=1;i<=N;i++) {
			dp[i] = dp[i-1]+A[i];
		}
		
		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			sb.append(dp[b]-dp[a-1]).append("\n");
		}
		System.out.println(sb.toString());
	}

}
