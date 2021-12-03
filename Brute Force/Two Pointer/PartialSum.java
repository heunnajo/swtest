package ss;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class PartialSum {
	static final int INF = 987654321;
	public static void main(String args[]) throws Exception{
        //n,s 입력ㄱ 받는다
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
        int[] a = new int[n+1];
        
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        int left=0;
        int right=0;
        int sum=a[0];
        int ans=INF;
        while (left <= right && right < n) {
            if (sum < s) {
                right += 1;
                sum += a[right];
            } else if (sum >= s) {
                ans = Math.min(ans, right-left+1);
                sum -= a[left];
                left++;
            }
        }
        if(ans == INF) ans = 0;
        System.out.println(ans);
    }

}
