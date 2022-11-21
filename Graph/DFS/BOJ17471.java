package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//게리맨더링
public class BOJ17471 {
	static int N,popul[],ans;//N개 구역의 인구수 저장
	static boolean[][] adj;//인접 행렬로 인접 정점 정보 저장
	static boolean[] area;//선거구 A:true, 선거구 B:false
	
	static final int INF = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		popul = new int[N+1];
		adj = new boolean[N+1][N+1];
		ans = INF;
		
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			popul[i] = Integer.parseInt(st.nextToken());
		}
		
		//N개의 정점(구역)마다 연결 정보 주어짐! M (M개의 인접 정점)
		int M = 0; int next = 0;
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			
			M = Integer.parseInt(st.nextToken());
			for(int j=0;j<M;j++) {
				next = Integer.parseInt(st.nextToken());
				
				adj[i][next] = adj[next][i] = true;
			}
		}
		
		for(int i=1;i<=N/2;i++) {
			area = new boolean[N+1];
			selectA(0,1,i);
		}
		
		if(ans == INF) ans = -1;
		System.out.println(ans);
		
	}
	static void selectA(int idx,int from,int aNum) {
		//1.종료 조건
		if(idx == aNum) {
			//연결 여부 확인, 연결되있다면 인구차 최솟값 도출
			if(isConnected(aNum)) {
				int diff = getDiff();
				if(ans > diff) ans = diff;
			}
			return;
		}
		//2.현재 선택, 다음 선택(재귀)
		for(int i=from;i<=N;i++) {
			//2-1.선택O
			area[i] = true;
			selectA(idx+1,i+1,aNum);
			//2-1.선택X(원복)
			area[i] = false;//?!=>맞음.
		}
	}
	static boolean isConnected(int aNum) {
		//전역변서 area와 현재의 선거구 A갯수 aNum으로 선거구 A, 선거구 B의 구역 도출 가능
		int[] areaA = new int[aNum];
		int aIdx = 0;
		int[] areaB = new int[N-aNum];
		int bIdx = 0;
		
		for(int i=1;i<=N;i++) {
			if(area[i]) {areaA[aIdx++] = i;}
			else {areaB[bIdx++] = i;}
		}
		
		//1.A 연결 여부 확인
		boolean[] v = new boolean[aNum];
		
		dfs(0,areaA,aNum,v);//0번째 구역부터 탐색
		
		for(int i=0;i<aNum;i++) {
			if(!v[i]) return false;
		}
		//2.B 연결 여부 확인
		v = new boolean[N-aNum];
		
		dfs(0,areaB,N-aNum,v);//0번째 구역부터 탐색
		
		for(int i=0;i<N-aNum;i++) {
			if(!v[i]) return false;
		}
		return true;
	}
	static void dfs(int cur,int[] info,int num,boolean[] v) {
		v[cur] = true;
		
		//인접한 정점 중 미방문 정점 탐색!
		//탐색 대상은 info
		for(int i=0;i<num;i++) {
			//인접 정보가 없거나 이미 방문했다면 컨티뉴 처리
//			if(!adj[cur][i] || v[info[i]]) continue;
			if(!adj[info[cur]][info[i]] || v[i]) continue;
			dfs(i,info,num,v);
		}
		
	}
	static int getDiff() {
		//현재의 area 정보로 인구합 차이 계산
		int aSum = 0; int bSum = 0;
		
		for(int i=1;i<=N;i++) {
			if(area[i]) {aSum += popul[i];}
			else {bSum += popul[i];}
		}
		
		return Math.abs(aSum-bSum);
	}
	
}

//		//인구수 정보, 인접 정보 확인 : O
//		for(int i=1;i<=N;i++) {
//			System.out.print(popul[i]+" ");
//		}
//		System.out.println();
//		
//		for(int i=1;i<=N;i++) {
//			System.out.print(i+": ");
//			for(int j=1;j<=N;j++) {
//				if(adj[i][j]) System.out.print(j+" ");
//			}
//			System.out.println();
//		}