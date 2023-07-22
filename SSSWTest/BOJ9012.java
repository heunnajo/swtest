import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

//괄호
public class BOJ9012 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		
		String word;
		int len;
		Stack<Character> st;
		int cnt = 0;
		boolean isVPS;//true로  초기화해서 틀렸었음.
		StringBuilder sb = new StringBuilder();
		while(TC-- >0) {
			word = br.readLine();
			len = word.length();
			st = new Stack<>();
			isVPS = true;
			
			for(int i=0;i<len;i++) {
				if(word.charAt(i) == '(') {
					st.push(word.charAt(i));
				} else {//)
					if(st.isEmpty() || st.peek() != '(') {//현재 문자열이 올바른 문자열이 아닌 경우!
						isVPS = false;
						break;
					}
					st.pop();
				}
			}
			
			if(!st.isEmpty()) {isVPS = false;}
			if(isVPS) {sb.append("YES").append("\n");}
			else {sb.append("NO").append("\n");}
			
		}
		System.out.println(sb.toString());
	}

}