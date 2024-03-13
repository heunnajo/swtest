import java.util.*;
//Q42748 K번째 수
class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int len = commands.length;
        int[] answer = new int[len];
        ArrayList<Integer> list;
        int s,e,idx;

        for(int i=0;i<len;i++){
            list = new ArrayList<Integer>();
            s = commands[i][0]-1;//1
            e = commands[i][1]-1;//4
            idx = commands[i][2]-1;//2

            for(int j=s;j<=e;j++){
                list.add(array[j]);
                //System.out.println(array[j]);
            }
            Collections.sort(list);
            answer[i] = list.get(idx);
        }
        return answer;
    }
}