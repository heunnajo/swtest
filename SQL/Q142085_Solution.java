//Q142085 디펜스 게임
import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());

        for (int e : enemy) {
            queue.add(e);
            answer++;
            n -= e;
            if (n < 0) {
                if(k==0){
                    return answer-1;
                }
                n = n + queue.poll();
                k--;
            }
        }

        return enemy.length;
    }
}