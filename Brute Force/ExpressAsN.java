class ExpressAsN {
    static int Num,N,tmp,ans;
    static final int INF = 987654321;
    public int solution(int n, int number) {
        Num = number; N = n;//전역변수로 사용
        tmp = INF;ans = INF;
        
        go(0,0);//ans에 최솟값을 저장
        if(ans==INF) ans = -1;
        return ans;
    }
    //index:현재 선택할 연산자의 인덱스, sum:현재까지 계산한 값, cnt:N 사용 횟수
    static void go(int index,int sum){
        //1.재귀 종료 조건
        if(index == 7){
            if(sum != Num) ans = -1;//
            //하지만 idx == 7까지 왔다는 건 num를 만들기 위해 왔는데 결국 sum != Num이면 문제 조건에 따라 그냥 ans=-1을 리턴:왜 자신감이 없나?ans에 넣지 않고 tmp에 넣다니
            return;
        }
        //2.정답 찾은 조건
        if(sum == Num){
            int cnt = index+2;
            if(cnt<ans) ans = cnt;
            return;
        }
        //3.현재 index의 연산자를 선택!
        //4.다음 경우 호출
        go(index+1,sum*10+N);
        
        go(index+1,sum+N);
        go(index+1,sum-N);
        go(index+1,sum*N);
        go(index+1,sum/N);
    }
}