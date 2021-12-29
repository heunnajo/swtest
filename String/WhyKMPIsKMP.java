package ss;
import java.io.*;
import java.util.*;
public class WhyKMPIsKMP {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		ArrayList<Integer> list = new ArrayList<>();
		int len = str.length();
		for(int i=0;i<len;i++) {
			if(str.charAt(i) == '-') {
				list.add(i);
			}
		}
		StringBuilder ans = new StringBuilder();
		ans.append(str.charAt(0));
		for(int idx:list) {
			ans.append(str.charAt(idx+1));
		}
		System.out.println(ans);
	}

}
