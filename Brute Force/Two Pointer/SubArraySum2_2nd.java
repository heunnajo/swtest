package ss;
import java.io.*;
import java.util.*;
public class SubArraySum2_2nd {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		int m = n/2;
		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		//두 그룹으로 나눠서 각각 모든 부분수열의합을 up,down에 저장한다!
		int[] up = new int[1<<m];
		for(int i=0;i<(1<<m);i++) {
			int sum = 0;
			for(int j=0;j<m;j++) {
				if((i & (1<<j)) != 0) {
					sum += arr[j];
				}
			}
			up[i] = sum;
		}
		int k = n-m;
		int[] down = new int[1<<k];
		for(int i=0;i<(1<<k);i++) {
			int sum = 0;
			for(int j=0;j<k;j++) {
				if((i & (1<<j)) != 0) {
					sum += arr[m+j];//(n-m)+k
				}
			}
			down[i] = sum;
		}
		Arrays.sort(up);
		Arrays.sort(down);
		
		int start=0;
		int e=down.length-1;
		while(start<e) {
			int tmp = down[start];
			down[start] = down[e];
			down[e] = tmp;
			start++;e--;
		}
		int up_len = up.length;
		int down_len = down.length;
		int a = 0,b = 0,sum = 0,ans = 0;
		while(a<up_len && b<down_len) {
			sum = up[a] + down[b];
			if(sum == s) {
				int a_cnt = 1,b_cnt = 1;
				while(a+1<up_len && up[a]==up[a+1]) {a_cnt++;}
				while(b+1<down_len && down[b]==down[b+1]) {b_cnt++;}
				ans += a_cnt*b_cnt; a += a_cnt; b += b_cnt;
			} else if(sum<s) {a++;}
			else if(sum>s) {b++;}
		}
		System.out.println(ans);
	}

}
//		for(int i=0;i<up.length;i++) {
//			System.out.print(up[i]+" ");
//		}
//		System.out.println();

//		for(int i=0;i<down.length;i++) {
//			System.out.print(down[i]+" ");
//		}
//		for(int i=0;i<n;i++) {
//			System.out.print(arr[i]+" ");
//		}