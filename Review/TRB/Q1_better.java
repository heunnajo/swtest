//1.문자열 
package ss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//1.문자열 
import java.util.*;
class TRB_Q1 {
	public static void main(String[] args) throws IOException {
		  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		  String ans = solution(br.readLine());
		  System.out.println(ans);
	  }
	static String solution(String s){
      int[] arr = new int[26];//각 알파벳 횟수를 기록. 인덱스 = 알파벳의 인덱스, 배열값 = 알파벳의 횟수
      s = s.toLowerCase();
      int len = s.length();
      for(int i=0;i<len;i++){
          char c = s.charAt(i);
          int idx = c-'a';
          arr[idx]++;
      }
      //가장많은 알파벳 도출
      int Max = 0;
      StringBuilder maxAlpha = new StringBuilder();
      for(int i=0;i<26;i++){
          if(Max<arr[i]) {
              //최댓값 갱신
              Max = arr[i]; maxAlpha = new StringBuilder(); maxAlpha.append((char)(i+97));
          } else if(Max==arr[i]){
        	  maxAlpha.append((char)(i+97));
          }
      }
      return maxAlpha.toString();
  }
  
  
}
