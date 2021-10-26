package ss;
import java.util.*;
import java.io.*;
class PlateNumber_Solution {
    static int ans,n;
    static String input;
    static void go(int index, char prev){
        if(index==n){
            ans++;
            return;
        }
        //현재 선택
        char start = (input.charAt(index) == 'c' ? 'a':'0');
        char end = (input.charAt(index) == 'c' ? 'z':'9');
        //char end = (input.charAt(index) == 'd' ? 'z':'9');
        for(char i=start;i<=end;i++) {
        	if(i==prev) continue;
        	go(index+1,i);//원복은 하지 않아도됨.선택하는 i마다 다르게 재귀호출하기 때문.
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();
        n = input.length();
        ans = 0;
        go(0,' ');
        System.out.println(ans);
    }
}