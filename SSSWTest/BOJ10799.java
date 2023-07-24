package barkingAlgo;

//쇠막대기
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ10799 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] input = br.readLine().toCharArray();
		int len = input.length;
		Stack<Integer> st = new Stack<>();
		int cnt = 0;
		
		for(int i=0;i<len;i++) {
			if(input[i] == '(') {
				st.push(i);
			}else {// )가 나온 경우!!!
				if(!st.isEmpty()) {
					//st.pop();
					
					if(st.peek() +1 == i) {st.pop();cnt += st.size();}//레이저인 경우
					else {st.pop();cnt += 1;}//쇠막대기인 경우
				}
			}
		}
		System.out.println(cnt);
	}

}
