//BOJ#1427 소트인사이드
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class SortInside {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		char[] sChar = s.toCharArray();
		
		Arrays.sort(sChar);//오름차순 정렬
		
		for(int i=s.length()-1;i>=0;i--) {//역순 출력
			System.out.print(sChar[i]);
		}
		
	}

}