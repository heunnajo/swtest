//implementation
class Solution {
    public int solution(int t, int a, int b, int[] pulse) {
        if(t == 1) return 1;

        int len = pulse.length;
        int exTime = 1;//운동한 시간 카운팅!
        for(int i=1;i<len;i++){
            int cur = pulse[i];
            if(cur <= a) continue;
            else if(cur >= b) exTime++;
            else if(pulse[i-1] <= cur) exTime++;

        }
        //System.out.println("운동 시간: "+ exTime);
        int ans = t - exTime <= 0 ? 0 : t - exTime;
        return ans;
    }
}
