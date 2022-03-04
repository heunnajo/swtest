//2.튜플
import java.util.*;
class Solution {
    public int[] solution(String s) {
        int len = s.length();
        
        Set<Integer> set = new HashSet<>();
        
        for(int i=0;i<len;i++){
            char cur = s.charAt(i);
            if(cur == '{' || cur == '}' || cur == ',') continue;
            else {
                System.out.println("cur: "+cur);
                System.out.println("지금 set에 넣는 정수 cur-'0' : "+(cur-'0'));
                set.add(cur-'0');
            }
        }
        
        int size = set.size(); int idx = 0;
        int[] answer = new int[size];
        for(int i:set) answer[idx++] = i;
        return answer;
    }
}