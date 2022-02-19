//3.자물쇠와 열쇠 정답
class Solution {
    void match(int[][] arr,int[][] key,int rot,int r,int c){
        int n = key.length;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(rot == 0){
                    arr[r+i][c+j] += key[i][j];
                } else if(rot == 1){ //90도 회전 : 첫번째 열이 첫번째 행으로 간다.
                    arr[r+i][c+j] += key[j][n-1-i];//첫번째 행 첫번째 칸 = 첫번째 행 마지막 칸, 마지막 행 첫번째 칸 = 첫번째 행 첫번째 칸
                } else if(rot == 2){//180도 회전
                    arr[r+i][c+j] += key[n-1-i][n-1-j];
                } else{//270도 회전 = 반시계 방향 90도 회전. 마지막 열->첫번째 행. 따라서 열 좌표=>행, 행 좌표=>열 으로 써주면 된다!
                    arr[r+i][c+j] += key[n-1-j][i];
                }
            }
        }
    }
    boolean check(int[][] arr,int offset,int n){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(arr[offset+i][offset+j] != 1) return false;
            }
        }
        return true;
    }
    public boolean solution(int[][] key, int[][] lock) {
        int offset = key.length-1;
        
        for(int r=0;r<offset+lock.length;r++){
            for(int c=0;c<offset+lock.length;c++){
                for(int rot = 0;rot<4;++rot){
                    //1.2차원 배열 생성 및 초기화, lock을 복사
                    int[][] arr = new int[58][58];
                    for(int i=0;i<lock.length;i++)
                        for(int j=0;j<lock.length;j++) arr[offset+i][offset+j] = lock[i][j];
                                             
                    //2.키를 매치함(키를 더함)
                    match(arr,key,rot,r,c);
                    //3.자물쇠가 열리는지 검사 check = 자물쇠 칸들이 모두 1이라면 true를 빈환하는 함수
                    if(check(arr,offset,lock.length)) return true;
                }
            }
        }
        return false;
    }
}