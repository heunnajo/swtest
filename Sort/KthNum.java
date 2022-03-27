//programmers "정렬"
import java.util.*;
class Solution {
    public int[] solution(int[] array, int[][] commands) {
        //commands[i] = i,j,k
        int len = commands.length;
        int[] answer = new int[len];
        for(int i=0;i<len;i++){
            answer[i] = process(array,commands[i]);
        }
        return answer;
    }
    int process(int[] arr,int[] command){
        //0:i,1:j,2:k
        int i = command[0]-1, j = command[1]-1, k = command[2]-1;
        int len = j-i+1;
//        System.out.println("len = j-i+1 : "+len);
        int[] tmp = new int[len];
        for(int idx=0;idx<len;idx++){
            tmp[idx] = arr[idx+i];
        }
        Arrays.sort(tmp);
        return tmp[k];
//        for(int idx=0;idx<len;idx++){
//            System.out.print(tmp[idx]+" ");
//        }
//        System.out.println();
    }
}