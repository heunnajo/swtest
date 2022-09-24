class Solution {
    static class Item{
        int size, element;

        Item(int size,int element){
            this.size = size;
            this.element = element;
        }
    }
    static Item[] arr;
    public int solution(int[][] queries) {
        int ans = 0;

        int n = queries.length;

        int cur;
        int num;
        int cnt;
        int size;
        int element;

        //초기화
        arr = new Item[1001];
        for(int i=0;i<n;i++){
            cur = queries[i][0];//배열 번호에 따라 해당 배열 크기(0), 원소 갯수(0) 초기화
            arr[cur] = new Item(0,0);
        }

        for(int i=0;i<n;i++){
            num = queries[i][0];
            cnt = queries[i][1];
            size = arr[num].size;
            element = arr[num].element;

            if(element + cnt > size){//복사가 발생하는 경우!
                //x 찾아야함.
                int x = 1;
                while(x < cnt){
                    x = 2*x;
                }
                //System.out.println("x: "+x);//16
                ans += arr[num].element;//기존 원소 갯수만큼 복사, 복사 횟수 증가 0
                arr[num].size = x;//num 배열 크기 갱신 : 16
                arr[num].element += cnt;//추가하는 원소 갯수만큼 해당 배열 원소 갯수 증가 10. 복사 발생 x
            } else{//cnt <= size : 
                arr[num].element += cnt;
            }
        }
        return ans;
    }
}