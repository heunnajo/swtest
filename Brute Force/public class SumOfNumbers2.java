package ss;
import java.util.*;
public class SumOfNumbers2 {
	static int n,m,a[];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt(); m = sc.nextInt();
		a = new int[n];
		for(int i=0;i<n;i++) {
			a[i] = sc.nextInt();
		}
		int left = 0,right = 0,sum = 0,ans = 0;
//		sum += a[right];//sum = a[0]
		sum += a[0];//sum = a[0]
		while(left<=right && right<n) {
			if(sum<m) {
				right++;
				sum+=a[right];
			} else if(sum == m) {
				ans++;
				right++;
				sum+=a[right];
			} else if(sum > m) {
				sum -= a[left];
				left++;
			}
		}
		System.out.println(ans);
	}

}
