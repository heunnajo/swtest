import java.util.*;
class Solution {
    static int K;
    static HashSet<String> Dic;
    public String solution(int k, String[] dic, String chat) {
        //initialize
        K = k;
        Dic = new HashSet<>();
        for(int i=0;i<dic.length;i++) Dic.add(dic[i]);

        String answer = "";
        StringBuilder sb = new StringBuilder();

        String[] input = chat.split(" ");
        int n = input.length;

        for(int i=0;i<n;i++){
            String prev = input[i];
            //System.out.println("cur: "+cur);
            String cur = change(prev);
            sb.append(cur);
            if(i != n-1) sb.append(" ");
        }

        answer = sb.toString();
        return answer;
    }
    //Change String str
    String change(String str){
        //1.완벽히 일치하는 경우
        if(Dic.containsKey(str)) return str;

        else{//2. '.'이 1 이상 k 이하로 바꿨을 때 비속아 될 수 있는 경우

        }
    }
}