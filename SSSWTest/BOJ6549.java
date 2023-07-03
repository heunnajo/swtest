package barkingAlgo;
import java.io.*;
import java.util.*;

//히스토그램에서 가장 큰 직사각형
public class BOJ6549 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n, ans;
		String[] input;
		int[] h;
		StringBuilder sb = new StringBuilder();
		while(true) {
			input = br.readLine().split(" ");
			n = Integer.parseInt(input[0]);
			if(n == 0) break;
			
			h = new int[n];
			for(int i=0;i<n;i++) {
				h[i] = Integer.parseInt(input[i+1]);
			}
			ans = solve(h);
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb.toString());
		
	}
	static int solve(int[] h) {
		int ans = 0;
		int minH = 1000000001;
		int maxH = -1; int maxIdx = -1;
		int n = h.length;
		
		for(int i=0;i<n;i++) {
			if(minH > h[i]) minH = h[i];
			if(maxH < h[i]) {
				maxH = h[i];
				maxIdx = i;
			}
		}
		//1.너비 최대
		ans = minH*n;
		
		//2.높이 최대
		Stack<Integer> st = new Stack<>();
		st.push(maxH);
		
		int x = maxIdx-1; if(x<0) x = 0;//이렇게 x,y를 조정해서 로직이 정상적으로 실행될까?
		int y = maxIdx+1; if(y>n-1) y = n-1;
		int curSize = 0;
		
		while(0 <= x) {
			if(x == maxH) continue;//x==maxH이면 그냥 이 while문 자체를 실행X
			if(st.peek() >= h[x]) {
				st.push(h[x]);
				curSize = st.size() * h[x];
				if(ans < curSize) ans = curSize;//ans = Math.max(1,2)인데 바로 이렇게 해도 되려나.
			} else {
				curSize = (st.size() + 1) * st.peek();
				if(ans < curSize) ans = curSize;//ans = Math.max(1,2)인데 바로 이렇게 해도 되려나.
			}
			
			x--;
		}
		
		while(y < n) {
			if(y == maxH) continue;//y==maxH이면 그냥 이 while문 자체를 실행X
			if(st.peek() >= h[y]) {
				st.push(h[y]);
				curSize = st.size() * h[y];
				if(ans < curSize) ans = curSize;//ans = Math.max(1,2)인데 바로 이렇게 해도 되려나.
			} else {
				curSize = (st.size() + 1) * st.peek();
				if(ans < curSize) ans = curSize;//ans = Math.max(1,2)인데 바로 이렇게 해도 되려나.
			}
			y++;
		}
		return ans;
	}

}
