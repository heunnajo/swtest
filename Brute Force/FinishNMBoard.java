package ss;
import java.io.*;
import java.util.*;
public class FinishNMBoard {
	static int cnt,ans,N,M;//N:세로=행, M:가로=열
	static char[][] Board;
	static boolean[][] visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception{
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = null;
//		StringTokenizer st = new StringTokenizer(br.readLine());
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int tc = 1;
//		while(st.hasMoreTokens()) {
		while(sc.hasNextInt()) {
			N = sc.nextInt();
			M = sc.nextInt();
			
			visited = new boolean[N][M];
			Board = new char[N][M];
			ans = -1;
			cnt = 0;
			
			for(int i=0;i<N;i++) {
				Board[i] = sc.next().toCharArray();
				for(int j=0;j<M;j++) {
					if(Board[i][j]=='.') cnt++;
				}
			}
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(Board[i][j] == '.') {
						go(i,j,0);
					}
				}
			}
			sb.append("Case "+tc+": "+ans);
		}
		System.out.print(sb);
	}
	static void go(int x,int y,int move) {
		if(cnt == 0) {
			//조건 만족하면 ans 최솟값 갱신해주고, 리턴
			if(ans==-1 || ans>move) {
				ans = move;
			}
			return;
		}
		int nx = x,ny = y;
		for(int dir=0;dir<4;dir++) {
			nx += dx[dir]; ny += dy[dir];
			while(isIn(nx,ny) && !visited[nx][ny] && Board[nx][ny] == '.') {
				nx += dx[dir]; ny += dy[dir];
				visited[nx][ny] = true; cnt--;
			}
			nx -= dx[dir];ny -= dy[dir];cnt++;
			if(!(x==nx && y == ny)) {
				//다음 경우 호출
				go(nx,ny,move+1);
				//원복
				while(nx!=x && ny!= y) {
					nx -= dx[dir]; ny -= dy[dir];
					visited[nx][ny] = false; cnt++;
				}
			}
		}
	}
	static boolean isIn(int x,int y) {
		return x>= 0 && x<N && y>=0 && y<M;
	}

}
