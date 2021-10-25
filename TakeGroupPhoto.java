import java.util.*;
class TakeGroupPhoto {
    static int ans;
    static boolean[] visited;
    static String[] friends = {"X","A","C","F","J","M","N","R","T"};
    static String[] condition;
    static boolean satisfy(String permu){
        for(String con:condition){
            int a_idx = permu.indexOf(con.charAt(0));
            int b_idx = permu.indexOf(con.charAt(2));
            //부등호 정보, 상수값 조회해서 조건 판단!
            if((con.charAt(3) == '=') && (Math.abs(a_idx-b_idx)-1) != con.charAt(4)-'0') return false;
            if((con.charAt(3) == '<') && (Math.abs(a_idx-b_idx)-1) >= con.charAt(4)-'0') return false;
            if((con.charAt(3) == '>') && (Math.abs(a_idx-b_idx)-1) <= con.charAt(4)-'0') return false;
        }
        return true;
    }
    static void go(int index,StringBuilder sb){
        if(index == 8){
            if(satisfy(sb.toString())) ans++;
            return;
        }
        for(int i=1;i<=8;i++){
            if(visited[i]) continue;
            StringBuilder ab = new StringBuilder(sb.toString());
            ab.append(friends[i]);
            visited[i] = true;
            go(index+1,ab);
            visited[i] = false;//원복 필수!!
        }
    }
    public int solution(int n, String[] data) {
        condition = data;
        StringBuilder sb = new StringBuilder();
        visited = new boolean[9];
        ans = 0;
        go(0,sb);
        return ans;
    }
}