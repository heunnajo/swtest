package ss;
import java.io.*;
import java.util.*;
public class SubArraySum2_3rd {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		int[] a = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		//up,down 두 부분으로 나눠서 각각 모든 부분 수열 구해서 합을 배열에 저장한다!
		//1.up
		int upSize = n/2;//up의 크기
		int downSize = n-upSize;
		int[] up = new int[1<<upSize];
		int[] down = new int[1<<downSize];
		int sum = 0;
		for(int i=0;i<(1<<upSize);i++) {
			for(int k=0;k<upSize;k++) {
				if((i&k) != 0) sum += a[k];
			}
			up[i] = sum;
		}
		//2.down
		sum = 0;
		for(int i=0;i<(1<<downSize);i++) {
			for(int k=0;k<downSize;k++) {
				if((i&k) != 0) sum += a[upSize+k];//upSize = mid = n/2
			}
			down[i] = sum;
		}
		Arrays.sort(up);
		Arrays.sort(down);
		//3.up,down에 투포인터
		int i=0,j=downSize-1,ans = 0;;
		while(i<upSize && j>=0) {
			sum = up[i]+down[j];
			if(sum == s) {
				long c1 = 1,c2 = 1;
				while(i+1<upSize && a[i]==a[i+1]) {
					c1++;i++;
				}
				while(j-1>=0 && a[j]==a[j-1]) {
					c2++;j--;
				}
				ans+= c1*c2;
			}
			else if(sum < s)j--;
			else if(sum > s)i++;
		}
		System.out.println(ans);
	}
	
}

//		for(int i=0;i<n;i++) {
//			System.out.print(a[i]+" ");
//		}