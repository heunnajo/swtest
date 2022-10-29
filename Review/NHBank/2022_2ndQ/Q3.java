//Q3
import java.util.*;
class Solution {
    static ArrayList<Integer> list;
    static boolean[] isInCart;
    static int N,K,Cart[];
    static String[] Records;

    public int[] solution(int n, String[] records, int[] cart, int k) {

        if(k==n){
            int[] answer = new int[n];
            for(int i=1;i<=n;i++){
                answer[i] = i;
            }
            return answer;
        }
        N = n; K = k;
        Cart = new int[cart.length]; int tmpi=0;
        for(int a:cart) Cart[tmpi++] = a;
        Records = new String[records.length]; tmpi = 0;
        for(String s:records) Records[tmpi] = s;

        list = new ArrayList<>();
        isInCart = new boolean[n+1];

        for(int a: cart){
            isInCart[a] = true;
        }

        solve();//list를 완성

        int idx = 0;
        int[] answer = new int[cart.length+list.size()];
        for(int a:cart){
            answer[idx++] = a;
        }

        for(int a:list){
            answer[idx++] = a;
        }

        Arrays.sort(answer);
        return answer;
    }
    void solve(){

    }
}