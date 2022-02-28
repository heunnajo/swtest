//2.수식 최대화
import java.util.ArrayList;
public class Solution {
    

    char[] prior = {'+','-','*'};
    boolean[] check = new boolean[3];

    ArrayList<Long> nums = new ArrayList<>();
    ArrayList<Character> ops = new ArrayList<>();
    long answer = 0;

    public long solution(String expression){
        String num = "";
        for(int i = 0; i<expression.length();i++){
            if(expression.charAt(i) >='0' && expression.charAt(i)<='9'){
                num += expression.charAt(i);
            }else{
                nums.add(Long.parseLong(num));
                num = "";
                ops.add(expression.charAt(i));
            }
        }
        nums.add(Long.parseLong(num));

        dfs(0,new char[3]);
        return answer;
    }

    public long calc(Long num1,Long num2, char op){
        long num = 0;
        switch (op){
            case '+' : return num1 + num2;
            case '-' : return num1 - num2;
            case '*' : return num1 * num2;
        }
        return num;
    }
    public void dfs(int count, char[] p){
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
        for(int i = 0; i< 3;i++){
            if(!check[i]){
                check[i] = true;
                p[count] = prior[i];
                dfs(count+1,p);
                check[i] = false;
            }
        }
    }
}