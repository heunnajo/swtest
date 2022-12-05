package Review.Madup;

class Solution {
    public int[] solution(int[] numbers, int[] start, int[] finish) {
        int n = numbers.length;
        int total = start.length;
        int[] answer = new int[total];
        int[] dp = new int[n];//총 n개의 누적합 구한다!

        dp[0] = numbers[0];
        for(int i=1;i<n;i++){
            dp[i] = dp[i-1]+numbers[i];
        }
        // for(int i=0;i<n;i++){
        //     System.out.print(dp[i]+" ");
        // }
        for(int i=0;i<total;i++){
            answer[i] = dp[finish[i]] - dp[start[i]-1];
        }

        return answer;
    }
}
