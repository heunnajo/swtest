package ss;
import java.util.*;
import java.io.*;
class PlateNumber1 {
    static int ans,n;
    static String input;
    static int[] num = {100,0,1,2,3,4,5,6,7,8,9};
    //static char[] chara = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    static String[] chara = {"no","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
    static void go(int index, String prev){
        if(index==n){
            ans++;
            return;
        }
        if(input.charAt(index)=='d'){//숫자 선택
            for(int i=1;i<=10;i++){
                if(i==Integer.parseInt(prev)) continue;
                //ab.append(num[i]);
                go(index+1,String.valueOf(num[i]));
                //원복은 할 필요없을 듯? 왜냐하면 각 경우마다 다르게 매개변수에 문자 붙여넣는 형식이기 때문.
            }
        }else{//문자 선택
            for(int i=1;i<=26;i++) {
            	if(chara[i]==prev) continue;
            	//ab.append(chara[i]);
            	go(index+1,chara[i]);
            }
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();
        n = input.length();
        ans = 0;
        //StringBuilder sb = new StringBuilder();
        go(0,"");
        System.out.println(ans);
//        char a = 'a';//아스키코드값:97 
//        System.out.println(a+1);//예상 출력값:98
    }
}