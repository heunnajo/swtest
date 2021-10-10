package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class DessertCafe {
	static final int INF = Integer.MAX_VALUE;
	static int ans,N,Map[][];
	static boolean[] visited;
	static int[] dx = {1,1,-1,-1};
	static int[] dy = {1,-1,-1,1};
	static void initV() {
		visited = new boolean[101];
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>N-1 || y<0 || y>N-1;
	}
	static void solve() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				initV();
				dfs(i,j,i,j,0,0,true);
			}
		}
	}
	static void dfs(int sx,int sy,int x,int y,int dir,int cnt,boolean first) {
		//1.재귀 종료 조건
		//1-1.정답 찾은 경우:초기 실행에는 아래 if문 실행X. 초기에만 호출시 true 넘겨주고 바로 false로 바꾼다.
		if(!first && x== sx && y == sy) {
			ans = Math.max(ans, cnt);
			return;
		}
		first = false;//초기 실행 이후 first는 false라서 초기 위치값과 동일한 위치로 오게 되면 그 때의 cnt출력해야하는데!
		//1-2.불가능한 경우
		if(dir == 4) return;
		if(x<=sx) return;//현재 x가 감소하는 구간에서 백트랙킹:초기실행에서는 해당X.
		
		//이동처리
		x += dx[dir]; y+=dy[dir];
		if(isOut(x,y)) return;
		if(visited[Map[x][y]]) return;//이동후 위치에 대해 백트랙킹
		
		//2.현재 경우 선택, 3.다음 경우 호출
		dfs(sx,sy,x,y,dir,cnt+1,first);
		dfs(sx,sy,x,y,dir+1,cnt+1,first);
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			N = Integer.parseInt(br.readLine());
			
			Map = new int[N][N];
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					Map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			ans = -1;//최댓값 찾기 위해 작은 값을 넣는다. 못 찾는 경우 -1을 출력해야함.
			solve();
			//if(ans == INF) ans = -1;//solve 했는데도 INF로 남아있다면 -1을 정답으로 출력!
			sb.append("#"+t+" "+ans+"\n");
		}
		System.out.print(sb);
	}

}

//			for(int i=0;i<N;i++) {
//				for(int j=0;j<N;j++) {
//					System.out.print(Map[i][j]+" ");
//				}
//				System.out.println();
//			}