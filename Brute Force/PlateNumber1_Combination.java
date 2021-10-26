package ss;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PlateNumber1_Combination {
	static int ans,n;
    static String input;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();
        n = input.length();
        ans = 1;
		for(int index=0;index<n;index++) {
			int cnt = input.charAt(index)=='c'?26:10;
			//i==0이면 비교할 필요가 없기 때문에 i>0부터 비교한다!
			if(index>0 && input.charAt(index)==input.charAt(index-1)) {
				cnt--;
			}
			ans *= cnt;//i번째의 경우의 수를 곱한다!
		}
		System.out.println(ans);
	}

}
