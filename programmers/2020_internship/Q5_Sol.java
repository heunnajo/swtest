//5. 동굴 탐험
import java.util.*;
class Solution {
int[] before;
    int[] savePoint;
    ArrayList<ArrayList<Integer>> map;
    boolean[] visited;
 
    public boolean solution(int n, int[][] path, int[][] order) {
        map = new ArrayList<ArrayList<Integer>>();
        before = new int[n];
        savePoint = new int[n];
        visited = new boolean[n];
 
        for (int i = 0; i < n; i++) { //
            map.add(new ArrayList<Integer>());
        }
 
        for (int i = 0; i < path.length; i++) { // 그래프 생성
            map.get(path[i][0]).add(path[i][1]);
            map.get(path[i][1]).add(path[i][0]);
        }
 
        for (int i = 0; i < order.length; i++) { // 탐험 순서 저장
            before[order[i][1]] = order[i][0];
        }
        
        if(before[0] != 0) { //0이 먼저 들려야하는 정점이 있다면 false
            return false;
        }
        
        visited[0] = true;
        Queue<Integer> q = new LinkedList<>();
        for(int i : map.get(0)) {
            q.add(i);
        }
        while (!q.isEmpty()) {
            int e = q.remove();
 
            if (visited[e]) {
                continue;
            }
 
            if (!visited[before[e]]) {
                savePoint[before[e]] = e;
                continue;
            }
 
            visited[e] = true;
            for (int i : map.get(e)) {
                q.add(i);
            }
 
            q.add(savePoint[e]);
        }
 
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                return false;
            }
        }
 
        return true;
    }
}