package ss;
import java.util.*;
import java.io.*;
public class Ring {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String x = br.readLine();
		int n = Integer.parseInt(br.readLine());
		int ans = 0;
		for(int i=0;i<n;i++) {
			//문자열 받아서 있는지 확인, 갯수 센다.
			String str = br.readLine();
			str += str;
			
			if(str.contains(x)) ans++;
		}
		System.out.println(ans);
	}

}
