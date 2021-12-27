package ss;
import java.util.*;
import java.io.*;
public class Censorship{
	static char[] l = new char[300001];
	static char[] r = new char[300001];
	static int ln = 0, rn = 0;//스택:배열로 만들 것이기 때문에 왼쪽 스택의 인덱스 ln, 오른쪽 스택의 인덱스 rn
	static char[] a,ar,b;
	static int n,m;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		a = br.readLine().toCharArray();//삭제할 문자!
		n = a.length;
		ar = new char[n];
		for(int i=0;i<n;i++) {
			ar[i] = a[n-i-1];//왜 필요한지는 알겠는데 어떻게 적시적소에 쓸까?
		}
		b = br.readLine().toCharArray();
		m = b.length;
		int left = 0,right = m-1;//문자열 b에서 왼쪽부터 비교하는 인덱스 left,왼쪽부터 비교하는 인덱스 right
		int where = 0;//처음엔 왼쪽부터 시작!
		while(left<=right) {
			if(where == 0) {
				l[ln++] = b[left++];
			} else {
				r[rn++] = b[right--];//문자열의 끝에서부터 시작하므로 right 인덱스는 감소
			}
			if(check(where)) {//a가 있는지 체크한 후에 있다면 삭제하고 차례를 바꿈! 없으면 방향 바꾸지 않음.ex)left=0->m-1로 끝까지 가게 되거나 right = m-1->left 까지 가게 된다!
				where = 1-where;
			}
		}
		//left==right가 되었다 : 스택 l을 스택 r에 넣어준다!
		//왼쪽 스택의 크기는 1증가된 ln이므로 인덱스는 ln-1부터 거꾸로 
		for(int i=ln-1;i>=0;i--) {
			r[rn++] = l[i];
			check(1);
		}
		for(int i=rn-1;i>=0;i--) {
			System.out.print(r[i]);
		}
		System.out.println();
	}
	static boolean check(int where) {
		char[] stack = l; int len = ln;
		char[] str = ar;//뒤집힌 문자열. 어디에 쓰이나?
		if(where == 1) {
			stack = r; len = rn; str = a;
		}
		if(len-n<0) return false;//길이로 백트랙킹
		//왼쪽/오른쪽 스택에 a가 있는지 검사
		for(int i=0;i<n;i++) {
			if(stack[len-i-1] != str[i]) return false;
		}
		if(where == 0) ln-=n;
		else rn -= n;
		return true;
	}
}
