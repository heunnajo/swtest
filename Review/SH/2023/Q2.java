//중복을 제거하지 못했음 ...
import java.util.*;
class Solution {
    int N,M,Ans;
    int[][] Pref;
    int[] Selected;
    boolean[] Visited;
    HashSet<String> set = new HashSet<>();
    public int solution(int n, int m, int[][] preferences) {
        N = n; M = m; Ans = 0;
        Pref = new int[N][M];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                Pref[i][j] = preferences[i][j];
            }
        }

        Selected = new int[M];
        Visited = new boolean[M];
        Arrays.fill(Selected,-1);
        go(0,0);
        return Ans;
    }
    void go(int idx,int from){//0
        //1.종료
        if(idx == M){

            if(!isDuplicated()){
                //print();
                solve();
            }

            return;
        }
        //2.현재 선택, 다음 경우 호출
        for(int i=from;i<M;i++){
            //if(Visited[i]) continue;
            //Visited[i] = true;
            Selected[idx] = i;
            go(idx+1,i+1);
            //Visited[i] = false;
            //Selected[idx] = -1;
        }
        Selected[idx] = -1;
        go(idx+1,from);
    }
    boolean isDuplicated(){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<M;i++){
            if(Selected[i] != -1){
                sb.append(Selected[i]);
            }
        }
        String cur = sb.toString();
        if(set.contains(cur)) return true;
        else return false;

    }
    void solve(){
        //현재 선택한 토핑정보 Selected로 만족하는 사람 구함
        int cnt = 0;
        boolean satisfied;
        for(int i=0;i<N;i++){
            satisfied = true;
            for(int j=0;j<M;j++){
                //조건판단1.현재 선택한 한쪽
                if(Selected[j] != -1){
                    if(Pref[i][j] == -1){
                        satisfied = false;
                    }
                } 
            }
            if(satisfied) {cnt++;}
        }
        if(cnt > Ans){
            Ans = cnt;
        }
    }
    void print(){//선택한 토핑 정보 확인
        for(int i=0;i<M;i++){
            if(Selected[i] != -1){
                System.out.print(Selected[i]+" ");
            }
        }
        System.out.println();
    }
}