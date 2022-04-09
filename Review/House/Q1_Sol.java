package ss;
import java.util.*;
import java.io.*;
public class Q1_Sol {

	public static void main(String[] args) throws Exception{
//		String input = "EEESEEEEEENNNN";
		String input = "SSSSSSWWWNNNNNN";
		int len = input.length();
		
		String s1 = "Time ";
		String s2 = " : Go Straight ";
		String s3 = "m and turn ";
		
		//int num = 10;
		LinkedList<String> list = new LinkedList<>();//main op : add 
		String tmpAns = "";
		int cnt = 1;
		int x = 0,y = 0;
		String d = "";
		for(int i=1;i<len;i++) {
			if(input.charAt(i) == input.charAt(i-1)) {
				cnt++;
			} else {//다른 방향 만났을 때
				//예외처리 : x>5 라
				x = i-cnt;
				if(cnt>5) {
					x = i-5;
					//System.out.println("x: "+x);
				}
				y = cnt*100;
				int prevDir = input.charAt(i-1);
				int nextDir = input.charAt(i);
				if(nextDir - prevDir>0) d = "right";
				else d = "left";
				cnt = 1;//초기화
				tmpAns = s1+x+s2+y+s3+d;
				list.add(tmpAns);
			}
		}
		String[] ans = list.toArray(new String[list.size()]);
		int idx = 0;
		for(String str:list) {
			ans[idx++] = str;
		}
		for(int i =0;i<list.size();i++) {
			System.out.println(ans[i]);
		}
		//System.out.println(s+num);
	}

}
