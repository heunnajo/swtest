import java.util.*;
import java.io.*;
public class FindPrefix {
	static class Trie{
		static class Node{
			int[] children;
			boolean valid;
			Node() {
				children = new int[26];
				for(int i=0;i<26;i++) {
					children[i] = -1;
				}
				valid = false;
			}
		}
		ArrayList<Node> trie;
		int root;
		int init() {
			Node x = new Node();
			trie.add(x);
			return trie.size()-1;
		}
		Trie(){
			trie = new ArrayList<>();
			init();
		}
		void add(int node,String s,int idx) {
			if(idx == s.length()) {
				trie.get(node).valid = true;
				return;
			}
			int c = s.charAt(idx)-'a';
			if(trie.get(node).children[c] == -1) {
				int next = init();
				trie.get(node).children[c] = next;
			}
			int child = trie.get(node).children[c];
			add(child,s,idx+1);
		}
		void add(String s) {
			add(root,s,0);
		}
		boolean search(int node,String s,int idx) {
			if(node == -1) return false;
			if(idx == s.length()) {
				return true;//있기만 하면 true를 리턴!
			}
			//찾아나선다!
			int c = s.charAt(idx)-'a';
			int child = trie.get(node).children[c];
			return search(child,s,idx+1);
		}
		boolean search(String s) {
			return search(root,s,0);
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		Trie trie = new Trie();
		while(n-->0) {
			//n개의 문자열들 trie를 완성
			trie.add(br.readLine());
		}
		int ans = 0;
		while(m-->0) {
			//trie에서 m개 문자열 search 하면됨!
			if(trie.search(br.readLine())){
				ans++;
			}
		}
		System.out.println(ans);
	}

}