
import java.util.*;
import java.io.*;
public class StringSet_HashMap {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		Map<String,Integer> map = new HashMap<>();
		while(n-->0) {
			String s = br.readLine();
			map.put(s, 1);
		}
		int ans = 0;
		while(m-->0) {
			String s = br.readLine();
			if(map.containsKey(s)) {
				ans++;
			}
		}
		System.out.println(ans);
	}

}
