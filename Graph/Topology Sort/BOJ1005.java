package ss;
//ACM Craft(위상정렬) + DP(Memoization)
import java.io.*;
import java.util.*;

public class BOJ1005 {
	static int N,K,W;//정점의 갯수N, 순서관계 K,목적지 건물번호W
	static ArrayList<Integer>[] need;
	static int[] memo,time;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		while(T-->0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			need = new ArrayList[N];
			memo = new int[N];
			time = new int[N];
			Arrays.fill(memo, -1);
			
			for(int i=0;i<N;i++)
				need[i] = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++)//N개의 건물 건설 시간 저장!
				time[i] = Integer.parseInt(st.nextToken());
			
			int u,v;
			for(int i=0;i<K;i++) {//K개의 순서 관계 저장
				st = new StringTokenizer(br.readLine());
				
				u = Integer.parseInt(st.nextToken())-1;
				v = Integer.parseInt(st.nextToken())-1;
				
				need[v].add(u);
			}
			W = Integer.parseInt(br.readLine())-1;
			sb.append(solve(W)).append("\n");//W에 이르는 최소 시간 저장
		}
		System.out.print(sb);
	}
	static int solve(int x) {//topologySort + DP
		if(memo[x] != -1) return memo[x];
		
		int ret = 0;
		for(int next:need[x]) {
			ret = Math.max(ret, solve(next));
		}
		ret += time[x];
		return memo[x] = ret;
	}
	
}
//			//입력 정보 확인
//			for(int i=0;i<N;i++) System.out.print(Time[i]+" ");
//			for(int i=1;i<=N;i++) {
////				if(inDegree[i] == 0) System.out.println(i);
//				System.out.println(i+"의 진입차수: "+inDegree[i]);
//			}