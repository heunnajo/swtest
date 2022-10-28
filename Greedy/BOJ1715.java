package boj;
import java.util.*;
import java.io.*;
//카드 정렬하기
public class BOJ1715 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int sum = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for(int i=0;i<n;i++) {
			pq.offer(Integer.parseInt(br.readLine()));
		}
		
		int a,b;
		while(pq.size()>=2) {
			a = pq.poll(); b = pq.poll();
			
			sum += (a+b);
			pq.offer(a+b);
		}
		System.out.println(sum);
	}

}
