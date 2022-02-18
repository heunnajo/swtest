//2.괄호 변환
class Solution {
    static int pos;//u의 길이, v의 시작 인덱스
    boolean isCorrect(String p){
        boolean flag = true;
        int sum = 0;
        for(int i=0;i<p.length();i++){
            if(p.charAt(i) == '(') sum++;
            else sum--;
            if(sum<0) flag = false;
            if(sum==0){
                pos = i+1;//최초로 sum이 0이되는 인덱스 i에 1더한값을 pos로 저장!
                return flag;
            }
        }
        
        return flag;//이까지 내려올 일은 없지만
    }
    public String solution(String p) {
        if(p.length()==0) return p;
        boolean correct = isCorrect(p);
        String u = p.substring(0,pos);
        String v = p.substring(pos,p.length());
        if(correct) return u+solution(v);
        //4단계를 수행할 문자열 변수:answer를 사용!
        String answer = "("+solution(v)+")";//4-1,4-2,4-3
        StringBuilder finalS = new StringBuilder();
        for(int i=1;i<u.length()-1;i++){//4-4
            if(u.charAt(i) == '(') finalS.append(')');
            else finalS.append('(');
        }
        answer += finalS.toString();
        return answer;
    }
}