import java.util.*;
class TripRoute {
    static int N;
    static boolean[] visited;
    static String[][] Tickets;
    static ArrayList<String> answers;
    public String[] solution(String[][] tickets) {
        N = tickets.length;
        visited = new boolean[N];
        answers = new ArrayList<>();
        Tickets = tickets;
        
        go("ICN","ICN",0);
        Collections.sort(answers);
        String[] ans = answers.get(0).split(" ");
        return ans;
    }
    static void go(String from,String route,int cnt){//현재까지하는 선택들을 저장
        if(cnt==N){
            answers.add(route);
            return;
        }
        for(int i=0;i<N;i++){//총 N개의 항공권 정보 중에서 선택
            if(visited[i]) continue;
            if(Tickets[i][0].equals(from)){
                visited[i] = true;
                go(Tickets[i][1],route+" "+Tickets[i][1],cnt+1);
                visited[i] = false;
            }
        }
    }
}