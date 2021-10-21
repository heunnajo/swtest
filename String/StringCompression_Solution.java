class StringCompression_Solution {
    public int solution(String s) {
        int answer = s.length();
        
        for(int i=1; i<=s.length()/2 ;++i){
            int pos = 0;//연산 수행 위치
            int len = s.length();//압축된 길이는 초기 문자열의 길이에서 압축된만큼 빼는 식으로 구한다.
            
            //반복문:pos+i가 s의 길이 이내인 동안 반복하고, pos는 반복문 내부에서 i단위로 증가시킨다.
            for(;pos+i<=s.length();){
                String unit = s.substring(pos,pos+i);//초기 비교 기준이 되는 문자열을 unit에 저장한다!
                pos += i;//pos = pos+i : 0=>3
                
                int cnt = 0;//갯수 카운팅!
                for(;pos+i<=s.length();){
                    if(unit.equals(s.substring(pos,pos+i))){//x단위로 잘랐을 때 다음 단위의 문자열과 초기 비교 기준 문자열 unit과 같은지 비교한다!
                        ++cnt;
                        pos += i;//pos = poss+i
                    } else break;//다르다면 break하고, 새로운 기준이 되는 문자열 가져온다.=>반복변수, 증감 단위는?
                }
            //cnt가 0이면 압축X이므로 길이 변화없음.
            //연속된 문자가 하나라도 존재한다면
                if(cnt > 0){
                    len -= i*cnt;//단위*반복 횟수 만큼(=반복해서 나타나는 문자의 갯수) 빼주면 됨.
                
                    //반복 횟수 자릿수에 따라
                    if(cnt<9) len += 1;//a가 10개라면 10a 이기 때문에 1이 아니라 2가 늘어난다. 따라서 반복횟수가 몇자리인지 알아야한닫!
                    else if(cnt<99) len += 2;
                    else if(cnt<999) len += 3;
                    else len += 4;//a가 1000개 들어오면 1000a : 1000-1*1000 = 0
                }
            }
            answer = Math.min(answer,len);
        }
        
        return answer;
    }
    
}