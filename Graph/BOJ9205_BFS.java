package ss;

import java.util.*;
import java.io.*;

public class BOJ9205_BFS {
	static class Point{
		int x,y;
		
		public Point(int x,int y) {
			this.x = x; this.y = y;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		int n;
		ArrayList<Point> list;
		ArrayList<Integer>[] graph;
		
		StringBuilder sb = new StringBuilder();
		while(TC-- >0) {
			n = Integer.parseInt(br.readLine());
			
			//일단 모든 정점 저장
			list = new ArrayList<>();
			
			for(int i=0;i<n+2;i++) {
				st = new StringTokenizer(br.readLine());
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				list.add(new Point(x,y));
			}
			
			//양방향 그래프 완성 : 정점들 중 거리 1000이내인 정점들 서로 연결
			graph = new ArrayList[n+2];
			for(int i=0;i<n+2;i++) {
				graph[i] = new ArrayList<>();
			}
			
			for(int i=0;i<n+2;i++) {
				for(int j=i+1;j<n+2;j++) {
					if(isInManDist(list.get(i),list.get(j))) {
						graph[i].add(j);
						graph[j].add(i);
					}
				}
			}
			//출발점에서 시작, 연결 정보로 그래프 탐색
//			if(solve(graph,n)) sb.append("happy").append("\n");
//			else sb.append("sad").append("\n");
			sb.append(solve(graph,n) ? "happy" : "sad").append("\n");
		}
		
		System.out.print(sb.toString());
	}
	
	static boolean solve(ArrayList<Integer>[] graph,int n) {//도착정점 : n+1
		Queue<Integer> q = new LinkedList<>();
		q.offer(0);//시작 정점(0번)에서 출발
		
		boolean[] v = new boolean[n+2];
		v[0] = true;
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			if(now == n+1) return true;
			
			//현재 정점의 인접 노드들을 탐색!
			for(int next : graph[now]) {
				if(!v[next]) {
					v[next] = true;
					q.offer(next);
				}
			}
		}
		return false;
	}
	
	static boolean isInManDist(Point a, Point b) {
		int dist = Math.abs(a.x-b.x) + Math.abs(a.y-b.y);
		
		if(dist <= 1000) return true;
		
		return false;
	}
}
