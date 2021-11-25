class WordsTransition {
    static int N,Ans;//글자의 길이
    static String Begin,Target,Words[];
    static boolean[] Visited;
    public int solution(String begin, String target, String[] words) {
        //전역 변수 초기화
        Begin = begin;
        Target = target;
        Words = words;
        N = begin.length();
        Visited = new boolean[words.length];
        Ans = -1;
        
        dfs(begin,0);//각 깊이별 단어를 매개변수로 넘겨서 그 단어 기준으로 만들어나간다.
        if(Ans == -1) Ans = 0;
        return Ans;
    }
    static void dfs(String current,int cnt){
        if(current.equals(Target)){
            if(Ans == -1 || Ans>cnt){
                Ans = cnt;
            }
            return;
        }
        //현재 선택!
        for(int i=0;i<Words.length;i++){
            if(Visited[i]) continue;
            int k=0;
            for(int j=0;j<current.length();j++){
                if(current.charAt(j) == Words[i].charAt(j)){
                    k++;
                }
            }
            if(k == current.length()-1){
                Visited[i] = true;
                dfs(Words[i],cnt+1);
                Visited[i] = false;
            }
        }
    }
}