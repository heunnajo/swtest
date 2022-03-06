//1.키패드 누르기_좌표값 도출 오답
import java.util.*;
class Solution {
    public String solution(int[] numbers, String hand) {
        StringBuilder ans = new StringBuilder();
        int left = 10, right = 11;
        for(int k=0;k<numbers.length;k++){
            if(numbers[k] == 1 || numbers[k] == 4 ||numbers[k] == 7 ) {
                ans.append("L");left = numbers[k];
            }
            else if(numbers[k] == 3 || numbers[k] == 6 || numbers[k] == 9 ) {
                ans.append("R");right = numbers[k];
            }
            else{//2,5,8,0인 경우
                 //0.이동할 숫자 -> 좌표:(i,j)
                int x = numbers[k]-1;
                int i = x/3, j = x%3;
                if(numbers[k] == 0) i = 3;j=1;
                //1.현재 왼손 엄지 위치 -> 좌표(leftI,leftJ)
                int tmpL = left-1, leftI = tmpL/3, leftJ = tmpL%3;//여기서 문제가 발생하는 듯하다!!!!!
                if(left == 0) leftI = 3;leftJ=1;
                //2.현재 오른손 엄지 위치 -> 좌표
                int tmpR = right-1, rightI = tmpR/3, rightJ = tmpR%3;
                if(right == 0) rightI = 3;rightJ=1;
                
                int distFromL = getDist(leftI,leftJ,i,j);
                int distFromR = getDist(rightI,rightJ,i,j);
                
                
                System.out.println("left: "+left+" right: "+right);
                System.out.print("left 좌표: "+leftI+","+leftJ);
                System.out.println();
                System.out.print("right 좌표: "+rightI+","+rightJ);
                System.out.println();
                
                System.out.println("distFromL: "+distFromL);
                System.out.println("distFromR: "+distFromR);
                System.out.println();
                if(distFromL<distFromR){
                    ans.append("L"); left = numbers[k];
                } else if(distFromL>distFromR){
                    ans.append("R");right = numbers[k];
                } else if(distFromL == distFromR){//hand에 따라 추가
                    if(hand.equals("left")) {
                        ans.append("L");left = numbers[k];
                    }
                    else if(hand.equals("right")){
                        ans.append("R");right = numbers[k];
                    }
                }
            }
        }
        
        return ans.toString();
    }
    
    int getDist(int sx,int sy,int dx,int dy){
        return Math.abs(sx-dx)+Math.abs(sy-dy);
    }
}