package barkingAlgo;
import java.util.*;
import java.io.*;

//균형잡힌 세상
public class BOJ4949 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String input = "";
		char[] arr;
		Stack<Character> st;
		while(true) {
			input = br.readLine();
			if(input.equals(".")) {break;}
			
			arr = input.toCharArray();
			st = new Stack<>();
			boolean flag = true;
			for(int i=0;i<arr.length;i++) {
				if(arr[i] == '(' || arr[i] == '[') {
					st.push(arr[i]);
				} else if(arr[i] == ')') {
					if(st.isEmpty() || st.peek() != '(') {
						flag = false;
						break;//no인 경우이므로, 반복 종료!
					}
					st.pop();
				} else if(arr[i] == ']') {
					if(st.isEmpty() || st.peek() != '[') {
						flag = false;
						break;
					}
					st.pop();
				}
			}
			
			//스택에 아무것도 없는데 닫힌 괄호가 나온 경우 flag = false이므로 아래의 else문에 해당! 
			if(!st.isEmpty()) flag = false;
			if(flag) {sb.append("yes");}
			else {sb.append("no");}
			sb.append("\n");
		}
			
		System.out.println(sb.toString());
		
	}

}
