//BOJ #11053 가장 긴 증가하는 부분 수열
package ss;
import java.io.*;
import java.util.*;
public class TheLongestSubArray {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] a = new int[n];
		for(int i=0;i<n;i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		int ans = -1;
		for(int i=0;i<n-1;i++) {
			LinkedList<Integer> list = new LinkedList<>();
			list.add(a[i]);
			for(int j=i;j<n-1;j++) {
				if(list.get(list.size()-1)<a[j+1]) {
					list.add(a[j+1]);
				}
			}
			ans = Math.max(ans, list.size());
		}
		System.out.println(ans);
	}

}

//		for(int i=0;i<n;i++) {
//			System.out.print(a[i]+" ");
//		}
//		System.out.println();