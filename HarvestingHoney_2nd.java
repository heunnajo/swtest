package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.LinkedList;

public class HarvestingHoney_2nd {
	static int N,M,C,T,ans;
	static int[][] Map,Profit;
	static void solve() {//1.최대합 구하기 2.일꾼1 먼저 최대합 선택, 일꾼2 최대합 선택, (일꾼1+일꾼2)최댓값 도출  
		makeProfit();
		
		//makeProfit()으로 구한 Profit최대합 배열을 이용하여 일꾼1,2가 시작점 선택한다.
		for(int i=0;i<N;i++) {
			for(int j=0;j<=N-M;j++) {
				combination(i,j+M,1,Profit[i][j]);
			}
		}
	}
	static void combination(int x,int y,int cnt,int sum) {
		//정답 찾은 경우
		if(cnt == 2) {
			ans = Math.max(ans, sum);
			return;
		}
		
		for(int i=x;i<N;i++) {
			for(int j=y;j<=N-M;j++) {
				combination(i,j,cnt+1,sum+Profit[i][j]);
			}
			y=0;//다음행으로 넘어갈 때 시작점 열0으로 초기화!
		}
	}
	static void makeProfit() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<=N-M;j++) {
				profitSubset(i,j,0,0,0);
			}
		}
	}
	static void profitSubset(int x,int y,int sum,int cnt,int maxProrfit) {
		//불가능한 경우 : sum > C
		if(sum>C) return;
		//정답 찾은 경우 : M개 구간 C이내의 합을 다 구한 경우 최대합으로 갱신해서 Profit배열에 저장!
		if(cnt == M) {
			Profit[x][y-M] = Math.max(Profit[x][y-M], maxProrfit);
			return;
		}
		//현재 칸을 합에 포함할지 선택 결정, 다음 경우 호출
		//1.선택O
		profitSubset(x,y+1,sum+Map[x][y],cnt+1,maxProrfit+Map[x][y]*Map[x][y]);
		//2.선택X
		profitSubset(x,y+1,sum,cnt+1,maxProrfit);
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			Map = new int[N][N];
			Profit = new int[N][N];
			ans = 0;
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					Map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
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
