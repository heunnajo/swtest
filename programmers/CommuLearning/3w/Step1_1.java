package programmers.CommuLearning.3w;
//1.위장
import java.util.*;
class Solution {
    public int solution(String[][] clothes) {
        HashMap<String,Integer> map = new HashMap<>();
        
        for(String[] s:clothes){
            String cur = s[1];
            
            map.put(cur,map.getOrDefault(cur,0)+1);
        }
        
        int answer = 1;
        for(int v:map.values()){
            answer *= (v+1);
        }
        answer -= 1;
        return answer;
    }
}
