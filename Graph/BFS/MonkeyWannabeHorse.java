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
		int x,y,jump,cnt;
		Pair(int x,int y,int jump,int cnt){
			this.x = x;
			this.y = y;
			this.jump = jump;
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
		bfs();
	}
	static void bfs() {
		Queue<Pair> q = new LinkedList<>();
		boolean[][][] visited = new boolean[k+1][h][w];
		q.add(new Pair(0,0,0,0));
		visited[0][0][0] = true;
		
		while(!q.isEmpty()) {
			Pair cur = q.remove();
			if(cur.x == h-1 && cur.y == w-1) {
				System.out.println(cur.cnt);
				return;
			}
			for(int d=0;d<4;d++) {
				int nx = cur.x+dx[d], ny = cur.y+dy[d];
				if(isOut(nx,ny) || visited[cur.jump][nx][ny]) continue;
				if(Map[nx][ny] == 0) {
					visited[cur.jump][nx][ny] = true;//말 이동횟수 변함없이 방문 체크!
					q.add(new Pair(nx,ny,cur.jump,cur.cnt+1));
				}
			}
			if(cur.jump<k) {
				for(int d=0;d<8;d++) {
					int nx = cur.x+Hdx[d], ny = cur.y+Hdy[d];
					if(isOut(nx,ny) || visited[cur.jump+1][nx][ny]) continue;
					if(Map[nx][ny] == 0) {
						visited[cur.jump+1][nx][ny] = true;
						q.add(new Pair(nx,ny,cur.jump+1,cur.cnt+1));
					}
				}
			}
		}
		System.out.println(-1);
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>h-1 || y<0 || y>w-1;
	}
}