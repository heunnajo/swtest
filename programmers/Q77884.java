//#77884 약수의 개수와 덧셈

class Solution {
    public int solution(int left, int right) {
        int answer = 0;
        int cnt;
        for(int x = left;x<=right;x++){
            cnt = 0;
            for(int i=1;i<=x;i++){
                if(x%i == 0){cnt++;}
            }
            if(cnt % 2 == 0) {answer += x;}
            else {answer -= x;}
            
        }
        return answer;
    }
}