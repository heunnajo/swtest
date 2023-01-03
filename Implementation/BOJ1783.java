import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//병든 나이트
public class BOJ1783 {
    
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    private static int height;
    private static int width;
    
    public static void main(String args[]) throws IOException {
        String[] input = br.readLine().split(" ");
        height = Integer.parseInt(input[0]);
        width = Integer.parseInt(input[1]);

        int result = 0;
        
        if (height == 1) {
            result = 1;
        } else if (height == 2) {
            result = Math.min(4, (width + 1) / 2);
        } else {
            if (width >= 7) {
                result = width - 2;
            } else {
                result = Math.min(4, width);
            }
        }
        
        System.out.println(result);
        br.close();
    }

}