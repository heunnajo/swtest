//#133502 햄버거 만들기
//대전제 : 스택에는 유효한 순서의 재료 인덱스만 넣는다.
import java.util.Stack;
class Solution {
    public int solution(int[] ingredient) {
        int answer = 0;
        int n = ingredient.length;
        Stack<Integer> st = new Stack<>();
        
        for(int i=0;i<n;i++){
            int cur = ingredient[i];
            //햄버거의 시작 or 끝
            if(cur == 1){
                
                //따라서 이전의 원소가 3이라면 이것은 123을 만족하고, 현재 다시 1을 만났으므로 햄버거를 의미함.
                if(st.size()>=3 && ingredient[st.peek()] == 3){
                    st.pop(); st.pop(); st.pop();
                    answer++;
                    continue;
                }
                //System.out.println("i: "+i);//push되는 인덱스를 추적! 1 2 3 4 가 되야함!
                st.push(i);
            } else{
                if(st.empty()) continue;//1을 만나기 전까지 절대 햄버거 될 수 없음.
                int prev = ingredient[st.peek()];
                if(prev+1 == cur){
                    st.push(i);
                } else{
                    st.clear();
                }
            }
        }
        return answer;
    }
}