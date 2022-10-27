package boj;

import java.util.*;
import java.io.*;
//신입 사원
public class BOJ1946 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		int n;
		StringTokenizer st;
		int[][] scores;
		//st = new StringTokenizer(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		while(t-- > 0) {
			n = Integer.parseInt(br.readLine());
			scores = new int[n][2];
			
			for(int i=0;i<n;i++) {
				st = new StringTokenizer(br.readLine());
				scores[i][0] = Integer.parseInt(st.nextToken());
				scores[i][1] = Integer.parseInt(st.nextToken());
			}
			sb.append(solve(scores,n)).append("\n");
		}
		System.out.print(sb.toString());
	}
	static int solve(int[][] arr,int n) {
		int sum = 1;
		
		Arrays.sort(arr,new Comparator<int[]>() {
			public int compare(int[] a,int[] b) {
				return a[0] - b[0];
			}
		});
		//제대로 정렬되는지 확인:O
//		for(int i=0;i<n;i++) {
//			System.out.println(arr[i][0]+" "+arr[i][1]);
//		}
		
		int min = arr[0][1];//서류 첫번째 사람의 면접 순위가 비교 기준값이 된다!
		for(int i=1;i<n;i++) {
			if(arr[i][1] < min) {//면접이라도 잘 했으면 뽑아줘라~
				//System.out.println("i: "+i);
				min = arr[i][1];//비교를 지속할 수록 더 낮은 면접 순위가 나와야함.
				sum++;
			}
		}
		return sum;
	}

}

//			for(int i=0;i<n;i++) {
//				System.out.println(scores[i][0]+" "+scores[i][1]);
//			}