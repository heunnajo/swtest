package ss;
import java.util.*;
import java.io.*;
//행복
public class BOJ15969 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] score = new int[n];
		for(int i=0;i<n;i++) {
			score[i] = Integer.parseInt(st.nextToken());
//			System.out.print(score[i]+" ");
		}
		
		int ans = -1;
		
		if(n==2) {
			ans = Math.abs(score[0]-score[1]);
			System.out.println(ans);
			return;
		} else {//그 외의 모든 경우 : 서브 태스크가 오름차순이므로 오름차순으로 진행~
			Arrays.sort(score);
			System.out.println(Math.abs(score[0]-score[n-1]));
			return;
		}

	}

}
