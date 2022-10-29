//Q1
class Solution {
    public int solution(int sx1, int sy1, int sx2, int sy2, int[][] sticks) {

        int n = sticks.length;
        int answer = n;

        for(int i=0;i<n;i++){
            int[] cur = sticks[i];
            for(int j=0;j<4;j++){
                if(j%2 == 0){//x좌표
                    if(cur[j] <= sx1 || cur[j] >= sx2) {
                        //System.out.println("x out되는 막대기 i:"+i);
                        answer--; 
                        break;//다음 막대기로 컨티뉴
                    }
                } else{//y좌표
                    if(cur[j] <= sy1 || cur[j] >= sy2){
                        //System.out.println("y out되는 막대기 i:"+i);
                        answer--;
                        break;
                    }
                }
            }
        }
        return answer;
    }
}