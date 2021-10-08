import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;
public class Iceberg {
	static int N,M,Map[][];
	static Queue<Point> q;
	static boolean[][] visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static class Point{
		int x,y;
		Point(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
	static int solve() {
		int time = 0;
		while(cntIce()!=0) {
			time++;
			melt();
			if(cntIce()>=2) return time;
		}
		
		return 0;
	}
	static void melt() {//매 턴마다 양수인 칸이 다르기 때문에 귀찮더라도 그 때마다 양수칸 완탐해야함.
		q = new LinkedList<>();
		visited = new boolean[N][M];
		int cnt;
		int[][] tmp = new int[N][M];
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				tmp[i][j] = Map[i][j];
				if(tmp[i][j]>0) q.add(new Point(i,j));
			}
		}
			
		while(!q.isEmpty()) {
			Point cur = q.remove();
			cnt=0;
			for(int dir=0;dir<4;dir++) {//상하좌우 인접위치 0갯수 카운팅
				int nx = cur.x+dx[dir],ny = cur.y+dy[dir];
				if(isOut(nx,ny)||visited[nx][ny]) continue;
				if(Map[nx][ny] == 0) cnt++;
			}
			if(tmp[cur.x][cur.y]-cnt>=0) {
				tmp[cur.x][cur.y]-=cnt;
			} else tmp[cur.x][cur.y] = 0;
		}
		Map = tmp;
	}
	static int cntIce() {//빙하갯수 카운팅 완탐
		int sum = 0;
		q = new LinkedList<>();
		visited = new boolean[N][M];
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(Map[i][j]>0 && !visited[i][j]) {
					sum++;
					visited[i][j] = true;
					q.add(new Point(i,j));
					
					while(!q.isEmpty()) {
						Point cur = q.remove();
						for(int dir = 0;dir<4;dir++) {
							int nx = cur.x+dx[dir],ny = cur.y+dy[dir];
							if(isOut(nx,ny) || visited[nx][ny]) continue;
							
							if(Map[nx][ny]>0) {//양수이면 빙하이니까 이동!
								q.add(new Point(nx,ny));
								visited[nx][ny] = true;
							}
						}
					}
				}
			}
		}
		
		return sum;
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>N-1 || y<0 || y>M-1;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Map = new int[N][M];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				Map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(solve());
	}

}