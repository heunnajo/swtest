class NewIdRecommendation_String_3rd {
    public String solution(String new_id) {
        String step1 = new_id.toLowerCase();
        
        String step2 = "";
        for(int i=0;i<step1.length();i++){
            if(step1.charAt(i)>='a' && step1.charAt(i)<='z' ||
              step1.charAt(i)>='0' && step1.charAt(i)<='9' ||
              step1.charAt(i)=='-' || step1.charAt(i)=='_' || step1.charAt(i)=='.'){
                step2 += step1.charAt(i);
            }
        }
        String step3 = "";
        //step3 = step2.replace("..",".");
        while(step2.contains("..")){
            step3 = step3.replace("..",".");
        }
        String step4 = "";
        if(step3.charAt(0) == '.'){
            step4 = step3.substring(1,step3.length());
        } 
        if(step3.charAt(step3.length()-1) == '.'){
            step4 = step3.substring(0,step3.length()-1);
        }
        
        String step5 = "";
        if(step4.length()==0){
            step5 += "a";
        }
        String step6 = "";
        if(step5.length()>=16){
            step6 = step5.substring(0,15);//0-14
            if(step6.charAt(step6.length()-1) == '.'){
                step6 = step6.substring(0,step6.length()-1);
            }
        }
        //String step7 = "";
        if(step6.length()<=2){
            char c = step6.charAt(step6.length()-1);
            while(step6.length()<3){
                step6 += c;
            }
        }
        //step7 = step6;
        //return step7;
        return step6;
    }
}