import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
//리모컨
public class BOJ1107_Solution {
	static boolean[] broken;
	static int target;
	static long count;//왜 long?
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		target = Integer.parseInt(br.readLine());
		int n = Integer.parseInt(br.readLine());
		
		broken = new boolean[10];
		StringTokenizer st;
		if(n>0) {
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<n;i++) {
				int cur = Integer.parseInt(st.nextToken());
				broken[cur] = true;
			}
		}
		
		if(target == 100) {
			System.out.println(0);
			return;
		}
		count = Math.abs(target - 100);
		
		dfs(0,0);
		System.out.println(count);
	}
	static void dfs(int idx,int click) {
		for(int i=0;i<10;i++) {
			if(!broken[i]) {
				int newBtn = click*10 + i;
				int cnt = Math.abs(target - newBtn) + String.valueOf(newBtn).length();
				count = Math.min(count, cnt);
				//if(idx < 6) {
				if(idx < String.valueOf(target).length()) {
					dfs(idx+1,newBtn);
				}
			}
		}
	}

}