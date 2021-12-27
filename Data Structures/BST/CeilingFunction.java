import java.util.*;
import java.io.*;
class Node {
    public int val;
    public Node left;
    public Node right;
    Node() {
        val = 0;
        left = null;
        right = null;
    }
}
public class CeilingFunction {
    static String preorder(Node root) {
        String ans = "";
        ans += "V";
        if (root.left != null) {
            ans += "L";
            ans += preorder(root.left);
            ans += "l";
        }
        if (root.right != null) {
            ans += "R";
            ans += preorder(root.right);
            ans += "r";
        }
        ans += "v";
        return ans;
    }
    static void remove(Node root) {
        if (root.left != null) {
            remove(root.left);
        }
        if (root.right != null) {
            remove(root.right);
        }
        root = null;
    }
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());//5
		int m = Integer.parseInt(st.nextToken());//3
        HashSet<String> s = new HashSet<String>();
        for (int k=0; k<n; k++) {
            int[] a = new int[m];
            st = new StringTokenizer(br.readLine());//n번 입력받는다!
            for (int i=0; i<m; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }
            Node root = new Node();
            root.val = a[0];
            for (int i=1; i<m; i++) {
                Node cur = root;
                while (true) {
                    if (cur.val > a[i]) {
                        if (cur.left == null) {
                            cur.left = new Node();
                            cur.left.val = a[i];
                            break;
                        } else {
                            cur = cur.left;
                        }
                    } else if (cur.val < a[i]) {
                        if (cur.right == null) {
                            cur.right = new Node();
                            cur.right.val = a[i];
                            break;
                        } else {
                            cur = cur.right;
                        }
                    } else {
                        break;
                    }
                }
            }
            s.add(preorder(root));
            remove(root);
        }
        System.out.println(s.size());
    }
}