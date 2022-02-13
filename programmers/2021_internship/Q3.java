//3. 표 편집
import java.util.*;
class Solution {
    class Node{
        Node prev,next;
        boolean removed;
        // Node(){};
        // Node(Node prev,Node next){
        //     this.prev; this.next = next;
        //     removed = false;
        // }
    }
    
    public String solution(int n, int k, String[] cmd) {
        //1.자료구조 : Node cur = 현재 행, Node 타입 배열 리스트 : 표
        //현재 행 cur, 표 table, 스택 st 생성, 초기화
        // Node cur = new Node();
        //ArrayList<Node> table = new ArrayList[n];
        Node[] table = new Node[n];
        for(int i=0;i<n;i++) table[i] = new Node();
        // for(int i=0;i<n;i++) {
            // if(i == 0) table[i].prev = null;
            // else table[i].prev = table[i-1];
            // if(i == n-1) table[i].next = null;
            // else table[i].next = table[i+1];
        // }
        for(int i=1;i<n;i++) {
            table[i].prev = table[i-1];
            table[i-1].next = table[i];
        }
        Node cur = table[k];
        Stack<Node> st = new Stack<>();
        
        //cmd배열 순회, 각 명령어 구현
        int len = cmd.length;
        for(int i=0;i<len;i++){
            String tmp = cmd[i];
            char curCmd = tmp.charAt(0);
            if(curCmd == 'U'){
                int x = tmp.charAt(2)-'0';
                for(int j=0;j<x;j++){//문제 제한에 따라 표 범위를 벗어나는 연산은 없음.
                    cur = cur.prev;
                }
            } else if(curCmd == 'D'){
                int x = tmp.charAt(2)-'0';
                for(int j=0;j<x;j++){
                    cur = cur.next;
                }
            } else if(curCmd == 'C'){
                cur.removed = true; st.push(cur);
                // if(cur.prev != null) {cur.prev.next = cur.next;}
                // if(cur.next != null) {
                //     cur.next.prev = cur.prev;
                //     cur = cur.next;//아래행을 현재행으로 갱신!
                // }
                Node up = cur.prev;
                Node down = cur.next;
                
                if(up!=null)
                    up.next = cur.next;
                if(down!=null){
                    down.prev = cur.prev;
                    cur = cur.next;
                } else cur = cur.prev;//삭제하는 행이 마지막 행인 경우!
            } else if(curCmd == 'Z'){
                Node rD = st.pop(); rD.removed = false;
                if(rD.prev != null) {rD.prev.next = rD;}
                if(rD.next != null) {rD.next.prev = rD;}
            }
        }
        
        //n은 최대 100만개이므로 StringBuilder를 사용!
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++){
            if(table[i].removed) sb.append('X');
            else sb.append('O');
        }
        return sb.toString();
    }
}