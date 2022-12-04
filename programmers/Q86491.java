// #86491 최소직사각형
import java.util.*;
class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        int maxW = 0; int maxH = 0;
        
        int len = sizes.length;
        int w = 0; int h = 0;
        for(int i=0;i<len;i++){
            w = sizes[i][0]; h = sizes[i][1];
            if(sizes[i][0] < sizes[i][1]){w = sizes[i][1]; h = sizes[i][0];}
            
            if(maxW < w) maxW = w;
            if(maxH < h) maxH = h;
        }
        
        answer = maxW * maxH;
        
        return answer;
    }
}