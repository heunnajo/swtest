//#72410 신규 아이디 추천

class Solution {
    public String solution(String new_id) {
        String answer = new_id;//answer를 바꿀 때 원본 데이터도 바뀔 것 같은데 => 아님 안 바뀜!
        //1
        answer = answer.toLowerCase();
        //System.out.println("1단계 후: "+answer);
        //2
        answer = answer.replaceAll("[^a-zA-Z0-9-_.]","");
        //System.out.println("2단계 후: "+answer);
        //3
        while(answer.contains("..")){
            //answer = answer.replaceAll("..",".");
            answer = answer.replace("..",".");
            //System.out.println("answer: "+answer);
        }
        //System.out.println("3단계 후: "+answer);
        //4
        if(answer.charAt(0) == '.'){answer = answer.substring(1);}
        if(answer.length() >= 1 && answer.charAt(answer.length()-1) == '.'){answer = answer.substring(0,answer.length()-1);}
        //System.out.println("4단계 후: "+answer);
        //5
        if(answer.length() == 0) {answer = "a";}
        //System.out.println("5단계 후: "+answer);
        //6
        if(answer.length() >= 16) {answer = answer.substring(0,15);}
        if(answer.charAt(answer.length()-1) == '.'){answer = answer.substring(0,answer.length()-1);}
        //System.out.println("6단계 후: "+answer);
        //7
        char last = answer.charAt(answer.length()-1);
        while(answer.length()<3){
            answer = answer + last;
        }

        return answer;
    }
}