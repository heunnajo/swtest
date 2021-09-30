package ss;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class CrashBrick_DFSinBFS_Str {
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
	static Brick[] startPos;
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
			spots = new int[N];//선택한 열들을 저장
			startPos = new Brick[N];
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
			//N개를 다 선택했으면 (spots배열 완성했다면) N개의 열마다 시작점을 startPos에 저장한다!
			findStart(copy);
			for(int i=0;i<N;i++) {
				Brick cur = startPos[i];
				Crash(copy,cur.x,cur.y,cur.range);//DFS.
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
	static void findStart(int[][] copy) {
		for(int j=0;j<N;j++) {
			int spot = spots[j];
			for(int i=0;i<H;i++) {
				if(copy[i][spot]>0) {
					startPos[j] = new Brick(i,spot,copy[i][spot]);
					break;
				}
			}
		}
	}
	static void Crash(int[][] copy,int x,int y,int cnt) {
		copy[x][y] = 0;
		if(cnt==1)return;
		
		if(cnt==1)return;
		
		for(int d=0;d<4;d++) {
			int nx = x;int ny = y;
			
			for(int k=0;k<cnt;k++) {
				nx+=dx[d];ny+=dy[d];
				
				if(nx<0 || nx>=H ||ny<0||ny>=W||copy[nx][ny]==0) continue;
				Crash(copy,nx,ny,copy[nx][ny]);
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
