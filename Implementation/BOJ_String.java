//BOJ #1120 문자열
import java.util.*;
import java.io.*;
public class BOJ_String {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String a = st.nextToken();
		String b = st.nextToken();
		
		int ans = a.length();
		for(int offset = 0;offset<=b.length()-a.length();offset++) {
			int cnt = 0;
			for(int i=0;i<a.length();i++) {
				if(a.charAt(i) != b.charAt(offset+i)) cnt++;
			}
			ans = ans > cnt? cnt : ans;
		}
		System.out.println(ans);
	}

}