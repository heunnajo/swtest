package boj;
import java.util.*;
import java.io.*;

//토마토
public class BOJ7569 {
	static int M,N,H,Ans;
	static int[][][] Map,Dist;
	static int[] dx = {0,0,0,0,1,-1};
	static int[] dy = {0,0,-1,1,0,0};
	static int[] dz = {1,-1,0,0,0,0};
	
	static class Pos{
		int z,x,y;
		
		Pos(int z,int x,int y){
			this.z = z;
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		Map = new int[H][N][M];
		Dist = new int[H][N][M];
		boolean isAllRippen = true;
		for(int z=0;z<H;z++) {
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<M;j++) {
					Map[z][i][j] = Integer.parseInt(st.nextToken());
					if(Map[z][i][j] == 0) {isAllRippen = false;}
				}
			}
		}
		
		if(isAllRippen) {
			System.out.println(0);
			return;
		}
		
		initializeDist();
		//bfs
		for(int z=0;z<H;z++) {
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(Map[z][i][j] == 1 && Dist[z][i][j] != 0) {
						bfs(z,i,j);
					}
				}
			}
		}
		//정답 도출
		System.out.println(solve());
		//print();
	}
	static void print() {
		System.out.println();
		for(int z=0;z<H;z++) {
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					System.out.print(Dist[z][i][j]+" ");
				}
				System.out.println();
			}
		}
	}
	static void initializeDist() {
		for(int z=0;z<H;z++) {
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					Dist[z][i][j] = -1;
				}
			}
		}
	}
	static void bfs(int sz,int sx,int sy) {
		
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(sz,sx,sy));
		Dist[sz][sx][sy] = 0;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			for(int d=0;d<6;d++) {
				int nz = cur.z+dz[d];
				int nx = cur.x+dx[d];
				int ny = cur.y+dy[d];
				
				//범위, 이동 가능칸인지, 방문 체크, 이미 방문했더라도 현재 방문이 더 빠른 시일이라면 갱신.
				if(isOut(nz,nx,ny) ||Map[nz][nx][ny] != 0) continue;
				if(Dist[nz][nx][ny] == -1 || Dist[nz][nx][ny] > Dist[cur.z][cur.x][cur.y]+1) {
					q.offer(new Pos(nz,nx,ny));
					Dist[nz][nx][ny] = Dist[cur.z][cur.x][cur.y]+1;
				}
				
			}
		}
	}
	static boolean isOut(int z,int x, int y) {
		return z<0 || z>=H || x<0 || x>=N || y<0 || y>=M;
	}
	static int solve() {
		int res = -1;
		for(int z=0;z<H;z++) {
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(Map[z][i][j] == 0 && Dist[z][i][j] == -1) {
						return -1;
					}
					if(res < Dist[z][i][j]) {
						res = Dist[z][i][j];
					}
				}
			}
		}
		return res;
	}
}

