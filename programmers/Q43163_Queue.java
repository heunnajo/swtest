//Q43163 단어변환
import java.util.*;
class Solution {
    class Pair{
        String str;
        int cnt;
        Pair(String str,int cnt){
            this.str = str;
            this.cnt = cnt;
        }
    }
    public int solution(String begin, String target, String[] words) {
        int INF = Integer.MAX_VALUE;
        int answer = INF;
        
        boolean flag = false;
        for(String s:words) {
            if(s.equals(target)) flag = true;
        }
        if(!flag) return 0;
        
        Queue<Pair> q = new LinkedList<>();
        boolean[] v = new boolean[words.length];
        q.offer(new Pair(begin,0));
        
        while(!q.isEmpty()){
            Pair cur = q.poll();
            if(cur.str.equals(target)){
                if(answer > cur.cnt) {
                    answer = cur.cnt;
                    //return answer;
                    break;
                }
            }
            
            for(int i=0;i<words.length;i++){
                if(v[i] || !isValid(cur.str,words[i])) continue;
                q.offer(new Pair(words[i],cur.cnt+1));
                v[i] = true;
            }
        }
        if(answer == INF) answer = 0;
        return answer;
    }
    boolean isValid(String cur,String next){
        int cnt = 0;
        for(int i=0;i<cur.length();i++){
            if(cur.charAt(i) != next.charAt(i)) cnt++;
        }
        if(cnt > 1) return false;
        return true;
    }
}