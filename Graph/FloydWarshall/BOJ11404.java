import java.util.*;
import java.io.*;
//플로이드
public class BOJ11404 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		int[][] adj = new int[n][n];
		int INF = Integer.MAX_VALUE;
		
		for(int i=0;i<n;i++) {
			Arrays.fill(adj[i], INF);
		}
		
		StringTokenizer st;
		int u,v,cost;
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			
			u = Integer.parseInt(st.nextToken())-1;
			v = Integer.parseInt(st.nextToken())-1;
			cost = Integer.parseInt(st.nextToken());
			
			if(adj[u][v] > cost) adj[u][v] = cost;
		}
		
		//FloydWarshall
		for(int k=0;k<n;k++) {
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(i == j) adj[i][j] = 0;
					if (adj[i][k] != INF && adj[k][j] != INF) {
						adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
					}
				}
			}
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(adj[i][j] == INF) {
					System.out.print(0+" ");
				} else System.out.print(adj[i][j]+" ");
			}
			System.out.println();
		}
	}

}