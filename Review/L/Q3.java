import java.util.*;
class Q3 {
    public int[] solution(int num_teams, String[] remote_tasks, String[] office_tasks, String[] employees) {

        int len = remote_tasks.length;
        HashMap<String,Boolean> remoteJob = new HashMap<>();

        for(int i=0;i<len;i++){
            remoteJob.put(remote_tasks[i],true);
        }
        //System.out.println(map.size());
        int n = employees.length;
        HashMap<Integer,LinkedList<Integer>> map2 = new HashMap<>();//<팀번호,팀원 리스트>
        for(int i=0;i<n;i++){//n명의 사원
            String s = employees[i];
            String[] st = employees[i].split(" ");//0:팀번호 1번~ 담당 업무
            int teamNum = Integer.parseInt(st[0]);
            if(!map2.containsKey(teamNum)){
                LinkedList<Integer> list = new LinkedList<>();
                list.add(i+1);
                map2.put(teamNum,list);
            } else {
                LinkedList<Integer> list = map2.get(teamNum);
                list.add(i+1);
                //System.out.println("기존의 리스트에 추가한 후 크기: "+list.size());
                map2.put(teamNum,list);
            }
        }
        //재택근무하는 사람들 사원번호를 저장
        //LinkedList<Integer> tmp = new LinkedList<>();
        // HashSet<Integer> tmp = new HashSet<>();
        HashMap<Integer,Boolean> tmp = new HashMap<>();
        for(int i=0;i<employees.length;i++){
            String[] s = employees[i].split(" ");
            boolean flag = true;
            for(int j=1;j<s.length;j++){//i+1번 사원의 담당 업무가 모두 재택 근무인지 확인
                if(!remoteJob.containsKey(s[j])){
                    flag = false;
                    break;
                }
                if(flag){
                    tmp.put(i+1,true);
                }
            }
        }
        //팀원들 모두가 재택인지 확인, 가장 빠른 팀원 제외, 삭제
        for(int t:map2.keySet()){
            LinkedList<Integer> list = map2.get(t);
            boolean allRemote = true;
            for(int i:list){
                if(!tmp.containsKey(i)){
                    allRemote = false;
                    break;
                }
            }
            if(allRemote){
                tmp.remove(list.get(0));
            }

        }
        int[] answer = new int[tmp.size()];
        int idx=0;
        for(int i:tmp.keySet()){
            answer[idx++] = i;
        }
        return answer;
    }
}