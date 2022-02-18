//1.문자열 압축
class Solution {
    public int solution(String s) {
        int len = s.length();
        int result = 0;
        int answer = len;//문자열 압축이 안 될 때는 s 그 자체
        for(int i=1;i<=(len/2)+1;i++){
            result = compressStr(s,i,1).length();
            if(i==1) answer = result;
            else answer = Math.min(answer,result);
        }
        return answer;
    }
    public String compressStr(String s,int n,int repeat){
        //1.종료 조건 : 불가능한 경우
        //=>s의 길이가 자르는 단위 n보다 작은 경우, 문자열 압축을 진행할 수 없으므로 s를 리턴
        if(s.length()<n) return s;
        
        //2.현재 경우 처리, 다음 경우 호출
        String preString = s.substring(0,n); String postString = s.substring(n,s.length());
        //String result = "";
        StringBuilder result = new StringBuilder(s.length());
        //문자열이 일치하지 않는 경우 : 압축된 문자열 길이 = ①반복횟수(길이)+②반복문자열(길이).
        //②반복문자열=>재귀함수 실행 대상
        if(!postString.startsWith(preString)){
            //1번만 반복되는 거면 숫자를 생략하기 때문에 if문으로 분기처리!
            //if(repeat == 1) return result += preString+compressStr(postString,n,1);//그냥 우변의 자체값이 아니라 += 가 되야하는 이유는?!
            if(repeat == 1) return result.append(preString+compressStr(postString,n,1)).toString();
            return result.append(Integer.toString(repeat)+preString+compressStr(postString,n,1)).toString();
        }
        //문자열이 일치하는 경우 재귀 실행
        return result.append(compressStr(postString,n,repeat+1)).toString();
    }
}