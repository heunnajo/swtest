//3. 보석 쇼핑
import java.util.*;
class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        //1.자료구조
        int len = gems.length;
        int left = 0, right = 0;
        int ans_left = 0,ans_right = 0;
        int distance = Integer.MAX_VALUE;
        
        HashMap<String,Integer> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        for(String s:gems) set.add(s);
        int n = set.size();
        
        
        //2.알고리즘 : 투포인터로 gems 순회
        while(true){
            //정답 찾은 경우 : 구간 길이 최솟값 도출 위해 구간 줄인다 : left++
            if(map.size() == n){
                //구간 길이 최솟값 도출
                if(right - left <distance){
                    distance = right-left;//dist 갱신
                    ans_left = left+1; ans_right = right;
                }
                
                map.put(gems[left],map.get(gems[left])-1);
                if(map.get(gems[left]) == 0) map.remove(gems[left]);
                left++;
            } 
            else if(right == len) break;//인덱스 범위 체크
            else{
                //모든 보석 종류 map에 기록 : 구간 길이 계속 늘인다 : right++
                //map.put(gems[right],map.getOrDefault(0,map.get(gems[right])+1));
                //map.put(gems[right],map.getOrDefault(0,gems[right])+1);
                map.put(gems[right],map.getOrDefault(gems[right],0)+1);
                right++;
            }
        }        
        answer[0] = ans_left; answer[1] = ans_right;
        return answer;
    }
}