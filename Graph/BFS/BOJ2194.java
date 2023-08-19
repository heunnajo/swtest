package swea;
import java.io.*;
import java.util.*;
//유닛 이동시키기
public class BOJ2194 {
	static int sx,sy,ex,ey;
	static int N,M,A,B,K,Ans;
	static boolean[][] Map;
	static int INF = Integer.MAX_VALUE;
	static class Pos{
		int x,y,dist;
		
		Pos(int x,int y,int dist){
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		Map = new boolean[N][M];
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			
			Map[x][y] = true;
		}
		st = new StringTokenizer(br.readLine());
		sx = Integer.parseInt(st.nextToken())-1;
		sy = Integer.parseInt(st.nextToken())-1;
		
		st = new StringTokenizer(br.readLine());
		ex = Integer.parseInt(st.nextToken())-1;
		ey = Integer.parseInt(st.nextToken())-1;
		
		Ans = INF;
		bfs();
		if(Ans == INF) Ans = -1;
		System.out.println(Ans);
	}
	static void bfs() {
		
		Queue<Pos> q = new LinkedList<>();
		boolean[][] v = new boolean[N][M];
		q.offer(new Pos(sx,sy,0));
		v[sx][sy] = true;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			if(cur.x == ex && cur.y == ey) {
				Ans = cur.dist;
				return;
			}
			for(int d=0;d<4;d++) {
				int nx = cur.x+dx[d]; int ny = cur.y+dy[d];
				if(isOut(nx,ny) || isBlocked(nx,ny)) continue;
				if(v[nx][ny]) continue;
				
				q.offer(new Pos(nx,ny,cur.dist+1));
				v[nx][ny] = true;
			}
		}
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>N-1 || y<0 || y>M-1 || (y+B-1)>M-1 || (x+A-1)>N-1;
	}
	static boolean isBlocked(int x,int y) {
		for(int i=x;i<x+A;i++) {
			for(int j=y;j<y+B;j++) {
				if(Map[i][j]) return true;
			}
		}
		return false;
	}
}
