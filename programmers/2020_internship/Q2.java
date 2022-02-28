//2.수식 최대화
class Solution {
    int[][] p;
    public long solution(String expression) {
        long answer = 0;
        
        p = new int[3][2];
        for(int i=0;i<3;i++){
            p[i][0] = i;
        }
        //p[i][1]에 대해 연산자 우선순위 정해서 저장해야함!:재귀함수로 구현 가능
        
        return answer;
    }
}