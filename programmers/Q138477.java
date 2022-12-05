// #138477 명예의 전당 (1)
import java.util.Arrays;
class Solution {
    public int[] solution(int k, int[] score) {
        int n = score.length;
        int INF = 2001;
        int[] tmp = new int[n];
        int[] answer = new int[n];
        Arrays.fill(tmp,INF);
        
        for(int i=0;i<n;i++){
            tmp[i] = score[i];
            Arrays.sort(tmp);
            if(i < k) {answer[i] = tmp[0];}
            else if(i >= k){answer[i] = tmp[i-k+1];}
        }
        
        return answer;
    }
}