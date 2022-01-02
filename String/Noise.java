import java.io.*;
import java.util.*;
public class Noise {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String a = br.readLine();
		String op = br.readLine();
		String b = br.readLine();
		int a_len = a.length();//1을 제외한 0 갯수
		int b_len = b.length();
		StringBuilder sb = new StringBuilder();
		if(op.equals("*")) {
			sb.append("1");
			for(int i=0;i<a_len+b_len-2;i++) {
				sb.append("0");
			}
		} else {//덧셈
			if(a_len<b_len) {//a<b:a가 더 작으면 b뒤에 a를 붙인다!
				String tmp = b.substring(0, b_len-a_len);
				sb.append(tmp).append(a);
			} else if(a_len>b_len) {//a>b:b가 더 작으면 a뒤에 b붙인다!
				String tmp = a.substring(0, a_len-b_len);
				sb.append(tmp).append(b);
			}
			else if(a_len == b_len) {
				sb.append("2");
				for(int i=0;i<a_len-1;i++) {
					sb.append("0");
				}
			}
		}
		System.out.println(sb);
	}

}