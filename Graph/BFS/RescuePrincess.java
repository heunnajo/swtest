//BOJ#17836번 공주님을 구해라!
package BFS;

import java.io.*;
import java.util.*;
public class RescuePrincess {
	static int N,M,T,ans;
	static String[][] Map;
	static final int INF = 987654321;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static class Pair{
		int x,y,dist;
		boolean gram;
		Pair(int x,int y,boolean gram,int dist){
			this.x = x;
			this.y = y;
			this.gram = gram;
			this.dist = dist;
		}
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>=N || y<0 || y>=M;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());//행
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		Map = new String[N][M];
		
		for(int i=0;i<N;i++) {
			Map[i] = br.readLine().split(" ");
		}
		
		ans = INF;
		bfs();
		
		if(ans == INF) System.out.println("Fail");
		else System.out.println(ans);
	}
	static void bfs() {
		int time = 0;
		Queue<Pair> q = new LinkedList<>();
		boolean[][][] v = new boolean[2][N][M];
		q.add(new Pair(0,0,false,0));
		v[0][0][0] = true;
        
        while(!q.isEmpty()) {
            Pair cur = q.poll();

            if(cur.dist > T) break;
            if(cur.x == N-1 && cur.y == M-1) {
                if(ans>cur.dist) ans = cur.dist;
            }

            boolean gram = cur.gram;

            for(int d=0;d<4;d++) {
                int nx = cur.x+dx[d], ny = cur.y+dy[d];

                if(isOut(nx,ny)) continue;

                if(!gram) {//1.그람X
                    if(v[0][nx][ny]) continue;
                    if(Map[nx][ny].equals("1")) continue;

                    if(Map[nx][ny].equals("2")) {
                        q.add(new Pair(nx,ny,true,cur.dist+1));
                        v[0][nx][ny] = true;//0인지 1인지 헷갈렸음.
                    } else {
                        q.add(new Pair(nx,ny,false,cur.dist+1));
                        v[0][nx][ny] = true;
                    }
                } else {//2.그람O
                    if(v[1][nx][ny]) continue;

                    q.add(new Pair(nx,ny,true,cur.dist+1));
                    v[1][nx][ny] = true;
                }
            }
        }
	}
}

