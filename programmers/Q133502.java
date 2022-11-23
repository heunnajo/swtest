//#133502 햄버거 만들기

import java.util.*;
class Solution {
    public int solution(int[] ingredient) {
        int answer = 0;
        int n = ingredient.length;
        
        ArrayList<Integer> hamList = new ArrayList<>();
        ArrayList<Integer> hamburger = new ArrayList<>();
        hamburger.add(1); hamburger.add(2); hamburger.add(3); hamburger.add(1);
        
        for(int i=0;i<n;i++){
            hamList.add(ingredient[i]);
            
            //마지막 재료 4개가 햄버거인지 판단
            if(hamList.size() >= 4){
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(hamList.get(hamList.size()-4));
                temp.add(hamList.get(hamList.size()-3));
                temp.add(hamList.get(hamList.size()-2));
                temp.add(hamList.get(hamList.size()-1));
                
                if(temp.equals(hamburger)){
                    answer++;
                    
                    hamList.remove(hamList.size()-1);
                    hamList.remove(hamList.size()-1);
                    hamList.remove(hamList.size()-1);
                    hamList.remove(hamList.size()-1);
                }
            }
        }
        return answer;
    }
}