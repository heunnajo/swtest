//1.크레인 인형뽑기 게임
import java.util.*;
class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        int n = board.length;
        Stack<Integer>[] Board = new Stack[n+1];
        Stack<Integer> Bucket = new Stack<>();
        for(int i=0;i<=n;i++) Board[i] = new Stack<Integer>();
        
        //1.2차원 배열 board 정보를 N개의 스택 배열에 저장:O
        for(int j=0;j<n;j++){//스택 배열의 인덱스에서는 +1
            for(int i=n-1;i>=0;i--){
                if(board[i][j] == 0) continue;
                else{
                    Board[j+1].add(board[i][j]);//인형 번호를 해당하는 번호의 스택에 저장
                }
            }
        }
        //2.moves를 순회하면서 게임을 진행!
        int m_len = moves.length;
        for(int i=0;i<m_len;i++){
            if(Board[moves[i]].isEmpty()) continue;//해당 스택 비었으면 컨티뉴처리!
            else {
                //현재 바구니 꼭대기의 인형과 비교
                //현재 바구니 비어있는 경우 : 그냥 넣는다.
                //현재 바구니 비지 않은 경우 : 아래 1,2로 나눠서 처리!
                if(Bucket.isEmpty()) Bucket.push(Board[moves[i]].pop());
                else{
                    int cur_bucket = Bucket.peek();
                    //1. 같으면 넣음과 동시에 터트린다.
                    if(cur_bucket == Board[moves[i]].peek()){
                        Bucket.pop();
                        Board[moves[i]].pop();
                        answer+=2;
                    } 
                    //2. 같지 않으면 그냥 넣기만 한다!
                    else{
                        Bucket.push(Board[moves[i]].pop());
                    }
                }
            }
        }
        
        return answer;
    }
}
// for(int i=1;i<=n;i++){
        //     while(!Board[i].isEmpty()){
        //         System.out.print(Board[i].pop()+" ");
        //     }
        //     System.out.println();
        // }