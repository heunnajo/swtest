//장애물 인식 프로그램
import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static String[] Map;
	static boolean[][] v;
	static class Point{
		int x,y,dist;
		Point(int x,int y,int dist){
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
		Point(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static boolean isOut(int x,int y) {
		return x<0 || x>=N || y<0 || y>=N;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		Map = new String[N];
		v = new boolean[N][N];
		
		for(int i=0;i<N;i++) {
			Map[i] = br.readLine();
		}
		
		LinkedList<Integer> list = new LinkedList<>();
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(!v[i][j] && Map[i].charAt(j) == '1') {//시작점 방문체크:bfs내에서!
					list.add(countBlock(i,j));//bfs
				}
			}
		}
		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		sb.append(list.size()).append("\n");
		
		for(int x:list) {
			sb.append(x).append("\n");
		}
		System.out.print(sb);
	}
	static int countBlock(int sx,int sy) {
		int size = 1;

		Queue<Point> q = new LinkedList<>();
		q.add(new Point(sx,sy));
		v[sx][sy] = true;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			for(int d=0;d<4;d++) {
				int nx = cur.x+dx[d], ny = cur.y+dy[d];
				if(isOut(nx,ny) ||v[nx][ny]) continue;
				if(Map[nx].charAt(ny) == '0') continue;
				
				q.add(new Point(nx,ny));
				v[nx][ny] = true;
				size++;
			}
		}

		return size;
	}
}