import java.util.*;
class Solution {
    public int[] solution(int[][] v) {
        int[] answer = new int[2];

        HashMap<Integer,Integer> mapX = new HashMap<>();
        HashMap<Integer,Integer> mapY = new HashMap<>();
        for(int i=0;i<3;i++){
            int[] curPos = v[i];
            int x = curPos[0]; int y = curPos[1];
            // if(mapX.get(x) == null){
            //     mapX.put(x,1);
            // } else {
            //     mapX.put(x,mapX.get(x)+1);
            // }
            mapX.put(x,mapX.getOrDefault(x, 0) + 1);
            // if(mapY.get(y) == null){
            //     mapY.put(y,1);
            // } else {
            //     mapY.put(y,mapY.get(y)+1);
            // }
            mapY.put(y,mapY.getOrDefault(y, 0) + 1);
        }
        for(int x:mapX.keySet()){
            if(mapX.get(x) == 1) answer[0] = x;
        }
        for(int y:mapY.keySet()){
            if(mapY.get(y) == 1) answer[1] = y;
        }
        return answer;
    }
}