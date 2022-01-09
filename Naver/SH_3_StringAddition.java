package ss;
import java.util.*;
public class SH_3_StringAddition {
	static String ans = "";//정답
	static String numToStr = "";//영어로된 문자열
	static int offset,l,r;
	static int len = numToStr.length();//전역변수 str의 길이!
	static String[] arr = {
			"zero",
		    "one",
		    "two",
		    "three",
		    "four",
		    "five",
		    "six",
		    "seven",
		    "eight",
		    "nine",
		    "ten",
		    "eleven",
		    "twelve",
		    "thirteen",
		    "fourteen",
		    "fifteen",
		    "sixteen",
		    "seventeen",
		    "eighteen",
		    "nineteen"
	};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		offset = sc.nextInt();
		l = sc.nextInt();
		r = sc.nextInt();
		
		ans = "";
		solution();//ans를 만든다.
		System.out.println(ans);
	}
	static void solution() {
		for(int i=offset;len<r;i++) {
			convert(i);//str을 완성해간다.
		}
		ans = numToStr.substring(l-1,r);
	}
	static void convert(int x) {
		//0.영어 문자열로 변환할 문자열 str
		String str = String.valueOf(x);
		int n = str.length();
		//1.문자열 str의 각 자릿수마다 확인
		for(int i=0;i<n;i++) {
			if(i+1<n && str.charAt(i)=='1') {
				int tmp = Integer.parseInt(str.substring(i,i+2));
				String num = arr[tmp];
				numToStr += num;//오답
				len += num.length();
				i++;
			} else {
				String num = arr[str.charAt(i)-'0'];
				numToStr += num;//오답
				len += num.length();
			}
		}
	}
}
