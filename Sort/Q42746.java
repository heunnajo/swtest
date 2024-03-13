//가장 큰 수
package cdTest;
import java.util.*;
import java.io.*;

public class Q42746 {
//	static class Comparator<String> implements Comparator{
//		@Override
//		public int compareTo(String str) {
//			return str-this;
//		}
//	}
	public static void main(String[] args) throws Exception{
//		int[] numbers = {6,10,2};
		int[] numbers = {3, 30, 34, 5, 9};
		solution(numbers);
	}
	static String solution(int[] numbers) { 
		int n = numbers.length;
		
		String[] tmp = new String[n];
		for(int i=0;i<n;i++) {
			tmp[i] = numbers[i]+"";
		}
		Comparator<String> comp = new Comparator<String>() {
			@Override
			public int compare(String a,String b) {
				//기본 내림차순 정렬 b.compareTo(a)
				String ab = a+b; String ba = b+a;
				return ba.compareTo(ab);
			}
		};
		
		Arrays.sort(tmp,comp);
		
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++){
            sb.append(tmp[i]);
        }
        String answer = sb.toString();
        if(answer.charAt(0) == '0') {
        	answer = "0";
        }
        System.out.println(answer);
		return answer;
	}
}
