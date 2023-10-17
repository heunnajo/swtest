//Q43163 단어변환
import java.util.*;
class Solution {
    int N,Ans,Len;
    String Target;
    String[] Words;
    boolean[] Visited;
    public int solution(String begin, String target, String[] words) {
        N = words.length;
        Ans = Integer.MAX_VALUE;
        Target = target;
        
        Words = new String[N];
        Visited = new boolean[N];
        boolean isPossible = false;
        for(int i=0;i<N;i++){
            Words[i] = words[i];
            if(Words[i].equals(target)) {isPossible = true;}
        }
        if(!isPossible) {return 0;}
        
        go(begin,0);
        
        if(Ans == Integer.MAX_VALUE){Ans = 0;}
        
        return Ans;
    }
    void go(String str,int cnt){
        //System.out.println("현재: "+str+",변환 횟수: "+cnt);
        //1.종료 조건
        if(str.equals(Target)){
            if(Ans > cnt){Ans = cnt;}
            return;
        }
        //2.다음 이동
        for(int i=0;i<N;i++){//(0,hot)
            String next = Words[i];
            if(!Visited[i] && check(str,next)) {
                Visited[i] = true;
                go(next,cnt+1);
                Visited[i] = false;
            }
        }
    }
    
    public boolean check(String begin, String target) {
        int count = 0;
        
        for (int i = 0; i < begin.length(); i++) {
            if (begin.charAt(i) == target.charAt(i)) {
                count++;
            }    
        }
        
        if (count == begin.length() - 1) {
            return true;
        }
        return false;
    }
}