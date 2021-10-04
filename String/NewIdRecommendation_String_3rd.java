class NewIdRecommendation_String_3rd {
    public String solution(String new_id) {
        String answer;
        //1단계
        String step1 = new_id.toLowerCase();
        //2단계:for-each문을 위해 step1을 배열로 변환
        char[] step1_arr = step1.toCharArray();
        StringBuilder step2 = new StringBuilder();
        for(char c:step1_arr){
            if(c>='a' && c<='z' || c>='0' && c<='9' ||c=='-' || c=='_' || c=='.'){
                step2.append(c);
            }
        }
        //3단계:String 메서드를 사용하기 위해 2단계의 StringBuilder를 String으로 변환
        String step3 = step2.toString().replace("..",".");
        while(step3.contains("..")){
            step3 = step3.replace("..",".");
        }
        //4단계
        String step4 = step3;
        if(step4.length()>0){
            if(step4.charAt(0) == '.'){
                step4 = step4.substring(1,step4.length());
            } 
        }
        if(step4.length()>0){
            if(step4.charAt(step4.length()-1) == '.'){
                step4 = step4.substring(0,step4.length()-1);
            }
        }
        //5단계
        String step5 = step4;
        if(step5.length()==0){
            step5 += "a";
        }
        //6단ㄴ계
        String step6 = step5;
        if(step6.length()>=16){
            step6 = step6.substring(0,15);//0-14
            if(step6.charAt(step6.length()-1) == '.'){
                step6 = step6.substring(0,step6.length()-1);
            }
        }
        StringBuilder step7 = new StringBuilder(step6);
        if(step7.length()<=2){
            char c = step6.charAt(step7.length()-1);
            while(step7.length()<3){
                step7.append(c);
            }
        }
        
        //answer = String.valueOf(step7);
        answer = step7.toString();
        return answer;
    }
}