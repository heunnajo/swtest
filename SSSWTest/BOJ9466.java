import java.io.*;
import java.util.*;

//텀 프로젝트
//Union-find
public class BOJ9466 {
	
	static int[] arr;
	static int[] parents;
	static boolean[] checked;
	static int ans;
	
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = read();
		while(T-->0) {
			int N = read();
			arr = new int[N+1];
			parents = new int[N+1];
			checked = new boolean[N+1];
			ans = N;
			
			for(int i=1; i<=N; i++) {
				arr[i] = read();
				parents[i] = i;
			}
			
			for(int i=1; i<=N; i++) {
				union(i, arr[i]);
			}
			
			sb.append(ans).append('\n');
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	static int findSet(int a) {
		if(parents[a] == a) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	static void check(int a) {
		if(parents[a] == a) return;
		ans--;
		check(arr[a]);
	}
	
	static void union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot == bRoot) {
			ans--;
			check(b);
			return;
		}
		
		parents[a] = bRoot;
	}
	
	static int read() throws Exception{
		int c, n = System.in.read() & 15;
		while((c = System.in.read()) > 32) n = (n<<3) + (n<<1) + (c & 15);
		return n;
	}
	
}
