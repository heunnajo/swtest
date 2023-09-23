import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
//괄호의 값 
class BOJ2504_Solution {
    

	
    public static void main (String[] args) throws Exception {
       // System.setIn(new BufferedInputStream(new FileInputStream("input.txt")));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine().trim();
        
        boolean unmatch = false;
        for (int i=0; i<s.length(); i++) {
        	char ch = s.charAt(i); 
        	if (ch =='(') {
        		push(-1);
        	}
        	else if (ch =='[') {
        		push(-2);
        	}
        	else if (ch ==')') {
        		if (!match(-1)) {
        			unmatch = true;
        			break;
        		}
        	}
        	else if (ch ==']') {
        		if (!match(-2)) {
        			unmatch = true;
        			break;
        		}
        	}
        	else {
    			unmatch = true;
    			break;
        	}
        }
        
        if (unmatch) {
        	System.out.println(0);
        }
        else {
        	int sum = 0;
        	while (sp>=0) {
        		if (top()<0) {
        			System.out.println(0);
        			return;
        		}
        		sum += pop();
        	}
        	System.out.println(sum);
        }
    }

	static int[] stack = new int[31];
	static int sp = -1; 

	static boolean match(int i) { 
		if (sp==-1) return false;  // error case
		int mult = i==-1?2:3;
		int sum = 0;
		while (sp>=0 && top()>0) { 
			sum += pop();
		}
		if (sp>=0 && top()==i) {
			pop();
			if (sum==0) {
				push(mult);
			}
			else {
				push(mult*sum);
			}
			return true;
		}
		else {
			return false;
		}
	}
	static void push(int i) { stack[++sp]=i; }	
	static int top() { return stack[sp]; }
	static int pop() { return stack[sp--]; }
}    
 
