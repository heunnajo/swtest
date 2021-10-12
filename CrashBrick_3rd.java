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
	static class Point{
		int x,y,range;
		Point(int x,int y,int range){
			this.x = x;
			this.y = y;
			this.range = range;
		}
	}
	static void solve(int cnt) {
		if(cnt == N) {//디버깅 후 N으로 수정!
//			System.out.println();
//			for(int i=0;i<N;i++)
//				System.out.print(selected[i]+" ");
//			System.out.println();
//			System.out.println("선택한 열:"+selected[0]);
			int sum = 0;//각 경우마다 남은 벽돌의 갯수 저장.
			//N개열 마다 BFS로 벽돌 제거
			for(int j=0;j<N;j++) {//디버깅 후 N으로 수정!
//				System.out.println("여기 들어오니?");
				BFS(j);//선택한 열j마다 BFS로 벽돌제거
			}
//			System.out.println("BFS 후 벽돌 제거된 tmpMap");
//			print();
			sum = cntBrick(tmpMap);
			ans = Math.min(sum, ans);
			return;
		}
		for(int i=0;i<W;i++) {
			selected[cnt] = i;
			solve(cnt+1);
		}
		
	}
	static void BFS(int j) {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[H][W];
		//벽돌이 있는가장 첫번째 행을 찾는다:BFS 탐색의 시작점이 된다!
		//System.out.println("BFS 실행하니?");
		for(int i=0;i<H;i++) {
			if(tmpMap[i][j]>0) {
				//System.out.println("벽돌위치한 첫행 찾았니?");
				q.add(new Point(i,j,tmpMap[i][j]));
				visited[i][j] = true;
				tmpMap[i][j] = 0;
				//System.out.printf("BFS 시작점:(%d,%d)",i,j);
				//System.out.println();
				break;//찾았으면 반복문 탈출!
			}
		}
//		int size=0;
		while(!q.isEmpty()) {
//			size = q.size();//디버깅용.현재 큐 크기만큼만 탐색하고 종료
//			for(int s=0;s<size;s++) {
				Point cur = q.remove();
				
				//영향권 범위 내에서 상하좌우 방향으로 탐색 반복한다!
				for(int r=1;r<cur.range;r++) {
					for(int dir=0;dir<4;dir++) {
						int nx = cur.x+dx[dir]*r;
						int ny = cur.y+dy[dir]*r;
						
						if(isOut(nx,ny)||visited[nx][ny]) continue;
						if(tmpMap[nx][ny] == 0) continue;
						
						q.add(new Point(nx,ny,tmpMap[nx][ny]));
						visited[nx][ny] = true;
						tmpMap[nx][ny] = 0;//벽돌 제거 처리
					}
				}
				
//			}
			return;
		}
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>H-1 || y<0 || y>W-1;
	}
	static int cntBrick(int[][] arr) {
		int sum = 0;
		for(int i=0;i<H;i++)
			for(int j=0;j<W;j++)
				if(arr[i][j]>0) sum++;
		return sum;
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
			tmpMap = new int[H][W];
			selected = new int[N];
			
			int cnt=0;
			for(int i=0;i<H;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<W;j++) {
					Map[i][j] = Integer.parseInt(st.nextToken());
					if(Map[i][j]>0) cnt++;
				}
			}
			if(cnt <= N) {//벽돌 갯수가 N개 이하이면 탐색하지 않고 정답은 바로 0. 
				sb.append("#"+t+" "+0+"\n");
				continue;
			}
			ans = INF;
			tmpMap = copyMap();//연산하기 전에 배열 복사해서 써야함!
			solve(0);
			sb.append("#"+t+" "+ans+"\n");
		}
		System.out.print(sb);
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
