//유형 검사
import java.util.*;
class Solution {
    public String solution(String[] survey, int[] choices) {

        HashMap<Character,Integer> map = new HashMap<>();
        int[] response = {0,3,2,1,0,1,2,3};

        int n = survey.length;

        map.put('R',0);map.put('T',0);map.put('C',0);map.put('F',0);
        map.put('J',0);map.put('M',0);map.put('A',0);map.put('N',0);
        for(int i=0;i<n;i++){
            char disagree = survey[i].charAt(0);
            char agree = survey[i].charAt(1);
            int cur = choices[i];

            if(1<=cur && cur<=3){
                map.put(disagree,map.get(disagree)+response[cur]);
            } else if(5<=cur && cur<=7){
                map.put(agree,map.get(agree)+response[cur]);
            } 
        }

        //map 확인
        // for(char c:map.keySet()){//없는 건 null로 되서 NPE가 발생할 것!그래서 대소비교 시 null인지도 함께 체크
        //     System.out.println("key: "+c+","+"점수: "+map.get(c));

        // }

        StringBuilder ans = new StringBuilder();
        int numOne1 = map.get('R'); int numOne2 = map.get('T');
        if(numOne1>= numOne2){ans.append('R');}
        else{ans.append('T');}

        int numTwo1 = map.get('C'); int numTwo2 = map.get('F');
        if(numTwo1>= numTwo2){ans.append('C');}
        else{ans.append('F');}

        int numThree1 = map.get('J'); int numThree2 = map.get('M');
        if(numThree1>= numThree2){ans.append('J');}
        else{ans.append('M');}

        int numFour1 = map.get('A'); int numFour2 = map.get('N');
        if(numFour1>= numFour2){ans.append('A');}
        else{ans.append('N');}

        return ans.toString();
    }
}
//System.out.print("disagree: "+disagree+","+"agree: "+agree);
            //System.out.println();