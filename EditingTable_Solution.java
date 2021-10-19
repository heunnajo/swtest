import java.util.*;
class EditingTable_Solution {
    class Node{
        boolean removed;
        Node prev;
        Node next;
    }
    Node[] NodeArr = new Node[1000000];//일단 크기는 백만개로 하고, n을 입력받고 난 후 n만큼 초기화해준다!
    public String solution(int n, int k, String[] cmd) {
        for(int i=0;i<n;++i)
            NodeArr[i] = new Node();//removed = false, prev next = null로 초기화된다.
        for(int i=1;i<n;++i){//n개의 행에 대해 next와 prev 링크를 초기화해준다!n-1까지 진행한다, n-1의 next = null, n-1의 prev는 반복문 돌면서 진행 O
            NodeArr[i-1].next = NodeArr[i];//0번행 next = 1번행...n-2행의 next = n-1행
            NodeArr[i].prev = NodeArr[i-1];//1번행 prev = 0번행...n-1행의 prev = n-2행
        }
        Node cur = NodeArr[k];
        Stack<Node> mystack = new Stack<>();//삭제하는 노드 번호 저장하는 스택
        //명령어 하나씩 처리 => 문자열 처리 요령 숙지! : 이 문제에서는 첫 문자로 명령어 구분 가능하고, 공백포함 3자리이기 때문에 그냥 charAt으로 명령어와 숫자를 구분가능하다!
        for(String str : cmd){
            if(str.charAt(0) == 'U'){
                int x = Integer.parseInt(str.substring(2));//문자열의 2번째부터 숫자를 의미한다!
                //x만큼 prev 링크 타고 올라가면 된다.
                for(int i=0;i<x;i++)
                    cur = cur.prev;
            } else if(str.charAt(0) == 'D'){
                int x = Integer.parseInt(str.substring(2));//문자열의 2번째부터 숫자를 의미한다!
                //x만큼 prev 링크 타고 올라가면 된다.
                for(int i=0;i<x;i++)
                    cur = cur.next;
            } else if(str.charAt(0) == 'C'){
                mystack.push(cur);
                cur.removed = true;
                //삭제되는 행의 링크를 갱신한다!=>예외사항:첫번째 행을 삭제할 때, prev=null이다.
                //삭제되는 행의 이전과 다음을 연결한다!
                Node up = cur.prev;
                Node down = cur.next;
                if(up != null)
                    up.next = down;
                if(down != null){
                    down.prev = up;
                    cur = down;
                } else{
                    cur = up;//마지막 행일 때 curr=up
                }
            } else{//Z
                Node node = mystack.pop();
                node.removed = false;
                Node up = node.prev;
                Node down = node.next;
                //null인지 아닌지 확인해야한다!?복원되는 행이 첫번째 행이라면 prev=null
                
                //복원 매커니즘 : 스택에서 꺼낸 노드의 prev의 next를 자기자신 가리키게 하고, next의 prev를 자기 자신 가리키게 하면 된다! 단, 이 때 스택에서 꺼낸 노드의 prev, next가 첫행이였는지 마지막행이였는지에 따라 null일수도 있기 때문에 얘외처리해줘야한다!
                if(up!=null)
                    up.next = node;
                if(down!=null)
                    down.prev = node;
            }
        }
        //Node 배열 순회하면서 removed값 확인해서 append 할 것이기 때문에 mutable한 StringBuilder로 한다! mutable=> 객체가 새로 생성되는 것이 아니라 값이 바뀐다.
        //String은 immutable 클래스이기 때문에 매번 추가되는 연산이 있다면 매번 새로운 객체가 생성되어 비효율적이기 때문에
        StringBuilder answer = new StringBuilder();
        for(int i=0;i<n;i++){
            if(NodeArr[i].removed) answer.append('X');
            else answer.append('O');
        }
        return answer.toString();
    }
}