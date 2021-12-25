import java.util.*;
import java.io.*;
public class Virus_UnionFind {
	static int ans,N,M;//N:노드 갯수, M:연결 정보
	static int[] parent;
	static int Find(int x) {
		if(parent[x] == x) return x;
		else {
			return parent[x] = Find(parent[x]);
		}
	}
	static void Union(int a,int b) {
		int x = Find(a), y = Find(b);
		//if( x != y) parent[a] = b;
		parent[y] = x;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		parent = new int[n+1];
		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		
		for(int i=0;i<=n;i++)parent[i] = i;
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			Union(a,b);
		}
		//1과 루트가 같은 것의 갯수를 세면 됨~!
		int ans = 0;//n제한은 100이하이므로 int로 가능.
		for(int i=0;i<=n;i++) {
			if(i==1) continue;
			if(Find(1)==Find(i)) ans++;
		}
		System.out.println(ans);
	}
	
}