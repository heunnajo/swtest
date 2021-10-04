class NewIdRecommendation_regex_2nd {
    public String solution(String new_id) {
        //step1.
        new_id = new_id.toLowerCase();
        //step2.허용가능한 문자 제외한 것들은 삭제
        new_id = new_id.replaceAll("[^a-z0-9-_.]","");
        //step3-1.마침표 2개 이상이면 하나의 마침표로 치환!
        // if(new_id.contains("..")){//정규식표현으로 2가 들어갔음.
        //     new_id = new_id.replace(".{2,}",".");
        // }
        //step3-2.둘 중 하나 선택!
        new_id = new_id.replaceAll("[.]{2,}",".");
        //step4..이처음 또는(|) 끝에 위치하는 경우 삭제
        new_id = new_id.replaceAll("^[.]|[.]$","");
        
        //step5.빈 문자열이면 a대입
        if(new_id.length() == 0){
            new_id = "a";
        }
        //step6.길이 16자 이상이면 첫 15개만 유지, 마침표(.)가 끝에 위치하면 제거
        if(new_id.length()>=16){
            //new_id = new_id.substring(0,16);
            new_id = new_id.substring(0,15);//0부터 15개만 유지하기 때문에 갯수맞추려면 n-1인덱스까지이다!
            new_id = new_id.replaceAll("[.]$","");
        }
        
        
        //step7.
        if(new_id.length()<=2){
            char tmp = new_id.charAt(new_id.length()-1);
            while(new_id.length()<3){
                new_id += tmp;
            }
        }
        
        String answer = new_id;
        return answer;
    }
}