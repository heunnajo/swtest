package barkingAlgo;
import java.util.*;
import java.io.*;

//좋은 단어
public class BOJ3986 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		
		String word;
		int len;
		Stack<Character> st;
		int cnt = 0;
		while(TC-- >0) {
			word = br.readLine();
			len = word.length();
			st = new Stack<>();
			
			for(int i=0;i<len;i++) {
				if(!st.isEmpty()) {
					if(st.peek() != word.charAt(i)) {st.push(word.charAt(i));}
					else {st.pop();}
				} else{st.push(word.charAt(i));}
			}
			
			if(st.isEmpty()) cnt++;
			
		}
		System.out.println(cnt);
	}

}
