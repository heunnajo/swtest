package ss;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class CrashBrick_BFS_Solution {
	static class Brick{
		int x,y,range;
		public Brick(int x,int y,int range) {
			this.x = x;
			this.y = y;
			this.range = range;
		}
	}
	static Queue<Brick> q;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static int[] spots;
	static int[][] Map;
	static boolean[][] visited;
	static int ans;
	static int T,N,W,H;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			q = new LinkedList<>();
			spots = new int[N];
			Map = new int[H][W];
			ans = Integer.MAX_VALUE;
			
			for(int i=0;i<H;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<W;j++) {
					Map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			makeSet(0);
			
			sb.append("#"+t+" "+ans+"\n");
		}
		System.out.print(sb);
	}
	private static void makeSet(int spot) {
		if(spot == N) {
			int[][] copy = copyMap();
			int brick = 0;
			
			for(int i=0;i<N;i++) {
				visited = new boolean[H][W];
				selectAndCrash(copy,spots[i]);//N개의 열j들 중에서 하나씩 전달.
				drop(copy);
			}
			brick = countBrick(copy);
			ans = ans > brick ? brick : ans;
			return;
		}
		for(int i=0;i<W;i++) {
			spots[spot] = i;
			makeSet(spot+1);
		}
	}
	//2차원 배열 복사본과 j번째 열을 받는다.
	static void selectAndCrash(int[][] copy,int spot) {
		//선택한 열j=spot 번째 열에서 맨윗칸의 벽돌 찾는다!
		//spot번 열에 대해서만 찾기 때문에 단일 for문!
		for(int i=0;i<H;i++) {
			if(copy[i][spot]>0) {
				q.add(new Brick(i,spot,copy[i][spot]));
				break;
			}
		}
		//본격 BFS 시작
		while(!q.isEmpty()) {
			Brick cur = q.remove();
			int nx,ny;
			
			for(int d=0;d<4;d++) {
				for(int r=0;r<cur.range;r++) {
					nx = cur.x+dx[d]*r;
					ny = cur.y+dy[d]*r;
					
					if(nx>=0 && nx<H && ny>=0 && ny<W && !visited[nx][ny]) {
						visited[nx][ny] = true;
						q.add(new Brick(nx,ny,copy[nx][ny]));
						copy[nx][ny] = 0;
					}
				}
			}
		}
		
	}
	static void drop(int[][] copy) {
		Queue<Integer> temp;
		
		for(int j=0;j<W;j++) {
			//각 열마다 초기화
			temp = new LinkedList<>();
			for(int i=H-1;i>=0;i--) {
				if(copy[i][j]>0) temp.add(copy[i][j]);
			}
			for(int i=H-1;i>=0;i--) {
				if(!temp.isEmpty()) {copy[i][j] = temp.remove();}
				else {copy[i][j] = 0;}
			}
		}
	}
	static int countBrick(int[][] copy) {
		int sum = 0;
		for(int i=0;i<H;i++) {
			for(int j=0;j<W;j++) {
				if(copy[i][j]>0)sum++;
			}
		}
		return sum;
	}
	static int[][] copyMap(){
		int[][] arr = new int[H][W];
		for(int i=0;i<H;i++) {
			for(int j=0;j<W;j++) {
				arr[i][j] = Map[i][j];
			}
		}
		return arr;
	}

}
