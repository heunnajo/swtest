import java.util.*;
import java.awt.*;
class Solution {
    static HashMap<String,Point> blockStore;
    static LinkedList<Point> blockList;
    static int cnt;
    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;
        
        int[][] tmpArr = new int[game_board.length][game_board.length];
        for(int i=0;i<game_board.length;i++){
            for(int j=0;j<game_board.length;j++){
                game_board[i][j] = game_board[i][j] == 0 ? 1:0;
                tmpArr[i][j] = game_board[i][j];
            }
        }
        //bfs로 1인칸 bfs 탐색!
        for(int i=0;i<game_board.length;i++)
            for(int j=0;j<game_board.length;j++)
                if(game_board[i][j]==1){
                    String blockString = bfs(i,j,tmpArr);
                    Point p = blockStore.getOrDefault(blockString,new Point(0,cnt));
                    p.x++;//Point(갯수, 크기)
                    blockStore.put(blockString,p);
                }
        //table 4번 회전시키면서 각 회전마다 bfs로 도형 탐색~~
        for(int dir=0;dir<4;dir++){
            table = rotate(table);//배열의 크기를 넣어준다X 회전할 table 배열 자체를 넣어준다!!
            for(int i=0;i<table.length;i++)
                for(int j=0;j<table.length;j++)
                    tmpArr[i][j] = table[i][j];
            
            for(int i=0;i<table.length;i++)
                for(int j=0;j<table.length;j++)
                    if(table[i][j]==1){
                        String blockString = bfs(i,j,tmpArr);
                        if(blockStore.containsKey(blockString)){
                            for(Point bp: blockList){
                                table[bp.x][bp.y] = 0;//table에서 삭제 처리!
                            }
                            Point p = blockStore.get(blockString);//이게 blockList 반복문 안에 있는 게 맞나?!
                            answer += p.y;
                            p.x--;//갯수 감소
                            if(p.x==0) blockStore.remove(blockString);
                        }
                    }
        }
        return answer;
    }//solution 메서드
     String bfs(int x,int y,int[][] tmpArr){//1:탐색 가능,0:탐색 X
        //임시 2차원 배열 만들어서 방문하려는 배열 똑같이 복사한다!
        //방문한 노드는 0으로 마킹해서 갈수 없는 노드와 방문한 노드 함께 판단 가능하다!
        int[] dx = {-1,1,0,0}; int[] dy = {0,0,-1,1};
        int minX = Integer.MAX_VALUE; int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE; int maxY = Integer.MIN_VALUE;
         
        Queue<Point> q = new LinkedList<Point>();
        q.add(new Point(x,y));
        //BFS할 때마다 초기화해야하는 변수, 객체
        blockList = new LinkedList<>();
        tmpArr[x][y] = 0;
        cnt = 0;
        
        while(!q.isEmpty()){
            Point cur = q.remove();
            cnt++;
            blockList.add(new Point(cur.x,cur.y));
            minX = minX>cur.x ? cur.x:minX; maxX = maxX<cur.x?cur.x:maxX;
            minY = minY>cur.y ? cur.y:minY; maxY = maxY<cur.y?cur.y:maxY;
            
            for(int dir=0;dir<4;dir++){
                int nx = cur.x+dx[dir]; int ny = cur.y+dy[dir];
                if(nx<0 || nx>=tmpArr.length || ny<0 || ny>=tmpArr.length) continue;
                if(tmpArr[nx][ny]==0) continue;
                
                tmpArr[nx][ny] = 0;
                q.add(new Point(nx,ny));
            }
        }
        return makeString(minX,maxX,minY,maxY,blockList);
    }
     int[][] rotate(int[][] arr){
        int N = arr.length;
        int[][] newarr = new int[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                newarr[j][N-1-i] = arr[i][j];
            }
        }
        return newarr;
    }
     String makeString(int minX,int maxX,int minY,int maxY,LinkedList<Point> list){
        int rowSize = maxX-minX+1; int colSize = maxY-minY+1;
        int[][] stringblock = new int[rowSize][colSize];
        
        //list의 좌표들 하나씩 빼서 넣어야하는데 무작위로 넣는게 아닐 텐데..
        //for(int i=0;i<rowSize;i++){
            //for(int j=0;j<colSize;j++){
                for(Point p:list)
                    stringblock[p.x-minX][p.y-minY] = 1; 
            //}
        //}
        String tmp = "";
        for(int i=0;i<rowSize;i++){
            for(int j=0;j<colSize;j++){
                // if(stringblock[i][j] == 1)tmp += "1";
                // else if(stringblock[i][j] == 0) tmp+="0";
                tmp += stringblock[i][j];
            }
            tmp += "n";
        }
        return tmp;
    }

}//solution 클래스