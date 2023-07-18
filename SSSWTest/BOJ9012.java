package barkingAlgo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

//괄호
//고쳐야함
public class BOJ9012 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		
		String word;
		int len;
		Stack<Character> st;
		int cnt = 0;
		boolean isVPS = true;
		StringBuilder sb = new StringBuilder();
		while(TC-- >0) {
			word = br.readLine();
			len = word.length();
			st = new Stack<>();
			
			for(int i=0;i<len;i++) {
				if(word.charAt(i) == '(') {
					st.push(word.charAt(i));
				} else {//)
//					if(st.isEmpty()) {//현재 문자열이 올바른 문자열이 아닌 경우!
//						isVPS = false;
//						break;
//					}
//					if(!st.isEmpty() && st.peek() == '(') {
//						st.pop();
//					}
					if(st.isEmpty() || st.peek() != '(') {//현재 문자열이 올바른 문자열이 아닌 경우!
						isVPS = false;
						break;
					}
					st.pop();
				}
			}
			
			//if(st.isEmpty()) {isVPS = true;}
			if(!st.isEmpty()) {isVPS = false;}
			if(isVPS) {sb.append("YES").append("\n");}
			else {sb.append("NO").append("\n");}
			
		}
		System.out.println(sb.toString());
	}

}
