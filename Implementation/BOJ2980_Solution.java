package boj;
import java.util.*;
import java.io.*;

//도로와 신호등
public class BOJ2980_Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		int position = 0;
		int time = 0;
		
		int D,R,G,gap;
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			
			D = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			G = Integer.parseInt(st.nextToken());
			
			time += (D-position);
			position = D;
			
			gap = time % (R+G);
			if(gap < R) {time += R-gap;}
			
		}
		time += (L-position);
		
		System.out.println(time);

	}

}
