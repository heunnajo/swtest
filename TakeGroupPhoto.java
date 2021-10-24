class TakeGroupPhoto {
    go(int index,int prev){
        if(index==9){
            ans++;
            return;
        }
        for(int i=1;i<=8;i++){
            if(used[i])continue;
            //갈등 정보 확인, 갈등 정보에 따라 현재 index번째 프렌즈를 선택!
            if(){..}
            used[i] = true;
            selected[index] = i;
            go(index+1,i);
            used[i] = false;
            selected[index] = -1;//배열값을 덮어쓰는 거라 굳이 안해줘도되지만 원복을 명시적으로 표현하기 위해.
        }
    }
    public int solution(int n, String[] data) {
        int answer = 0;
        return answer;
    }
}