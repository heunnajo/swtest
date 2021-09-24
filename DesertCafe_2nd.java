package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class DesertCafe_2nd {
	static int N,T,ans,Map[][];
	static boolean[] visited;
	static int xa,xb,xc,xd,ya,yb,yc,yd;
	static int db,dc,cnt;
	static int[] dx = {1,1,-1,-1};
	static int[] dy = {1,-1,-1,1};
	static void solve() {
		for(int i=0;i<N-2;i++) {//i는 N-3까지
			for(int j=1;j<N-1;j++) {//j는 N-2까지
				dfs(i,j,i,j,0,new boolean[101],1);//시작 좌표 x,y,다음 선택할 좌표 x,y, 디저트 중복체크배열,디저트 갯수
			}
		}
	}
	static void dfs(int sx,int sy,int nx,int ny,int dir,boolean[] visited,int cnt) {
		//이동하기 전의 좌표(nx,ny)도 중복체크!
		visited[Map[nx][ny]] = true;
		
		//1.불가능한 경우
		//if(nx<0 || nx>N-1 || ny<0 || ny>N-1) return;//범위 체크:이 또한 이동하고 난 후 해야하는 것 아닐까?놉 이건재귀함수 호출&실행하고 난 후에 입구에 위치해도 될듯.
		//if(visited[Map[nx][ny]]) return;//중복 체크:이동하고난 후 체크!
		if(dir>3) return;//4가지 방향만 가능
		if(nx<sx) return;//백트랙킹.처음좌표의 x좌표보다 작으면 안된다.
		
		//3.현재 선택하고, 다음 경우 호출
		//이동할 좌표 계산
		nx += dx[dir];ny+=dy[dir];
		if(nx<0 || nx>N-1 || ny<0 || ny>N-1) return;//범위 체크:이 또한 이동하고 난 후 해야하는 것 아닐까?
		if(visited[Map[nx][ny]]) return;//중복 체크:이동하고난 후 체크!
		
		//2.정답 찾은 경우
		if(nx==sx && ny == sy) {//처음 좌표를 넣자마자 이 조건에 해당되서 리턴된다.그렇기 때문에 정답찾은 경우는 nx,ny를 갱신하고 난 후에 필요한듯하다!
			ans = Math.max(ans, cnt);//최댓값 도출인데 어떻게 왜 -1이 나오지?
			return;
		}
		
		visited[Map[nx][ny]] = true;
		dfs(sx,sy,nx,ny,dir,visited,cnt+1);
		dfs(sx,sy,nx,ny,dir+1,visited,cnt+1);
		visited[Map[nx][ny]] = false;//재귀함수 리턴된후 복원처리 필수.다음 선택을 위해.
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			Map = new int[N][N];
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					Map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			ans = -1;//디저트 갯수 최댓값 도출 위해 불가능한 작은값 셋팅.
			solve();
			sb.append("#"+t+" "+ans+"\n");
		}
		System.out.println(sb);
	}

}
//for(int i=0;i<N;i++) {
//	for(int j=0;j<N;j++) {
//		System.out.print(Map[i][j]+" ");
//	}
//	System.out.println();
//}