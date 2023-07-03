package barkingAlgo;
import java.io.*;
import java.util.*;

//옥상 정원 꾸미기
public class BOJ6198 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] h = new int[n];
		for(int i=0;i<n;i++) {
			h[i] = Integer.parseInt(br.readLine());
		}
		
		long sum = 0;
		Stack<Integer> st = new Stack<>();
		for(int i=0;i<n;i++) {
			while(!st.isEmpty() && st.peek() <= h[i]) {
				st.pop();
			}
			sum += st.size();
			st.push(h[i]);
		}
		
		System.out.println(sum);
	}

}
