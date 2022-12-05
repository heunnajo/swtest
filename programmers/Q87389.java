//#87389 나머지가 1이 되는 수 찾기

class Solution {
    public int solution(int n) {
        int i = 2;
        int answer = 0;
        while(i < n){
            if(n % i == 1) {
                answer = i;
                break;
            }
            i++;
        }
        return answer;
    }
}