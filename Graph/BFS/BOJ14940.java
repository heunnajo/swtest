package swea;
import java.io.*;
import java.util.*;
//쉬운 최단거리
public class BOJ14940 {
	static int N,M,Map[][],Dist[][];//N:행,M:열
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static class Pair{
		int x,y;
		
		Pair(int x,int y){
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
		Dist = new int[N][M];
		
		int sx=0; int sy=0;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				Map[i][j] = Integer.parseInt(st.nextToken());
				if(Map[i][j] == 2) {
					sx = i; sy = j;
				}
			}
		}
		bfs(sx,sy);
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(Map[i][j] == 1 && Dist[i][j] == 0) {
					Dist[i][j] = -1;
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				sb.append(Dist[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}
	static void bfs(int sx,int sy) {
		Queue<Pair> q = new LinkedList<>();
		boolean[][] v = new boolean[N][M];
		q.offer(new Pair(sx,sy));
		v[sx][sy] = true;
		
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			
			for(int d=0;d<4;d++) {
				int nx = cur.x+dx[d]; int ny = cur.y+dy[d];
				if(isOut(nx,ny) || Map[nx][ny] == 0 || v[nx][ny]) continue;
				
				Dist[nx][ny] = Dist[cur.x][cur.y]+1;
				q.offer(new Pair(nx,ny));
				v[nx][ny] = true;
			}
		}
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>=N || y<0 || y>=M;
	}
}
