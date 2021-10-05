package ss;
import java.io.*;
public class ThreeTimes {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		int sum;
		int cnt = 0;;
		while(str.length()>1) {
			cnt++; sum = 0;
			char[] str_arr = str.toCharArray();
			for(char c:str_arr) {
				sum+= c-'0';
			}
			str = String.valueOf(sum);
		}
		boolean flag = false;
		int finalN = Integer.parseInt(str);
		if(finalN == 3 || finalN == 6 || finalN == 9) flag = true;
		
		System.out.println(cnt);
		if(flag) System.out.println("YES");
		else System.out.println("NO");
	}

}
