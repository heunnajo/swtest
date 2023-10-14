package cdTest;
import java.util.*;
import java.io.*;
//절댓값 힙
public class BOJ11286 {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int x; int[] pair = null;
		StringBuilder sb = new StringBuilder();
		
		Comparator<int[]> comp = new Comparator<int[]>() {
			@Override
			public int compare(int[] a,int[] b) {
				if(a[1] == b[1]) return a[0] - b[0];
				return a[1] - b[1];
			}
		};
		
		//PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b)->a[1]-b[1]);
		PriorityQueue<int[]> minHeap = new PriorityQueue<>(comp);
		
		for(int i=0;i<N;i++) {
			x = Integer.parseInt(br.readLine());
			if(x != 0) {
				pair = new int[]{x,(int)Math.abs(x)};
				minHeap.offer(pair);
			} else if(x == 0) {
				if(minHeap.isEmpty()) sb.append(0);
				else sb.append(minHeap.poll()[0]);
				
				sb.append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}

}
