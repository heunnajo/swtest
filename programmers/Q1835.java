//Q1835 단체사진 찍기
import java.util.*;
class Solution {
    int Ans;
    char[] Friends = {'A','C','F','J','M','N','R','T'};
    char[] Selected;
    String[] Data;
    boolean[] Visited;
    public int solution(int n, String[] data) {
        Ans = 0;
        Selected = new char[8];
        Data = data;
        Visited = new boolean[8];
        go(0);
        return Ans;
    }
    void go(int idx){
        //1.종료 : 8명 순서 선택 다 한 경우
        if(idx == 8){
            HashMap<Character,Integer> map = new HashMap<>();
            for(int i=0;i<8;i++){
                map.put(Selected[i],i);
            }
            //조건 만족 판단
            boolean flag = true;
            for(int i=0;i<Data.length;i++){
                String cur = Data[i];
                char f1 = cur.charAt(0); char f2 = cur.charAt(2);
                int f1Idx = map.get(f1); int f2Idx = map.get(f2);
                if(cur.charAt(3) == '='){
                    if(Math.abs(f1Idx-f2Idx)-1 != cur.charAt(4)-'0') flag = false;
                } else if(cur.charAt(3) == '>'){
                    if(Math.abs(f1Idx-f2Idx)-1 <= cur.charAt(4)-'0') flag = false;
                } else if(cur.charAt(3) == '<'){
                    if(Math.abs(f1Idx-f2Idx)-1 >= cur.charAt(4)-'0') flag = false;
                }
            }
            if(flag) Ans++;
            return;
        }
        //2.현재 경우 선택, 다음 경우 호출
        for(int i=0;i<8;i++){
            if(Visited[i]) continue;
            Visited[i] = true;
            Selected[idx] = Friends[i];
            go(idx+1);
            Visited[i] = false;
        }
    }
}