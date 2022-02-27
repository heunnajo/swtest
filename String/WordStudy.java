/*백준 1157 단어 공부 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//1.문자열 
import java.util.*;
class Main {
	public static void main(String[] args) throws IOException {
		  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		  char ans = solution(br.readLine());
		  System.out.println(ans);
	  }
	static char solution(String s){
      int[] arr = new int[26];//각 알파벳 횟수를 기록. 인덱스 = 알파벳의 인덱스, 배열값 = 알파벳의 횟수
      s = s.toUpperCase();
      int len = s.length();
      for(int i=0;i<len;i++){
          char c = s.charAt(i);
          int idx = c-'A';
          arr[idx]++;
      }
      //횟수  카운팅  확인!O
//      for(int i=0;i<26;i++){
//    	 System.out.print(arr[i]+" ");
//      }
//      System.out.println();
      
      //"가장 많은" 알파벳 "하나" 도출
      int Max = -1;
      char maxAlpha = '?';
      for(int i=0;i<26;i++){
          if(Max<arr[i]) {
              //최댓값 갱신
              Max = arr[i];
              maxAlpha = (char)(i+65);
          } 
          else if(Max==arr[i]){
        	  maxAlpha = '?';
          }
      }
      return maxAlpha;
  }
  
  
}