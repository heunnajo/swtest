import java.util.*;
public class OrganicCabbage {
	static int ans,N,M,K,Map[][];
	static boolean[][] visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int tc = sc.nextInt();
		for(int t = 0;t<tc;t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			K = sc.nextInt();
			Map = new int[N][M];
			visited = new boolean[N][M];
			
			for(int k=0;k<K;k++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				Map[x][y] = 1;
			}
			ans = 0;
			solve();
			sb.append(ans+"\n");
			
		}
		System.out.print(sb);
		
	}
	static void solve() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(Map[i][j] == 1 && !visited[i][j]) {
					bfs(i,j);ans++;
				}
			}
		}
	}
	static void bfs(int sx,int sy) {
		Queue<Integer> q = new LinkedList<>();
		visited[sx][sy] = true;
		q.add(sx); q.add(sy);
		
		while(!q.isEmpty()) {
			int curX = q.remove(); int curY = q.remove();
			
			for(int d=0;d<4;d++) {
				int nx = curX+dx[d];
				int ny = curY+dy[d];
				
				if(isOut(nx,ny) || visited[nx][ny]) continue;
				if(Map[nx][ny] == 0) continue;
				q.add(nx); q.add(ny);
				visited[nx][ny] = true;
			}
		}
	}
	static boolean isOut(int x,int y) {
		return x<0 ||x>N-1 || y<0 || y>M-1;
	}
}