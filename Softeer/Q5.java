//동계 테스트 시점 예측(BOJ 치즈)
import java.util.*;
import java.io.*;

public class Q5 {
	static int N,M;//N:행크기
	//static String[][] Map;
	static int[][] Map,cnt;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static boolean isOut(int x,int y) {
		return x<0 || x>=N || y<0 || y>=M;
	}
	static class Point{
		int x,y;
		Point(int x,int y){
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
		cnt = new int[N][M];
		
		String[][] tmp = new String[N][M];
		
		for(int i=0;i<N;i++) {
			tmp[i] = br.readLine().split(" ");
		}
		for(int i=0;i<N;i++) {//굳이 int로 형변환할 필요는 없을 것 같음.
			for(int j=0;j<M;j++) {
				Map[i][j] = Integer.parseInt(tmp[i][j]);
			}
		}
		
		int time = 0;
		boolean flag = true;
		while(flag) {
			melt();
			time++;
			if(checkAllMelted()) {
				flag = false;
			}
			//checkIceSize();//각 반복문마다 남은 빙하 갯수 출력!
			//print();//현재 빙하 상태 출력
			//cnt 초기화
			for(int i=0;i<N;i++) Arrays.fill(cnt[i], 0);
		}
		System.out.println(time);
	}
	//모든 외부 공기(0인칸)을 탐색한다!
	static void melt() {//BFS 수행 : 인접한 1인 칸에 대해 1씩 증가한다.
		Queue<Point> q = new LinkedList<>();
		boolean[][] v = new boolean[N][M];
		q.add(new Point(0,0));
		v[0][0] = true;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			for(int d= 0;d<4;d++) {
				int nx = cur.x+dx[d], ny = cur.y+dy[d];
				
				if(isOut(nx,ny) || v[nx][ny]) continue;
				if(Map[nx][ny] == 1) cnt[nx][ny]++;
				
				if(Map[nx][ny] == 0) {
					q.add(new Point(nx,ny));
					v[nx][ny] = true;
				}
			}
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(cnt[i][j] >= 2) Map[i][j] = 0;
			}
		}
	}
	static int countExposed(int sx,int sy) {
		int sum = 0;
		
		for(int d=0;d<4;d++) {
			int nx = sx+dx[d],ny = sy+dy[d];
			if(isOut(nx,ny)) continue;
			if(Map[nx][ny] == 0) sum++;
		}
		
		return sum;
	}
	static void checkIceSize() {
 		int cnt = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(Map[i][j] == 1) {
					cnt++;
				}
			}
		}
		System.out.println("현재 빙하 갯수: "+cnt);
	}
 	static boolean checkAllMelted() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(Map[i][j] == 1) {
					return false;
				}
			}
		}
		return true;
	}
	static void print() {

		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				System.out.print(Map[i][j]+" ");
			}
			System.out.println();
		}
		
	}
}
