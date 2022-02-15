//2.행렬 테두리 회전하기
import java.util.*;
class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int len = queries.length;
        int[] answer = new int[len];
        int[][] arr = new int[rows+1][columns+1];
        //배열을 먼저 완성
        for(int i=1;i<=rows;i++)
            for(int j=1;j<=columns;j++) arr[i][j] = (i-1)*columns+j;
        
        //queries[i]를 수행한 후 최솟값을 answer[i]에 저장!
        //queries[i] 마다 리스트를 생성, 오름차순 정렬 후 0번째 요소를 answer[i]에 저장!
        for(int k=0;k<len;k++){
            int[] cmd = queries[k];
            int x1 = cmd[0], y1 = cmd[1], x2 = cmd[2], y2 = cmd[3];
            ArrayList<Integer> list = new ArrayList<>();
            int tmp = arr[x1][y1]; list.add(tmp);
            //본격 회전
            //왼쪽
            for(int i=x1;i<x2;i++) {
                arr[i][y1] = arr[i+1][y1]; list.add(arr[i][y1]);
            }
            //아래쪽
            for(int j=y1;j<y2;j++){
                arr[x2][j] = arr[x2][j+1]; list.add(arr[x2][j]);
            }
            //오른쪽
            for(int i=x2;i>x1;i--){
                arr[i][y2] = arr[i-1][y2]; list.add(arr[i][y2]);
            }
            //위쪽
            for(int j=y2;j>y1;j--){
                arr[x1][j] = arr[x1][j-1]; list.add(arr[x1][j]);
            }
            arr[x1][y1+1] = tmp;
            Collections.sort(list);
            answer[k] = list.get(0);
        }
        
        return answer;
    }
}
// for(int j=0;j<cmd.length;j++){
            //     System.out.print(cmd[j]+" ");
            // }
            // System.out.println();