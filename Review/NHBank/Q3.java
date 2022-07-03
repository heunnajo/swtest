//implementation
import java.util.*;
class Solution {
    public String[] solution(int n, int m, String[] suspects, String[] transactions) {
        HashMap<String,int[]> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();

        for(int i=0;i<suspects.length;i++){
            set.add(suspects[i]);
        }

        for(int i=0;i<transactions.length;i++){//map을 완성!
            String[] input = transactions[i].split(" ");
            String acc1 = input[0]; String acc2 = input[1]; int price = Integer.parseInt(input[2]);

            if(set.contains(acc1) && set.contains(acc2)) continue;
            else if(set.contains(acc1)){//acc2가 의심 계좌에 해당하는지 조건 판단, 해당하면 map 갱신!
                if(map.containsKey(acc2)){
                    int[] info = map.get(acc2);
                    info[0]++; info[1] = info[1]+price;
                    map.put(acc2,info);
                } else{
                    //의심계좌이면 map에 넣음
                    if(price >= m){
                        int[] info = new int[2];
                        info[0] = 1; info[1] = price;
                        map.put(acc2,info);
                    }
                }
            } else if(set.contains(acc1)){//acc1가 의심 계좌에 해당하는지 조건 판단, 해당하면 map 갱신!
                if(map.containsKey(acc1)){
                    int[] info = map.get(acc1);
                    info[0]++; info[1] = info[1]+price;
                    map.put(acc1,info);
                } else{
                    //의심계좌이면 map에 넣음
                    if(price >= m){
                        int[] info = new int[2];
                        info[0] = 1; info[1] = price;
                        map.put(acc1,info);
                    }
                }
            }
        }
        ArrayList<String> tmp = new ArrayList<>();
        for(int i=0;i<suspects.length;i++){
            tmp.add(suspects[i]);
        }
        for(String key:map.keySet()){
            tmp.add(key);
        }

        Collections.sort(tmp);

        String[] answer = new String[tmp.size()];
        int idx = 0;

        for(String str:tmp){
            answer[idx++] = str;
        }

        // String[] answer = new String[map.size()];
        // int idx = 0;

        // for(String key:map.keySet()){
        //     answer[idx++] = key;
        // }
        return answer;
    }
}