//BOJ #1613 역사
package ss;
import java.io.*;
import java.util.*;
public class History {
	static int n,k,adj[][];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		adj = new int[n+1][n+1];
		
		while(k-- >0) {
			st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			adj[u][v] = -1;//u가 먼저
			adj[v][u] = 1;//v가 먼저
			
		}
		floyd_warshall();
		
		StringBuilder sb = new StringBuilder();
		int s = Integer.parseInt(br.readLine());
		while(s-- >0) {
			st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			sb.append(adj[u][v]+"\n");
		}
		System.out.print(sb);
	}
	static void floyd_warshall() {
		for(int k=1;k<=n;k++) {
			for(int i=1;i<=n;i++) {
				for(int j=1;j<=n;j++) {
					if(adj[i][j] == 0) {
						if(adj[i][k] == 1 && adj[k][j] == 1) {
							adj[i][j] = 1;
						} else if(adj[i][k] == -1 && adj[k][j] == -1) {
							adj[i][j] = -1;
						}
					}
				}
			}
		}
	}
}
