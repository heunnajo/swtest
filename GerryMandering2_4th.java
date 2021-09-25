package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.LinkedList;
public class GerryMandering2_4th {
	static final int INF = Integer.MAX_VALUE;
	static int N,Map[][],Area[][];
	static int[] dx = {1,1,-1,-1};
	static int[] dy = {-1,1,1,-1};
	static void fill(int x,int y,int value) {
		if(x<0 || x>N-1 || y<0 || y>N-1) return;
		if(Area[x][y] != 0) return;
		
		Area[x][y] = value;
		fill(x-1,y,value);
		fill(x+1,y,value);
		fill(x,y-1,value);
		fill(x,y+1,value);
	}
	static int solve() {
		int ans=INF;
		//x,y,d1,d2 모두 선택.
		for(int x=0;x<N-2;x++) {//N-3까지
			for(int y=1;y<N-1;y++) {//N-2까지
				for(int d1=1;x+d1<=N-2 && y-d1>=0;d1++) {
					for(int d2=1;x+d1+d2<=N-1&&y+d2<=N-1;d2++) {
						//선거구 마킹 배열 초기화
						for(int i=0;i<N;i++) {
							Arrays.fill(Area[i], 0);
						}
						//1,4번 경계선부터 선택
						for(int i=0;i<=d1;i++) {
							Area[x+i][y-i] = 5;
							Area[x+d2+i][y+d2-i] = 5;
						}
						//2,3번 경계선부터 선택
						for(int i=0;i<=d2;i++) {
							Area[x+i][y+i] = 5;
							Area[x+d1+i][y-d1+i] = 5;
						}
						//선거구번호대로 경계선 마킹!
						//1번 선거구:열고정, 행 감소
						for(int r=x-1;r>=0;r--) {
							Area[r][y] = 1;
						}
						//2번 선거구:행고정,열 증가
						for(int c=y+d2+1;c<N;c++) {
							Area[x+d2][c] = 2;
						}
						//3번 선거구:행고정, 열감소 
						for(int c=y-d1-1;c>=0;c--) {
							Area[x+d1][c] = 3;
						}
						//4번 선거구:열 고정,행감소
						for(int r=x+d2+d1+1;r<N;r++) {
							Area[r][y+d2-d1] = 4;
						}
						//나머지 구역들 각 선거구 번호로 채우기
						fill(0,0,1);
						fill(0,N-1,2);
						fill(N-1,0,3);
						fill(N-1,N-1,4);
						
						//각 선거구 인구수 구하기
						int[] people = new int[6];
						for(int i=0;i<N;i++) {
							for(int j=0;j<N;j++) {
								people[Area[i][j]] += Map[i][j];
							}
						}
						people[5] += people[0];
						
						int min = INF, max = 0;
						for(int i=1;i<=5;i++) {
							max = Math.max(max, people[i]);
							min = Math.min(min, people[i]);
						}
						ans = Math.min(ans, max-min);
					}
				}
			}
		}
		return ans;
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