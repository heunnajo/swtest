//Q2
import java.util.*;
class Solution {
    static int[] limit = {0,31,28,31,30,31,30,31,31,30,31,30,31};
    public long solution(String[] birthdays) {
        int n = birthdays.length;
        long answer = 1;//(n-1)^n
        for(int i=0;i<n;i++){//n번 반복
            answer *= (n-1);
        }

        HashMap<Integer,Integer> map = new HashMap<>();
        int[] days = new int[n];
        for(int i=0;i<n;i++){
            String cur = birthdays[i];
            String m = cur.substring(0,2);
            String d = cur.substring(2);

            //int days = Integer.parseInt(m)+Integer.parseInt(d);
            int intM = Integer.parseInt(m);
            int intD = Integer.parseInt(d);

            for(int j=1;j<intM;j++){
                intD += limit[j];
            }
            days[i] = intD;
            map.put(intD,1);
            //System.out.println(intD);            
        }

        for(int i=0;i<n;i++){
            //조건1
            for(int j=1;j<5;j++){
                if(map.containsKey(days[i]-j)) answer--;
            }
            //조건2
            if(days[i]%7 == 1 || days[i]%7 == 0) answer--;
        }
        return answer;
    }
}
// System.out.println("월: "+m);
//             System.out.println("일: "+d);
