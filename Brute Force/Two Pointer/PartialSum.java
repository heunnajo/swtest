package ss;
import java.util.*;
public class PartialSum {

	public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s = sc.nextInt();
        int[] a = new int[n+1];
        for (int i=0; i<n; i++) {
            a[i] = sc.nextInt();
        }
        int left=0;
        int right=0;
        int sum=a[0];
        int ans=987654321;
        while (left <= right && right < n) {
            if (sum < s) {
                right += 1;
                sum += a[right];
            } else if (sum >= s) {
                ans = Math.min(ans, right-left+1);
                left = right; right = left+1;
//                sum -= a[left];
//                left++;
            }
        }
        System.out.println(ans);
    }

}
