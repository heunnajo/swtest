package boj;
import java.io.*;
import java.util.*;

//연구소
public class BOJ14502 {
	static int N,M,Ans;//세로 크기 N(행), 가로 크기 M(열)
	static int[][] tmpMap,Map;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static ArrayList<Pair> VirusList;
	static class Pair{
		int x,y;
		
		Pair(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		Map = new int[N][M];
		tmpMap = new int[N][M];
		VirusList = new ArrayList<>();
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				Map[i][j] = Integer.parseInt(st.nextToken());
				if(Map[i][j] == 2) {
					VirusList.add(new Pair(i,j));
				}
			}
		}
		Ans = -1;
		go(0);
		System.out.println(Ans);
	}
	static void go(int idx) {
		//1.종료
		if(idx == 3) {
			spreadVirus();
			int cur = getSafeArea();
			if(Ans < cur) {Ans = cur;}
			
			return;
		}
		//2.현재 선택, 다음 호출
		for(int i=0;i<N*M;i++) {
			int x = i/M; int y = i%M;
			if(Map[x][y] == 0) {
				Map[x][y] = 1;
				go(idx+1);
				Map[x][y] = 0;
			}
		}
	}
	static void spreadVirus() {
		copyMap();
		
		//tmpMap에 대해 확산 수행
		Queue<Pair> q = new LinkedList<>();
		for(Pair p:VirusList) {
			q.offer(p);
		}
		
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			
			for(int d=0;d<4;d++) {
				int nx = cur.x+dx[d]; int ny = cur.y+dy[d];
				
				if(isOut(nx,ny)) continue;
				if(tmpMap[nx][ny] == 0) {
					tmpMap[nx][ny] = 2;
					q.offer(new Pair(nx,ny));
				}
			}
		}
	}
	static void copyMap() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				tmpMap[i][j] = Map[i][j];
			}
		}
	}
	static int getSafeArea() {
		int sum = 0;
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(tmpMap[i][j] == 0) {
					sum++;
				}
			}
		}
		return sum;
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>N-1 || y<0 || y>M-1;
	}
}

//		for(int i=0;i<N;i++) {
//			for(int j=0;j<M;j++) {
//				System.out.print(Map[i][j]+" ");
//			}
//			System.out.println();
//		}