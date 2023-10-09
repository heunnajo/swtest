//벽 부수고 이동하기
import java.util.*;
import java.io.*;

public class BOJ2206 {
	static int N,M,Ans;
	static int[][] Map;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static class Pos{
		int x,y,dist,drill_cnt;
		
		Pos(int x,int y,int dist,int drill_cnt){
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.drill_cnt = drill_cnt;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		Map = new int[N][M];
		
		for(int i=0;i<N;i++) {
			input = br.readLine().split("");
			for(int j=0;j<M;j++) {
				Map[i][j] = Integer.parseInt(input[j]);
			}
		}
		Ans = -1;
		bfs();
		System.out.println(Ans);
	}
	static void bfs() {
		Queue<Pos> q = new LinkedList<>();
		boolean[][][] v = new boolean[2][N][M];
		q.offer(new Pos(0,0,1,0));
		v[0][0][0] = true;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			//System.out.println("현재위치: "+cur.x+", "+cur.y);
			if(cur.x == N-1 && cur.y == M-1) {
				Ans = cur.dist;
				return;
			}
			for(int d=0;d<4;d++) {
				int nx = cur.x+dx[d]; int ny = cur.y+dy[d];
				if(isOut(nx,ny)) continue;
				
				if(cur.drill_cnt == 1) {
					if(v[1][nx][ny] || Map[nx][ny] == 1) continue;
					q.offer(new Pos(nx,ny,cur.dist+1,cur.drill_cnt));
					v[1][nx][ny] = true;
				}else {//1이든 0이든 이동 가능, 1:[1][nx][ny] = true, 0:[0][nx][ny] = true
					if(v[0][nx][ny]) continue;
					q.offer(new Pos(nx,ny,cur.dist+1,Map[nx][ny]));
					v[Map[nx][ny]][nx][ny] = true;
				}
			}
		}
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>=N || y<0 || y>=M;
	}
}

//		for(int i=0;i<N;i++) {
//			for(int j=0;j<M;j++) {
//				System.out.print(Map[i][j]);
//			}
//			System.out.println();
//		}