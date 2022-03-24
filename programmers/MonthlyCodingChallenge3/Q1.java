import java.util.*;
class Q1 {
    public int solution(int[] numbers) {
        int answer = 0;
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<numbers.length;i++){
            map.put(numbers[i],1);
        }
        for(int i=0;i<10;i++){
            if(!map.containsKey(i)) answer += i;
        }
        return answer;
    }
}