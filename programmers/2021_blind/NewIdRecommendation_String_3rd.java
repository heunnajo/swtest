class Solution {
    public String solution(String new_id) {//길이 1000
        String answer = new_id;
        
        //1
        answer = answer.toLowerCase();
        
        //2
        StringBuilder tmp = new StringBuilder();
        for(char c:answer.toCharArray()){
            if(c == '-' || c == '_' || c == '.' || ('a' <= c && c <= 'z') || ('0' <= c && c<='9')){
                tmp.append(c);
            }
        }
        answer = String.valueOf(tmp);
        
        //3 : 막힌 부분!
        while(answer.contains("..")){
            answer = answer.replace("..",".");
        }
        //4 : 이전 단계로부터 문자열이 없을 숟 ㅗ있지 않나!=> 첫문자, 마지막 문자 분리 시켜줘야함
        //왜냐하면 첫문자가 .인 경우 이를 제거하고 난 후에도 동일한 조건 판단을 해줘야하기 때문!
        if(answer.length() > 0){
            if(answer.charAt(0) == '.') {
                answer = answer.substring(1);
            }
        }
        if(answer.length() > 0){
            if(answer.charAt(answer.length()-1) == '.') {
                answer = answer.substring(0,answer.length()-1);
            }
        }
        //5
        //if(answer.equals("")) answer += "a";
        if(answer.length()==0) answer += "a";
        
        //6
        if(answer.length() >= 16) {
            answer = answer.substring(0,15);
        }
        if(answer.charAt(answer.length()-1) == '.') answer = answer.substring(0,answer.length()-1);
        //7
        char last = answer.charAt(answer.length()-1);
        while(answer.length()<=2){
            answer += last;
        }
        return answer;
    }
}