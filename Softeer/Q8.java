//비밀 메뉴
import java.util.*;
import java.io.*;
public class SecretMenu {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        String[] input = br.readLine().split(" ");
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<input.length;i++) {
        	sb.append(input[i]);
        }
        String secretMenu = sb.toString();
        
        input = br.readLine().split(" ");
        sb = new StringBuilder();
        for(int i=0;i<input.length;i++) {
        	sb.append(input[i]);
        }
        String userInput = sb.toString();
        
//        System.out.println("secretMenu: "+secretMenu);
//        System.out.println("userInput: "+userInput);
        
        if(userInput.contains(secretMenu)) System.out.println("secret");
        else System.out.println("normal");
	}

}
