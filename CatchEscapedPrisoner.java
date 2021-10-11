package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class CatchEscapedPrisoner {
	static int ans,N,M,R,C,L,Map[][],Time[][];//N세로=행,M가로=열,맨홀 뚜경위치 R,C:idx 0부터 시작.
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int[][] Ternal = {{-100},
			{0,1,2,3},
			{0,1},
			{2,3},
			{0,3},
			{1,3},
			{1,2},
			{0,2}
	};
	static int[][] Possible = {//존재유무만 판단하기 때문에 순서는 상관없음.
			{1,2,5,6},
			{1,2,4,7},
			{1,3,4,5},
			{1,3,6,7}
	};
	static class Point{
		int x,y,dist;
		Point(int x,int y){
			this.x = x;
			this.y = y;
		}
		Point(int x,int y,int dist){
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
	static void bfs() {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		q.add(new Point(R,C,1));
		visited[R][C] = true;
		Time[R][C] = 1;
		
		while(!q.isEmpty()) {
			Point cur = q.remove();
			if(cur.dist == L+1) return;
			int curTernal = Map[cur.x][cur.y];
			int[] list = Ternal[curTernal];//현재 파이프 모양으로 가능한 이동방향!
			for(int j=0;j<list.length;j++) {//모든 방향에 대해 다 도는 게 아님!
				int nd = list[j];//현재 방향
				int nx = cur.x+dx[nd];
				int ny = cur.y+dy[nd];
				
				if(isOut(nx,ny)) continue;
				if(visited[nx][ny]||Map[nx][ny]==0) continue;
				
				if(checkPossible(nd,Map[nx][ny])) {
					q.add(new Point(nx,ny,cur.dist+1));
					if(Time[nx][ny] == 0 || Time[nx][ny]>Time[cur.x][cur.y]+1) {
						Time[nx][ny] = Time[cur.x][cur.y]+1;//최솟값을 지킨다!
					}
					visited[nx][ny] = true;
				}
			}
		}
		
	}
	static boolean checkPossible(int dir,int next) {
		for(int j=0;j<4;j++) {
			if(Possible[dir][j] == next) return true;
		}
		return false;
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>N-1 || y<0 || y>M-1;
	}
	static void countVisited() {
		int sum = 0;
		for(int i=0;i<N;i++)
			for(int j=0;j<M;j++)
				if(Time[i][j]>0 && Time[i][j]<=L) sum++;
		ans = sum;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			Map = new int[N][M];
			Time = new int[N][M];
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<M;j++) {
					Map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			bfs();
			//print();
			countVisited();
			sb.append("#"+t+" "+ans+"\n");
		}
		System.out.print(sb);
	}
	static void print() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				System.out.print(Time[i][j]+" ");
			}
			System.out.println();
		}
		
	}
}
