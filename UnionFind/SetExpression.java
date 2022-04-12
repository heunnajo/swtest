//BOJ #1717 집합의 표현
import java.util.*;
import java.io.*;
public class Main {
	static int[] parent;
	static void union(int a,int b) {
		int x = find(a); int y = find(b);
		parent[y] = x;
	}
	static int find(int x) {
		if(x == parent[x]) return x;
		else {
			return parent[x] = find(parent[x]);
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());//노드 갯수
		int m = Integer.parseInt(st.nextToken());//연산 갯수
		
		parent = new int[n+1];
		for(int i=0;i<=n;i++) parent[i] = i;
		
		StringBuilder sb = new StringBuilder();
		while(m-- >0) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			if(cmd == 0) {//union
				union(u,v);
			} else if(cmd == 1){//find :1 a b
				if(find(u) == find(v)) sb.append("yes"+"\n");
				else sb.append("no"+"\n");
			}
		}
		System.out.print(sb);
		
	}

}

//		System.out.println("n: "+n);
//		System.out.println("m: "+m);