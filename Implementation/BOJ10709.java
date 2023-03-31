package boj;
import java.util.*;
import java.io.*;
//기상 캐스터
public class BOJ10709 {
	static int H,W;
	static char[][] JOI;
	static int[][] ans;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		JOI = new char[H][W];
		ans = new int[H][W];
		
		for(int i=0;i<H;i++) {
			JOI[i] = br.readLine().toCharArray();
		}
		
		for(int i=0;i<H;i++) {
			for(int j=0;j<W;j++) {
				if(JOI[i][j] == 'c') {
					ans[i][j] = 0;
					continue;
				}
				ans[i][j] = solve(i,j);
			}
		}
		for(int i=0;i<H;i++) {
			for(int j=0;j<W;j++) {
				sb.append(ans[i][j]+" ");
			}
			sb.append("\n");
		}
		
		System.out.print(sb.toString());
		

	}
	static int solve(int r,int c) {
		int dist = 0;
		
		for(int i = c-1;i>=0;i--) {
			dist++;
			if(JOI[r][i] == 'c') {
				return dist;
			}
		}
		return -1;
	}
}

//for(int i=0;i<H;i++) {
//	for(int j=0;j<W;j++) {
//		System.out.print(JOI[i][j]+" ");
//	}
//	System.out.println();
//}