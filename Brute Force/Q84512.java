//Q84512 모음 사전
import java.util.*;
class Solution {
    ArrayList<String> list;
    public int solution(String word) {
        list = new ArrayList<>();
        go("",0);
        return list.indexOf(word);
    }
    void go(String str,int len){
        //1. 종료 : 자릿수가 5를 초과할 때
        if(len > 5) return;
        list.add(str);
        //2. 현재 경우 선택, 다음 경우 재귀 호출
        for(int i=0;i<5;i++){
            go(str+"AEIOU".charAt(i),len+1);
        }
    }
}