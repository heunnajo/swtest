import java.util.*;

class ParenthesesConversion {
    int pos;
    boolean isCorrect(String str){
        boolean ret = true;
        int left = 0, right = 0;
        Stack<Character> mystack = new Stack<Character>();
        
        for(int i=0;i<str.length();++i){
            if(str.charAt(i)=='('){
                left++;
                mystack.push('(');
            } else{
                right++;
                if(mystack.empty()) ret = false;//짝이 안 맞는 경우!
                else mystack.pop();//스택이 비지 않았으면 짝이 맞기 때문에 pop한다~
            }
            if(left == right){//u와 v를 분리하는 위치.
                pos = i+1;//pos는 v의 시작위치이면서 u의 길이를 의미한다! 중의적 의미!
                return ret;
            }
        }
        
        return ret;//left==righht가 같아지는 순간이 반드시 존재, ret를 반환하므로 이까지 도달할 일은 없지만 임의로 true를 반환한다고 함.
    }
    public String solution(String p) {
        if(p.isEmpty()) return p;
        
        //u와 v를 분리할 때, u가 올바른 문자열인지도 함께 판단하도록 한다.
        //u가 올바른 문자열인지 아닌지에 따라 처리하는 로직이 갈리기 때문.
        //2번. u와 v를 분리
        boolean correct = isCorrect(p);//p를 u와 v로 분리함과 동시에 올바른 문자열인지 판단한다! isCorrect메서드를 통해 u와 v를 분리하는 인덱스 pos를 알아낼 수 있다.
        String u = p.substring(0,pos);
        String v = p.substring(pos,p.length());
        //분리한 u가 올바른 인지 아닌지에 따라 3번 또는 4번 수행
        if(correct){
            return u + solution(v);//3-1에 주어진 대로 그대로 구현
        }
        //else로 해도 되지만 리턴되지 않고 여기 왔다는 것은 올바르지 않은 것이므로 else문 없이 구현.
        //4-1.빈문자열에 첫번째문자로 (를 붙인다고 했다. answer로 그냥 사용하면 됨.
        String answer = "("+solution(v)+")";//4-3까지 완료
        //인덱스를 1부터 하면 첫번째 문자 제거되고, u의 마지막 까지가 아니라 1작게 하면 마지막 문자 제거된다.
        for(int i=1;i<u.length()-1;i++){//4-4: 그대로 반대로 뒤집어주면됨. 너무 어렵게 생각해버림.
             if(u.charAt(i) == '(') 
                 answer += ")";
            else
                answer += "(";
        }
        return answer;
    }
}