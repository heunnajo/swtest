//Q92334 신고 결과 받기

import java.util.*;
class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int n = id_list.length;
        int[] answer = new int[n];
        HashMap<String,ArrayList<String>> reportInfo = new HashMap<>();
        HashMap<String,Integer> mailInfo = new HashMap<>();
        
        for(int i=0;i<report.length;i++){
            String[] tmp = report[i].split(" ");
            String A = tmp[0]; String B = tmp[1];
            //System.out.println("신고한 사람A: "+A+"신고받은 사람B: "+B);
            ArrayList<String> list = new ArrayList<>();
            if(reportInfo.containsKey(B)){
                list = reportInfo.get(B);
            } else{
                list = new ArrayList<>();
            }
            if(!list.contains(A)){
                list.add(A);
            }
            reportInfo.put(B,list);
        }
        for(String key:reportInfo.keySet()){
            int count = reportInfo.get(key).size();
            if(count < k) continue;
            for(String value:reportInfo.get(key)){
                mailInfo.put(value,mailInfo.getOrDefault(value,0)+1);
            }
        }
        
        for(int i=0;i<n;i++){
            if(mailInfo.get(id_list[i]) == null) continue;
            answer[i] = mailInfo.get(id_list[i]);
        }
        return answer;
    }
}