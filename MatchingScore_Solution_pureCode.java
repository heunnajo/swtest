import java.util.*;
class MatchingScore_Solution_pureCode {
    class Page{
        int idx;
        int basic,link;
        double score;
        Page(int idx,int basic,int link,double score){
            this.idx = idx;
            this.basic = basic;
            this.link = link;
            this.score = score;
        }
    }
    //정렬 기준 재정의. 매칭 점수 큰 것 먼저, 
    class Comp implements Comparator<Page> {
        public int compare(Page a,Page b){
            if(a.score == b.score){
                return a.idx - b.idx;
            } else if(a.score < b.score){
                return 1;
            } else return -1;
        } 
    };
    public int solution(String word, String[] pages) {
        int wsize = word.length();
        Map<String, Integer> pageMap = new HashMap<String, Integer>();
        List<Page> pageList = new ArrayList<Page>();
        
        word = word.toLowerCase();
        for(int i=0;i<pages.length;i++){
            String s = pages[i] = pages[i].toLowerCase();
            int mid = 0, posL = 0, posR = 0;//posL:meta태그 시작, posR:meta태그 끝
            while(mid<=posL){//html 문자열에서 indexOf를 이용하여 <meta태그의 위치를 찾아서 반환한다!
                posL = s.indexOf("<meta",posL+1);//2번째 파라미터는 검색 시작 위치.1씩 증가.
                posR = s.indexOf(">",posL);
                mid = s.lastIndexOf("https://",posR);
            }
            posR = s.indexOf("\"",mid);//url마지막을 posR에 저장
            String url = s.substring(mid,posR);
            
            posL = s.indexOf("<body>",posR);
            int basic = 0;
            for(int start = posL; ; ){
                start = s.indexOf(word,start+1);//찾으면 0 이상의 인덱스 값 반환.
                if(start == -1) break;//?왜 -1이 돼지?ㅇㅂㅇ...
                if(!Character.isLetter(s.charAt(start-1)) && !Character.isLetter(s.charAt(start+wsize))) {
                    basic++;
                    start += wsize;//다음 찾을 시작 위치는 검색어 길이만큼 증가시켜주면 됨.
                }
            }

            int link = 0;
            for(int start = posL; ;){
                start = s.indexOf("<a href",start +1);
                if(start == -1) break;
                link++;//그렇지 않은 경우는, a태그 찾은 것이므로 1증가.
            }

            pageMap.put(url,i);
            pageList.add(new Page(i,basic,link,(double)basic));
        }

        for(int i=0;i<pages.length;i++){
            String s = pages[i];
            for(int posL = 0,posR = 0; ;){
                posL = s.indexOf("<a href",posR);
                if(posL == -1) break;
                
                posL = s.indexOf("https://",posL);
                posR = s.indexOf("\"",posL);//끝은  큰따옴표로 끝난다.
                String linkurl = s.substring(posL,posR);
                                 
                Integer value = pageMap.get(linkurl);
                if(value != null){
                    pageList.get(value).score += (double)pageList.get(i).basic/pageList.get(i).link;
                }
            }
        }
        pageList.sort(new Comp());
        return pageList.get(0).idx;
    }
}