//Q1.중복된 과목은 높은 성적으로 선택, 나머지는 삭제, 순서는 기존 input 유지
import java.util.*;
class Solution {
    public String[] solution(String[] grades) {
        //성적을 그 문자열 자체로 13개를 ㄴ내림차순 정렬?ㄴㄴ..배열값으로 인덱스 도출해야할텐데.그러면 Map으로 저장해서 성적 비교?

        //전체 단계
        //기본적을 n개의 행에 대해서 각 인덱스마다 살릴 행을 표시해야함
        //boolean 타입의 크기 n인 배열 check 생성, 살릴 행 : true로 마킹, cnt++
        //cnt 크기의 정답 String 배열 ans 생성
        //check[i] = true인 행에 대해 ans[i] = grades[i]

        //문제 조건 로직 : 중복되는 과목에 대해 최고점수 과목을 저장:Map<String,String> = 과목명, 성적
        //1.중복된 과목인지 확인 : map에 있는지 확인
        //2.중복됐다면 현재 입력의 성적과 map의 성적 비교 : 더 높은 입력을 선택=> 중복되는 과목에 대해 더 높은 입력은 어떻게 저장?기존의 인덱스 false, 현재 인덱스 true로 해야할텐데!
        //그냥 value에 grades에서의 인덱스를 저장하면 되지. 그러면 기존의 것 false, 현재의 것 true로 최고 성적 과목 도출 가능.
        //그리고 최종 정답에 저장할 때는 map에는 <중복 과목,최고성적의 인덱스> 이렇게 저장되있을 것.
        //grades 순회하면서 map에 있으면 해당 인덱스의 성적을 저장 : k개 중복되면 k개 동일한 작업 반복?
        //성적 A,B,C인지 확인
        //일단 해보자=>Map에 어떤 데이터를 저장할 것인가
        //모든 과목 하나씩 다 저장하고, 중복되는 데이터만 높은 성적으로 저장하면 됨.
        int n = grades.length;

        HashMap<String,Integer> alpha = new HashMap<>();
        alpha.put("A+",0);alpha.put("A0",1);alpha.put("A-",2);alpha.put("B+",3);
        alpha.put("B0",4);alpha.put("B-",5);alpha.put("C+",6);alpha.put("C0",7);
        alpha.put("C-",8);alpha.put("D+",9);alpha.put("D0",10);alpha.put("D-",11);alpha.put("F",12);

        HashMap<String,Integer> map = new HashMap<>();

        for(int i=0;i<n;i++){
            String cur = grades[i];
            String[] input = cur.split(" ");//input[0]:과목명, input[1]:성적
            if(!map.containsKey(input[0])){
                map.put(input[i],i);
            } else{//높은 점수 찾아서 갱신해야함!
                int idx = map.get(input[0]);//기존의,과목명에 해당하는 인덱스 조회
                String[] tmp = grades[idx].split(" ");
                System.out.println(tmp[0]+","+tmp[1]);
                int prevGrade = alpha.get(tmp[1]);
                int curGrade = alpha.get(input[1]);
                if(prevGrade > curGrade){//더 낮은 등급일 수록 높은 성적
                    map.put(input[i],i);
                }
            }
        }
        //map에는 각 과목별로 높은 성적의 인덱스가 저장되어 있을 것.
        String[] answer = new String[map.size()];
        int idx = 0;
        for(int i=0;i<n;i++){//한번 조회해온 key에 대해서는 value =-1로 업데이트해줌으로써 중복 제거
            String cur = grades[i];
            String[] input = cur.split(" ");
            if(map.get(input[0]) != -1){
                int k = map.get(input[0]);
                answer[idx++] = input[0]+" "+grades[k].split(" ")[1];
                map.put(input[0],-1);
            }

        }

        return answer;
    }
}
//동일한 과목 여러번 수강 : 가장 좋은 것만 인정, 나머지 삭제
//가장 좋은 것 : A B C 중 알파벳 오름차순
//동일한 알파벳 중 + 0 - : 어떻게 판단?