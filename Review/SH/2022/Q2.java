package Review.SH.2022;

import java.util.*;
class Solution {
    public int solution(String[] students, int n, int m, int k) {
        int answer = 0;
        HashMap<String,ArrayList<String>> stuInfo = new HashMap<>();
        HashMap<String,ArrayList<String>> clubInfo = new HashMap<>();
        HashMap<String,String> deptInfo = new HashMap<>();
        int num = students.length;
        for(int i=0;i<num;i++){
            String[] input = students[i].split(" ");
            String id = input[0];
            String dept = input[1];
            String club = input[2];

            //stuInfo
            ArrayList<String> list = new ArrayList<>();
            list.add(club);
            if(!stuInfo.containsKey(id)){
                stuInfo.put(id,list);
            }else{
                list = stuInfo.get(id);
                list.add(club);
                stuInfo.put(id,list);
            }

            //clubInfo
            list = new ArrayList<>();
            list.add(id);
            if(!clubInfo.containsKey(club)){
                clubInfo.put(club,list);
            } else{
                list = stuInfo.get(club);
                list.add(id);
                stuInfo.put(club,list);
            }

            //deptInfo
            deptInfo.put(id,dept);
        }
        //조건1 : 클럽을 순회!
        for(String club:clubInfo.keySet()){
            int total = clubInfo.get(club).size();
            int duplicated = 0;

            for(String stu:clubInfo.get(club)){
                if(stuInfo.get(stu).size()>=2) duplicated++;
            }
            total -= duplicated;
            //조건2 : 학번 갯수
            if(total >= n){
                HashSet<String> tmp = new HashSet<>();

                for(String stu:clubInfo.get(club)){//현재 클럽 club의  동아리원들 순회!
                    tmp.add(stu);
                }
                int yearCnt = tmp.size();
                //조건3
                if(yearCnt >= m){
                    tmp = new HashSet<>();

                    for(String stu:clubInfo.get(club)){//현재 클럽 club의  동아리원들 순회!
                        tmp.add(deptInfo.get(stu));
                    }
                    int deptCnt = tmp.size();
                    if(deptCnt >= k) {
                        answer++;
                    }
                } 
            } 
        }

        return answer;
    }
}
//stuInfo 확인
        // for(String key:stuInfo.keySet()){
        //     System.out.print(key+":");
        //     for(String club:stuInfo.get(key)){
        //         System.out.print(club+" ");
        //     }
        //     System.out.println();
        // }
