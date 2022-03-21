//BOJ #1120 문자열
import java.util.*;
import java.io.*;
public class BOJ_String {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String a = st.nextToken();
		String b = st.nextToken();
		
		int ans = 100;//최대 길이 50이므로
		//0.b에 a가 있는 경우 :ans = 0
		if(b.contains(a)) ans = 0;
		//1.길이가 같은 경우
		else if(a.length() == b.length()) {
			int cnt = 0;
			for(int i=0;i<b.length();i++) {
				if(a.charAt(i) != b.charAt(i)) cnt++;
			}
			ans = cnt;
		} else {//2.a의 길이가 더 작은 경우
			//2-1.앞에서부터 비교
			int cnt1 = 0;
			for(int i=0;i<a.length();i++) {
				if(a.charAt(i) != b.charAt(i)) cnt1++;
			}
			//2-2.뒤에서부터 비교
			int cnt2 = 0;
			StringBuffer sbA = new StringBuffer(a);
			StringBuffer sbB = new StringBuffer(b);
			String reversedA = sbA.reverse().toString();
			String reversedB = sbB.reverse().toString();
			
			for(int i=0;i<reversedA.length();i++) {
				if(reversedA.charAt(i) != reversedB.charAt(i)) cnt2++;
			}
			ans = cnt1 < cnt2 ? cnt1 : cnt2;
		}
		System.out.println(ans);
		
		
	}

}