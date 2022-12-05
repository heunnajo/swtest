//#81301 숫자 문자열과 영단어

import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        StringBuilder ans = new StringBuilder();
        int len = s.length();
        HashMap<String,Integer> map = new HashMap<>();
        map.put("zero",0); map.put("one",1); map.put("two",2); map.put("three",3); map.put("four",4);
        map.put("five",5); map.put("six",6); map.put("seven",7); map.put("eight",8); map.put("nine",9);
        
        StringBuilder tmp = new StringBuilder();
        for(int i=0;i<len;i++){
            char cur = s.charAt(i);
            //1.숫자일 때
            if(0 <= cur - '0' && cur -'0' <= 9){
                ans.append(cur);
            //2.숫자가 아닐 때 : 숫자를 만날 때까지 문자열 합쳐나간다. 숫자를 만나면 지금까지의 tmp에 대해 문자=>숫자 형변환!
            } else{
                tmp.append(cur);
                String curStr = tmp.toString();
                if(map.containsKey(curStr)){
                    ans.append(map.get(curStr));
                    tmp = new StringBuilder();
                }
            }
        }
        answer = Integer.parseInt(ans.toString());
        return answer;
    }
}