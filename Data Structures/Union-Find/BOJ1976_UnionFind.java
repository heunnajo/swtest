package ss;
//여행 가자
import java.util.*;
import java.io.*;
public class BOJ1976_UnionFind {
	static int n,m;
	//static ArrayList<Integer>[] adj;
	static int[][] adj;
	static int[] route;
	static int[] parent;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		adj = new int[n+1][n+1];
		parent = new int[n+1];
		route = new int[m];
		
		//for(int i=1;i<=n;i++) parent[i] = i;//오답 원인1.빼먹은 부분
		
		StringTokenizer st;
		for(int i=1;i<=n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=n;j++) {
				adj[i][j] = Integer.parseInt(st.nextToken());
				if(adj[i][j] == 1) union(i,j);//오답 원인2.1부터 시작하는 노드 번호를 저장!i를 j의 부모 노드로 : 인접행렬 저장 필요X.합집합 처리만 하면 되기 때문..
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<m;i++) {
			route[i] = Integer.parseInt(st.nextToken());
		}
		
		//int ans = parent[route[0]];오답 원인3.
		int ans = find(route[0]);//최상위 루트 찾는 연산!
		
		for(int i=1;i<m;i++) {
//			if(ans != parent[route[i]]) {
			if(ans != find(route[i])) {//오답 원인3.각 여행 루트의 노드의 최상위 루트와 비교(parent가 아니라)
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
		for(int i=1;i<=n;i++)
			System.out.print(parent[i]+" ");
		
	}
	static void union(int a,int b) {
		int x = find(a); int y = find(b);
		parent[y] = x;
	}
	static int find(int x) {
		if(parent[x] == x) return x;
		else return parent[x] = find(parent[x]);
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