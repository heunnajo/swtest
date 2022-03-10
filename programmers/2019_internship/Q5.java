//5. 징검다리 건너기
import java.util.*;
class Solution {
    int max;
    public int solution(int[] stones, int k) {
        int len = stones.length;
        int[] tmp = new int[len];
        for(int i=0;i<len;i++) tmp[i] = stones[i];
        Arrays.sort(tmp);
        max = tmp[len-1];
        return binarySearch(stones,k);
    }
    int binarySearch(int[] stones,int k){
        int s = 1,e = max, mid = 0;
        int ans = 0;
        while(s<=e){
            mid = (s+e)/2;
            if(!cross(mid,stones,k)) e = mid-1;
            else {//건널 수 있는 경우 : 기존의 ans 와 대소비교, 더 큰 값 선택?!
                s = mid+1; ans = Math.max(ans,mid);
            }
        }
        return ans;
    }
    boolean cross(int mid,int[] stones,int k){
        int cnt = 0;
        for(int stone:stones){
            //int cnt = 0;
            if(stone-mid<0) cnt++;
            else cnt = 0;
            if(cnt == k) return false;
        }
        return true;
    }
}