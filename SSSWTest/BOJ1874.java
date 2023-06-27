package barkingAlgo;
import java.io.*;
import java.util.*;
//스택 수열
public class BOJ1874 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int i = 0; int cur = 0;
		StringBuilder sb = new StringBuilder();
		Stack<Integer> st = new Stack<>();
		
		while(n-- >0) {
			i = Integer.parseInt(br.readLine());
			if(cur < i) {
				for(int x = cur+1;x<=i;x++) {
					st.push(x);//1,2,3,4
					sb.append("+").append("\n");
					//cur = i;
				}
				cur = i;
			} else {//cur > i
				if(st.peek() != i) {
					System.out.println("NO");
					return;
				} 
//					else {
//					st.pop();
//					sb.append("-").append("\n");
//				}
			}
			//스택에서 pop하는  것은 cur < i || cur > i || top == i 모두 해당되는 것..?!
			st.pop();//i를 출력
			sb.append("-").append("\n");
		}
		
		System.out.println(sb.toString());

	}

}
