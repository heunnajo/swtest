//백준 2251.물통
package ss;
import java.util.*;
import java.io.*;
public class Bucket {
	static class Pair{
		int a,b;
		Pair(int a,int b){
			this.a = a; this.b = b;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] cap = new int[3];
		cap[0] = Integer.parseInt(st.nextToken());
		cap[1] = Integer.parseInt(st.nextToken());
		cap[2] = Integer.parseInt(st.nextToken());
		
		
		int[] from = {0,0,1,1,2,2};
		int[] to = {1,2,0,2,0,1};
		
		boolean[] ans = new boolean[201];
		Queue<Pair> q = new LinkedList<>();
		boolean[][] v = new boolean[201][201];
		q.add(new Pair(0,0));
		v[0][0] = true;
		ans[cap[2]] = true;
		
		while(!q.isEmpty()) {
			Pair tmp = q.poll();
			int[] cur = new int[3];
			cur[0] = tmp.a; cur[1] = tmp.b; cur[2] = cap[2]-cur[0]-cur[1];
			
			for(int k=0;k<6;k++) {
				int[] next = new int[3];
				next[0] = cur[0]; next[1] = cur[1]; next[2] = cur[2];
				
				next[to[k]] += next[from[k]];
				next[from[k]] = 0;
				
				//용량이 넘치는 케이스 처리
				if(next[to[k]] >= cap[to[k]]) {
					next[from[k]] = next[to[k]] - cap[to[k]];
					next[to[k]] = cap[to[k]];//v
				}
				//방문 체크 후 다음 상태로 이동
				if(!v[next[0]][next[1]]) {
					v[next[0]][next[1]] = true;
					q.add(new Pair(next[0],next[1]));
					if(next[0] == 0) ans[next[2]] = true;
				}
			}
		}
		
		for(int i=0;i<=cap[2];i++) {
			if(ans[i]) System.out.print(i+" ");
		}
		
	}

}
