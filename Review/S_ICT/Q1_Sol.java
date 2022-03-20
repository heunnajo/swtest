package ss;

import java.util.Arrays;

public class SKT_Q1_Math {

	public static void main(String[] args) {
		int money = 4578;
		int[] costs = {1,4,99,35,50,1000};
		int[] price = new int[501];
		price[1] = costs[0];price[5] = costs[1];price[10] = costs[2];
		price[50] = costs[3];price[100] = costs[4];price[500] = costs[5];
		
		//money를 생산하는 최소 비용 구하기
		int[][] coins = new int[6][2];
		coins[0][0] = 1; coins[0][1] = costs[0]*500;//1
		coins[1][0] = 5; coins[1][1] = costs[1]*100;//5
		coins[2][0] = 10; coins[2][1] = costs[2]*50;//10
		coins[3][0] = 50; coins[3][1] = costs[3]*10;//50
		coins[4][0] = 100; coins[4][1] = costs[4]*5;//100
		coins[5][0] = 500; coins[5][1] = costs[5]*1;//500
		
		Arrays.sort(coins,(a,b)->{return a[1]-b[1];});
		
		int ans = 0;
		for(int i=0;i<6;i++) {
			ans += (money / coins[i][0]) * price[coins[i][0]];//갯수 * 생산단가.
			money %= coins[i][0];
		}
		ans += money * costs[0];
		
		System.out.println(ans);
	}

}
