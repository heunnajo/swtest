package barkingAlgo;
import java.util.*;
import java.io.*;
//스택
public class BOJ10828 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		int[] myStack = new int[10000];
		int top = 0;
		String cmd = "";
		
		while(tc-- >0) {
			String[] input = br.readLine().split(" "); //공백문자가 없을 때에도 동작하는가?
			cmd = input[0];
			if(cmd.equals("push")) {
				myStack[top] = Integer.parseInt(input[1]);
				//System.out.println(myStack[top]);
				top++;
			} else if(cmd.equals("pop")) {
				if(top>0) {sb.append(myStack[top-1]);}
				else {sb.append(-1);}
				sb.append("\n");
				top--;
				if(top < 0) top = 0;
			} else if(cmd.equals("size")) {
				sb.append(top).append("\n");;
			} else if(cmd.equals("empty")) {
				if(top == 0) {sb.append("1");}
				else {sb.append("0");}
				sb.append("\n");
			} else if(cmd.equals("top")) {
				if(top>0) {sb.append(myStack[top-1]);}
				else {sb.append(-1);}
				sb.append("\n");
			}
			
		}
		System.out.print(sb.toString());
	}

}
//while(tc-- >0) {
//	String[] input = br.readLine().split(" "); //공백문자가 없을 때에도 동작하는가?
//	for(int i=0;i<input.length;i++) {
//		sb.append(input[i]).append(" ");
//	}
//	sb.append("\n");
//}
//System.out.println(sb.toString());