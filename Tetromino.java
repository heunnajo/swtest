import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Tetromino{
    public static void main(String[] args) throws IOException{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 종이크기
        String[] xy = br.readLine().split(" ");
        int x = Integer.parseInt(xy[0]);
        int y = Integer.parseInt(xy[1]);

        int[][] numArry = new int[x][y];
        
        // 숫자
        for (int i = 0; i < x; i++) {
            String[] num = br.readLine().split(" ");
            for (int j = 0; j < y; j++) {
                numArry[i][j] = Integer.parseInt(num[j]);
            }
        }
        // 최대값
        int maxSum = 0;

        // 종이의 0,0 위치부터 테트로미노를 놓아 최대값을 구한다.
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++){
                int sum = 0;

                if (i+3 < x) {
                    sum = numArry[i][j] + numArry[i+1][j] + numArry[i+2][j] + numArry[i+3][j];
                    if (maxSum < sum) {
                        maxSum = sum;
                    }
                }
                if (j+3 < y) {
                    sum = numArry[i][j] + numArry[i][j+1] + numArry[i][j+2] + numArry[i][j+3];
                    if (maxSum < sum) {
                        maxSum = sum;
                    }
                }
               
                if (i+1 < x && j+1 < y) {
                    sum = numArry[i][j] + numArry[i+1][j] + numArry[i][j+1] + numArry[i+1][j+1];
                    if (maxSum < sum) {
                        maxSum = sum;
                    }
                }
              



                if (i+1 < x && j+2 < y) {
                    sum = numArry[i][j] + numArry[i][j+1] + numArry[i][j+2] + numArry[i+1][j+2];
                    if (maxSum < sum) {
                        maxSum = sum;
                    }
                        
                   sum = numArry[i][j] + numArry[i+1][j] + numArry[i+1][j+1] + numArry[i+1][j+2];
                    if (maxSum < sum) {
                        maxSum = sum;
                    }
                        
                 sum = numArry[i+1][j] + numArry[i+1][j+1] + numArry[i][j+2] + numArry[i+1][j+2];
                    if (maxSum < sum) {
                        maxSum = sum;
                    }
                        
                    sum = numArry[i][j] + numArry[i+1][j] + numArry[i][j+1] + numArry[i][j+2];
                    if (maxSum < sum) {
                        maxSum = sum;
                    }
                    
                   sum = numArry[i][j] + numArry[i][j+1] + numArry[i+1][j+1] + numArry[i+1][j+2];
                    if (maxSum < sum) {
                        maxSum = sum;
                    }

                   sum = numArry[i+1][j] + numArry[i][j+1] + numArry[i+1][j+1] + numArry[i][j+2];
                    if (maxSum < sum) {
                        maxSum = sum;
                    }
                      
                    sum = numArry[i][j] + numArry[i][j+1] + numArry[i+1][j+1] + numArry[i][j+2];
                    if (maxSum < sum) {
                        maxSum = sum;
                    }

                 sum = numArry[i+1][j] + numArry[i][j+1] + numArry[i+1][j+1] + numArry[i+1][j+2];
                    if (maxSum < sum) {
                        maxSum = sum;
                    }
                }







                if (i+2 < x && j+1 < y) {
                 sum = numArry[i+2][j] + numArry[i][j+1] + numArry[i+1][j+1] + numArry[i+2][j+1];
                    if (maxSum < sum) {
                        maxSum = sum;
                    }
                        
                    sum = numArry[i][j] + numArry[i+1][j] + numArry[i+2][j] + numArry[i][j+1];
                    if (maxSum < sum) {
                        maxSum = sum;
                    }
                        
                    sum = numArry[i][j] + numArry[i+1][j] + numArry[i+2][j] + numArry[i+2][j+1];
                    if (maxSum < sum) {
                        maxSum = sum;
                    }
                        
                   sum = numArry[i][j] + numArry[i][j+1] + numArry[i+1][j+1] + numArry[i+2][j+1];
                    if (maxSum < sum) {
                        maxSum = sum;
                    }

                   sum = numArry[i+1][j] + numArry[i+2][j] + numArry[i][j+1] + numArry[i+1][j+1];
                    if (maxSum < sum) {
                        maxSum = sum;
                    }
                        
                   sum = numArry[i][j] + numArry[i+1][j] + numArry[i+1][j+1] + numArry[i+2][j+1];
                    if (maxSum < sum) {
                        maxSum = sum;
                    }

                    sum = numArry[i][j] + numArry[i+1][j] + numArry[i+2][j] + numArry[i+1][j+1];
                    if (maxSum < sum) {
                        maxSum = sum;
                    }
                        
                 sum = numArry[i+1][j] + numArry[i][j+1] + numArry[i+1][j+1] + numArry[i+2][j+1];
                    if (maxSum < sum) {
                        maxSum = sum;
                    }
                }
            }
        }

        bw.write(Integer.toString(maxSum));
        bw.flush();
        bw.close();
    }
}
