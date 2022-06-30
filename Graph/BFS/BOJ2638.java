package ss;
//치즈
import java.io.*;
import java.util.*;

public class BOJ2638 {
	static int N,M,Map[][],Cnt[][];
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static class Point{
		int x,y;
		
		Point(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st =  new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Map = new int[N][M];
		Cnt = new int[N][M];
		
		for(int i=0;i<N;i++) {
			st =  new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				Map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int time = 0;
		while(!isMelted()) {
			bfs();
			meltCheese();
			//print(Map); print(Cnt);
			//System.out.println("===============================");
			initializeCnt();
			time++;
		}
		System.out.println(time);
	}
	static void print(int[][] arr) {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	static boolean isMelted() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(Map[i][j] != 0) return false;
			}
		}
		return true;
	}
	static void bfs() {//0인 칸 기준으로 상하좌우 탐색, 1인 칸을 만나면 그 칸 of Cnt 값을 1씩 증가!
		Queue<Point> q = new LinkedList<>();
		boolean[][] v = new boolean[N][M];
		
		q.add(new Point(0,0));
		v[0][0] = true;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			for(int d=0;d<4;d++) {
				int nx = cur.x+dx[d];
				int ny = cur.y+dy[d];
				
				if(isOut(nx,ny) || v[nx][ny]) continue;
				if(Map[nx][ny] == 1) {
					Cnt[nx][ny]++;
					continue;
				}
				
				//0인 칸만 방문!
				q.add(new Point(nx,ny));
				v[nx][ny] = true;
				
			}
		}
	}
	static void meltCheese() {//Cnt 완탐, 2이상인 값이면 그 칸 of Map 0으로 갱신!
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(Cnt[i][j] >= 2) Map[i][j] = 0;
			}
		}
	}
	static void initializeCnt() {
		for(int i=0;i<N;i++) Arrays.fill(Cnt[i],0);
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>=N || y<0 || y>=M;
	}
}

//		for(int i=0;i<N;i++) {
//			for(int j=0;j<M;j++) {
//				System.out.print(Map[i][j]+" ");
//			}
//			System.out.println();
//		}