import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//진우의 달 여행(Large)
public class BOJ17485 {
	static int N,M,Ans;//N행 M열
	static int[][] Map;
	static int[][][] DP;
	static int INF;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Map = new int[N][M];
		DP = new int[N][M][3];
		INF = Integer.MAX_VALUE;
		Ans = INF;
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				Map[i][j] = Integer.parseInt(st.nextToken());
				if(i==0) DP[i][j][0] = DP[i][j][1] = DP[i][j][2] = Map[i][j];
				
				if(j == 0) DP[i][j][2] = INF;
				if(j == M-1) DP[i][j][0] = INF;
			}
		}
		solve();
		System.out.println(Ans);
	}
	static void solve() {
		for(int i=1;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(j+1<M) DP[i][j][0] = Math.min(DP[i-1][j+1][1], DP[i-1][j+1][2])+Map[i][j];
				DP[i][j][1] = Math.min(DP[i-1][j][0], DP[i-1][j][2])+Map[i][j];
				if(j-1>=0) DP[i][j][2] = Math.min(DP[i-1][j-1][0], DP[i-1][j-1][1])+Map[i][j];
				
			}
		}
		//최종 정답 도출
		for(int j=0;j<M;j++) {
//			Ans = DP[N-1][j][0];
//			if(Ans > DP[N-1][j][1]) Ans = DP[N-1][j][1];
//			if(Ans > DP[N-1][j][2]) Ans = DP[N-1][j][2];
			for (int k = 0; k < 3; k++) {
				if (Ans > DP[N-1][j][k])
					Ans = DP[N-1][j][k];
			}
		}
	}
}
