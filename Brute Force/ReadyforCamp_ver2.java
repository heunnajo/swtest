package ss;
import java.util.*;
public class ReadyforCamp_ver2 {
	static int n,L,R,x;
	static int[] nums;
	static boolean[] check;
	static int go(int index,int sum,int maxD,int minD) {
		if(index == n) {
			int cnt = 0;
			for(int i=0;i<n;i++) {
				if(check[i])cnt++;
			}
			if(cnt<2)return 0;
			if(sum>=L && sum<=R && maxD-minD>=x) return 1;
			else return 0;
		}
		//1.선택O
		check[index] = true;
		int curMax = Math.max(maxD, nums[index]);
		int curMin = Math.min(minD, nums[index]);
		int cnt1 = go(index+1,sum+nums[index],curMax,curMin);//curMax와 curMin에 모두 현재선택하는 nums[index]가 들어감!
		//2.선택X
		check[index] = false;
		int cnt2 = go(index+1,sum,maxD,minD);
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
		System.out.println(go(0,0,-1,2000000));
	}

}
