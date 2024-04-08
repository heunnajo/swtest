//Q12944 평균구하기
class Solution {
    public double solution(int[] arr) {
        double answer = 0;
        int sum = 0;
        int n = arr.length;
        for(int i=0;i<n;i++){
            sum += arr[i];
        }
        answer = (double) sum / n;
        return answer;
    }
}