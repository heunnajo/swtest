package ss;
//여행 가자
import java.util.*;
import java.io.*;
public class BOJ1976_FloydWarshall {
	static int n,m;
	static int[][] adj;
	static int[] route;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		adj = new int[n][n];
		route = new int[m];
		
		StringTokenizer st;
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				adj[i][j] = Integer.parseInt(st.nextToken());
				if(i == j) adj[i][j] = 1;//없어서 오답 됐었음.
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<m;i++) {
			route[i] = Integer.parseInt(st.nextToken())-1;
		}
		
		for(int k=0;k<n;k++) {
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(adj[i][k] == 1 && adj[k][j] == 1) adj[i][j] = 1;
				}
			}
		}
		
		for(int i=0;i<m-1;i++) {
			if(adj[route[i]][route[i+1]] == 0) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
	}
	
}

//		for(int i=1;i<=n;i++) {
//			for(int j=1;j<=n;j++) {
//				System.out.print(adj[i][j]+" ");
//			}
//			System.out.println();
//		}
//		
//		for(int i=0;i<m;i++) {
//			System.out.print(route[i]+" ");
//		}