package barkingAlgo;
import java.io.*;
import java.util.*;
//탑
public class BOJ2493 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		String[] input = br.readLine().split(" ");
		int[] nums = new int[n+1];
		for(int i=0;i<n;i++) {
			nums[i+1] = Integer.parseInt(input[i]);
		}

		Stack<Integer> st = new Stack<>();
		StringBuilder sb = new StringBuilder();
		st.push(1);
		sb.append(0).append(" ");
		for(int i=2;i<=n;i++) {
			if(nums[st.peek()] >= nums[i]) {
				sb.append(st.peek()).append(" ");
			} else {
				boolean isValid = false;
				while(!st.isEmpty()) {
					if(nums[st.peek()] < nums[i]) {
						st.pop();
					} else {//top >= cur : 신호 수신 탑을 찾은 경우
						isValid = true;
						sb.append(st.peek()).append(" ");
						break;
					}
				}
				if(!isValid) {
					sb.append(0).append(" ");
				}
			}
			st.push(i);
		}
		
		System.out.print(sb.toString());
	}

}

//		for(int i=1;i<=n;i++) {
//			System.out.println(nums[i]);
//		}