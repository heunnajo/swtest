package ss;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class ChickenDelivery_3rd {
	static int hCnt, cCnt,ans,N,M,Map[][];
	static Point[] Houses;
	static Point[] Chickens;
	static int cntOne(int num) {
		int sum = 0;
		while(num>0) {
			if((num & 1) != 0) {sum++;}
			num = num>>1;//오른쪽으로 1씩 시프트해서 감소시킨다.
		}
		return sum;
	}
	static void solve() {
		for(int subset = 0;subset<(1<<cCnt);subset++) {//총 치킨집갯수 cCnt비트에 대해서 비트마스킹으로 모든 경우의수 구함! 
			if(cntOne(subset) == M) {
				int cityDist = 0;//현재 경우의 도시거리 구한다!
				for(int h=0;h<hCnt;h++) {
					Point curH = Houses[h];
					int dist = Integer.MAX_VALUE;//최솟값 갱신을 위해 큰값 셋팅
					int tmpDist = 0;//현재 구하는 치킨거리값
					for(int m=0;m<M;m++) {
						if((subset &(1<<m)) != 0) {
							Point curChick = Chickens[m];
							tmpDist = Math.abs(curH.x-curChick.x)+Math.abs(curH.y-curChick.y);
							//dist = dist > tmpDist ? tmpDist:dist;//현재 집의 치킨거리 최소거리로 갱신!
							dist = Math.min(dist, tmpDist);//현재 집의 치킨거리 최소거리로 갱신!
						}
					}
					cityDist += dist;//hCnt개 집마다 치킨거리(최소) 구해서 cityDist에 더해준다!
				}
				//현재 경우의 cityDist를 최솟값 갱신해서 최종 정답변수에 저장!
				//ans = ans > cityDist ? cityDist:ans;
				ans = Math.min(ans, cityDist);
			}
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Map = new int[N][N];
		hCnt = 0; cCnt = 0;
		Houses = new Point[2500];//몇개인지 알 수 없으니 일단 최대갯수로.
		Chickens = new Point[2500];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				Map[i][j] = Integer.parseInt(st.nextToken());
				if(Map[i][j] == 1) {
					Houses[hCnt++] = new Point(i,j);
				}
				else if(Map[i][j] == 2) {
					Chickens[cCnt++] = new Point(i,j);
				}
			}
		}
		ans = Integer.MAX_VALUE;
		solve();
		System.out.println(ans);
	}

}

//		int k=21;//10101
//		int k2=7;//10101
//		System.out.println(cntOne(k2));
//		for(int i=0;i<N;i++) {
//			for(int j=0;j<N;j++) {
//				System.out.print(Map[i][j]+ " ");
//			}
//			System.out.println();
//		}