import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
public class GerryMandering {
	private static final int INF = Integer.MAX_VALUE;
	static int N,Arr[][],Mark[][];
	static void fill(int x,int y,int value) {
		if(x<0 || x>N-1 || y<0 || y>N-1) return;
		if(Mark[x][y] != 0) return;
		
		Mark[x][y] = value;
		fill(x-1,y,value);
		fill(x+1,y,value);
		fill(x,y-1,value);
		fill(x,y+1,value);
	}
	static int solve() {
		int ret = INF;
		for(int i=0;i<=N-3;i++) {
			for(int j=1;j<=N-2;j++){
				for(int d1=1;i+d1<=N-2 && j-d1>=0;d1++) {
					for(int d2=1;i+d1+d2<=N-2 && j+d2<=N-1;d2++) {
						//각각의 경우의 수에 대해 인구수 차이 최소 구한다!
						//0.마킹할 배열 초기화
						Mark = new int[N][N];
						for(int k=0;k<N;k++)
							Arrays.fill(Mark[k], 0);
						//1.5번 경계선부터 먼저 마킹
						for(int k=0;k<=d1;k++) {//1,4
							Mark[i+k][j-k] = 5;
							//Mark[i+k+d2][j-k+d2] = 5;
							Mark[i+k+d2][j-k+d2] = 5;
						}
						for(int k=0;k<=d2;k++) {//2,3
							Mark[i+k][j+k] = 5;
							//Mark[i+d1+k][j+k-d1] = 5;
							Mark[i+k+d1][j+k-d1] = 5;
						}
						//2.1,2,3,4번 선거구 마킹
						for(int r=i-1;r>=0;r--)
							Mark[r][j] = 1;
						for(int c=j+d2+1;c<N;c++)
							Mark[i+d2][c] = 2;
						for(int c=j-d1-1;c>=0;c--)
							Mark[i+d1][c] = 3;
						for(int r=i+d1+d2+1;r<N;r++)
							Mark[r][j-d1+d2] = 4;
						//3.경계선 기준으로 숫자 채운다 - fill(r,c,value)
						fill(0,0,1);
						fill(0,N-1,2);
						fill(N-1,0,3);
						fill(N-1,N-1,4);
						//4.최대인구-최소인구 차이 구한다
						int[] people = new int[6];//1,2,3,4,5 선거구당 인구수 저장!
						for(int r=0;r<N;r++) {
							for(int c=0;c<N;c++) {
								people[Mark[r][c]] += Arr[r][c];
							}
						}
						people[5] += people[0];
						int minP = INF, maxP = 0;
						for(int k=1;k<=5;k++) {
							minP = Math.min(minP, people[k]);
							maxP = Math.max(maxP, people[k]);
						}
						//5.인구 차이 최솟값 저장
						ret = Math.min(ret, maxP-minP);
					}
				}
			}
		}
		return ret;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		Arr = new int[N][N];
		for(int i=0;i<N;i++) {
			String[] input = br.readLine().split(" ");
			for(int j=0;j<N;j++) {
				Arr[i][j] = Integer.parseInt(input[j]);
			}
		}
		System.out.println(solve());
		br.close();
	}

}