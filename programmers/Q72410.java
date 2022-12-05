//#72410 신규 아이디 추천

class Solution {
    public String solution(String new_id) {
        String answer = new_id;
        //1
        answer = answer.toLowerCase();
        //2
        answer = answer.replaceAll("[^a-zA-Z0-9-_.]","");
        //3
        while(answer.contains("..")){
            answer = answer.replace("..",".");
        }
        //4
        if(answer.charAt(0) == '.'){answer = answer.substring(1);}
        if(answer.length() >= 1 && answer.charAt(answer.length()-1) == '.'){answer = answer.substring(0,answer.length()-1);}
        //5
        if(answer.length() == 0) {answer = "a";}
        //6
        if(answer.length() >= 16) {answer = answer.substring(0,15);}
        if(answer.charAt(answer.length()-1) == '.'){answer = answer.substring(0,answer.length()-1);}
        //7
        char last = answer.charAt(answer.length()-1);
        while(answer.length()<3){
            answer = answer + last;
        }

        return answer;
    }
}