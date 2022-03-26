//1.로그 수집
class Q1 {
    public int solution(String[] logs) {
        int len = logs.length;
        int cnt = 0;//수집하는 로그 갯수 저장
        // for(int i=0;i<len;i++){
        //     if(logs[i].length()>100) continue;

        // }
        for(int i=0;i<len;i++){
            //길이 제한
            if(logs[i].length()>100) {
                cnt++;
                continue;
            }
            logs[i] = logs[i].replaceAll(" : "," ");
            String[] str = logs[i].split(" ");
            //공백 문자 단위로 split했을 때 갯수
            if(str.length>8 || str.length<8) {
                cnt++;
                continue;
            }
            //문자열 구성 문자 유효성 검사
            boolean isAllEng = false;
            for(int j=0;j<str.length;j++){
                //소문자, 대문자 아스키 
                for(int x=0;x<str[j].length();x++){
                    char ch = str[j].charAt(x);
                    if((97<=ch && ch<=122) || (65<=ch && ch<=90)){
                        //System.out.println("영어대소문자에 해당하지 않는 문자: "+str[j].charAt(x));
                        isAllEng = true;//만족하는지
                    }
                }
                //System.out.print(str[j]+",");
            }
            System.out.println();
            if(!isAllEng) cnt++;//만족하지 않는다면
        }
        System.out.println("len: "+len);
        System.out.println("cnt: "+cnt);
        return  cnt;
    }
}