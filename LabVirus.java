package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import ss.LabVirus_Solution.Point;
public class LabVirus {
	static int N,M;//N:세로, M:가로.
	static int ans = Integer.MIN_VALUE;
	static int[][] Map,copiedMap;
	static class Point{
		int x,y;
		Point(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
//	static Point[] Vlist = new Point[10];
	static ArrayList<Point> Vlist;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static void make_wall(int wall_cnt) {
		if(wall_cnt == 3) {
			spread_virus();
			return;
		}
		for(int i=0;i<M;i++) {
			for(int j=0;j<N;j++) {
				if(Map[i][j] == 0) {//복사본으로 써야함!
					Map[i][j] = 1;
					make_wall(wall_cnt+1);
					Map[i][j] = 0;//다시 원래대로 바꿔주는 것이 중요함!!!
				}
			}
		}
	}
	
	static void spread_virus() {//벽 3개 세웠으면 바이러스 확산!
		int[][] viurs_map = new int[M][N];
		for(int i=0;i<M;i++) {
			for(int j=0;j<N;j++) {
				viurs_map[i][j] = Map[i][j];
			}
		}
		Queue<Point> q = new LinkedList<>();
		for(Point pnt : Vlist) {
			q.add(pnt);
		}
		while(!q.isEmpty()) {
			Point cur = q.remove();
			
			for(int d=0;d<4;d++) {
				int nx = cur.x+dx[d];//왜 null이냐?
				int ny = cur.y+dy[d];
				
				if(nx<0 || nx>=M || ny<0 || ny>=N) continue;
				if(viurs_map[nx][ny]==0) {//0이면 그 노드 큐에 넣고 상하좌우 탐색이 된다!
					viurs_map[nx][ny] = 2;
					q.add(new Point(nx,ny));
				}
			}
		}
		safe_zone(viurs_map);
	}
	static void safe_zone(int[][] viurs_map) {
		int zero_cnt=0;
	
		for(int i=0;i<M;i++) {
			for(int j=0;j<N;j++) {
				if(viurs_map[i][j] == 0) {
					zero_cnt++;
				}
			}
		}
		ans = Math.max(ans, zero_cnt);
	}
	static void copyMap() {
		//Map 복사본 생성.
		for(int i=0;i<M;i++) {
			for(int j=0;j<N;j++) {
				copiedMap[i][j] = Map[i][j];
			}
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		M = Integer.parseInt(input[0]);
		N = Integer.parseInt(input[1]);
		Map = new int[M][N];
		copiedMap = new int[M][N];
		Vlist = new ArrayList<Point>();
		
		for(int i=0;i<M;i++) {
			input = br.readLine().split(" ");
			for(int j=0;j<N;j++) {
				Map[i][j] = Integer.parseInt(input[j]);
				if(Map[i][j] == 2) {
					Vlist.add(new Point(i,j));
				}
			}
		}
		copyMap();
		make_wall(0);
		System.out.println(ans);
	}

}
//1로 바꾸는 시점에서 copied map이 필요함.
		//전역변수로 2개를 만들까?
		//복사본 맵 라이프사이클은 어떻게 되나?
		//make_wall 내부에 만들 순 없음.재귀함수 돌 때마다 X.
