package barkingAlgo;
import java.io.*;
import java.util.*;
//제로
public class BOJ10773 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		int num;
		Stack<Integer> myStack = new Stack<>();
		
		while(tc-- >0) {
			num = Integer.parseInt(br.readLine());
			if(num == 0) {
				if(!myStack.isEmpty()) {
					myStack.pop();
				}
			} else {
				myStack.push(num);
			}
		}
		
		int sum = 0;
		while(!myStack.isEmpty()) {
			sum += myStack.pop();
		}
		
		System.out.println(sum);
	}

}
