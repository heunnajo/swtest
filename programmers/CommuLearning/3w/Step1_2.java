import java.util.HashMap;
import java.util.Map;
//1.방울
class Solution {
    public int solution(int[] bell) {
        for(int i=0; i<bell.length; i++) {
            if(bell[i] == 2) {
                bell[i] = -1;
            }
        }
        
        int total = bell[0];
        int answer = 0;
        
        Map<Integer, Integer> dict = new HashMap<>();
        dict.put(0, -1);
        dict.put(total, 0);
        
        for(int i=1; i<bell.length; i++) {
            total += bell[i];
            if(!dict.containsKey(total)) {
                dict.put(total, i);
            } else {
                answer = Math.max(answer, i - dict.get(total));
            }
        }
        
        return answer;
    }
}