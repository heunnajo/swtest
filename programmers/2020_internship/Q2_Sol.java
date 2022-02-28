//2.수식 최대화
import java.util.ArrayList;
public class Q2_Sol {
    
    char[] prior = {'+','-','*'};
    boolean[] check = new boolean[3];
    //숫자, 연산자를 분리 시키기 위해 2개의 리스트 nums, ops 생성
    ArrayList<Long> nums = new ArrayList<>();
    ArrayList<Character> ops = new ArrayList<>();
    long answer = 0;

    public long solution(String expression){
        //1.숫자와 연산자를 분리
        //숫자 저장 위한 변수 num
        String num = "";
        for(int i = 0; i<expression.length();i++){
            //1-1.숫자이면 num에 덧셈
            if(expression.charAt(i) >='0' && expression.charAt(i)<='9'){
                num += expression.charAt(i);
            }else{//1-2.숫자가 아니면 지금까지의 숫자를 nums에 추가, num 초기화, 현재의 문자를 ops에 추가!
                nums.add(Long.parseLong(num));
                num = "";
                ops.add(expression.charAt(i));
            }
        }
        //주의! 마지막 숫자는 else문에 해당되지 않으므로 for문 순회한 후 nums에 추가하는 처리 추가적으로 해줘야함!
        nums.add(Long.parseLong(num));
        //2.재귀함수로 수식 계산.
        dfs(0,new char[3]);
        return answer;
    }
    //2.재귀함수로 연산자 순서와 극에 맞게 계산
    public void dfs(int count, char[] p){
        //2-1.종료 조건:3개의 연산자 모두 처리 마친 경우!
        if(count == 3){
            ArrayList<Long>cNums = new ArrayList<>(nums);
            ArrayList<Character> cOps = new ArrayList<>(ops);
            for(int i = 0; i<p.length;i++){
                for(int j = 0; j<cOps.size();j++){
                    if(p[i] == cOps.get(j)){
                        Long tmp = calc(cNums.remove(j),cNums.remove(j),p[i]);
                        cNums.add(j,tmp);
                        cOps.remove(j);
                        j--;
                    }
                }
            }
            answer = Math.max(answer,Math.abs(cNums.get(0)));
            return;
        }
        //2-2.현재 처리, 다음 경우 호출
        //현재 처리:아직 사용하지 않은 연산자를 선택.
        for(int i = 0; i< 3;i++){
            if(!check[i]){
                check[i] = true;
                p[count] = prior[i];//prior = {+,-,*}, prior[i]값으로 현재 cnt번째의 p배열값을 저장한다!
                dfs(count+1,p);
                check[i] = false;
            }
        }
    }
    //3.숫자1, 숫자2를 op로 계산, 결과 리턴
    public long calc(Long num1,Long num2, char op){
        long num = 0;
        switch (op){
            case '+' : return num1 + num2;
            case '-' : return num1 - num2;
            case '*' : return num1 * num2;
        }
        return num;
    }
}