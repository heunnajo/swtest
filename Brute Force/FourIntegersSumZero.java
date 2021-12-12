package ss;
import java.io.*;
import java.util.*;
public class FourIntegersSumZero {
	static int n,A[],B[],C[],D[];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		A = new int[n];
		B = new int[n];
		C = new int[n];
		D = new int[n];
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
			C[i] = Integer.parseInt(st.nextToken());
			D[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] ABsum = new int[n*n];
		int[] CDsum = new int[n*n];
		
		//AB, CD 합 구하고, 오름차순 정렬
		int index = 0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				ABsum[index++] = A[i]+B[j];
			}
		}
		Arrays.sort(ABsum);
		
		index = 0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				CDsum[index++] = C[i]+D[j];
			}
		}
		Arrays.sort(CDsum);
		//CD는 내림차순 정렬
		int k = CDsum.length;
		for(int i=0;i<CDsum.length/2;i++) {
			int tmp = CDsum[i];
			CDsum[i] = CDsum[k-i-1];
			CDsum[k-i-1] = tmp;
		}
		//본격적으로 AB CD 합 = 0되는 것 카운팅한다!
		long ans = 0;
		int i = 0,j = 0;
		while(i<n && j<n) {
			if(ABsum[i]+CDsum[j] == 0) {
				long c1 = 1; long c2 = 1;
				while(i+1<n && ABsum[i] == ABsum[i+1]) {c1++;}
				while(j+1<n && CDsum[j] == CDsum[j+1]) {c2++;}
				ans += c1*c2; i+=c1; j+=c2;
			} else if(ABsum[i]+CDsum[j] < 0) {i++;}
			else if(ABsum[i]+CDsum[j] > 0) {j++;}
		}
		System.out.println(ans);
	}
	
}
//		for(int i=0;i<ABsum.length;i++) {//맞게 잘 구해지는 것 같음!
//			System.out.print(ABsum[i]+" ");
//		}
//		System.out.println();
//		for(int i=0;i<n;i++) {
//			System.out.println(A[i]+" "+ B[i] +" "+ C[i] +" "+ D[i]);
//		}
