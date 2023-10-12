package cdTest;
//좌표 정렬하기

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class BOJ11650_Comparable_Class {
	static class Pair implements Comparable<Pair>{
		int x,y;
		
		Pair(int x,int y){
			this.x = x;
			this.y = y;
		}
		
		@Override
		public int compareTo(Pair p) {
			if(this.x == p.x) return this.y - p.y;
			return this.x - p.x;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		int x,y;
		
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			
			pq.offer(new Pair(x,y));
		}
		
		StringBuilder sb = new StringBuilder(N);
		Pair cur = null;
		while(!pq.isEmpty()) {
			cur = pq.poll();
			sb.append(cur.x).append(" ").append(cur.y).append("\n");
		}
		System.out.print(sb.toString());
	}

}
