//1.로또의 최고 순위와 최저 순위
import java.util.*;
class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        int[] ranking = {6,6,5,4,3,2,1};//인덱스 : 맞춘 갯수
        Map<Integer,Integer> map = new HashMap<>();
        for(int a:win_nums) map.put(a,1);//value는 사실상 무의미.
        
        int base = 0;//0을 고려하지 않고 일치하는 숫자 갯수
        int zeroCnt = 0;
        for(int a:lottos){
            if(a == 0) zeroCnt++;
            if(map.containsKey(a)) base++;
        }
        int highestRank = base + zeroCnt;//최대 6개, 최소 0개
        int lowestRank = base;//최대 6개, 최소 0개
        
        answer[0] = ranking[highestRank]; answer[1] = ranking[lowestRank];
        return answer;
    }
}
