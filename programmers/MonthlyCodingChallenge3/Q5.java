//5. 나머지가 1이 되는 수 찾기
class Q5 {
    public int solution(int n) {
        int answer = -1;
        
        for(int i=1;i<=n-1;i++){
            if(n%i == 1) {
                answer = i;
                break;
            }
        }
        
        return answer;
    }
}