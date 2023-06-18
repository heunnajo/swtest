package barkingAlgo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//개수 세기
public class BOJ10807 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] num = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] cnt = new int[205];
		for(int i=0;i<n;i++) {
			num[i] = Integer.parseInt(st.nextToken());
			if(0<=num[i] && num[i]<=100) {
				cnt[num[i]]++;
			} else if(num[i] < 0) {
				cnt[Math.abs(num[i])+100]++;
			}
		}
		
		int v = Integer.parseInt(br.readLine());
		if(v < 0) {v = Math.abs(v)+100;}
		System.out.println(cnt[v]);
		
	}

}

//		for(int i=0;i<n;i++) {
//			System.out.print(num[i]+" ");
//		}