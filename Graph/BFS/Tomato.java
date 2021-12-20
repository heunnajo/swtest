package ss;
import java.util.*;
import java.io.*;
public class Tomato {
	static int N,M,Map[][],dist[][];
	static Queue<Integer> q;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M= Integer.parseInt(st.nextToken());//열 
		N = Integer.parseInt(st.nextToken());//행
		
		Map = new int[N][M];
		dist = new int[N][M];
		q = new LinkedList<>();
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				Map[i][j] = Integer.parseInt(st.nextToken());
				dist[i][j] = -1;
				if(Map[i][j] == 1) {
					q.add(i); q.add(j);
					dist[i][j] = 0;
				}
			}
		}
		int ans = 0;
		boolean flag = bfs();
		if(!flag) ans = -1;
		else {
			ans = getMinTime();
		}
		System.out.println(ans);
	}
	static boolean bfs() {
		boolean flag = true;
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		while(!q.isEmpty()) {
			int curX = q.remove(), curY = q.remove();
			for(int d=0;d<4;d++) {
				int nx = curX+dx[d],ny = curY+dy[d];
				if(isOut(nx,ny) || Map[nx][ny] == -1 || dist[nx][ny] != -1) continue;
				q.add(nx); q.add(ny);
				dist[nx][ny] = dist[curX][curY]+1;
			}
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(Map[i][j]==0 && dist[i][j] == -1) flag = false;
			}
		}
		return flag;
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>N-1 || y<0 || y>M-1;
	}
	static int getMinTime() {
		int tmp = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				tmp = Math.max(tmp, dist[i][j]);
			}
		}
		return tmp;
	}
	static void print() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				System.out.print(dist[i][j]+" ");
			}
			System.out.println();
		}
		
	}
}
