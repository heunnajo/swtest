//Q142086 가장 가까운 같은 글자
import java.util.*;

class Solution {
    public int[] solution(String s) {
        int len = s.length();
        int[] answer = new int[len];
        HashMap<Character,Integer> map = new HashMap<>();
        
        for(int i=0;i<len;i++){
            char c = s.charAt(i);
            
            if(!map.containsKey(c)){
                answer[i] = -1;
                map.put(c,i);
            } else{
                answer[i] = i-map.get(c);
                map.put(c,i);
            }
        }
        
        // for(Character key:map.keySet()){
        //     System.out.print("key: "+key+" value: "+map.get(key));
        //     System.out.println();
        // }
        return answer;
    }
}