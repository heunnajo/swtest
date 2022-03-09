//3. 불량 사용자
import java.util.*;
class Solution {
    int m;//불량 사용자 수
    int n;//전체 사용자 수
    String[] regex;
    String[] userId;
    boolean[] v;
    Set<String> set;//가능한 아이디 목록을 저장. set 크기 = 전체 경우의 수
    public int solution(String[] user_id, String[] banned_id) {
        //0.변수 초기화
        m = banned_id.length; regex = new String[m];
        n = user_id.length; userId = new String[n];
        v = new boolean[n];
        for(int i=0;i<n;i++) userId[i] = user_id[i];
        
        //1.전처리 : banned_id를 정규 표현식으로 이용하기 위해 *을 .으로 치환
        for(int i=0;i<m;i++){
            regex[i] = banned_id[i].replace("*",".");
        }
        
        //2.banned_id마다 매치되는 user_id가 있는지 확인, 매치되는 user_id를 저장!
        set = new HashSet<>();
        go(0,"");
        
        //3.정답 도출 = set의 크기
        return set.size();
    }
    void go(int idx,String res){
        //1.종료 조건 : 모든 banned_id에 대해 수행한 경우, 현재까지의 res를 set에 저장하고 리턴
        if(idx == m){
            String[] tmp = res.split(" ");
            Arrays.sort(tmp);//!!!
            
            String ans = "";
            for(int i=0;i<tmp.length;i++){
                ans += tmp[i];
            }
            set.add(ans);
            return;
        }
        //2.현재 경우 처리 : banned_id[idx]를 user_id에서 매치되는 것이 있는지 확인!
        for(int i=0;i<n;i++){
            if(!v[i] && userId[i].matches(regex[idx])){
                v[i] = true;
                go(idx+1,res+" "+userId[i]);
                v[i] = false;
            }
        }
    }
}