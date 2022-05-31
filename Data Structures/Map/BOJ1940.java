//주몽
import java.util.*;
import java.io.*;
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		HashMap<Integer,Boolean> map = new HashMap<>();
		
		for(int i=0;i<N;i++) {
			map.put(arr[i], true);
		}
		
		int ans = 0;
		
		for(int i=0;i<N;i++) {
			if(!map.get(arr[i])) continue;
			if(arr[i] == M/2) continue;//예외처리
			if(map.containsKey(M-arr[i])) {
				ans++;
				map.put(M-arr[i],false);
				map.put(arr[i],false);
			}
		}
		System.out.println(ans);
	}

}