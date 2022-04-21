//BOJ #13417 카드 문자열
import java.io.*;
import java.util.*;
public class CardString {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- >0) {
			int n = Integer.parseInt(br.readLine());
			char[] tmp = br.readLine().toCharArray();
			char[] input = new char[n];
			int idx = 0;
			for(char c:tmp) {
				if(c != ' ') input[idx++] = c;
			}
			
			String ans = "";
			ans += input[0];
			
			for(int i=1;i<n;i++) {
				if(ans.charAt(0) >= input[i]) {
					ans = input[i] + ans;
				} else {
					ans = ans + input[i];
				}
			}
			sb.append(ans+"\n");
		}
		System.out.print(sb);
	}

}