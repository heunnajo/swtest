//#82612 부족한 금액 계산하기

class Solution {
    public long solution(int price, int money, int count) {
        long answer = 0;
        
        for(int i=1;i<=count;i++){
            answer += i*price;
        }
        //System.out.println("필요  이용  금액: "+answer);
        answer -= money;
        if(answer < 0) answer = 0;//edge case(돈이 모자라지 않는 경우) : 총 이용료 2, 처음 가진 금액 10 => 2-10 
        return answer;
    }
}