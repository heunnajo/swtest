//5. 징검다리 건너기
import java.util.*;
class Solution {
    public int solution(int[] stones, int k) {
        return binarySearch(stones,k);
    }
    int binarySearch(int[] stones,int k){
        int s = 1,e = 200000000, mid = 0;
        int ans = 0;
        while(s<=e){
            mid = (s+e)/2;
            //1.건널 수 없는 경우
            if(!cross(mid,stones,k)) e = mid-1;
            else {//2.건널 수 있는 경우 : mid의 최댓값을 도출
                s = mid+1; ans = Math.max(ans,mid);
            }
        }
        return ans;
        //return mid;
    }
    boolean cross(int mid,int[] stones,int k){
        int cnt = 0;
        for(int stone:stones){
            if(stone-mid<0) cnt++;
            else cnt = 0;
            if(cnt == k) return false;
        }
        return true;
    }
}