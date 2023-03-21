package boj;
import java.util.*;
import java.io.*;

//스타트와 링크
public class BOJ14889 {
	static int N,Ans;
	static int[][] S;
	static int[] Selected;
	static boolean[] Visited;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		S = new int[N][N];
		Selected = new int[N/2];
		Visited = new boolean[N];
		
		StringTokenizer st;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				S[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		Ans = 987654321;
		go(0,0);
		System.out.println(Ans);
	}
	static void go(int idx,int from) {
		//1.종료
		if(idx == N/2) {
			int tmp = getDiff();
			if(Ans > tmp) Ans = tmp;
			return;
		}
		//부등호 판단 유의
		if(idx < N/2 && from >= N) {
			return;
		}
		
		//2.현재 경우 선택
		for(int i=from;i<N;i++) {
			if(Visited[i]) continue;
			Visited[i] = true;
			Selected[idx] = i;
			go(idx+1,i+1);
			Visited[i] = false;
			Selected[idx] = -1;
		}
	}
	static int getDiff() {
		int sumA = 0;
		int sumB = 0;
		
		for(int i=0;i<N/2;i++) {
			for(int j=0;j<N/2;j++) {
				if(i==j) continue;
				sumA += S[Selected[i]][Selected[j]];
			}
		}
		
		//sumB : Selected에서 불포함된 값들의 합.
		boolean[] A = new boolean[N];
		for(int i=0;i<N/2;i++) {
			A[Selected[i]] = true;
		}
		int[] B = new int[N/2];
		int idx=0;
		for(int i=0;i<N;i++) {
			if(!A[i]) {
				B[idx] = i;
				idx++;
			}
		}
		
		for(int i=0;i<N/2;i++) {
			for(int j=0;j<N/2;j++) {
				if(i==j) continue;
				sumB += S[B[i]][B[j]];
			}
		}
		return Math.abs(sumA-sumB);
	}

}
//선택한 숫자들 확인 O
//for(int i=0;i<N/2;i++) {
//	System.out.print(Selected[i]+" ");
//}
//System.out.println();
//		for(int i=0;i<N;i++) {
//			for(int j=0;j<N;j++) {
//				System.out.print(S[i][j]+" ");
//			}
//			System.out.println();
//		}