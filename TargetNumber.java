class TargetNumber {
    static int[] num;
    static int T,ans;
    public int solution(int[] numbers, int target) {
        num = numbers;
        T = target;
        ans = 0;
        go(0,0);
        return ans;
    }
    static void go(int index,int sum){
        if(index==num.length){
            if(sum == T){
                ans++;
            }
            return;
        }
        go(index+1,sum+num[index]);
        go(index+1,sum-num[index]);
    }
}