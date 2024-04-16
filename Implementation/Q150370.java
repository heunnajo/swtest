//Q150370 개인정보 수집 유효기간

import java.util.*;
class Solution {
    public ArrayList<Integer> solution(String today, String[] terms, String[] privacies) {
        
        HashMap<String,Integer> termsInfo = new HashMap<>();
        for(int i=0;i<terms.length;i++){
            String[] tmp = terms[i].split(" ");
            termsInfo.put(tmp[0],Integer.parseInt(tmp[1]));
        }
        int intToday = getDate(today);
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<privacies.length;i++){
            String[] tmp = privacies[i].split(" ");
            int date = getDate(tmp[0]);
            int period = termsInfo.get(tmp[1]);
            if(date + period*28 <= intToday){
                list.add(i+1);
            }
        }
        return list;
    }
    static int getDate(String str){
        int res = 0;
        
        String[] tmp = str.split("\\.");
        int y = Integer.parseInt(tmp[0]);
        int m = Integer.parseInt(tmp[1]);
        int d = Integer.parseInt(tmp[2]);
        
        res = y*12*28 + m*28 + d;
        //System.out.println(res);
        return res;
    }
}