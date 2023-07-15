package barkingAlgo;
import java.util.*;
import java.io.*;

//최솟값 찾기
public class BOJ11003 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		String[] input = br.readLine().split(" ");
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(input[i]);
		}
		
		Deque<int[]> deque = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<N;i++) {
			//윈도우 크기 L을 만족
			if(!deque.isEmpty() && deque.getFirst()[0] == i-L) {
				deque.pollFirst();
			}
			//덱 오름차순 유지
			while(!deque.isEmpty() && deque.getLast()[1] >= arr[i]) {
				deque.pollLast();
			}
			deque.offer(new int[] {i,arr[i]});
			sb.append(deque.getFirst()[1]).append(" ");
		}
		sb.append("\n");
		System.out.print(sb.toString());
		
	}

}
