package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class DesertCafe {
	static int N,T,ans,Map[][];
	static boolean[] visited;
	static int xa,xb,xc,xd,ya,yb,yc,yd;
	static int db,dc,cnt;
	//static void combB(int sx,int sy,int ex,int ey,int db) {
	static void combB(int db) {//변위값 존재해야함!
		for(int i=xa+1;i<=N-2;i++) {
			for(int j=ya+1;j<=N-1;j++) {
				if(!visited[Map[i][j]]) {
					xb = i+db;yb = j+db;//범위체크 필요할뜻.
					combCD();
				}
			}
		}
	}
	static void combCD() {
		for(int i=xb+1;i<=N-1;i++) {
			for(int j=1;j<=yb-1;j++) {
				if(!visited[Map[i][j]]) {
					for(dc=1;;dc++) {
						xc = xb+dc;yc = yb-dc;
						cnt+=dc;
						xd = xc+Math.abs(xb-xa);
						yd = yc-Math.abs(yb-ya);
						cnt+=db;cnt+=dc;
					}
				}
			}
		}
	}
	static void init() {
		visited = new boolean[101];
		xa = xb =xc = xd = ya = yb = yc = yd = -1;//초기화 
		db = dc = -1;
		cnt=0;//각 사각형마다 디저트 갯수 저장.
	}
	static void solve() {
		//2차원 배열 순회하면서 꼭짓점 4개(a,b,c,d)선택하고
		//꼭짓점 선택하면서 중복 디저트 제거하고, 각 꼭짓점 변위값에 따라 디저트 갯수 카운팅한다.
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				init();//사각형 경우의 수 시작
				if(0<=i && i<=N-3 && 1<=j && j<=N-2) {//1.a
					xa = i;ya = j;
					cnt++; visited[Map[xa][ya]] = true;
					//2.b 선택
					for(db=1;;db++) {
						combB(db);//xb,yb 정해짐.
					}
				}
				ans = Math.max(ans, cnt);
			}
		}
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
			ans = Integer.MIN_VALUE;//최댓값 도출 위해 작은값 셋팅.
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