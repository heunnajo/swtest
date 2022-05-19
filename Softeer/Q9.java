//전광판
package ss;
import java.util.*;
import java.io.*;

public class ElectronicDisplay {
	static boolean[][] bulbs;
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        
        StringTokenizer st;
        
        bulbs = new boolean[11][7];
        
        makeBulbsInfo();
        //checkBulbsInfo();
        
        StringBuilder sb = new StringBuilder();
        while(t-- >0) {
        	st = new StringTokenizer(br.readLine());
        	String tmpA = st.nextToken();
        	String tmpB = st.nextToken();
        	
        	String a = tmpA;
        	String b = tmpB;
        	for(int i=0;i<5-tmpA.length();i++) a = ' '+a;
        	for(int i=0;i<5-tmpB.length();i++) b = ' '+b;
        	
//        	System.out.println("a:"+a);
//        	System.out.println("b:"+b);
        	sb.append(solve(a,b)).append("\n");
        }
        System.out.print(sb);
	}
	static int solve(String a,String b) {
		int sum = 0;
		
		for(int i=0;i<5;i++) {
			boolean[] curA,curB;
			
			if(a.charAt(i) == ' ') curA = bulbs[10];
			else curA = bulbs[a.charAt(i)-'0'];
			
			if(b.charAt(i) == ' ') curB = bulbs[10];
			else curB = bulbs[b.charAt(i)-'0'];
			
			for(int j=0;j<7;j++) {
				if(curA[j] != curB[j]) sum++;
			}
		}
		return sum;
	}
	static void checkBulbsInfo() {
		for(int i=0;i<10;i++) {
			for(int j=0;j<7;j++) {
				System.out.print(bulbs[i][j]+" ");
			}
			System.out.println();
		}
	}
	static void makeBulbsInfo() {
		//0
		for(int i=0;i<7;i++) {
        	if(i == 6)continue;
        	bulbs[0][i] = true;
        }
		//1
		bulbs[1][1] = bulbs[1][2] = true;
		//2
		for(int i=0;i<7;i++) {
			if(i==2 || i==5) continue;
			bulbs[2][i] = true;
		}
		//3
		for(int i=0;i<7;i++) {
			if(i==4 || i==5) continue;
			bulbs[3][i] = true;
		}
		//4
		for(int i=0;i<7;i++) {
			if(i==0 || i==3||i==4) continue;
			bulbs[4][i] = true;
		}
		//5
		for(int i=0;i<7;i++) {
			if(i==1||i==4) continue;
			bulbs[5][i] = true;
		}
		//6
		for(int i=0;i<7;i++) {
			if(i==1) continue;
			bulbs[6][i] = true;
		}
		//7
		for(int i=0;i<7;i++) {
			if(i==3||i==4||i==6) continue;
			bulbs[7][i] = true;
		}
		//8
		for(int i=0;i<7;i++) {
			bulbs[8][i] = true;
		}
		//9
		for(int i=0;i<7;i++) {
			if(i==4) continue;
			bulbs[9][i] = true;
		}
		//10:dummy
	}

}
