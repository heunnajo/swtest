//Q1. 로그 식별
import java.io.*;
import java.util.*;

public class Q1_2nd_Solved {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		String pattern = "^team_name : [a-zA-Z]+ application_name : [a-zA-Z]+ error_level : [a-zA-Z]+ message : [a-zA-Z]+$";//+$ vs *$
		int ans = 0;
		if(!str.matches(pattern) || str.length()>=100) ans++;
		System.out.println(ans);
	}

}
