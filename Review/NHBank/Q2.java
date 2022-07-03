//implementation
class Solution {
    public int[] solution(int n, int[][] times) {
        int[] ans = new int[n];
        boolean flag = true;
        int tmp = 0;
        int len = times.length;

        for(int i = 0;i<len;i++){
            int A = times[i][0]-1; int B = times[i][1]-1; int C = times[i][2];
            //System.out.println("A: "+A+"B: "+B+"C: "+C);
            if(C<0) {
                tmp = ans[A]+C;
                if(ans[B] == 0) ans[B] = ans[A]+C;
                else if(ans[B] != tmp){
                    flag = false;
                    break;
                }
            } else {
                tmp = ans[A]+C;
                if(ans[B] == 0) ans[B] = ans[A]+C;
                else if(ans[B] != tmp) {
                    flag = false;
                    break;
                }
            }            
        }
        if(!flag) {
            ans = new int[1];
            ans[0] = -1;
        }
        return ans;
    }
}