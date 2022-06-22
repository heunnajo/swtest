package ss;
//운동(플로이드 와샬)
import java.util.*;
import java.io.*;
public class BOJ1956_FloydWarshall {
	static int V,E,ans;
	static int[][] adj;
	static boolean[] v;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		int INF = 987654321;
		ans = INF;
		adj = new int[V][V];
		
		for(int i=0;i<V;i++) Arrays.fill(adj[i], INF);
		
		for(int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken())-1;
			int v = Integer.parseInt(st.nextToken())-1;
			int dist = Integer.parseInt(st.nextToken());
			
			adj[u][v] = dist;
		}
		
		
		for(int k=0;k<V;k++) {
			for(int i=0;i<V;i++) {
				for(int j=0;j<V;j++) {
					if(i == j) continue;
					
					if(adj[i][j] > adj[i][k] + adj[k][j]) {
						adj[i][j] = adj[i][k] + adj[k][j];
					}
				}
			}
		}
		
		//사이클 판단
		for(int i=0;i<V;i++) {
			for(int j=0;j<V;j++) {
				if(i == j) continue;
				if(adj[i][j] != INF && adj[j][i] != INF) {
//					ans = Math.min(ans, adj[i][j]);
					ans = Math.min(ans, adj[i][j]+adj[j][i]);//여기서 2번 틀림 
				}
			}
		}
		if(ans == INF) System.out.println(-1);
		else System.out.println(ans);
	}

}

//		for(int i=0;i<V;i++) {
//			for(int j=0;j<V;j++) {
//				System.out.print(adj[i][j]+" ");
//			}
//			System.out.println();
//		}