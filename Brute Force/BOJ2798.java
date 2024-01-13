//블랙잭
import java.util.*;
import java.io.*;

public class BOJ2798 {
	static int Ans,Diff,N,M;
	static int[] Num;
	static boolean[] Selected;
	static boolean[] Visited;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Num = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			Num[i] = Integer.parseInt(st.nextToken());
		}
		Selected = new boolean[N];
		Visited = new boolean[N];
		
		Ans = 0;
		Diff = Integer.MAX_VALUE;
		go(0,0);
		System.out.println(Ans);
	}
	static void go(int idx,int from) {
		//1.종료 : N개 선택 다 한 경우, M과 차이 구한 후 최솟값 도출
		if(idx == 3) {
			int curSum = 0;
			int curDiff = 0;
			//System.out.print("Selected: ");
			for(int i=0;i<N;i++) {
				if(Selected[i]) {
					//System.out.print(i+", ");
					curSum += Num[i];
				}
			}
			//System.out.println();
			curDiff = Math.abs(M-curSum);
			//System.out.println(curSum);
			//System.out.println(curDiff);
			if(curSum <= M && curDiff < Diff) {
				Diff = curDiff;
				Ans = curSum;
			}
			return;
		}
		//2.현재 선택 & 다음 재귀 호출
		for(int i=from;i<N;i++) {
			if(Selected[i]) continue;
			Selected[i] = true;
			go(idx+1,i+1);
			Selected[i] = false;
		}
	}

}
