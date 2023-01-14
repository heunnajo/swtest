package boj;

import java.io.*;
import java.util.*;

public class SMC_Q2 {
	static int N,M,Ans;
	static int[][] Map;
	static boolean[][] V;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static class Pair{
		int x,y;
		Pair(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String... args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        Map = new int[N][M];
        V = new boolean[N][M];
        
        for(int i=0;i<N;i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0;j<M;j++) {
        		Map[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
		
        for(int i=0;i<N;i++) {
        	for(int j=0;j<M;j++) {
        		if(!V[i][j] && Map[i][j] == 0) {
        			Ans++;
        			bfs(i,j);
        		}
        	}
        }
        System.out.println(Ans);
	}
	static void bfs(int sx,int  sy) {
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(sx,sy));
		V[sx][sy] = true;
		
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			
			for(int d=0;d<4;d++) {
				int nx = cur.x+dx[d]; int ny = cur.y+dy[d];
				if(nx == -1) {nx = N-1;}
				if(nx == N) {nx = 0;}
				if(ny == -1) {ny = M-1;}
				if(ny == M) {ny = 0;}
				
				if(V[nx][ny] || Map[nx][ny] == 1) continue;
				q.offer(new Pair(nx,ny));
				V[nx][ny] = true;
			}
		}
	}
}

//        for(int i=0;i<N;i++) {
//        	for(int j=0;j<M;j++) {
//        		System.out.print(Map[i][j]+" ");
//        	}
//        	System.out.println();
//        }