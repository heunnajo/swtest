//BOJ#2992 크면서 작은 수
import java.util.*;
import java.io.*;
public class SmallestAmongBigger {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String nStr = br.readLine();
		int n = Integer.parseInt(nStr);
		
		int[] nums = new int[nStr.length()];
		int idx = nStr.length()-1;
		while(n>0) {
			nums[idx--] = n % 10;
			n /= 10;
		}
		
		int ans = solve(nums);
		System.out.println(ans);
	}
	static int solve(int[] nums) {
		//1.증가시켜야하는 digit 찾음
		int i = nums.length-1;
		while(i>0 && nums[i-1]>=nums[i]) {//동일한 경우에도 i--
			i--;
		}
		if(i == 0) return 0;
		
		//2.i-1보다 큰 인덱스 j를 찾아서 swap
		int j = nums.length-1;
		while(nums[i-1] > nums[j]) {
			j--;
		}
		//3.nums[i-1] <=> nums[j]
		int tmp = nums[i-1];
		nums[i-1] = nums[j];
		nums[j] = tmp;
		
		//4.i~끝까지 오름차순 재정렬
		j = nums.length-1;
		while(i<=j) {
			tmp = nums[i];
			nums[i] = nums[j];
			nums[j] = tmp;
			i++; j--;
		}
		
		//5.정답 도출
		int ans = 0;
		for(int k=0;k<nums.length;k++) {
			ans += nums[k];
			ans *= 10;
		}
		return ans /= 10;
	}

}

//		for(int i=0;i<nStr.length();i++) {
//			System.out.println(nums[i]+" ");
//		}