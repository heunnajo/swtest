//3.다단계 칫솔 판매
import java.util.*;
class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        
        //1.자료구조  : Map 2개
        Map<String,String> myParent = new HashMap<>();
        Map<String,Integer> myIdx = new HashMap<>();
        
        for(int i=0;i<enroll.length;i++){
            myParent.put(enroll[i],referral[i]);
            myIdx.put(enroll[i],i);
        }
        
        //2.알고리즘 : seller(amount) 순회하면서 정답 저장
        for(int i=0;i<seller.length;i++){
            String me = seller[i]; int profit = amount[i]*100;
            while(!me.equals("-")){
                //1.현재 내 이익 계산, 합산해서 저장
                int profitToParent = profit/10;
                int myProfit = profit-profitToParent;
                answer[myIdx.get(me)] += myProfit;
                //2.다음 노드로 갱신 : 노드, 이익 갱신
                me = myParent.get(me); profit /= 10;
                if(profit < 1) break;
            }
        }
        return answer;
    }
}