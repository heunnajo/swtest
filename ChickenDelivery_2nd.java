package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
public class ChickenDelivery_2nd {
	static int N, M,Map[][],CshopCnt,HouseCnt;
	static final int INF = Integer.MAX_VALUE;
	static class Point{
		int x,y,dist;
		Point(int x,int y,int dist){
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
	static Point[] Cshops = new Point[13];
	static Point[] Houses;
	static int OneCnt(int N) {
		int cnt=0;
		while(N>0) {
			if((N&1) != 0) {cnt++;}//1이면 갯수 증가시킨다!
			N = N>>1;//N은 오른쪽으로 한칸씩 시프트!
		}
		return cnt;
	}
	static int solve() {
		int ans = INF;//dis들 중 최솟값을 고르기 위해.
		for(int subset = 0;subset<(1<<CshopCnt);subset++) {
			if(OneCnt(subset)==M) {//M개 부분집합(치킨갯수)에 대해서 연산!
				int sum = 0;//현재 부분집합의 치킨 거리 합을 저장한다.
				for(int h = 0;h<HouseCnt;h++) {
					int dist = INF;//선택한 각 치킨집과의 거리를 각각 구하고, 최솟값을 저장한다!
					for(int c=0;c<CshopCnt;c++) {
						if((subset&(1<<c)) == 1) {//c번째 치킨집을 선택했다는 것이므로 h번째집과 c번째 치킨집 거리 구한다!
							int tmp = Math.abs(Houses[h].x-Cshops[c].x)+Math.abs(Houses[h].y-Cshops[c].y);
							dist = Math.min(dist,tmp);//거리의 최솟값이 치킨거리 dist가 된다!
						}
					}//치킨집 반복문을 돌면서 h번째 집의 치킨거리를 구한다.
					sum += dist;//h번째 치킨거리들을 M 부분집합 A의 sum에 더한다!
				}
				ans = Math.min(ans, sum);//각각의 집에 대한 치킨거리들 다 구한후(집 반복문 끝난 후)
			}//M 부분집합 if문.
		}//subset-for문.
		return ans;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		Map = new int[N][N];
		Houses = new Point[2*N];
		HouseCnt = 0;
		CshopCnt = 0;
		for(int i=0;i<N;i++) {
			input = br.readLine().split(" ");
			for(int j=0;j<N;j++){
				Map[i][j] = Integer.parseInt(input[j]);
				if(Map[i][j] == 2) {
					Cshops[CshopCnt] = new Point(i,j,0);
					CshopCnt++;
				}
				else if(Map[i][j] == 1) {//오든 집 리스트를 배열로 저장?
					Houses[HouseCnt] = new Point(i,j,0);
					HouseCnt++;
				}
			}
		}
		System.out.println(solve());
		
	}

}
//System.out.println(CshopCnt);
//for(int i=0;i<N;i++) {
//	for(int j=0;j<N;j++){
//		System.out.print(Map[i][j]+" ");
//	}
//	System.out.println();
//}