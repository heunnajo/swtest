//Q161988 연속 펄스 부분 수열의 합
class Solution {
    
    public long solution(int[] sequence) {
        long answer = 0;
        int N = sequence.length;
        int[] arr1 = getPulseArr(sequence,1);
        int[] arr2 = getPulseArr(sequence,-1);
        
        long[] dp1 = new long[N];
        long[] dp2 = new long[N];
        dp1[0] = arr1[0];
        dp2[0] = arr2[0];
        answer = Math.max(dp1[0],dp2[0]);
        //answer = Long.MIN_VALUE;
        for(int i=1;i<N;i++){
            dp1[i] = Math.max(dp1[i-1]+arr1[i],arr1[i]);
            dp2[i] = Math.max(dp2[i-1]+arr2[i],arr2[i]);
            answer = Math.max(answer,Math.max(dp1[i],dp2[i]));
        }
        // for(int i=1;i<N;i++){
        //     dp2[i] = Math.max(dp2[i-1]+arr2[i],arr2[i]);
        //     answer = Math.max(answer,dp2[i]);
        // }
        return answer;
    }
    int[] getPulseArr(int[] seq,int pulse){
        int N = seq.length;
        int[] res = new int[N];
        
        for(int i=0;i<N;i++){
            res[i] = seq[i] * pulse;
            pulse *= -1;
        }
        return res;
    }
}