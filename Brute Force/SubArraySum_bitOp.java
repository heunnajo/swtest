package ss;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SubArraySum_bitOp {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int target = Integer.parseInt(st.nextToken());
		
		int[] num = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		int answer = 0;
		int sum;
		for(int i=1;i<(1<<n);i++) {
			sum = 0;
			for(int j=0;j<n;j++) {
				if((i&(1<<j)) != 0) {
					sum+=num[j];
				}
			}
			if(sum == target) answer++;
		}
		System.out.println(answer);
	}

}

//		for(int i=0;i<n;i++) {
//			System.out.print(num[i]+" ");
//		}
//		System.out.println();