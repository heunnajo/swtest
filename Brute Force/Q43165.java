//Q43165 타겟 넘버
class Solution {
    static int N,Ans;
    static int[] Numbers, opInfo;
    static int Target;
    public int solution(int[] numbers, int target) {
        N = numbers.length;
        Ans = 0;
        Target = target;
        Numbers = new int[N];
        opInfo = new int[N];
        for(int i=0;i<N;i++) Numbers[i] = numbers[i];
        
        go(0);
        
        return Ans;
    }
    static void go(int idx){
        //1.종료
        if(idx == N){
            int sum = 0;
            for(int i=0;i<N;i++){
                sum += Numbers[i] * opInfo[i];
            }
            if(sum == Target) {
                Ans += 1;
            }
            return;
        }
        //2.현재 경우 선택, 다음 경우 호출
        //2-1.idx번째 원소 +
        opInfo[idx] = 1;
        go(idx+1);
        
        //2-1.idx번째 원소 -
        opInfo[idx] = -1;
        go(idx+1);
    }
}