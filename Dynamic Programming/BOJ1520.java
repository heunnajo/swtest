//내리막 길
package ss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1520 {
	static int m,n,ans,map[][],dp[][];
	static class Pair{
		int x,y;
		
		Pair(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		map = new int[m][n];
		dp = new int[m][n];
		
		for(int i=0;i<m;i++) Arrays.fill(dp[i], -1);
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(dfs(0,0));
	}
	static int dfs(int x,int y) {
		if(x == m-1 && y == n-1) return 1;
		
		if(dp[x][y] != -1) {
			return dp[x][y];
		}
		dp[x][y] = 0;
		for(int d=0;d<4;d++) {
			int nx = x+dx[d], ny = y+dy[d];
			
			if(isOut(nx,ny) || map[nx][ny] >= map[x][y]) continue;
			dp[x][y] += dfs(nx,ny);
		}
		
		return dp[x][y];
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>=m || y<0 || y>=n;
	}
}

//		for(int i=0;i<m;i++) {
//			for(int j=0;j<n;j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}