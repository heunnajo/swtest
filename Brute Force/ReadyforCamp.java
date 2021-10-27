package ss;
import java.util.*;
public class ReadyforCamp {
	static int n,L,R,x;
	static int[] nums;
	static boolean[] check;
	static int go(int index) {
		if(index == n) {
			int sum = 0,cnt=0,maxD=-1,minD=2000000;
			for(int i=0;i<n;i++) {
				if(check[i]) {
					cnt++;
					sum += nums[i];
					if(maxD<nums[i]) maxD = nums[i];
					if(minD>nums[i]) minD = nums[i];
				}
			}
			if(sum>=L && sum<=R && maxD-minD>=x) return 1;
			else return 0;
		}
		//1.선택O
		check[index] = true;
		int cnt1 = go(index+1);
		//2.선택X
		check[index] = false;
		int cnt2 = go(index+1);
		return cnt1+cnt2;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		L = sc.nextInt();
		R = sc.nextInt();
		x = sc.nextInt();
		nums = new int[n];
		check = new boolean[n];
		for(int i=0;i<n;i++) {
			nums[i] = sc.nextInt();
		}
		System.out.println(go(0));
	}

}
