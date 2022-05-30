//좋은 구간
package ss;
import java.io.*;
import java.util.*;
public class BOJ1059_Sol {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int l = Integer.parseInt(br.readLine());
		int[] arr = new int[l];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<l;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int n = Integer.parseInt(br.readLine());
		
		Arrays.sort(arr);
		
		int start = -1, end = -1;
		for(int i=0;i<l;i++) {
			if(arr[i]<n) start = arr[i];
			else if(arr[i]>n) {
				end = arr[i];
			}
			else {
				System.out.println("0");
				return;
			}
			if(start != -1 && end != -1) break;
		}
		if(start == -1) start = 1;
		if(end == -1) end = 1000;
		int ans = left == 0? 1*right-1 : left*right-1;
		//int ans = (n-start) * (end-n) -1;
		System.out.println(ans);
	}

}

