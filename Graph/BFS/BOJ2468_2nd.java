import java.io.*;
import java.util.*;

//안전영역
public class BOJ2468_2nd {
	static int N,Map[][];
	static int m,M,count;
	static boolean[][] v;
	static boolean[] height;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static class Pos{
		int x,y;
		
		Pos(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		Map = new int[N][N];
		
		m = 101;M = 0;
		height = new boolean[101];
		
		StringTokenizer st;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				Map[i][j] = Integer.parseInt(st.nextToken());
				
				height[Map[i][j]] = true;
				
				if(m > Map[i][j]) {m = Map[i][j];}
				if(M < Map[i][j]) {M = Map[i][j];}
			}
		}
		
		int ans = 1;//안전영역 갯수 최댓값 도출 : 경계값 처리.모든 영역이 물에 잠기거나 모든 영역이 물에 잠기지 않거나
		
		for(int h=m;h<=M;h++) {
			count = 0;//높이마다 안전영역 갯수 구한다!
			v = new boolean[N][N];//높이마다 방문체크 초기화!
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(!height[Map[i][j]]) continue;
					
					if(Map[i][j] <= h || v[i][j]) continue;
					
					bfs(i,j,h);
					count++;
					//System.out.println("count: "+count);
					
				}
				if(ans < count) {ans = count;}
			}
		}
		System.out.println(ans);
	}
	static void bfs(int sx,int sy,int h) {
		Queue<Pos> q = new LinkedList<>();
		
		q.offer(new Pos(sx,sy));
		v[sx][sy] = true;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			for(int d=0;d<4;d++) {
				int nx = cur.x+dx[d];
				int ny = cur.y+dy[d];
				
				if(isOut(nx,ny) || Map[nx][ny] <= h) continue;
				if(v[nx][ny]) continue;
				
				q.offer(new Pos(nx,ny));
				v[nx][ny] = true;
			}
		}
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>=N || y<0 || y>=N;
	}
}