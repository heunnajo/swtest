package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.LinkedList;
public class GerryMandering2_3rd {
	static int ans,N,Map[][],Area[][];
	static int[] dx = {1,1,-1,-1};
	static int[] dy = {-1,1,1,-1};
//	static Point A,B,C,D;
//	static LinkedList<Point> fourP;
	static void solve() {
		//1번 선택점 먼저 선택.
		for(int i=0;i<N-2;i++) {//N-3까지
			for(int j=1;j<N-1;j++) {//N-2까지
				dfs(i,j,i,j,0,new int[N][N],new LinkedList<Point>());//각 위치에서 4가지 방향 중 하나를 선택.
			}
		}
	}
	static class Point{
		int x,y;
		Point(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
	static void dfs(int sx,int sy,int nx,int ny,int dir,int[][] Area,LinkedList<Point> fourP) {
		//재귀 종료할 때
		//1.불가능한 경우
		//4가지 방향을 모두 순회한 경우 종료.
		Area[nx][ny] = 5;
		if(dir>3) return;
		nx += dx[dir];ny += dy[dir];
		
		//2.정답 찾은 경우
		if(nx == sx && ny == sy) {
			//countPop(fourP);//사각형 경로형성했으면 그때 4개의 꼭짓점 저장한 fourP로 인구수를 구할 수 있다!?
			//fourP.addFirst(new Point(sx,sy));
			countPop(fourP,sx,sy);
			System.out.println("꼭짓점 갯수: "+fourP.size());
			return;
		}
		if(nx<sx) return;
		//1.불가능한 경우>범위 벗어나는 경우
		if(nx<0 || nx>N-1 || ny<0 || ny>N-1) return;
		//1.불가능한 경우>이미 선택한 경우
		if(Area[nx][ny] == 5) return;
		
		//현재 위치 방향 정하고, 다음 경우 호출
		Area[nx][ny] = 5;//경계선 선택 처리
		dfs(sx,sy,nx,ny,dir,Area,fourP);
		fourP.add(new Point(nx,ny));//꼭짓점!
		dfs(sx,sy,nx,ny,dir+1,Area,fourP);
		//fourP.add(new Point(nx,ny));//
		Area[nx][ny] = 0;//경계선 선택 해제 처리
		fourP.remove(fourP.size()-1);
		
	}
	static void countPop(LinkedList<Point> fourP,int sx,int sy) {//첫 시작 좌표 처음에 추가해서 선거구별로 인구수 카운팅!
//	static void countPop(LinkedList<Point> fourP) {
		fourP.addFirst(new Point(sx,sy));
		System.out.println("꼭짓점 갯수: "+fourP.size());
		int M = 0;int m = 0;
		
		//각 선거구마다 인구 수 구한다!
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(Area[i][j] != 0) continue;//이미 선거구 마킹된 것.
				//1번 선거구
				if(i<=N-3) {
					
				}
			}
		}
		
		ans = Math.min(ans, M-m);
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		
		Map = new int[N][N];
		Area = new int[N][N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				Map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		ans = 0;
		solve();
		
		//System.out.println(ans);
	}

}
//남과 비교하지말고 내 페이스대로 헤쳐나가자!잘 할 수 있고, 잘 할 것이니까!
//just simple thing. just do it.
//for(int i=0;i<N;i++) {
//	for(int j=0;j<N;j++) {
//		System.out.print(Map[i][j]+" ");
//	}
//	System.out.println();
//}