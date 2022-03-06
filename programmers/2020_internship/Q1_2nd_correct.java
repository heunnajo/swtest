//1.키패드 누르기_정답맞춤
class Solution {
    //0 1 2 3 4 5 6 7 8 9
    int[][] pos = {{3,1},{0,0},{0,1},{0,2},{1,0},{1,1},{1,2},{2,0},{2,1},{2,2}};
    class Pair{
        int x,y;
        Pair(int x,int y){
            this.x = x; this.y = y;
        }
    }
    public String solution(int[] numbers, String hand) {
        StringBuilder ans = new StringBuilder();
        Pair left = new Pair(3,0); Pair right = new Pair(3,2);
        
        for(int k=0;k<numbers.length;k++){
            if(numbers[k] == 1 || numbers[k] == 4 ||numbers[k] == 7 ) {
                ans.append("L"); left.x = pos[numbers[k]][0];left.y = pos[numbers[k]][1];
            }
            else if(numbers[k] == 3 || numbers[k] == 6 || numbers[k] == 9 ) {
                ans.append("R"); right.x = pos[numbers[k]][0];right.y = pos[numbers[k]][1];
            }
            else{//2,5,8,0인 경우
                 //0.이동할 숫자 -> 좌표:(i,j)
                Pair dest = new Pair(pos[numbers[k]][0],pos[numbers[k]][1]);
                //1.현재 왼손 엄지 위치 -> 좌표(leftI,leftJ)
                int distFromL = Math.abs(left.x-dest.x)+Math.abs(left.y-dest.y);
                //2.현재 오른손 엄지 위치 -> 좌표
                int distFromR = Math.abs(right.x-dest.x)+Math.abs(right.y-dest.y);
                
                if(distFromL<distFromR){
                    ans.append("L"); left.x = pos[numbers[k]][0];left.y = pos[numbers[k]][1];
                } else if(distFromL>distFromR){
                    ans.append("R");right.x = pos[numbers[k]][0];right.y = pos[numbers[k]][1];
                } else if(distFromL == distFromR){//hand에 따라 추가
                    if(hand.equals("left")) {
                        ans.append("L");left.x = pos[numbers[k]][0];left.y = pos[numbers[k]][1];
                    }
                    else if(hand.equals("right")){
                        ans.append("R");right.x = pos[numbers[k]][0];right.y = pos[numbers[k]][1];
                    }
                }
            }
        }
        
        return ans.toString();
    }
}