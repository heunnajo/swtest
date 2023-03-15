package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//이차원 배열과 연산
public class BOJ17140_Solution {
	static int rowN=3, colN=3;
	static int[] num;
	static int[][] map = new int[100][100];
	static PriorityQueue<Pair> pq = new PriorityQueue<>();
	
	static class Pair implements Comparable<Pair> {
		int n, count;
		Pair (int n, int count) {
			this.n=n; this.count=count;
		}
		@Override
		public int compareTo(Pair o) {
			if(this.count==o.count) {
				return this.n-o.n;
			} else return this.count-o.count;
		}
	}
	
	static void showAll(int limit) {
		for(int i=0; i<limit; i++) {
			for(int j=0; j<limit; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	static void numR(int row) {
		int max=0;
		for(int i=0; i<rowN; i++) {
			if(map[row][i]!=0) {
				num[map[row][i]]++;
				max = Math.max(max, map[row][i]);
			}
		}
		for(int i=1; i<=max; i++) {
			if(num[i]!=0) pq.add(new Pair(i, num[i]));
		}
		int index=0;
		while(!pq.isEmpty()) {
			Pair pair = pq.remove();
			map[row][index]=pair.n;
			index++;
			map[row][index]=pair.count;
			index++;
		}
		colN = Math.max(colN, index);
		for(int i=index; i<100; i++) map[row][i]=0;
	}
	
	static void numC(int col) {
		int max=0;
		for(int i=0; i<colN; i++) {
			if(map[i][col]!=0) {
				num[map[i][col]]++;
				max = Math.max(max, map[i][col]);
			}
		}
		for(int i=1; i<=max; i++) {
			if(num[i]!=0) pq.add(new Pair(i, num[i]));
		}
		int index=0;
		while(!pq.isEmpty()) {
			Pair pair = pq.remove();
			map[index][col]=pair.n;
			index++;
			map[index][col]=pair.count;
			index++;
		}
		rowN = Math.max(rowN, index);
		for(int i=index; i<100; i++) map[i][col]=0;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int r = Integer.parseInt(st.nextToken())-1;
		int c = Integer.parseInt(st.nextToken())-1;
		int k = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<3; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		int count=0;
		while (true) {
			if(map[r][c]==k || count>100) {
				break;
			}
			if (rowN >= colN) {
				for (int i = 0; i < rowN; i++) {
					num = new int[101];
					numR(i);
				}	
				count++;
			} else {
				num = new int[101];
				for (int i = 0; i < colN; i++) {
					num = new int[101];
					numC(i);
				}
				count++;
			}
		}
		if(count==101) count=-1;
		System.out.println(count);
	}
}
