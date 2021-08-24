class Solution {
    public String solution(String new_id) {
        //step1
        String answer = new_id.toLowerCase();
        //step2:NOT연산으로 허용되지 않는 문자들은 제거
        answer = answer.replaceAll("[^-_.a-z0-9]","");
        //step3:{2,}로 2번 이상 나오면 .으로 치환
        answer = answer.replaceAll("[.]{2,}",".");
        //step4:^시작문자, 끝문자$로 .제거
        answer = answer.replaceAll("^[.]|[.]$","");
       
        //step5
        if(answer.equals("")){
            answer = "a";
        }
        //step6
        if(answer.length()>=16){
            answer = answer.substring(0,15);
            answer = answer.replaceAll("[.]$","");
        }
        //step7 =>answer로 바꿔서 수정해보기!
        if(answer.length()<=2){
            char last = answer.charAt(answer.length()-1);
            while(answer.length()<3){
                answer += last;
            }
        }
        return answer;
    }
}