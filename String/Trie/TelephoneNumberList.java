package ss;
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
				for(int i=0;i<10;i++) children[i] = -1;
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
			int c = s.charAt(idx)-'0';
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
			if(idx == s.length()) return true;//여기서 true!!!
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
			boolean isContain = false;//일관성 만족O
			List<String> list = new LinkedList<>();
			int n = Integer.parseInt(br.readLine());
			while(n-->0){//n개의 숫자를 문자열로 받아서 Trie에 있는지 확인, 없으면 추가. 있으면 바로 NO
				String num = br.readLine();//길어야 10자리<정수 저장 범위
				list.add(num);
				
			}
			//91124, 91123, 911 순으로 정렬해야한다!
			Collections.sort(list,Collections.reverseOrder());
			
			for(String num:list) {
				//System.out.println(num);
				if(trie.search(num)) {//접두어로 존재한다는 의미이므로 바로 NO를 출력,반복 종료!
					//if(num.equals("911"))  System.out.println("isContain : "+isContain);
					isContain = true;//일관성 만족X
					break;
				} else {
					trie.add(num);
				}
				//if(num.equals("911"))  System.out.println("isContain : "+isContain);
			}
			//n개의 전화번호마다 검사해서 flag값에 따라 sb에 저장!
			if(isContain) sb.append("NO"+"\n");//리턴되지 않고 n개의 전화번호 확인했다면 YES
			else sb.append("YES"+"\n");
		}
		System.out.print(sb);
	}
}