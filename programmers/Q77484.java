//#77484 로또의 최고 순위와 최저 순위

import java.util.*;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        
        int zeroCnt = 0;
        int hitCnt = 0;
        HashSet<Integer> set = new HashSet<>();
        int[] rank = {6,6,5,4,3,2,1};
        
        for(int i=0;i<6;i++){
            set.add(win_nums[i]);
        }
        
        for(int i=0;i<6;i++){
            if(set.contains(lottos[i])){hitCnt++;}
            else if(lottos[i] == 0){zeroCnt++;}
        }
        //System.out.println("현재 확실한 맞춘 갯수: "+hitCnt);
        //System.out.println("0 갯수: "+zeroCnt);
        
        answer[0] = rank[zeroCnt+hitCnt];
        answer[1] = rank[hitCnt];
        
        return answer;
    }
}