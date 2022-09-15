package ss;

import java.util.*;
import java.io.*;

public class BOJ9205_FloydWarshall {
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
		boolean[][] isPossible;
		
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
			isPossible = new boolean[n+2][n+2];
			
			for(int i=0;i<n+2;i++) {
				for(int j=i+1;j<n+2;j++) {
					if(isInManDist(list.get(i),list.get(j))) {
						isPossible[i][j] = isPossible[j][i] = true;
					}
				}
			}
			//출발점에서 시작, 연결 정보로 그래프 탐색
			sb.append(solve(isPossible,n) ? "happy" : "sad").append("\n");
		}
		
		System.out.print(sb.toString());
	}
	
	static boolean solve(boolean[][] isPossible ,int n) {//도착정점 : n+1
		for(int k=0;k<n+2;k++) {
			for(int i=0;i<n+2;i++) {
				for(int j=0;j<n+2;j++) {
					if(isPossible[i][j] == false && (isPossible[i][k] == true && isPossible[k][j] == true)) {
//					if(isPossible[i][k] == true && isPossible[k][j] == true) {
						isPossible[i][j] = true;
					}
				}
			}
		}
		if(isPossible[0][n+1]) return true;
		return false;
	}
	
	static boolean isInManDist(Point a, Point b) {
		int dist = Math.abs(a.x-b.x) + Math.abs(a.y-b.y);
		
		if(dist <= 1000) return true;
		
		return false;
	}
}
