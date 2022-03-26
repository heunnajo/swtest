//BOJ #10773 제로
import java.io.*;
import java.util.*;
public class Zero {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> st = new Stack<>();
		
		int k = Integer.parseInt(br.readLine());
		while(k-- >0) {
			int x = Integer.parseInt(br.readLine());
			if(x != 0) st.push(x);
			else st.pop();
		}
		int ans = 0;
		while(!st.isEmpty()) {
			ans += st.pop();
		}
		System.out.println(ans);
	}

}