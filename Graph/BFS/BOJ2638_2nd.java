package BFS;
import java.util.*;
import java.io.*;
//치즈
public class BOJ2638_2nd {
	static int N,M,Map[][];//N:행 M:열
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int[][] Times;
	static class Pair{
		int x,y;
		Pair(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Map = new int[N][M];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				Map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int time = 0;
		while(true) {
			time++;
			bfs();
			//print(time);
			melt();
			if(isAllMelted()) break;
		}
		System.out.println(time);
	}
	static void print(int time) {
		System.out.println(time+"일 때 Map");
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				System.out.print(Map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println(time+"일 때 Times");
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				System.out.print(Times[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	static void bfs() {
		Times = new int[N][M];
		Queue<Pair> q = new LinkedList<>();
		boolean[][] v = new boolean[N][M];
		q.offer(new Pair(0,0));
		v[0][0] = true;
		
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			
			for(int d=0;d<4;d++) {
				int nx = cur.x+dx[d]; int ny = cur.y+dy[d];
				
				if(isOut(nx,ny) || v[nx][ny]) continue;
				
				if(Map[nx][ny] == 1) {
					Times[nx][ny]++;
				}
				if(Map[nx][ny] == 0) {
					q.offer(new Pair(nx,ny));
					v[nx][ny] = true;
				}
			}
		}
	}
	static void melt() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(Times[i][j] >= 2) {
					Map[i][j] = 0;
				}
			}
		}
	}
	static boolean isAllMelted() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(Map[i][j] != 0) return false;
			}
		}
		return true;
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>N-1 || y<0 || y>M-1;
	}
}
