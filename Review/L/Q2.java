//2. 부서진 키보드
import java.util.*;
class Q2 {
    public int solution(String[] sentences, int n) {
        //m개에서 n개를 선택, 조합
        HashMap<Integer,Boolean> map = new HashMap<>();
        int len = sentences.length;
        //sentences[i]의 대문자 갯수 카운팅, 사용된 알파벳 저장!
        int[] capCnt = new int[len];//i번 문자열의 대문자 갯수
        for(int k=0;k<len;k++){
            String[] s = sentences[k].split(" ");
            for(int i=0;i<s.length;i++){
                for(int j=0;j<s[i].length();j++){//대문자면 갯수 카운팅, shift+해당 문자 저장~
                    //문자열 파싱한 sentences[i]
                    //System.out.print(s[i].charAt(j)+" ");//문자열 파싱결과 확인
                    if(97<= s[i].charAt(j) && s[i].charAt(j) <= 122){
                        capCnt[k]++;
                        map.put(0,true);//0 = shift를 의미
                        map.put(s[i].charAt(j)-'A',true);
                    } else{//소문자면 바로 저장
                        map.put(s[i].charAt(j)-'a',true);
                    }
                }
            }
        }
        //map, capCnt 확인
        for(int num:map.keySet()){
            System.out.print(num+" ");
        }
        int answer = -1;
        return answer;
    }
}