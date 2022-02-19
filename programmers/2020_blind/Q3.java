//3.자물쇠와 열쇠
import java.util.*;
class Solution {
    int[][][] fourKeys;
    int n,m,len;
    int[][] arr;
    public boolean solution(int[][] key, int[][] lock) {
        //0.변수 초기화
        n = key.length; m = lock.length;
        len = 2*n+m-2;
        arr = new int[len][len];
        fourKeys = new int[4][n][n];
        
        //1.4가지 가능한 열쇠 모양 생성, fourKeys를 미리 완성
        makeFourKeys(key);//key를 4번 회전 시켜서 fourKeys를 완성.
        
        //2.각 칸마다 열쇠를 놓는 시도:만족하면 true를 리턴=>arr 내에서 자물쇠의 마지막 칸까지만 반복하면 되기 때문에 인덱스는 n+m-2까지만 해보면 됨.
        for(int i=0;i<n+m-1;i++){
            for(int j=0;j<n+m-1;j++){
                for(int k=0;k<4;k++){//4가지 열쇠 모양에 대해 반복
                    initializeArr(lock);//현재 열쇠로 비교하기 위해 arr을 lock으로 초기화
                    int[][] curKey = fourKeys[k];
                    if(isFit(curKey,lock,i,j)) return true;//현재 키모양으로 자물쇠열리는지 판단
                }
            }
        }
        return false;
    }
    void makeFourKeys(int[][] key){
        for(int k=0;k<4;k++){
            int[][] newOne = rotate(key);
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++) {
                    fourKeys[k][i][j] = newOne[i][j];
                }
            }
            key = newOne;
        }
        
    }
    int[][] rotate(int[][] key){
        int[][] newOne = new int[n][n];//newOne은 n*n 크기 2차원 배열, 0으로 초기화되어있음!
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++) {
                newOne[i][j] = key[n-1-j][i];
            }
        }
        //print(newOne);
        return newOne;
    }
    void initializeArr(int[][] lock){//주어진 3*3 크기 배열로는 1,2 모두 확인O
        //1.arr 초기화
        for(int i=0;i<len;i++) Arrays.fill(arr[i],0);
        //print(arr);
        //2.가운데에 lock으로 복사
        for(int i=0;i<m;i++)
            for(int j=0;j<m;j++) arr[n-1+i][n-1+j] += lock[i][j];
        //print(arr);
    }
    //curKey를 arr[i][j]를 시작점으로 위치시켜서 lock과 매치해본다!
    boolean isFit(int[][] curKey,int[][] lock,int sx,int sy){
        //1.key를 arr에 위치시킴
        for(int i=0;i<n;i++) for(int j=0;j<n;j++) arr[sx+i][sy+j] += curKey[i][j];
        //2.자물쇠가 열리는지 판단 : arr에서 lock의 모든 칸이 1인지 확인
        for(int i=0;i<m;i++) for(int j=0;j<m;j++) if(arr[n-1+i][n-1+j] != 1) return false;
        
        return true;
    }
    void print(int[][] a){
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a.length;j++){
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}