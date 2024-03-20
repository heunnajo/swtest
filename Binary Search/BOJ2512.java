//예산
import java.util.*;
import java.io.*;
public class BOJ2512 {
	static int n,m;
	static int[] budget;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		budget = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			budget[i] = Integer.parseInt(st.nextToken());
		}
		m = Integer.parseInt(br.readLine());
		
		int min = 0;
		int max = m;
		int maxSum = 0;
		int mid = 0;
		int sum = 0;
		int ans = 0;
		int k = 0;
		while(min < max) {
			mid = (min + max) / 2;
			sum = 0;
//			System.out.println("min: "+min+",max: "+max+", mid: "+mid);
			
			//상한액(mid) 예산 총액 구하기
			for(int i=0;i<n;i++) {
				if(budget[i] <= mid) sum += budget[i];
				else sum += mid;
			}
			
			if(sum > maxSum && sum <= m) {
				maxSum = sum;
				k = mid;
			}
			
//			System.out.println("sum: "+sum);
			
			//min, max 조정
			if(sum> m) {
				max = mid;
			} else {
				min = mid + 1;
			}
			
		}
//		System.out.println("상한액 k: "+k);//상한액
		
		int[] tmp = new int[n];
		for(int i=0;i<n;i++) {
			if(budget[i] <= k) tmp[i] = budget[i];
			else tmp[i] = k;
		}
//		for(int i=0;i<n;i++) {
//			System.out.print(tmp[i]+" ");
//		}
//		System.out.println();
		
		Arrays.sort(tmp);
		ans = tmp[n-1];
		System.out.println(ans);
	}
	
}
//		for(int i=0;i<n;i++) {
//			System.out.println(budget[i]+" ");
//		}
