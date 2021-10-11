import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class DessertCafe {
	static final int INF = 987654321;
	static int ans,N,Map[][];
	static boolean[] visited;
	static int[] dx = {1,1,-1,-1};
	static int[] dy = {1,-1,-1,1};
	static void initV() {
		visited = new boolean[101];
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>N-1 || y<0 || y>N-1;
	}
	static void solve() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				initV();
				dfs(i,j,i,j,0,1);
			}
		}
	}
	static void dfs(int sx,int sy,int x,int y,int dir,int cnt) {
		visited[Map[x][y]] = true;//리턴할 때 리턴하더라도 재방문 방지를 위해 먼저 true로 마킹!
		if(dir == 4) return;
		x += dx[dir]; y+=dy[dir];
		if(x== sx && y == sy) {
			ans = Math.max(ans, cnt);//ans는 최댓값 갱신이 되야함.
			return;
		}
		if(isOut(x,y)) return;
		if(visited[Map[x][y]]) return;
		if(x<sx) return;//현재 x가 감소하는 구간에서 백트랙킹:초기실행에서는 해당X.
		visited[Map[x][y]] = true;
		dfs(sx,sy,x,y,dir,cnt+1);
		dfs(sx,sy,x,y,dir+1,cnt+1);
		visited[Map[x][y]]  = false;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			N = Integer.parseInt(br.readLine());
			
			Map = new int[N][N];
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					Map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			ans = -1;
			solve();
			sb.append("#"+t+" "+ans+"\n");
		}
		System.out.print(sb);
	}
}