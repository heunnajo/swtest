//BOJ #1475 방번호
import java.util.*;
import java.io.*;
public class RoomNumber {

	public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	//int n = Integer.parseInt(br.readLine());
    	String n = br.readLine();
    	int[] num = new int[10];
    	
    	int len = n.length();
    	for(int i=0;i<len;i++) {
    		num[n.charAt(i)-'0']++;
    	}
    	
    	int max = -1;
    	for(int i=0;i<10;i++) {
    		if(i == 6 || i == 9) continue;
    		if(max<num[i]) max = num[i];
    	}
    	
    	int a = max;
    	int sum = num[6]+num[9];
    	int b = sum%2 == 0 ? sum/2 : (sum/2)+1;
    	int ans = Math.max(a, b);
    	
    	System.out.println(ans);
    	
	}

}

//    	for(int i=0;i<10;i++) {
//    		System.out.print(num[i]+" ");
//    	}
//    	System.out.println();