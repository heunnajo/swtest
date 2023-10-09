//벽 부수고 이동하기2
import java.util.*;
import java.io.*;

public class BOJ14442 {
	static int N,M,K,Ans;
	static char[][] Map;
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
		K = Integer.parseInt(input[2]);
		Map = new char[N][M];
		
		for(int i=0;i<N;i++) {
			Map[i] = br.readLine().toCharArray();
		}
		Ans = -1;
		bfs();
		System.out.println(Ans);
	}
	static void bfs() {
		Queue<Pos> q = new LinkedList<>();
//		boolean[][][] v = new boolean[(int)Math.pow(2, K)][N][M];
		boolean[][][] v = new boolean[K+1][N][M];
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
				
				if(Map[nx][ny] == '1') {//벽인 경우.
					if(cur.drill_cnt < K && !v[cur.drill_cnt+1][nx][ny]) {//다음이동좌표는 드릴 쓸것이기 때문에 중복검사하는 visited의마지막차원은 1에 대해서 검사.
						q.add(new Pos(nx,ny,cur.dist+1,cur.drill_cnt+1));
						v[cur.drill_cnt+1][nx][ny] = true;
					}
				}else {//빈칸인 경우:현재까지 벽 부순 횟수를 그대로 다음으로 넘긴다.
					if(!v[cur.drill_cnt][nx][ny]) {
						q.add(new Pos(nx,ny,cur.dist+1,cur.drill_cnt));
						v[cur.drill_cnt][nx][ny] = true;
					}
				}
//				if(cur.drill_cnt >= K) {
//					if(v[cur.drill_cnt][nx][ny] || Map[nx][ny] == '1') continue;
//					q.offer(new Pos(nx,ny,cur.dist+1,cur.drill_cnt));
//					v[cur.drill_cnt][nx][ny] = true;
//				}else {//1이든 0이든 이동 가능, 1:[1][nx][ny] = true, 0:[0][nx][ny] = true
//					if(v[cur.drill_cnt][nx][ny]) continue;
//					q.offer(new Pos(nx,ny,cur.dist+1,cur.drill_cnt+Map[nx][ny]-'0'));
//					v[cur.drill_cnt+Map[nx][ny]-'0'][nx][ny] = true;
//				}
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