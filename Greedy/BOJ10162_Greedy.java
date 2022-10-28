package boj;
import java.util.*;
import java.io.*;
//전자레인지
public class BOJ10162_Greedy {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		int[] ans = new int[3];
		
		if(t % 10 != 0) {
			System.out.println(-1);
			return;
		} else {
			ans = new int[3];
			
			//작업 수행~
			while(t != 0) {
				if(t >= 300) {
					ans[0] = t/300;
					t %= 300;
				} else if(t >= 60) {
					ans[1] = t/60;
					t %= 60;
				} else {
					ans[2] = t/10;
					t %= 10;
				}
			}
			System.out.println(ans[0]+" "+ans[1]+" "+ans[2]);
		}
		
	}

}
