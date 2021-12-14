package ss;
import java.util.*;
import java.io.*;
public class MonkeyWannabeHorse {
	static int k,w,h,Map[][],Dist[][];
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int[] Hdx = {-1,-1,-2,-2,1,1,2,2};
	static int[] Hdy = {-2,2,-1,1,-2,2,-1,1};
	static class Pair{
		int x,y,cnt;
		Pair(int x,int y,int cnt){
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		k = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());//가로 길이 w = 열
		h = Integer.parseInt(st.nextToken());//세로 길이 h = 행
		
		Map = new int[h][w];
		Dist = new int[h][w];
		
		for(int i=0;i<h;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<w;j++) {
				Map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		if(Dist[h-1][w-1] == 0) System.out.println(-1);
		else System.out.println(Dist[h-1][w-1]);
	}
	static void bfs() {
		Queue<Pair> q = new LinkedList<>();
		boolean[][] visited = new boolean[h][w];
		q.add(new Pair(0,0,0));
		visited[0][0] = true;
		
		while(!q.isEmpty()) {
			Pair cur = q.remove();
			for(int d=0;d<4;d++) {
				int nx = cur.x+dx[d], ny = cur.y+dy[d];
				if(isOut(nx,ny) && !visited[nx][ny]) {
					visited[nx][ny] = true;
					q.add(new Pair(nx,ny,cur.cnt));
					Dist[nx][ny] = Dist[cur.x][cur.y]+1;
				}
			}
			if(cur.cnt+1<k) {//현재 이동횟수+1이 k보다 작을 때!
				for(int d=0;d<8;d++) {
					int nx = cur.x+Hdx[d], ny = cur.y+Hdy[d];
					if(isOut(nx,ny) && !visited[nx][ny]) {
						visited[nx][ny] = true;
						q.add(new Pair(nx,ny,cur.cnt+1));
						Dist[nx][ny] = Dist[cur.x][cur.y]+1;
					}
				}
			}
		}
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>h-1 || y<0 || y>w-1;
	}
}

//		for(int i=0;i<h;i++) {
//			for(int j=0;j<w;j++) {
//				System.out.print(Map[i][j]+" ");
//			}
//			System.out.println();
//		}