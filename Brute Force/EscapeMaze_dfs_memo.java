package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;
public class EscapeMaze_dfs_memo {
	static int ans,N,M,dp[][];//N:행,M:열 
	static char[][] Map;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static boolean[][] visited;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dp = new int[N][M];//1:가능 -1:불가능
		
		Map = new char[N][M];
		visited = new boolean[N][M];
		
		for(int i=0;i<N;i++) {
			Map[i] = br.readLine().toCharArray();
		}
		ans = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(dfs(i,j)) ans++;
			}
		}
		System.out.println(ans);
	}
	static boolean dfs(int x,int y) {
		boolean result = false;
		if(isOut(x,y)) return true;
		if(dp[x][y]==1) return true;
		else if(dp[x][y] ==-1) return false;
		
		if(visited[x][y]) return false;
		visited[x][y] = true;
		
		if(Map[x][y]=='U') {
			result = dfs(x-1,y);
		} else if(Map[x][y]=='R') {
			result = dfs(x,y+1);
		} else if(Map[x][y]=='D') {
			result = dfs(x+1,y);
		} else if(Map[x][y]=='L') {
			result = dfs(x,y-1);
		}
		dp[x][y] = result?1:-1;
		
		return result;
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>N-1 || y<0 || y>M-1;
	}
}

//		for(int i=0;i<N;i++) {
//			for(int j=0;j<M;j++) {
//				System.out.print(Map[i][j]+" ");
//			}
//			System.out.println();
//		}