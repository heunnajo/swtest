package swea;
import java.io.*;
import java.util.*;
//민서의 응급 수술
public class BOJ20955 {
	static int N,M;//N:뉴런 갯수 = 정점 갯수, M:시냅수 갯수 = 간선 갯수,K:집합 갯수
	static int[] parent;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parent = new int[N];
		
		for(int i=0;i<N;i++) parent[i] = i;
		
		int ans = 0;
		int cycle = 0;
		int set = 0;
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken())-1;
			int v = Integer.parseInt(st.nextToken())-1;
			
			if(find(u) == find(v)) {
				cycle++;
			}
			union(u,v);
		}
		
		for(int i=0;i<N;i++) {
			if(parent[i] == i) set++;
		}
		
		ans = cycle + set -1;
		System.out.println(ans);
		
	}
	static int find(int x) {
		if(parent[x] == x) return x;
		else {
			return parent[x] = find(parent[x]);
		}
	}
	static void union(int x,int y) {
		x = find(x);
		y = find(y);
		
		if(x<y) parent[y] = x;
		else if(x>y) parent[x] = y;
	}

}
