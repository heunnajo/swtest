import java.util.*;
class FindPrimeNumber {
    static int answer,len;
    static String Numbers;
    static boolean[] check;//중복 선택 방지
    static Set<Integer> set;
    public int solution(String numbers) {
        //정답  변수, 입력 => 전역변수로 가져온다!
        answer = 0;
        Numbers = numbers;
        len = Numbers.length();
        set = new HashSet<>();
        //1,2,3,..len자리 수를 만든다.
        for(int i=1;i<=len;i++){
            check = new boolean[len];//크기:Numbers의 길이, 초기화시점 : i자리 만들때마다?
            go(0,i,"");//모든 경우마다 숫자를 만들어서 set에 저장
        }
        for(int x:set){
            if(isPrime(x)) answer++;  
        }
        return answer;
    }
    //idx:현재 몇번째 숫자를 만드는지
    //target:만들 숫자 갯수(자릿수)
    //num:지금까지 만든 숫자 => 문자열 형태로 이어붙인다.
    static void go(int idx,int target,String num){
        //1.재귀 종료 조건
        if(idx == target){
            int curNumber = Integer.parseInt(num);//011=>11로 처리함!
            set.add(curNumber);
            //if(isPrime(curNumber)) answer++;     
            return;
        }
        //2.현재 선택
        for(int i=0;i<len;i++){
            if(check[i]) continue;
            check[i] = true;
            go(idx+1,target,num+Numbers.charAt(i));//3.다음 경우 호출
            check[i] = false;
        }
        
    }
    static boolean isPrime(int x){
        if(x==0 || x==1)return false;
        int root = (int)Math.sqrt(x);
        for(int i=2;i<root;i++){
            if(x%i == 0) return false;//num과 나눠떨어지는 수가 있으면 소수가 아님!
        }
        return true;
    }
}