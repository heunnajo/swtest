import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class TelephoneNumberList {
	
	static class Trie{
		class Node{
			int[] children;
			boolean valid;
			Node(){
				children = new int[10];//0,1,2,...9
				for(int i=0;i<10;i++) {
					children[i] = -1;
				}
				valid = false;
			}
		}
		ArrayList<Node> trie;
		int root;
		int init(){
			Node x = new Node();
			trie.add(x);
			return trie.size()-1;
		}
		Trie(){//Trie 초기화!
			trie = new ArrayList<>();
			root = init();
		}
		void add(int node,String s,int idx) {
			//1.종료 조건
			if(idx == s.length()) {
				trie.get(node).valid = true;
				return;
			}
			//2.현재 넣넣
			int c = s.charAt(idx)-'0';//0,1,2,...9 중 하나!
			if(trie.get(node).children[c] == -1) {
				int next = init();
				trie.get(node).children[c] = next;
			}
			//3.재귀:다음 실행
			add(trie.get(node).children[c],s,idx+1);
		}
		void add(String s) {
			add(root,s,0);
		}

		boolean search(int node,String s,int idx) {
			//1.종료 조건
			if(node==-1) return false;
			if(idx == s.length()) return trie.get(node).valid;//그렇지 않으면 valid를 리턴.
			//2.현재 찾기
			int c = s.charAt(idx)-'0';
			//3.재귀 : 다음 실행
			return search(trie.get(node).children[c],s,idx+1);
		}
		boolean search(String s) {
			return search(root,s,0);
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(tc-->0) {
			//tc마다 Trie를 생성
			Trie trie = new Trie();
			int n = Integer.parseInt(br.readLine());
			boolean isContain = false;//일관성 만족여부 판단
			List<String> list = new ArrayList<String>();//추가!
			//1.trie와 list에 번호 추가!
			while(n-->0){//n개의 숫자를 문자열로 받아서 Trie에 있는지 확인, 없으면 추가. 있으면 바로 NO
				String num = br.readLine();//길어야 10자리<정수 저장 범위
				trie.add(num);
				list.add(num);
			}
			//2.list 순회하면서 trie에 있는지 확인. 있으면 isContain = true, sb에 false를 추가
			for(String s:list) {
				if(trie.search(s)) {
					isContain = true;//있으면 포함되있으므로 true로 갱신, break로 종료!
					break;
				}
			}
			
			//n개의 전화번호마다 검사해서 flag값에 따라 sb에 저장!
			if(isContain) sb.append("NO");//리턴되지 않고 n개의 전화번호 확인했다면 YES
			else sb.append("YES");//isContain==false이면 YES가 되야하는데!
			sb.append("\n");
		}
		System.out.print(sb);
	}

}
