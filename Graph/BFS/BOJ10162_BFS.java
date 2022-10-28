package BFS;

import java.util.*;
import java.io.*;
//전자레인지
public class BOJ10162_BFS {
	static int t,ans[];
	static int[] dir = {300,60,10};
	
	static class State{
		int pos,cntA,cntB,cntC;
		
		State(int pos,int cntA,int cntB,int cntC){
			this.pos = pos;
			this.cntA = cntA;
			this.cntB = cntB;
			this.cntC = cntC;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		t = Integer.parseInt(br.readLine());
		
		if(t % 10 != 0) {
			System.out.println(-1);
			return;
		}
		
		ans = new int[3];
		
		if(!bfs()) System.out.println(-1);
		else {
			System.out.println(ans[0]+" "+ans[1]+" "+ans[2]);
		}
	}
	static boolean bfs() {
		Queue<State> q = new LinkedList<>();
		boolean[] v = new boolean[10301];
		q.offer(new State(0,0,0,0));
		v[0] = true;
		
		while(!q.isEmpty()) {
			State cur = q.poll();
			
			if(cur.pos == t) {
				ans[0] = cur.cntA;
				ans[1] = cur.cntB;
				ans[2] = cur.cntC;
				
				return true;
			}
			
			for(int d=0;d<3;d++) {
				int nx = cur.pos+dir[d];
				
				if(nx > 10030 || v[nx]) continue;
				
				v[nx] = true;
				if(d==0) {
					q.offer(new State(nx,cur.cntA+1,cur.cntB,cur.cntC));
				} else if(d==1) {
					q.offer(new State(nx,cur.cntA,cur.cntB+1,cur.cntC));
				} else {
					q.offer(new State(nx,cur.cntA,cur.cntB,cur.cntC+1));
				}
			}
		}
		return false;
	}
}
