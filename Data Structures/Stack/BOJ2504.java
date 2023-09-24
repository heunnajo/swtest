import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

//괄호의 값
public class BOJ2504 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] input = br.readLine().toCharArray();
		
		Stack<Character> st = new Stack<>();
		int temp = 1; // 닫는 괄호가 나올 때마다 중간 계산결과값을 합산한다.
		int answer = 0;
		for(int i=0;i<input.length;i++) {
			if(input[i] == '(') {
				temp *= 2;
				st.push(input[i]);
				
			} else if(input[i] == '[') {
				temp *= 3;
				st.push(input[i]);
			} else if(input[i] == ')') {
				//1.올바르지 않은 괄호
				if(st.isEmpty() || st.peek() != '(') {
					answer = 0;
					break;
				}
				//2.()
				if(input[i-1] == '('){
					answer += temp;
					temp /= 2;
					st.pop();
				}
				//3.(X)
				else {
					temp /= 2;
					st.pop();
				}
			} else if(input[i] == ']') {
				//1.올바르지 않은 괄호
				if(st.isEmpty() || st.peek() != '[') {
					answer = 0;
					break;
				}
				//2.[]
				if(input[i-1] == '['){
					answer += temp;
					temp /= 3;
					st.pop();
				}
				//3.[X]
				else {
					temp /= 3;
					st.pop();
				}
			} 
		}
		if(!st.isEmpty()) answer = 0;
		System.out.println(answer);
	}

}
