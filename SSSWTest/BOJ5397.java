package barkingAlgo;
import java.util.*;
import java.io.*;
//키로거

public class BOJ5397 {//틀림!

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		String output = "";
		while(TC-- >0) {
			output = solve(br.readLine());
			sb.append(output).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static String solve(String input) {
		
		Deque<Character> left = new ArrayDeque<>();
		Deque<Character> right = new ArrayDeque<>();
		
		char[] arr = input.toCharArray();
		int len = arr.length;
		for(int i=0;i<len;i++) {
			char cur = arr[i];
			if(cur == '<') {
				if(!left.isEmpty()) right.addLast(left.removeLast());
				
			} else if(cur == '>') {
				if(!right.isEmpty()) left.addLast(right.removeLast());
				
			} else if(cur == '-'){
				if(!left.isEmpty()) left.removeLast();
				
			} else {
				left.addLast(cur);
				
			}
			
		}
		StringBuilder sb = new StringBuilder();
		while(!left.isEmpty()) sb.append(left.removeFirst()); 
		while(!right.isEmpty()) sb.append(left.removeLast()); 
		
		return sb.toString();
	}
}
