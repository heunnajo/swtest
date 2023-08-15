package swea;
import java.util.*;
import java.io.*;
//진우의 달 여행(Small)
public class BOJ17484_BFS {
	static int Ans,N,M,Map[][];
	static int[] dy = {-1,0,1};
	static int[] visited;
	static class Pos{
		int x,y,dir, fuel;
		
		Pos(int x,int y,int dir,int fuel){
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.fuel = fuel;
		}
		Pos(int x,int y,int dir){
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Map = new int[N+1][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				Map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		solve();
		System.out.println(Ans);
	}
	static void solve() {
		Ans = Integer.MAX_VALUE;
		
		for(int i=0;i<M;i++) {
			visited = new int[N];
			visited[0] = i;
			bfs(i);
		}
	}
	static void bfs(int sy) {
		//boolean[][] visited = new boolean[N][M];
		Queue<Pos> q = new LinkedList<>();
		q.offer(new Pos(0,sy,-1,Map[0][sy]));//처음에는 모든 방향으로 이동 가능하도록 -1로.
		
		while(!q.isEmpty()) {
			
			for(int i=0;i<q.size();i++) {
				Pos cur = q.poll();
				if(cur.x == N) {
					if(cur.fuel < Ans) {
						Ans = cur.fuel;
					}
					continue;//리턴하는 게 아니라 다음 pos 인스턴스로!
				}
				for(int d=0;d<3;d++) {
					int ny = cur.y+dy[d];
					
					if(isOut(ny) || cur.dir == d) continue;
					q.offer(new Pos(cur.x+1,ny,d,cur.fuel+Map[cur.x+1][ny]));//여기서 또 덱스초과오류발생가능.
					
				}
			}
		}
	}
	static boolean isOut(int y) {
		return y<0 || y>=M;
	}

}
//for(int i=0;i<N;i++) {
//	for(int j=0;j<M;j++) {
//		System.out.print(Map[i][j]+" ");
//	}
//	System.out.println();
//}