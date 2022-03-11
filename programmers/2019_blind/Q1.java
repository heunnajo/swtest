//1.오픈채팅방
import java.util.*;
class Solution {
    public String[] solution(String[] record) {
        //0.전처리 : 문자열 파싱
        //1.Map을 완성 : <id,닉네임> 으로 저장
        //2.Enter, Leave일 때 아이디에 해당하는 닉네임과 출력문을 완성
        int len = record.length;
        HashMap<String,String> map = new HashMap<>();
        for(int i=0;i<len;i++){
            String[] cur = record[i].split(" ");
            if(cur[0].equals("Enter") || cur[0].equals("Change")){
                map.put(cur[1],cur[2]);
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<len;i++){
            String[] cur = record[i].split(" ");
            if(cur[0].equals("Enter")){
                sb.append(map.get(cur[1])+"님이 들어왔습니다."+"\n");
            } else if(cur[0].equals("Leave")){
                sb.append(map.get(cur[1])+"님이 나갔습니다."+"\n");
            }
        }
        String tmp = sb.toString();
        return tmp.split("\n");
    }
}