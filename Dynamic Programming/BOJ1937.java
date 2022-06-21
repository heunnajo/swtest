package ss;
//욕심쟁이 판다
import java.util.*;
import java.io.*;
public class BOJ1937 {
	static int n;
	static int[][] map,dp;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		map = new int[n][n];
		dp = new int[n][n];
		for(int i=0;i<n;i++) Arrays.fill(dp[i], -1);
		
		StringTokenizer st;
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				dp[i][j] = dfs(i,j);
			}
		}
		int max = -1;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(max <dp[i][j]) max = dp[i][j];
			}
		}
		System.out.println(max);
	}
	static int dfs(int x,int y) {
		if(dp[x][y] != -1) return dp[x][y];
		
		dp[x][y] = 1;//0이 아니라 1
		for(int d=0;d<4;d++) {
			int nx = x+dx[d];
			int ny = y+dy[d];
			
			if(isOut(nx,ny) || map[nx][ny] <= map[x][y]) continue;
			//dp[x][y] = dfs(nx,ny)+1;//여기서 최댓값을 도출해야함.이미 값이 채워져있다고 하더라도 최댓값을 보장할 수 없기 때문.
			dp[x][y] = Math.max(dp[x][y], dfs(nx,ny)+1);
		}
		return dp[x][y];
	}
	static boolean isOut(int x,int y) {
		return x<0|| x>=n || y<0 || y>=n;
	}
}

//		for(int i=0;i<n;i++) {
//			for(int j=0;j<n;j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}