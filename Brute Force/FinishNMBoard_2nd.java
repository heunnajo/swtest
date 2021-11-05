import java.io.*;
import java.util.*;
public class FinishNMBoard_2nd {
	static int cnt,ans,N,M;//N:세로=행, M:가로=열
	static char[][] Board;
	static boolean[][] visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int tc = 1;
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
						visited[i][j] = true;cnt--;//시작점 방문체크해주고 
						go(i,j,0);
						visited[i][j] = false;cnt++;//돌고난 후에는 다음 탐색을 위해 방문체크 원복 
					}
				}
			}
			sb.append("Case "+tc+": "+ans+"\n");
			tc++;
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
		for(int dir=0;dir<4;dir++) {
			int nx = x,ny = y;
			while(true) {
				nx += dx[dir]; ny += dy[dir];
				if(isOut(nx,ny)) break;
				if(Board[nx][ny]=='*') break;
				if(visited[nx][ny])break;
				
				visited[nx][ny] = true; cnt--;
			}
		
			nx -= dx[dir]; ny -= dy[dir];
			if(x==nx && y == ny) continue;
			
			go(nx,ny,move+1);//다음 경우 호출
			while(!(nx==x && ny== y)) {//원복
				visited[nx][ny] = false; cnt++;
				nx -= dx[dir]; ny -= dy[dir];
			}
		}
	}
	static boolean isOut(int x,int y) {
		return x< 0 || x>N-1 || y<0 || y>M-1;
	}
	static boolean isIn(int x,int y) {
		return x>= 0 && x<N && y>=0 && y<M;
	}
}