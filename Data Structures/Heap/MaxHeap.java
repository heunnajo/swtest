import java.util.*;
import java.io.*;
public class MaxHeap {
    public static int[] heap = new int[100001];
    public static int hn;
    public static void push(int x) {
        heap[++hn] = x;
        for (int i=hn; i>1; i/=2) {
            if (heap[i/2] < heap[i]) {
                int temp = heap[i/2];
                heap[i/2] = heap[i];
                heap[i] = temp;
            } else {
                break;
            }
        }
    }
    public static int pop() {
        int ans = heap[1];
        heap[1] = heap[hn];
        heap[hn--] = 0;
        for (int i=1; i*2 <= hn;) {
            if (heap[i] > heap[i*2] && heap[i] > heap[i*2+1]) {
                break;
            } else if (heap[i*2] > heap[i*2+1]) {
                int temp = heap[i];
                heap[i] = heap[i*2];
                heap[i*2] = temp;
                i = i*2;
            } else {
                int temp = heap[i];
                heap[i] = heap[i*2+1];
                heap[i*2+1] = temp;
                i = i*2+1;
            }
        }
        return ans;
    }
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        while (n-- > 0) {
            int x = Integer.parseInt(br.readLine());
            if (x == 0) {
                if (hn == 0) {
                    System.out.println(0);
                } else {
                    System.out.println(pop());
                }
            } else {
                push(x);
            }
        }
    }
}