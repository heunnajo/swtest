//Q1. 로그 식별
import java.io.*;
import java.util.*;

/*
로그 조건만족 : 0 로그 조건 불만족 : 1
0 team_name : teamOne application_name : line error_level : serious message : Fuck//로그 수집 조건 만족
1 team_name : team123 application_name : line error_level : serious message : Fuck//숫자 포함
1 team_name : teamOne application_name : line error_level : serious!!! message : Fuck//특수문자포함
1    team_name : teamOne application_name : line error_level : serious message : Fuck//앞에 공백문자
1 team_name : teamOne application_name : line error_level : serious message : Fuck       //뒤에 공백문자
1 team_name: teamOne application_name : line error_level : serious message : Fuck//" : "형태 불만족ㄱ
1 team_name : teamOne application_name : line error_level : ThisIsVeryVerySeriousSoThatYouNeedToFixItRrightAwayNoMatterWhatYouAreDoingNowYouNeedToComeToOfficeRIGHTNOW message : Fuck//로그 길이 100자 이상.
*/

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
