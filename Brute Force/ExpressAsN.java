class ExpressAsN {
    static int answer,N,Num;
    static final int INF = 987654321;
    public int solution(int n, int number) {
        //전역변수로 설정!
        N = n;
        Num = number;//만들려고 하는 수
        answer = INF;
        go(0,0);//answer를 최솟값으로 갱신..왜 안 되나?
        if(answer == INF) answer = -1;
        return answer;
    }
    static void go(int count,int sum){
        //1.종료 조건 : 정답 찾은 경우
        if(sum == Num){
            answer = Math.min(answer,count);
            return;
        }
        //2.현재 깊이에서 선택
        //3.다음 경우 호출
        int x = N;
        for(int i =0;i<8-count;i++){
            int newCount = count+i+1;
            go(newCount,sum+x);
            go(newCount,sum-x);
            go(newCount,sum*x);
            go(newCount,sum/x);
            x = x*10+N;
        }
    }
}