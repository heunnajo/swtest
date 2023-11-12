//Q150367 표현 가능한 이진트리

class Solution {
    
    int possible;
    
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        
        for (int k = 0; k < answer.length; k++) {
            String binaryNum = Long.toBinaryString(numbers[k]); // 숫자를 바이너리 이진 문자열로 변경 toBinaryString()
            
            int fullTreeLen = 0;
            int h = 1; // 포화 이진트리를 만들기 위한 높이
            
            while (fullTreeLen < binaryNum.length()) {
                fullTreeLen = (int) Math.pow(2, h++) - 1;
            }
            
            boolean[] isOne = new boolean[fullTreeLen]; // 포화 이진 트리를 생성하기 위한 배열
            
            // 참조#1
            int notDummyIdx = isOne.length - binaryNum.length(); // 더미 문자열이 아닌 인덱스
            for (int i = 0; i < binaryNum.length(); i++) {
                isOne[notDummyIdx++] = binaryNum.charAt(i) == '1'; // 더미 문자열이 아닌 인덱스부터 대응되는 인덱스의 문자열이 1인지 판단 
            }
            
            possible = 1; // 정답 초기화
            dfs(0, isOne.length - 1, false, isOne); 
            answer[k] = possible;
            
        }
        return answer;
    }
    
    void dfs(int start, int end, boolean isParentZero, boolean[] isOne) {
        if (possible == 0) return;
        
        int mid = (start + end) / 2; // 포화 이진 트리에서 부모 노드는 자식 노드의 위치의 중간

        if(isParentZero && isOne[mid]) { // 만약 부모가 zero인데, 해당 자식 노드가 1이라면 유효성 X
            possible = 0;
            return; // 종료
        }

        if(start != end) { // 참조#2
            dfs(start, mid - 1, !isOne[mid], isOne); // !fullTree[mid] -> 만약 1이었다면 true 이므로 false
            dfs(mid + 1, end, !isOne[mid], isOne); // 만약 0이었다면 false -> true 즉 부모가 0이므로 자식 노트가 1이 된다면 유효성 X
        }

    }
}