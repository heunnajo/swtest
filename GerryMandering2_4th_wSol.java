package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.LinkedList;
public class GerryMandering2_4th_wSol {
	static final int INF = 987654321;
	static int N,Map[][],Area[][];
	static void fill(int r,int c,int value) {
		if(r<0 || r>N-1 || c<0 || c>N-1) return;
		if(Area[r][c] != 0) return;
		
		
		Area[r][c] = value;
		fill(r-1,c,value);
		fill(r+1,c,value);
		fill(r,c-1,value);
		fill(r,c+1,value);
	}
	static int solve() {
		int ret = INF;//최솟값 찾기 위해
		//조건을 만족하는 모든 x,y,d1,d2 를 만들어낸다.
		for(int x=0;x<N-2;x++) {//N-3까지
			for(int y=1;y<N-1;y++) {//N-2까지
				for(int d1 = 1;x+d1<=N-2&&y-d1>=0;d1++) {
					for(int d2=1;x+d1+d2<=N-1&& y+d2<=N-1;d2++) {
						//각 경우의 수마다 선거구를 새로 정하기 때문에 Area배열도 초기화해야한다.
						//자바는 1차원 배열을 초기화하는 메서드가 있기 때문에, 2차원은 for문으로 초기화해준다.
						for(int i=0;i<N;i++) {
							Arrays.fill(Area[i], 0);
						}
						//d1으로 마킹할 수 있는 1번,4번 경계선
						for(int i=0;i<=d1;i++) {
							Area[x+i][y-i] = 5;
							Area[x+d2+i][y+d2-i] = 5;
						}
						//d2로 마킹할 수 있는 2번,3번 경계선
						for(int i=0;i<=d2;i++) {
							Area[x+i][y+i] = 5;
							Area[x+d1+i][y-d1+i] = 5;
						}
						//그때의 시작점 (x,y)기준으로 선거구 마킹한다!
						//1번 선거구
						for(int r=x-1;r>=0;r--) {
							Area[r][y] = 1;//열 고정 
						}
						//2번 선거구
						for(int c=y+d2+1;c<N;c++) {
							Area[x+d2][c] = 2;//행 고정 
						}
						//3번 선거구
						for(int c=y-d1-1;c>=0;c--) {
							Area[x+d1][c] = 3;//행 고정 
						}
						//4번 선거구
						for(int r=x+d1+d2+1;r<N;r++) {
							Area[r][y-d1+d2] = 4;//열 고정 
						}
						
						//이제 나머지 구역을 해당 선거구 번호로 채우면 된다.
						fill(0,0,1);
						fill(0,N-1,2);
						fill(N-1,0,3);
						fill(N-1,N-1,4);
						
						int people[] = new int[6];
						for(int r=0;r<N;r++) {
							for(int c=0;c<N;c++) {
								people[Area[r][c]]+= Map[r][c];
							}
						}
						//Area[r][c]가 0인 값이 people[0]에 저장될 것이다.
						//5번 선거구역 인구수는 5번 경계 + 5번 경계 내부이다
						//=Arr[r][c]가 5인 칸과 Arr[r][c]가 0인 칸들의 인구수를 더하면 되기 때문에
						//people[5] = people[5] + people[0]이 된다.
						people[5] += people[0];
						
						int minP=INF,maxP=0;
						for(int i=1;i<=5;i++) {
							minP = Math.min(minP, people[i]);
							maxP = Math.max(maxP, people[i]);
						}
						ret = Math.min(ret, maxP-minP);
					}
				}
			}
		}
		
		return ret;
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
		
		System.out.println(solve());
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