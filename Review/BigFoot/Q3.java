//그래프 탐색  : Union-Find 방법으로 풀어봄..
import java.util.*;
class Solution {
    static int[] parent;
    static int ans;
    public int[] solution(int m, String[] people, String[] pairs) {
        int n = people.length;//간선 갯수
        int k = pairs.length;
        parent = new int[m+1];

        int[] answer = new int[k];


        for(int i=1;i<=m;i++) parent[i] = i;

        HashMap<String,LinkedList<Integer>> map = new HashMap<>();
        for(int i=0;i<n;i++){
            //문자열 파싱, union 연산
            String[] str = people[i].split(" ");
            String key = str[0];
            LinkedList<Integer> list;
            HashSet<Integer> tmp = new HashSet<>();

            for(int j=1;j<str.length-1;j++){
                int u = Integer.parseInt(str[j]);
                int v = Integer.parseInt(str[j+1]);
                union(u,v);
                tmp.add(u);
            }
            tmp.add(Integer.parseInt(str[str.length-1]));
            list = new LinkedList<>(tmp);
            map.put(key,list);
        }

        for(int i=0;i<k;i++){
            String[] str = pairs[i].split(" ");
            String u = str[0]; String v = str[1];
            LinkedList<Integer> listU = map.get(u);
            LinkedList<Integer> listV = map.get(v);

            if(parent[listU.get(0)] != parent[listV.get(0)]) answer[i] = -1;
            else{
                answer[i] = ans;
            }

        }
        return answer;
    }
    void union(int a,int b){
        int x = find(a,0); int y = find(b,0);
        parent[y] = x;
    }
    int find(int x,int cnt){
        if(parent[x] == x) {
            ans = cnt;
            return x;
        }
        else return parent[x] = find(parent[x],cnt+1);
    }
}

//간선 정보 확인
        // for(String key:map.keySet()){
        //     System.out.print("key: "+key);
        //     for(int val:map.get(key)) {
        //         System.out.print(val+" ");
        //     }
        //     System.out.println();
        // }