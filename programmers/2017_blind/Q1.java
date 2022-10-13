import java.util.*;
//1.단체 사진 찍기
class Solution {
    int ans,N;
    boolean[] v;
    String[] friends = {"-","A","C","F","J","M","N","R","T"};
    String[] conditions;
    
    public int solution(int n, String[] data) {
        N = n;
        v = new boolean[9];
        conditions = new String[n];
        for(int i=0;i<n;i++){
            conditions[i] = data[i];
        }
        
        StringBuilder sb = new StringBuilder();
        go(0,sb);
        
        return ans;
    }
    void go(int idx,StringBuilder selected){
        //1.종료
        if(idx == 8){
            if(isSatisfied(selected.toString())) ans++;
            return;
        }
        //2.현재 경우 선택, 다음 경우 처리
        for(int i=1;i<=8;i++){
            if(v[i]) continue;
            v[i] = true;
            StringBuilder tmp = new StringBuilder(selected.toString());//매 경우마다 새로운 객체를 생성해서 여기다가 append~!
            go(idx+1,tmp.append(friends[i]));
            v[i] = false;
        }
    }
    boolean isSatisfied(String s){
        for(int i=0;i<N;i++){
            int me = s.indexOf(conditions[i].charAt(0));
            int you = s.indexOf(conditions[i].charAt(2));
            int dist = conditions[i].charAt(4)-'0';
            
            if(conditions[i].charAt(3) == '='){
                if(Math.abs(me-you)-1 != dist) return false;
            } else if(conditions[i].charAt(3) == '<'){
                if(Math.abs(me-you)-1 >= dist) return false;
            } else if(conditions[i].charAt(3) == '>'){
                if(Math.abs(me-you)-1 <= dist) return false;
            }
        }
        return true;
    }
}