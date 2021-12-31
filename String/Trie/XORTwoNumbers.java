package Trie;
import java.io.*;
import java.util.*;

public class XORTwoNumbers {
	static int n;
	static class Trie{
		static class Node{
			int[] children;
			boolean valid;
			Node() {
				children = new int[2];
				for(int i=0;i<2;i++) {
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
		void add(int node,int n,int bit) {
			if(bit == -1) {
				trie.get(node).valid = true;
				return;
			}
			int c = (n>>bit)&1;//현재 비트값 조회
			if(trie.get(node).children[c] == -1) {
				int next = init();
				trie.get(node).children[c] = next;
			}
			int child = trie.get(node).children[c];
			add(child,n,bit-1);
		}
		void add(int n) {
			add(root,n,31);
		}
		int search(int node,int n,int bit) {
			if(bit == -1) return 0;//불가능한 경우이지만 혹시나 해서 구현.
			//찾아나선다!
			//변수 2개 만들 필요 없음!
			int c = (n>>bit)&1;//현재 비트값 조회
			c = 1-c;//최댓값을 찾기 위해 찾고자 하는 값
			
			if(trie.get(node).children[c] == -1) {//찾고자 하는 값 없으면 그냥 원복
				c = 1-c;
			}
			if(trie.get(node).children[c] == -1) {//원래 값도 없으면 그냥 리턴:불가능한 경우이지만 혹시나 해서 구현.
				return 0;
			}
			int next = 0;
			if(c==1) next = (1<<bit);
			int child = trie.get(node).children[c];//찾고자하는 값 c에 대해 다음 노드를 찾아?그렇겠구먼.
			return next | search(child,n,bit-1);
		}
		int search(int n) {
			return search(root,n,31);
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		Trie trie = new Trie();
		int ans = 0;
		for(int i=0;i<n;i++) {
			int num = Integer.parseInt(st.nextToken());
			trie.add(num);//숫자를 다 넣고,N^2번 XOR 연산해야하는디!
			int temp = (trie.search(num) ^ num);//^ : XOR연산!num X num?  초반에는 그냥 0이 될 것. 자기자신과 하니까!
			if(ans < temp) ans = temp;
		}
		System.out.println(ans);
	}
}
//		for(int i=0;i<n;i++) {
//			System.out.print(num[i]+" ");
//		}
