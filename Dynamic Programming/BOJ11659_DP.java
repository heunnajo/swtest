package boj;
//구간합 구하기4
import java.util.*;
import java.io.*;
public class BOJ11659_DP {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] nums = new int[N+1];
		int[] sum = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			sum[i] = sum[i-1]+nums[i];
		}
		
		int start; int end;
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<=M;i++) {
			st = new StringTokenizer(br.readLine());
			
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			
			sb.append(sum[end]-sum[start-1]).append("\n");
		}
		
		System.out.print(sb.toString());
	}

}
