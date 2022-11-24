//#118666 성격 유형 검사하기
//현타가 굉장한 빡구현 in Java

import java.util.*;
class Solution {
    public String solution(String[] survey, int[] choices) {
        HashMap<Character,Integer> map = new HashMap<>();
        map.put('R',0); map.put('T',0);
        map.put('C',0); map.put('F',0);
        map.put('J',0); map.put('M',0);
        map.put('A',0); map.put('N',0);
        int n = survey.length;
        
        //지표의 8개 알파벳별로 map<알파벳,점수> 완성
        for(int i=0;i<n;i++){
            String curS = survey[i];
            char first = curS.charAt(0);
            char second = curS.charAt(1);
            int curC = choices[i];
            
            if(1 <= curC && curC <= 3){//비동의인 경우, 첫번째 글자 점수 증가
                if(curC == 1) {map.put(first,map.get(first)+3);}
                else if(curC == 2) {map.put(first,map.get(first)+2);}
                else if(curC == 3) {map.put(first,map.get(first)+1);}
            } else if(5 <= curC && curC <= 7){//동의인 경우, 2번째 글자 점수 증가
                if(curC == 7) {map.put(second,map.get(second)+3);}
                else if(curC == 6) {map.put(second,map.get(second)+2);}
                else if(curC == 5) {map.put(second,map.get(second)+1);}
            }
        }
        //map 확인 O //헷갈림 지옥:/
        // for(Character key:map.keySet()){
        //     System.out.println("key: "+key+" value: "+map.get(key));
        // }
        StringBuilder sb = new StringBuilder();
        int a = -1; int b = -1;
        a = map.get('R'); b = map.get('T');
        if(a >= b) {sb.append("R");}
        else {sb.append("T");}
        
        a = map.get('C'); b = map.get('F');
        if(a >= b) {sb.append("C");}
        else {sb.append("F");}
        
        a = map.get('J'); b = map.get('M');
        if(a >= b) {sb.append("J");}
        else {sb.append("M");}
        
        a = map.get('A'); b = map.get('N');
        if(a >= b) {sb.append("A");}
        else {sb.append("N");}
        
        return sb.toString();
    }
}