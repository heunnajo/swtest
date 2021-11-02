package ss;
import java.util.*;
public class ArithmeticSequence {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] Nums = new int[N];
		int ans = -1;
		for(int i=0;i<N;i++) {
			Nums[i] = sc.nextInt();
		}
		if(N==1) {
			System.out.println(0);
			System.exit(0);
		}
		for(int d1=-1;d1<=1;d1++) {
			for(int d2=-1;d2<=1;d2++) {
				int count = 0;
				if(d1!=0) count++;
				if(d2!=0) count++;
				
				//첫항과 공차를 구한다!=>an을 구할 수 있다!
				//첫번째 항과 두번째 항의 차 = diff로 설정.
				int a0 = Nums[0]+d1;
				int diff = Nums[1]+d2-a0;// a1-a0
				
				int an = a0+diff;//수식에 근거.
				boolean flag = true;
				for(int i=2;i<N;i++) {
					an += diff;
					if(an == Nums[i]) continue;
					else if(an == Nums[i]-1) count++;
					else if(an == Nums[i]+1) count++;
					else {
						flag = false; break;
					}
				}
				if(flag) {
					if(ans == -1 || ans>count) ans = count;
				}
			}
		}
		System.out.println(ans);
	}

}
