package swea;
//최대 상금
import java.io.*;
import java.util.*;

public class SWEA1244 {
	static int Max;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		
		sb = new StringBuilder();
		
		String[] input;
		int N; int[] Num; int K;
		for(int t=1;t<=tc;t++) {
			Max = -1;
			input = br.readLine().split(" ");
			N = input[0].length();
			Num = new int[N];
			K = Integer.parseInt(input[1]);
			
			for(int i=0;i<N;i++) {
				Num[i] = input[0].charAt(i)-'0';
				//System.out.print(Num[i]+" ");
			}
			//System.out.println();
			if(Num.length < K) {	// swap 횟수가 자릿수보다 클 때
            	K = Num.length;	// 자릿수만큼만 옮겨도 전부 옮길 수 있음
            }
			solve(0,0,Num,K);
			
			sb.append("#").append(t).append(" ").append(Max).append("\n");
		}

		System.out.println(sb.toString());
	}
	static int calculate(int[] Num) {
		int sum = 0;
		int N = Num.length;
		for(int i=0;i<N;i++) {
			sum += Num[i] * Math.pow(10, N-(i+1));
		}
		//System.out.println("calculatedSum: "+sum);
		return sum;
	}
	static void solve(int idx,int from, int[] Num,int K) {
		//1.종료 : K번 교환 끝나면 최댓값 갱신
		if(idx == K) {
			int cur = calculate(Num);
			if(Max < cur) {
				Max = cur;
			}
			return;
		}
		
		//2.현재 경우 처리, 다음 경우 호출
		int tmp;
		for(int i=from;i<Num.length;i++) {
			for(int j=i+1;j<Num.length;j++) {
				tmp = Num[i]; Num[i] = Num[j]; Num[j] = tmp;
				//solve(idx+1,i+1,Num,K);
				solve(idx+1,i,Num,K);
				tmp = Num[i]; Num[i] = Num[j]; Num[j] = tmp;
			}
		}
	}
}
