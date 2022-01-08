import java.util.*;
class MockTest {
    public int[] solution(int[] answers) {
        
        int[] one = {1,2,3,4,5};
        int[] two = {2,1,2,3,2,4,2,5};
        int[] three = {3,3,1,1,2,2,4,4,5,5};
        
        //1,2,3의 정답 맞춘 갯수 카운팅! : hit배열을 완성!
        int n = answers.length;
        int[] hit = new int[3];//0:1번의 정답 맞춘 갯수, 1:2의 갯수, 2:3의 갯수
        for(int i=0;i<n;i++){
            if(answers[i] == one[i%5]) hit[0]++;
            if(answers[i] == two[i%8]) hit[1]++;
            if(answers[i] == three[i%10]) hit[2]++;
        }
        for(int i=0;i<3;i++) System.out.print(hit[i]+" ");
        
        //hit을 통해 최고점자와 최고점을 판단
        int maxPeople = -1; int maxCount = -1;
        for(int i=0;i<3;i++){
            if(maxCount<hit[i]){
                maxPeople = i;
                maxCount = hit[i];
            }
        }
        
        //최고점자가 여러명일 수 있끼 때문에 다시한번 처리
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<3;i++){
            if(hit[i]==maxCount){
                list.add(i+1);
            }
        }
        
        //정답 배열 answer에 데이터 저장!
        int[] answer = new int[list.size()];//최대 3인이니까 크기 3으로 생성하는 게 맞을까?아닐
        for(int i=0;i<list.size();i++){
            answer[i] = list.get(i);
        }
        return answer;
    }
}