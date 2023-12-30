import java.util.*;
import java.io.*;
//신규 아이디 추천
public class KB2021_Q1 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String new_id = br.readLine();
		solution(new_id);
	}
	public static String solution(String new_id) {
        String answer = new_id;
        //1.step1 : 소문자로 치환
        answer = answer.toLowerCase();
        
        //2.step2 : 소문자,숫자,빼기,밑줄,마침표 외 문자 제거
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<answer.length();i++) {
        	char cur = answer.charAt(i);
        	if(('a' <= cur && cur <= 'z') || 
        			('0' <= cur && cur <= '9') ||
        			cur == '-' || cur == '_' || cur == '.') {
        		sb.append(cur);
        	}
        }
        answer = sb.toString();
        //System.out.println(answer);
        //3.step3 : 마침표 2번 이상 연속된 것 하나의 마침표로 치환
        while(answer.contains("..")) {
        	answer = answer.replace("..", ".");
        }
        //System.out.println(answer);
        
        //4.step4: 처음이나 끝에 위치하면 제거
        if(answer.charAt(0) == '.') answer = answer.substring(1);
        if(answer.length() >= 1 && answer.charAt(answer.length()-1) == '.') answer = answer.substring(0,answer.length()-1);
        //System.out.println(answer);
        
        //5.step5 : 빈 문자열이면 'a' 대입
        if(answer.length() == 0) answer = "a";
        
        //6.step6 : 길이 16자 이상이면 첫15개 외 나머지 문자 모두 제거, 제거 후 가장 끝 마침표이면 제거
        if(answer.length() >= 16) {
        	answer = answer.substring(0,15);
        }
        if(answer.charAt(answer.length()-1) == '.') answer = answer.substring(0,answer.length()-1);
        
        //7.step7 :길이 2 이하이면 마지막 문자 길이 3될때까지 반복적으로 붙임
        if(answer.length() <= 2){
        	char last = answer.charAt(answer.length()-1);
        	while(answer.length() < 3) {
        		answer = answer + last;
        	}
        }
        System.out.println(answer);
        
        return answer;
    }

}
