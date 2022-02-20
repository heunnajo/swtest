//5.기둥과 보 설치
class Solution {
    //x = 열, y = 행
    boolean[][] Pillar;
    boolean[][] Bar;
    int N;
    boolean checkPillar(int x,int y){
        //가능한 경우
        //1.바닥면일 때 || 아래에 기둥 있는 경우
        if(y==0 || Pillar[x][y-1]) return true;
        
        //2.보가 있는 경우 : 보의 왼쪽 끝 = 같은 x, 오른쪽 끝 = x-1 위치 확인
        if((x > 0 && Bar[x-1][y]) || Bar[x][y]) return true;
        
        return false;
    }
    
    boolean checkBar(int x,int y){
        //가능한 경우
        //1.왼쪽 시작점 아래에 기둥 있는 경우 = 같은 x || 오른쪽 점 아래에 기둥 있는 경우 = (y-1,x+1)
        if(Pillar[x][y-1] || (x+1<=N && Pillar[x+1][y-1])) return true;//HERE x+1에 대해서 범위체크
        //2. 양쪽에 보가 있는 경우
        if(x > 0 && Bar[x-1][y] && (x+1<=N &&  Bar[x+1][y])) return true;//HERE x+1에 대해서 범위체크
        
        return false;
    }
    
    boolean canDelete(int x,int y){
        for(int i=Math.max(x-1,0);i<=x+1;i++){
            for(int j=y;j <= y+1;j++){
                if(Pillar[i][j] && checkPillar(i,j) == false) return false;
                if(Bar[i][j] && checkBar(i,j) == false) return false;
            }
        }
        return true;
    }
    
    public int[][] solution(int n, int[][] build_frame) {
        //0.변수 초기화
        Pillar = new boolean[n+1][n+1];
        Bar = new boolean[n+1][n+1];
        N = n;
        int count = 0;
        
        //1.build_frame 순회하면서 각 명령 실행
        for(int[] build : build_frame){
            int x = build[0], y = build[1];
            int type = build[2], cmd = build[3];
            
            if(type == 0){//기둥
                if(cmd == 1){//설치
                    if(checkPillar(x,y)){
                        Pillar[x][y] = true;
                        count++;
                    }
                } else{//삭제
                     Pillar[x][y] = false;
                    if(!canDelete(x,y)) Pillar[x][y] = true;//일단 지우고, 그 때 canDelete로 가능한지 판단!
                    else count--;//삭제 성공한 경우!
                }
            } else{//보
                if(cmd == 1){//설치
                    if(checkBar(x,y)){
                        Bar[x][y] = true;
                        count++;
                    }
                } else{//삭제
                    Bar[x][y] = false;
                    if(!canDelete(x,y)) Bar[x][y] = true;
                    else count--;//삭제 성공한 경우!
                }
            }
        }
        
        int[][] answer = new int[count][3];
        count = 0;
        
        for(int x = 0;x<=n;x++){//x좌표 오름차순이기 때문에 우선순위 먼저~!
            for(int y=0;y<=n;y++){
                //1.기둥인 경우
                if(Pillar[x][y]){
                    answer[count][0] = x;answer[count][1] = y;answer[count++][2] = 0;
                }
                //2.보인 경우
                if(Bar[x][y]){
                    answer[count][0] = x;answer[count][1] = y;answer[count++][2] = 1;
                }
            }
        }
        
        return answer;
    }
}