package ss;
//파일 합치기
import java.io.*;
import java.util.*;
public class BOJ11066 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int k;
		while(T-- >0) {
			k = Integer.parseInt(br.readLine());
			
			int[] fileSize = new int[k+1];
			int[] sum = new int[k+1];
			int[][] dp = new int[k+1][k+1];
			
			st = new StringTokenizer(br.readLine());
			for(int i=1;i<=k;i++) {
				fileSize[i] = Integer.parseInt(st.nextToken());
				sum[i] = sum[i-1] + fileSize[i];
			}
			
			//bottom-up dp
			for(int n=1;n<=k;n++) {
				for(int from=1;from+n<=k;from++) {
					int to = from+n;
					dp[from][to] = Integer.MAX_VALUE;
					for(int mid=from;mid<to;mid++) {
						dp[from][to] = Math.min(dp[from][to], dp[from][mid]+dp[mid+1][to]+sum[to]-sum[from-1]);
					}
				}
			}
			sb.append(dp[1][k]).append("\n");
		}
		
		System.out.print(sb);
	}

}

//			for(int i=1;i<=k;i++) {
//				System.out.println(sum[i]);
//			}