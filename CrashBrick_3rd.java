package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;
public class CrashBrick_3rd {
	static final int INF = 987654321;
	static int ans,N,W,H,Map[][],tmpMap[][];//W열 H행, N:구슬 쏘는 횟수=W개 열 중에서 N개를 선택!
	static int[] selected;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static boolean[][] visited;
	static class Point{
		int x,y,range;
		Point(int x,int y,int range){
			this.x = x;
			this.y = y;
			this.range = range;
		}
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>H-1 || y<0 || y>W-1;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			Map = new int[H][W];
			selected = new int[N];
			ans = INF;
			
			//int cnt=0;
			for(int i=0;i<H;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<W;j++) {
					Map[i][j] = Integer.parseInt(st.nextToken());
					//if(Map[i][j]>0) cnt++;
				}
			}
//			if(cnt <= N) {//벽돌 갯수가 N개 이하이면 탐색하지 않고 정답은 바로 0. 
//				sb.append("#"+t+" "+0+"\n");
//				continue;
//			}
			
			//tmpMap = copyMap();//연산하기 전에 배열 복사해서 써야함!:N개 열 선택한 후, 벽돌 깨기 BFS할 때!
			solve(0);
			sb.append("#"+t+" "+ans+"\n");
		}
		System.out.print(sb);
	}
	static void solve(int cnt) {
		if(cnt == N) {//디버깅 후 N으로 수정!
			int[][] copy = copyMap();
			int sum = 0;//각 경우마다 남은 벽돌의 갯수 저장.
			for(int j=0;j<N;j++) {//디버깅 후 N으로 수정!
				visited = new boolean[H][W];
				BFS(copy,selected[j]);//선택한 열j마다 BFS로 벽돌제거
				down(copy);
			}
			sum = cntBrick(copy);
			ans = Math.min(sum, ans);
			return;
		}
		for(int i=0;i<W;i++) {
			selected[cnt] = i;
			solve(cnt+1);
		}
	}
	static int cntBrick(int[][] arr) {
		int sum = 0;
		for(int i=0;i<H;i++)
			for(int j=0;j<W;j++)
				if(arr[i][j]>0) sum++;
		return sum;
	}
	static void down(int[][] copy) {
		Queue<Integer> q = new LinkedList<>();
		for(int j=0;j<W;j++) {
			for(int i=H-1;i>=0;i--) {
//				if(copy[i][j] != 0) q.add(copy[i][j]);
				if(copy[i][j] > 0) q.add(copy[i][j]);
			}
			for(int i=H-1;i>=0;i--) {
//				while(!q.isEmpty()) {
//					copy[i][j] = q.remove();
//				}
//				copy[i][j] = 0;
				if(!q.isEmpty()) {
					copy[i][j] = q.remove();
				} else copy[i][j] = 0;
			}
		}
	}
	static void BFS(int[][] copy,int j) {
		Queue<Point> q = new LinkedList<>();//BFS에 쓰이는 큐 여기서 생성했는데 이외에 다른 곳에서도 필요한가?
		//boolean[][] visited = new boolean[H][W];//정답코드에서는 BFS 밖에 만듦.
		//벽돌이 있는가장 첫번째 행을 찾는다:BFS 탐색의 시작점이 된다!
		for(int i=0;i<H;i++) {
			if(copy[i][j]>0) {
				q.add(new Point(i,j,copy[i][j]));
				//visited[i][j] = true;
				//copy[i][j] = 0;
				break;//찾았으면 반복문 탈출!
			}
		}
		while(!q.isEmpty()) {
			Point cur = q.remove();
			int nx,ny;
			//영향권 범위 내에서 상하좌우 방향으로 탐색 반복한다!
			for(int r=0;r<cur.range;r++) {//1부터 cur.range-1까지 해버려서 오답의 원인. 1부터 cur.range까지 해도 오답. 0부터 cur.range-1까지해야 정답처리.
				for(int dir=0;dir<4;dir++) {
					nx = cur.x+dx[dir]*r;
					ny = cur.y+dy[dir]*r;
					
					if(isOut(nx,ny)||visited[nx][ny]) continue;
					//if(copy[nx][ny] == 0) continue;//0일 때에도 탐색이동하나봄!!?
					
					q.add(new Point(nx,ny,copy[nx][ny]));
					visited[nx][ny] = true;
					copy[nx][ny] = 0;//벽돌 제거 처리
				}
			}
		}
	}
	static int[][] copyMap() {
		int[][] tmp = new int[H][W];
		for(int i=0;i<H;i++) {
			for(int j=0;j<W;j++) {
				tmp[i][j] = Map[i][j];
			}
		}
		return tmp;
	}
	static void print() {
		for(int i=0;i<H;i++) {
			for(int j=0;j<W;j++) {
				System.out.print(tmpMap[i][j]+" ");
			}
			System.out.println();
		}
	}

}
