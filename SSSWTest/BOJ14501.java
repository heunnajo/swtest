package boj;
import java.util.*;
import java.io.*;
//퇴사
public class BOJ14501 {
	static int N,Ans;
	static int[] T,P;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		T = new int[N];
		P = new int[N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		Ans = 0;
		go(0,0);
		System.out.println(Ans);
	}
	static void go(int idx,int sum) {
		//1.종료
		if(idx == N) {
			if(Ans < sum) {
				Ans = sum;
			}
			return;
		}
		
		//1-1.불가능한 경우
		if(idx > N) {
			return;
		}
		
		//2.현재 경우 선택, 다음 경우 호출
		//2-1.현재 선택O
		go(idx+T[idx],sum+P[idx]);
		//2-2.현재 선택O
		go(idx+1,sum);
	}

}

//		for(int i=0;i<N;i++) {
//			System.out.println(T[i]+" "+P[i]);
//		}