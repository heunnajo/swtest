package ss;
import java.util.*;
public class SH_1_BruteForce {
	//1:승 0:패 최다연승 횟수를 구하는 문제
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] nums = new int[n];
		for(int i=0;i<n;i++) {
			nums[i] = sc.nextInt();
		}
		
		int cnt = 0;
		ArrayList<Integer> list = new ArrayList<>();
		for(int i=0;i<n;i++) {
			if(nums[i]==1) cnt++;
			else {
				list.add(cnt);
				cnt = 0;
			}
		}
		list.add(cnt);
		Collections.sort(list,Collections.reverseOrder());
		System.out.println(list.get(0));
	}
	
}
