//트리 순회
import java.util.*;
import java.io.*;
public class BOJ1991 {
	static StringBuilder sb;
	static Node[] tree;
	static class Node{
		int l,r;
		Node(int l,int r){
			this.l = l;
			this.r = r;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		//그래프 완성
		tree = new Node[26];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int x = st.nextToken().charAt(0)-'A';
			int y = st.nextToken().charAt(0);
			int z = st.nextToken().charAt(0);
			
			int left,right;
			if(y != '.') {
				//tree[x].l = y-'A';
				left = y-'A';
			} else {
				//tree[x].l = -1;
				left = -1;
			}
			if(z != '.') {
				//tree[x].r = z-'A';
				right = z-'A';
			} else {
				//tree[x].r = -1;
				right = -1;
			}
			tree[x] = new Node(left,right);
		}
		
		sb = new StringBuilder();
		preorder(0); sb.append("\n");
		inorder(0); sb.append("\n");
		postorder(0); sb.append("\n");
		
		System.out.print(sb.toString());
	}
	static void preorder(int node) {
		if(node == -1) return;
		sb.append((char)(node+'A'));
		preorder(tree[node].l);
		preorder(tree[node].r);
	}
	static void inorder(int node) {
		if(node == -1) return;
		inorder(tree[node].l);
		sb.append((char)(node+'A'));
		inorder(tree[node].r);
	}
	static void postorder(int node) {
		if(node == -1) return;
		postorder(tree[node].l);
		postorder(tree[node].r);
		sb.append((char)(node+'A'));
	}

}
