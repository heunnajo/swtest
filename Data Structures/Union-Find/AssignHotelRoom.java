import java.util.*;
class AssignHotelRoom {
    public long[] solution(long k, long[] room_number) {
        int len = room_number.length;//최대 20만
        long[] answer = new long[len];
        HashMap<Long,Long> hm = new HashMap<>();
        for(int i=0;i<len;i++){
            //배정할 방 번호 num
            long num = room_number[i];
            //1.처음 배정하는 번호인 경우
            if(!hm.containsKey(num)){
                hm.put(num,num+1);
                answer[i] = num;
            }
            //2.이미 배정된 바 있는 번호인 경우:루트 노드를 찾고, 경로 압축 통해 갱신
            else{
                ArrayList<Long> list = new ArrayList<>();
                while(hm.containsKey(num)){
                    list.add(num);
                    num = hm.get(num);
                }
                list.add(num);
                answer[i] = num;
                //3.경로 압축
                for(long l:list){
                    hm.put(l,num+1);
                }
            }
            
        }
        return answer;
    }
}