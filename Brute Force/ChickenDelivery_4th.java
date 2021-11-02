package ss;
import java.io.*;
import java.util.*;
public class ChickenDelivery_4th {
	static int ans,N,M,Map[][],hCnt,cCnt;
	static class Point{
		int x,y;
		Point(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
	static Point[] Houses;
	static Point[] Chickens;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Map = new int[N][N];
		hCnt = cCnt = 0;
		
		Houses = new Point[2*N];
		Chickens = new Point[13];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				Map[i][j] = Integer.parseInt(st.nextToken());
				if(Map[i][j]==1) {
					Houses[hCnt++] = new Point(i,j);
				} else if(Map[i][j] == 2) {
					Chickens[cCnt++] = new Point(i,j);
				}
			}
		}
		ans = 987654321;//최솟값 갱신을 위해 충분히 큰값으로 셋팅.이것 조차도 정수저장범위를 벗어나며 ㄴ어떻하나!?
		solve();
		System.out.println(ans);
	}
	static void solve() {
		for(int subset=0;subset<(1<<cCnt);subset++) {//전체 치킨집 갯수 모두 비트연산. 모든 경우 다 구한다!
			if(cntOne(subset)==M) {//M개 고른 치킨집 경우의 수.
				int cityDist = 0;//각 경우마다 도시 치킨 거리 구한다.
				for(int h=0;h<hCnt;h++) {
					Point curHouse = Houses[h];
					int dist = 200;//현문제조건제한 고려해서 최솟값 도출 위해 가장 큰 값으로 셋팅.
					for(int c=0;c<cCnt;c++) {
						if((subset&(1<<c))>0){//선택한 치킨집과 현재 집과 거리 구한다.
							Point curChic = Chickens[c];
							dist = Math.min(dist,
							Math.abs(curHouse.x-curChic.x)+Math.abs(curHouse.y-curChic.y));
							
						}
					}
					cityDist += dist;//최소값 치킨거리 다 더해준다.
				}
				ans = Math.min(cityDist, ans);//M개 치킨집 모든 경우의 도시 치킨 거리 최솟값으로 갱신!
			}
		}
	}
	static int cntOne(int num) {
		int sum = 0;
		while(num>0) {
			if((num&1) != 0) sum++;
			num = num>>1;//오른쪽으로 1시프트!
		}
		return sum;
	}
}