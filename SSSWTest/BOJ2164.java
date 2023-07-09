package barkingAlgo;

import java.util.*;
import java.io.*;
//카드2

public class BOJ2164 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		if(N == 1) {
			System.out.println(1);
			return;
		}
		Queue<Integer> q = new LinkedList<>();
		int x = 0;
		for(int i=1;i<=N;i++) {
			q.offer(i);
		}
		
		while(true) {
			q.poll();
			x = q.peek();
			if(q.size() == 1) {
				System.out.println(x);
				break;
			}
			q.offer(x);
			q.poll();
		}
	}

}
