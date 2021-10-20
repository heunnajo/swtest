import java.util.*;
class Solution {
    static class Node{
        boolean removed;
        Node prev;
        Node next;
    }
    Node[] NodeArr = new Node[1000000];
    public String solution(int n, int k, String[] cmd) {
        //Node 배열 초기화
        for(int i=0;i<n;i++)
            NodeArr[i] = new Node();
        //prev,next 링크 초기화!:n개의 행에 대해.
        for(int i=1;i<n;i++){
            NodeArr[i].prev = NodeArr[i-1];
            NodeArr[i-1].next = NodeArr[i];
        }
        
        Node cur = NodeArr[k];
        Stack<Node> mystack = new Stack<>();
        
        for(int i=0;i<cmd.length;i++){//현재의 명령어 = cmd[i]
            String str = cmd[i];
            if(str.charAt(0) == 'U'){
                int num = Integer.parseInt(str.substring(2));
                for(int j=0;j<num;j++)
                    cur = cur.prev;
            } else if(str.charAt(0) == 'D'){
                int num = Integer.parseInt(str.substring(2));
                for(int j=0;j<num;j++)
                    cur = cur.next;
            } else if(str.charAt(0) == 'C'){//현재 행 삭제!
                cur.removed = true;
                mystack.push(cur);
                Node up = cur.prev;//반복조회방지를 위해 일단 한번 조회해서 로컬변수에 저장.
                Node down = cur.next;
                //링크 갱신
                if(up!=null)
                    up.next = cur.next;
                if(down!=null){
                    down.prev = cur.prev;
                    cur = cur.next;
                } else cur = cur.prev;//cur = up;//down==null일 때 :마지막행일 때
            } else{//Z
                Node node = mystack.pop();
                node.removed = false;

                if(node.prev!=null) node.prev.next = node;
                if(node.next!=null) node.next.prev = node;
            }
        }
        StringBuilder answer = new StringBuilder();
        for(int i=0;i<n;i++){
            if(NodeArr[i].removed) answer.append('X');
            else answer.append('O');
        }
        return answer.toString();
    }
}