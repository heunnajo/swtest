package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.LinkedList;

public class HarvestingHoney_Solution {
	static int N,M,C,T,res;
	static int[][] Map,Profit;//Profit:꿀 담을 용기
	
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
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					Map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			res = 0;
			process();
			sb.append("#"+t+" "+res+"\n");
		}
		System.out.println(sb);
	}
	private static void process() {//일꾼 A의 최대 부분합 조합 도출하는 함수인듯.
		//꿀 채취가능 구간에서 얻을 수 있는 최대 수익
		makeProfit();
		//일꾼 A가 채취할 구간
		for(int i=0;i<N;i++) {
			for(int j=0;j<=N-M;j++) {
				combination(i,j+M,1,Profit[i][j]);//2번째 인자는 일꾼B가 채취 시작가능한 점!j=0,M=5:j+M=5
			}
		}
	}
	private static void combination(int x,int y,int cnt,int sum) {
		if(cnt == 2) {//재귀 종료하는 조건
			res = Math.max(res, sum);
			return;
		}
		//일꾼 B가 채취할 구간
		for(int i=x;i<N;i++) {
			for(int j=y;j<=N-M;j++) {
				combination(i,j,cnt+1,sum+Profit[i][j]);
			}
			y=0;
		}
	}
	private static void makeProfit() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<=N-M;j++) {//M개 선택한 윈도우
				//여기서 얻을 수 있는 최대 수익(부분집합)
				profitSubset(i,j,0,0,0);
			}
		}
	}
	private static void profitSubset(int i,int j,int cnt,int sum,int totalSum) {
		if(sum > C)return;
		if(cnt == M) {
			//해당 구간에서 최대 수익 갱신
			Profit[i][j-M] = Math.max(Profit[i][j-M], totalSum);
			return;
		}
		//이 꿀 채취해보자!
		profitSubset(i,j+1,cnt+1,sum+Map[i][j],totalSum+Map[i][j]*Map[i][j]);//점수 제곱을 합산해가면서 재귀함수 실행,최댓값 도출
		profitSubset(i,j+1,cnt+1,sum,totalSum);
		//이 꿀 채취 안한다!
	}
}
//for(int i=0;i<N;i++) {
//	for(int j=0;j<N;j++) {
//		System.out.print(Map[i][j]+" ");
//	}
//	System.out.println();
//}
