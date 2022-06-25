//구현
class Solution {
    static int n,k;//n:카드 갯수 k:섞는 횟수
    public int[] solution(int[] cards, String[] shuffles) {
        n = cards.length;
        k = shuffles.length;

        for(int i=0;i<k;i++){
            if(shuffles[i].equals("Cut")) cards = Cut(cards);
            else cards = Riffle(cards);
            //print(cards);
        }

        return cards;
    }
    int[] Cut(int[] arr){
        int[] P1 = new int[n/2];
        int[] P2 = new int[n/2];

        for(int i=0;i<n/2;i++) P1[i] = arr[i];//i=0,1,2
        for(int i=n/2;i<n;i++) P2[i-n/2] = arr[i];//i=3,4,5=>3-3,4-3,5-3

        //print(P1);
        //print(P2);
        for(int i=0;i<n/2;i++){
            arr[i] = P2[i];
        }
        for(int i=n/2;i<n;i++){
            arr[i] = P1[i-n/2];
        }
        return arr;
    }
    void print(int[] arr){
        System.out.println();
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
    int[] Riffle(int[] arr){
        int[] P1 = new int[n/2];
        int[] P2 = new int[n/2];

        for(int i=0;i<n/2;i++) P1[i] = arr[i];
        for(int i=n/2;i<n;i++) P2[i-n/2] = arr[i];

        int idx = (n/2) -1;

        for(int i=n-1;i>=0;i--){
            if(i % 2 != 0){
                arr[i] = P1[idx];
            } else{
                arr[i] = P2[idx];
                idx--;
            }

        }
        return arr;
    }
}