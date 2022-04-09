//can't handle infinite loop
import java.util.*;
class Solution {

    public String[] solution(String path) {
        //바로 정적 자료구조에 담을 수는 없음.
        ArrayList<String> list = new ArrayList<>();
        HashMap<String,Integer> dir = new HashMap();
        dir.put(N,0); dir.put(E,1); dir.put(S,2); dir.put(W,3);
        String ans = "Time XX: Go straight YYm and turn DD";

        //path의 각 문자열 순회하면서 x,y,d를 구함.
        //list에 replace()로 x,y,d로 치환함.
        int cnt = 1;
        //replace를 이용한다면 자료형은 String이 되어야 하나?!
        String x = "",y = "", d = "";
        for(int i=1;i<path.length();i++){
            if(path.charAt(i) == path.charAt(i+1)){
                cnt++;
            } else{//방향이 바뀌는 경우
                //500m이하일 때 메세지를 출력.
                x = i-cnt;//int 타입을 String에 저장
                y = cnt*100;
                int prevDir = dir.get(path.charAt(i-1));
                int nextDir = dir.get(path.charAt(i));
                if(prevDir<nextDir) {d = "right";}//방향에 해당하는 방향값 크기로 방향 판단
                else {d = "left"};
                //cnt 초기화
                cnt = 1;
                ans = ans.replace(XX,x);
                ans = ans.replace(YY,y);
                ans = ans.replace(DD,d);

                list.add(ans);
            }

        }
        String[] answer = list.toArray(new String[list.size()]);
        return answer;
    }
}