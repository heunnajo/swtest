package boj;
import java.util.*;
import java.io.*;
//설탕 배달
public class BOJ2839 {
	static int n,ans;
	static int[] dir = {3,5};
	
	static class Pair{
		int pos,cnt;
		
		Pair(int pos,int cnt){
			this.pos = pos;
			this.cnt = cnt;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		ans = 0;
		
		bfs();
		
		System.out.println(ans);
	}
	static void bfs() {
		Queue<Pair> q = new LinkedList<>();
		boolean[] visited = new boolean[n+6];//3의 배수||5의 배수||3과 5의 공배수 만 가능할 것이다.
		q.offer(new Pair(0,0));
		visited[0] = true;
		
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			if(cur.pos == n) {
				ans = cur.cnt;
				return;
			}
			for(int d=0;d<2;d++) {
				int nx = cur.pos + dir[d];
				
				if(isOut(nx) || visited[nx]) continue;
				
				q.offer(new Pair(nx,cur.cnt+1));
				visited[nx] = true;
			}
		}
		ans = -1;
	}
	static boolean isOut(int x) {
		return x<0 || x>n+5;
	}
}
