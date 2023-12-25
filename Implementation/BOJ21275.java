import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
 
//폰 호석만 
public class BOJ21275 {
	static String xA,xB;
	static int[] dict;
	static String max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        xA = input[0];
        xB = input[1];
        dict = new int[200];
        max = String.valueOf(Math.pow(2, 63));
        
        for(int i=0;i<9;i++) {//여기서 틀림!0~9까지 문자로서의 아스키 숫자값은 1이 아니라 '1'이다!
        	dict['1'+i] = 1+i;
        }
        for(int i=0;i<26;i++) {
        	dict['a'+i] = 10+i;
        }
        int count = 0;
        String X="";
        int A = 0;
        int B = 0;
        
        for(int i=2;i<=36;i++) {
        	for(int j=2;j<=36;j++) {
        		if(i==j) continue;
        		
        		if(isValid(i,xA) && isValid(j,xB)) {
        			String valA = convert(i,xA); String valB = convert(j,xB);
        			//System.out.println(i+"진법을 10진법 변환한 A: "+valA+","+j+"진법을 10진법 변환한 B: "+valB);
					if(valA.equals(valB)) {
        				if(convert(i,xA).compareTo(max) >= 1) continue;//compareTo로 대소비교하는 방법 확인 필요!
        				
        				count++;
        				X = convert(i,xA);
        				X = X.substring(0,X.length()-2);
        				A = i;
        				B = j;
        			}
        		}
        	}
        }
        if(count >= 2) {System.out.println("Multiple");}
        else if(count ==  1) {System.out.println(X+" "+A+" "+B);}
        else {System.out.println("Impossible");}
    }
    static boolean isValid(int base,String num) {//이 로직과 코드는 정확함.
    	//num의 자릿수 중 가장 큰 값을 찾아서 base 진법이 가능한지 판단
    	char[] chArr = num.toCharArray();
    	Arrays.sort(chArr);
    	char max = chArr[chArr.length-1];
    	int maxVal = dict[max];
    	
    	if(base <= maxVal) return false;
    	return true;
    }
    static String convert(int base,String num) {//이 로직과 코드는 정확함.
    	int jisoo = 0; double res = 0;
    	for(int i=num.length()-1;i>=0;i--) {
    		res += Math.pow(base, jisoo) * dict[num.charAt(i)];
    		jisoo++;
    	}
    	return String.valueOf(res);
    }
}
//        String str = "daz0";
//        char[] arr = str.toCharArray();
//        Arrays.sort(arr);
//        for(int i=0;i<arr.length;i++) {
//        	System.out.print(arr[i] +"");
//        }