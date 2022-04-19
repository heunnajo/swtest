//BOJ #2870 수학문제
import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		LinkedList<BigInteger> nums = new LinkedList<>();
		
		while(N-- >0) {
			String input = br.readLine();
			int len = input.length();
			StringBuilder tmpNum = new StringBuilder();
			
			for(int i=0;i<len;i++) {
				if(!Character.isLowerCase(input.charAt(i))) {
					tmpNum.append(input.charAt(i));
				} else {
					if(tmpNum.length()>0) {
						BigInteger cur = new BigInteger(tmpNum.toString());
						nums.add(cur);
						tmpNum = new StringBuilder();
					}
				}
			}
			if(tmpNum.length()>0) {
				BigInteger cur = new BigInteger(tmpNum.toString());
				nums.add(cur);
			}
		}
		Collections.sort(nums);
		StringBuilder ans = new StringBuilder();
		
		for(BigInteger a:nums) {
			ans.append(a+"\n");
		}
		System.out.print(ans);
	}

}
