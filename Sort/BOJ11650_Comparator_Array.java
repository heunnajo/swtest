package cdTest;
//좌표 정렬하기

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class BOJ11650_Comparator_Array {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		int[] pair = null;
		int x,y;
		
		Comparator<int[]> comp = new Comparator<int[]>() {
			@Override
			public int compare(int[] a,int[] b) {
				if(a[0] == b[0]) return a[1] - b[1];
				return a[0] - b[0];
			}
		};
		PriorityQueue<int[]> pq = new PriorityQueue<>(comp);
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			pair = new int[]{x,y};
			pq.offer(pair);
		}
		
		StringBuilder sb = new StringBuilder(N);
		int[] cur = null;
		while(!pq.isEmpty()) {
			cur = pq.poll();
			sb.append(cur[0]).append(" ").append(cur[1]).append("\n");
		}
		System.out.print(sb.toString());
	}

}
