import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//단축키 지정
public class BOJ1283_Solution {
    public static boolean[] isSelected = new boolean[26];
    public static boolean isShortcut;
    public static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        sb = new StringBuilder();

        for(int i = 0 ; i < n; ++i) {
            StringBuilder s = new StringBuilder(br.readLine());
            isShortcut = false;

            s = useOption1(s);
            s = useOption2(s);
            sb.append(s).append("\n");
        }
        System.out.println(sb);
    }

    public static StringBuilder useOption1(StringBuilder s) {
        if(isShortcut) return s;

        for(int i = 0 ; i < s.length(); ++i) {
            if(i == 0 || !Character.isAlphabetic(s.charAt(i-1))) {
                char ch = Character.toLowerCase(s.charAt(i));
                if(!isSelected[ch - 'a']) {
                    isShortcut = true;
                    isSelected[ch - 'a'] = true;
                    String replacedString = "[" + s.charAt(i) + "]";
                    s.replace(i, i+1, replacedString);
                    return s;
                }
            }
        }
        return s;
    }

    public static StringBuilder useOption2(StringBuilder s) {
        if(isShortcut) return s;

        for(int i = 0 ; i < s.length(); ++i) {
            char ch = Character.toLowerCase(s.charAt(i));
            if(Character.isAlphabetic(s.charAt(i)) && !isSelected[ch - 'a']) {
                isShortcut = true;
                isSelected[ch - 'a'] = true;
                String replacedString = "[" + s.charAt(i) + "]";
                s.replace(i, i+1, replacedString);
                return s;
            }
        }
        return s;
    }


}
