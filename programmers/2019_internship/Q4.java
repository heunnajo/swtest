//4.호텔 방 배정
import java.util.*;
class Solution {
    public long[] solution(long k, long[] room_number) {
        Map<Long,Long> map = new HashMap<>();
        int len = room_number.length;
        
        long[] ans = new long[len];
        for(int i=0;i<len;i++){
            long num = room_number[i];
            if(!map.containsKey(num)){//1.아직 방 배정X
                map.put(num,num+1); ans[i] = num;
            } else{//2.이미 방 배정O
                LinkedList<Long> list = new LinkedList<>();
                while(map.containsKey(num)){
                    num = map.get(num);
                    list.add(num);
                }
                //map에 key로 존재하지 않는 num이 바로 배정할 방번호이며, 루트 노드가 된다!
                ans[i] = num;
                //경로 압축 : 루트 노드 갱신!
                for(Long l:list){
                    map.put(l,num+1);
                }
            }
        }
        return ans;
    }
}