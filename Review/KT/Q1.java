//1.호텔 방 배정
import java.util.*;
class Solution {
    boolean[][] Reserv;
    int[] answer;
    LinkedList<Room> reservedList;
    class Room{
        int roomCnt, chIn, chOut;
        int[] start;
        Room(int roomCnt,int chIn,int chOut,int[] start){
            this.roomCnt = roomCnt; this.chIn = chIn;
            this.chOut = chOut; this.start = start;
        }
    }
    public int[] solution(int h, int w, int[][] books) {
        Reserv = new boolean[h][w];//호텔 층수 = h = 행, 한 층의 방 수 = w = 열
        reservedList = new LinkedList<>();
        answer = new int[books.length];
        isOk(h,w,books);
        return answer;
    }
    void isOk(int h,int w,int[][] books){

        int len = books.length; //int h = Reserv.length; int w = Reserv[0].length;
        LinkedList<Integer> removeIdx = new LinkedList<>();
        for(int t=1;t<=100;t++){
            for(int b=0;b<len;b++){
                int roomCnt = books[b][0], chIn = books[b][1], chOut = books[b][2];
                //1.체크아웃 확인, 처리
                for(int r=0;r<reservedList.size();r++){
                    Room cur = reservedList.get(r); 
                    if(cur.chOut == t){
                        removeIdx.add(r);
                        for(int i=0;i<cur.roomCnt;i++){
                            Reserv[cur.start[0]][cur.start[1]] = false;
                        }
                    }

                }
                for(int r=0;r<removeIdx.size();r++){
                    reservedList.remove(removeIdx.get(r));
                }
                //2.체크인 처리
                //유효성 판단, 만족하지 못하면 false 리턴
                int row = 0, col = 0;
                boolean flag = false;
                for(int i=0;i<h;i++){
                    int cnt = 0;
                    for(int j=0;j<w;j++){
                        if(!Reserv[i][j]) {
                            cnt++;
                            //System.out.println("cnt: "+cnt);
                        }
                        else{
                            if(cnt == roomCnt){//여기에 안 들어옴
                                flag = true;row = i;col = j;
                                for(int jj=j;jj>j-roomCnt;jj--){

                                    Reserv[i][jj] = true;
                                }
                            } else{
                                cnt = 0;
                            }
                        }
                    }
                }
                //System.out.println("row: "+row+"col: "+col);
                reservedList.add(new Room(roomCnt,chIn,chOut,new int[]{row,col}));
                //System.out.println("예약리스트 갯수: "+reservedList.size());
                if(!flag) answer[b] = 0;
                else answer[b] = 1;//예약 가능하다는 의미
            }

        }



    }
}