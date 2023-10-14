package cdTest;
//좌표 정렬하기

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class BOJ11650_Comparator_Class {
	static class Pair{
		int x,y;
		
		Pair(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		int[] pair = null;
		int x,y;
		
		Comparator<Pair> comp = new Comparator<Pair>() {
			@Override
			public int compare(Pair a, Pair b) {
				if(a.x == b.x) return a.y - b.y;
				return a.x - b.x;
			}
		};
		PriorityQueue<Pair> pq = new PriorityQueue<>(comp);
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
