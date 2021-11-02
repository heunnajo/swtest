package ss;
import java.util.*;
public class AddParenthesis {
	static class Term{
		int num,op;
		Term(int num,int op){
			this.num = num;
			this.op = op;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String tmp = sc.next();
		
		Term[] input = new Term[n];
		
		for(int i=0;i<n;i++) {
			if(i%2 == 0) {//숫자!
				input[i] = new Term(tmp.charAt(i)-'0',0);
			} else {
				if(tmp.charAt(i)=='+') input[i] = new Term(0,1);
				else if(tmp.charAt(i)=='-') input[i] = new Term(0,2);
				else if(tmp.charAt(i)=='*') input[i] = new Term(0,3);
			}
		}
		
		int m = (n-1)/2;
		int ans = Integer.MIN_VALUE;
		for(int i=0;i<(1<<m);i++) {
			boolean flag = true;
			for(int j=0;j<m;j++) {
				if((i&(1<<j))>0 && (i&(1<<(j+1))) >0) flag = false;
			}
			if(!flag) continue;
			//원래 배열 복사해서 수식값 계산한다!?
			Term[] b = new Term[n];
//			for(int j=0;j<n;j++) b[j] = input[j];//원래 배열의 참조변수값만 넘겨줘도되는 것 아닌가?!
			for(int j=0;j<n;j++) b[j] = new Term(input[j].num,input[j].op);//원래 배열의 참조변수값만 넘겨줘도되는 것 아닌가?!
			
			//본격 수식 계산!
			//1. 몇전째 연산자가 켜져있는지 먼저 판별하고, 해당 연산자로 먼저 계산해줘야한다!!
			for(int j=0;j<m;j++) {
				if((i&(1<<j))>0) {
					int k = 2*j+1;
					if(b[k].op==1) {
						b[k-1].num += b[k+1].num;
						b[k].op = 1; b[k+1].num = 0;
					} else if(b[k].op==2) {
						b[k-1].num -= b[k+1].num;
						b[k].op = 1; b[k+1].num = 0;
					} else if(b[k].op==3) {
						b[k-1].num *= b[k+1].num;
						b[k].op = 1; b[k+1].num = 0;
					}
				}
			}
			//2. 1에서 계산한 결과를 쭉 계산하면 된다! 
			int result = b[0].num;
			for(int j=0;j<m;j++) {
				int k = 2*j+1;
				if(b[k].op==1) {
					result += b[k+1].num;
				} else if(b[k].op==2) {
					result -= b[k+1].num;
				} else if(b[k].op==3) {
					result *= b[k+1].num;
				}
			}
			if(ans<result) ans = result;
		}
		System.out.println(ans);
	}

}
