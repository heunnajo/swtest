//2. K진수에서 소수 갯수 구하기
package ss;
import java.util.*;
import java.io.*;
public class CountPrimeNumber_baseK {
	static int solve(int n,int k) {
		int cnt = 0;
		//1.k진법 변환
		String numStr = "";
		while(n>0) {
			numStr = n%k + numStr;
			n /= k;
		}
		//2.소수 갯수 세기
		String[] nums = numStr.split("0");
		System.out.println("split으로 자른 갯수=nums의 크기 :"+nums.length);
		
		for(String s:nums) {
			System.out.println("s: "+s);
			try{
                long num = Long.parseLong(s);//여기서 NumberFormatException 터짐.
                if(isPrime(num)) cnt++;
            } catch (NumberFormatException e) {
                continue;
            }
		}
		return cnt;
	}
	//prime number 확인
	static boolean isPrime(long n) {
		if(n==1 || n==0) return false;
		int root = (int)Math.sqrt(n);
		for(int i=2;i<=root;i++) {
			if(n%i == 0) return false;
		}
		return true;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		System.out.println(solve(n,k));
	}

}
