//#138477 명예의 전당(1)

import java.util.*;
class Solution {
    public int[] solution(int k, int[] score) {
        int n = score.length;
        ArrayList<Integer> tmpAns = new ArrayList<>();//일단 k개만 만들어서 최하위 점수 도출
        int[] answer = new int[n];
        
        for(int i=0;i<n;i++){
            if(tmpAns.size() == 0) {
                tmpAns.add(score[i]);//10
            }
            else {//리스트 크기에 따라 비교 대상의 인덱스가 달라짐!
                if(tmpAns.size() <= k){
                    if(score[i] > tmpAns.get(0)){//100 > 10
                        tmpAns.add(0,score[i]);//100 10
                        Collections.sort(tmpAns);//10 100
                    }
                } else{
                    if(score[i] > tmpAns.get(tmpAns.size()-k)){//100 > 10
                        tmpAns.add(0,score[i]);//100 10
                        Collections.sort(tmpAns);//10 100
                    }    
                }
            }
            //for(int x: tmpAns) System.out.print(x+" ");
            if(tmpAns.size() <= k) answer[i] = tmpAns.get(0);
            else answer[i] = tmpAns.get(tmpAns.size()-k);
            
        }
        // for(int j=0;j<k;j++){
        //     System.out.print(tmpAns.get(j)+" ");
        // }
        // System.out.println();
        return answer;
    }
}