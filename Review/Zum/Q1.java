class Solution {
    public long solution(long n) {
        long answer = 0;
        int N = (int)n;

        //StringBuilder sb = new StringBuilder();
        for(int i=1;i<N;i++){
            int k = N*i+i; //sb.append("k:").append(k).append("\n");
            answer += k;
        }
        //System.out.println(sb);
        return answer;
    }
}
