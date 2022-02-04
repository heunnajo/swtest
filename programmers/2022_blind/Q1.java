//1. 신고 결과 받기
import java.util.*;
class ReceiveReportResult {
    static Map<String,Integer> id_list_Map;
    static void id_listToMap(String[] id_list){
        id_list_Map = new HashMap<>();
        
        int n = id_list.length;
        for(int i=0;i<n;i++){
            id_list_Map.put(id_list[i],i);
        }
    }
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        //0.id_list를 Map으로 만들어 이름으로 인덱스를 조회
        id_listToMap(id_list);
        Map<String,Set<String>> map = new HashMap<>();
        //1.id 별 신고횟수  카운팅 => report 배열 문자열 파싱, map을 완성
        int cnt = report.length;
        for(int i=0;i<cnt;i++){
            String x = report[i];
            int idx = x.indexOf(" ");
            String from = x.substring(0,idx);//신고한 사람
            String to = x.substring(idx+1);//신고받은 사람
            //System.out.println(from);//확인O
            //System.out.println(to);//확인O
            //map에 추가!
            if(!map.containsKey(to)){
                Set<String> set = new HashSet<>();
                set.add(from);
                map.put(to,set);
            }else{
                Set<String> set = map.get(to);
                set.add(from);
                map.put(to,set);
            }
        }
        //2.Map의 key를 순회하면서 ID 별로 몇번 나오는지 카운팅한다 : map의 value인 set의 크기가 된다!
        //3.2에서 k번 이상 신고받은 ID 확인
        //4.3의 ID를 신고한 ID가 받는 메일 1 증가
        for(String s:map.keySet()){
            if(map.get(s).size()>=k){//value set을 순회하면서 이름을 찾고, 그 이름의 인덱스를 얻어야한다.인덱스를 얻어야 answer를 완성하기 때문.
                Set<String> IDWhoReported = map.get(s);
                for(String str:IDWhoReported){
                    answer[id_list_Map.get(str)]++;
                }
            }
        }
        return answer;
    }
}