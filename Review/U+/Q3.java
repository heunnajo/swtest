import java.util.*;

public class Solution {
    static int ans, tmpAns;
    static ArrayList<Integer> nums;
    static int[] ops,order;
    static boolean[] v;

    public int solution(int[] arr) {
        ans = -1;//최댓값 도출
        nums = new ArrayList<>();
        ops = new int[3];//ops[i] = 1:덧셈, 2:곱셈
        order = new int[3];//연산자의 순서 저장
        v = new boolean[3];

        for(int i=0;i<4;i++) {
            nums.add(arr[i]);
        }
        selectOp(0);//연산자를 선택

        return ans;
    }
    //연산자 선택
    static void selectOp(int idx){
        //1.종료
        if(idx == 3){
            // for(int i=0;i<3;i++){
            //     System.out.print(ops[i]+" ");
            // }
            // System.out.println();
            selectPrior(0);//우선순위 선택
            //계산, ans 최댓값을 도출!
            return;
        }
        //2.현재 경우 선택, 다음 경우 호출
        //2-1.덧셈 선택
        ops[idx] = 1;
        selectOp(idx+1);
        //2-2.곱셈 선택
        ops[idx] = 2;
        selectOp(idx+1);
    }


    static void selectPrior(int idx){

        if(idx == 3){
            calculate();
            return;
        }

        for(int i=0;i<3;i++){
            if(v[i]) continue;
            v[i] = true;
            order[idx] = i;//012 021 102 120 201 210 
            selectPrior(idx+1);
            v[i] = false;
        }
    }
    static void calculate(){
        //ops[order[i]] = 1:덧셈, 2:곱셈, 숫자 : nums 리스트
        for(int i=0;i<3;i++){//012 021 102 120 201 210 
            int curIdx = order[i];//연산자의 인덱스를 차례로 가져온다 : 피연산자 조회를 위해 필요!
            int curOp = ops[curIdx];//ops[order[i]];

            //계산
            int num1 = nums.remove(curIdx);
            int num2 = nums.remove(curIdx);
            // int num1,num2;
            // if(curIdx < nums.size()){
            //     num1 = nums.remove(curIdx);
            //     num2 = nums.remove(curIdx);
            // } else{
            //     num1 = nums.remove(nums.size()-1);
            //     num2 = nums.remove(nums.size()-1);
            // }


            if(curOp == 1) nums.add(curIdx,num1+num2);
            else nums.add(curIdx,num1*num2);
        }
        tmpAns = nums.get(0);
        if(tmpAns>ans) ans = tmpAns;
    }
}