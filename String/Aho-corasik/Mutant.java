import java.io.*;
import java.util.*;
public class Mutant {
	static int n;
	static ArrayList<Node> trie;
	static class Node{
		int[] children;
		int pi;//fail 링크!
		int cnt;
		ArrayList<Integer> indexes;
		Node(){
			children = new int[4];
			for(int i=0;i<4;i++) children[i] = -1;
			pi = -1; cnt = 0;
			indexes = new ArrayList<>();
		}
	}
	static int  to_node(char x) {//각 문자를 문자로 저장X 숫자로 리턴!
		if(x == 'A') return 0;
		else if(x == 'T') return 1;
		else if(x == 'G') return 2;
		else if(x == 'C') return 3;
		else return 4;
	}
	static int init() {
		Node x = new Node();
		trie.add(x);
		return (int)trie.size()-1;
	}
	static void add(int node,String s,int idx) {
		//1.재귀 종료 조건
		if(idx == s.length()) {
			trie.get(node).cnt = 1;//-1이 아닌 값으로 도장 콱!
			return;
		}
		//2.현재 idx번째의 문자를 추가
		int c = to_node(s.charAt(idx));
		while(trie.get(node).children[c] == -1) {
			int next = init();
			trie.get(node).children[c] = next;
		}
		//3.재귀 호출
		add(trie.get(node).children[c],s,idx+1);
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		while(T-->0) {
			trie = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());//DNA 글자 수
			int b = Integer.parseInt(st.nextToken());//마크 글자 수
			String DNA = br.readLine();
			String Marker = br.readLine();
			int root = init();
            //0.중복없이 트리형태로 저장
			TreeSet<String> set = new TreeSet<>();
			for(int i=0;i<b;i++) {
				for(int j=i;j<b;j++) {
					String tmp = new StringBuilder(Marker.substring(i,j+1)).reverse().toString();
					set.add(Marker.substring(0,i)+tmp+Marker.substring(j+1));
				}
			}
            //1.set의 문자열(돌연변이)들을 Trie로 구성
			for(String s:set) {
				add(root,s,0);
			}
			//2.KMP처럼 BFS로 fail링크 완성!
			Queue<Integer> q = new LinkedList<>();
			trie.get(root).pi = root;
			q.add(root);
			while(!q.isEmpty()) {
				int cur = q.remove();
				for(int i=0;i<4;i++) {
					int next = trie.get(cur).children[i];
					if(next == -1) continue;
					if(cur == root) {//root->a 인 경우, cur의 fail링크를 루트로
						trie.get(next).pi = root;
					} else{//본격적으로 j 찾아서 fail을 완성!
						int x = trie.get(cur).pi;
						while(x != root && trie.get(x).children[i] == -1) {
							x = trie.get(x).pi;
						}
						if(trie.get(x).children[i] != -1) {
							x = trie.get(x).children[i];
						}
						trie.get(next).pi = x;//next의 fail을 x로
					}
					int pi = trie.get(next).pi;
					trie.get(next).cnt += trie.get(pi).cnt;
					q.add(next);
				}
			}
			//3.list를 순회하면서 DNA에 있는지 확인, 있으면 ans++
			int ans = 0;
			int node = root;
			for(int i=0;i<a;i++) {
				int c = to_node(DNA.charAt(i));
				while(node != root && trie.get(node).children[c]==-1) {
					node = trie.get(node).pi;
				}
				if(trie.get(node).children[c] != -1) {
					node = trie.get(node).children[c];
				}
				ans += trie.get(node).cnt;
			}
			//각 TC마다 카운팅해서 sb에 append
			sb.append(ans+"\n");
		}
		System.out.print(sb);
	}

}