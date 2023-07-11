package barkingAlgo;
import java.util.*;
import java.io.*;

//덱
public class BOJ10866_Deque {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());

		char[] input;
		String str;
		StringBuilder sb = new StringBuilder();
		Deque<Integer> deque = new LinkedList<>();
		while(TC-- >0) {
			str = br.readLine();
			input = str.toCharArray();
			
			switch(input[0]) {
			case 'p':
				if(input[5] == 'f') {//push_front
					deque.addFirst(Integer.parseInt(str.substring(11)));
					
				} else if(input[5] == 'b') {//push_back
					deque.addLast(Integer.parseInt(str.substring(10)));
					
				} else if(input[4] == 'f') {//pop_front
					if(deque.isEmpty()) {sb.append(-1);}
					else{sb.append(deque.removeFirst());}
					
					sb.append("\n");
				}else if(input[4] == 'b') {//pop_back
					if(deque.isEmpty()) {sb.append(-1);}
					else{sb.append(deque.removeLast());}
					
					sb.append("\n");
				}
				break;
			case 's':
				sb.append(deque.size()).append("\n");
				break;
			case 'e':
				if(deque.isEmpty()) {sb.append(1);}
				else {sb.append(0);}
				sb.append("\n");
				break;
			case 'f':
				if(deque.isEmpty()) {sb.append(-1);}
				else {sb.append(deque.getFirst());}
				sb.append("\n");
				break;
			case 'b':
				if(deque.isEmpty()) {sb.append(-1);}
				else {sb.append(deque.getLast());}
				sb.append("\n");
				break;
			}
		}
		System.out.println(sb.toString());
	}

}
