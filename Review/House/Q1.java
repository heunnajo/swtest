
import java.util.*;

class Solution {
    public String solution(String tstring, String[][] variables) {
        HashMap<String,String> map = new HashMap<>();

        //전처리 과정 : map을 완성

        int len = variables.length;
        for(int i=0;i<len;i++){
            String[] cmd = variables[i];
            if(!map.containsKey(cmd[0])){
                map.put("{"+cmd[0]+"}",cmd[1]);//중복되는 단어는 없다.
            }
        }

        //예외 케이스 : 만약 value에 모든 해당하는 부분이 {}를 포함한다면 tstring 그 자체가 정답이 됨.
        boolean isValid = false;
        for(int i=0;i<len;i++){
            String[] cmd = variables[i];
            String key = cmd[0], value = cmd[1];
            if(!map.containsKey(value)){
                isValid = true;
            }
        }
        if(!isValid) return tstring;

        //tstring에서 치환할 부분을 판단해야함:replace 사용
        //tstring에서 map의 key에 해당하는 부분을 map.get(key)로 변환
        //String cmd = "";
        for(String s:map.keySet()){
            //System.out.println(s+": "+map.get(s));
            String cmd = map.get(s);
            while(map.containsKey(cmd)){
                cmd = map.get(cmd);
            }
            tstring = tstring.replace(s,cmd);
        }

        System.out.println("최종 정답 tstring: "+tstring);
        //변환한 tstring을 리턴
        return tstring;
    }
}