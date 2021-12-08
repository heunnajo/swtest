import java.util.Scanner;
 
public class GetDivisor {
 
    static int N,K;
    static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args)  {
        N = sc.nextInt();
        K = sc.nextInt();
        
        for (int i = 1; i <= N; i++) {
            if(N % i == 0) K--;
                
            if(K == 0) {
                System.out.println(i);
                break;
            }
        }
        if(K != 0) 
            System.out.println(0); 
    }
}
