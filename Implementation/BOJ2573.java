import java.util.*;
import java.io.*;
//빙산
public class BOJ2573 {
	static int N,M;
	static int[][] Iceberg,newIceberg;
	static boolean[][] Visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static class Pos{
		int x,y;
		Pos(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Iceberg = new int[N][M];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				Iceberg[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int cnt = 1;
		for(int t=1;;t++) {
			melt();
			//print();
			cnt = countIceberg();
			//print();
			//System.out.println("cnt: "+cnt);

			if(cnt >= 2) {
				System.out.println(t);
				break;
			} else if(cnt == 0) {//빙산이 다 녹았는데, 덩어리가 2개 이상인 조건을 만족하지 않는다면 0을 출력
				System.out.println(0);
				break;
			}
			
			//System.out.println("==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ");
		}
	}
	static void print() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				System.out.print(Iceberg[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	static void melt() {
		newIceberg = new int[N][M];
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				int zeroCnt = 0;
				if(Iceberg[i][j] == 0) continue;
				for(int d=0;d<4;d++) {
					int nx = i+dx[d]; int ny = j+dy[d];
					if(isOut(nx,ny)) continue;
					if(Iceberg[nx][ny] == 0) zeroCnt++;
				}
				newIceberg[i][j] = Iceberg[i][j]-zeroCnt;
				if(newIceberg[i][j] < 0) newIceberg[i][j] = 0;
			}
		}
		
		Iceberg = newIceberg;
	}
	static int countIceberg() {
		int sum = 0;
		Visited = new boolean[N][M];
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(Visited[i][j] || Iceberg[i][j] == 0) continue;
				bfs(i,j);
				sum++;
			}
		}
		return sum;
	}
	static void bfs(int sx,int sy) {
		Queue<Pos> q = new LinkedList<>();
		q.offer(new Pos(sx,sy));
		Visited[sx][sy] = true;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			for(int d=0;d<4;d++) {
				int nx = cur.x+dx[d]; int ny = cur.y+dy[d];
				if(isOut(nx,ny) || Visited[nx][ny] || Iceberg[nx][ny] == 0) continue;
				
				q.offer(new Pos(nx,ny));
				Visited[nx][ny] = true;
			}
		}
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>=N || y<0 || y>=M;
	}

}

