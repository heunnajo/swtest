import java.util.*;
class Q5_Sol {
    class Page{
        int idx,basic,link;//여기서 link는 링크 점수가 아니라 외부링크수(a태그 수)를 의미한다!
        double score;
        Page(int idx,int basic,int link,double score){
            this.idx = idx;
            this.basic = basic;
            this.link = link;
            this.score = score;
        }
    }
    class Comp implements Comparator<Page>{
        public int compare(Page a,Page b){
            if(a.score == b.score){//점수가 동일하다면 인덱스 오름차순
                return a.idx - b.idx;
            } else if(a.score < b.score){
                return 1;//자리 바꿈! 내림차순!
            } else return -1;
        }
    }
    public int solution(String word, String[] pages) {
        //0.준비물 셋팅
        int wsize = word.length();
        Map<String,Integer> pageMap = new HashMap<String,Integer>();
        List<Page> pageList = new ArrayList<Page>();
        word = word.toLowerCase();
        
        for(int i=0;i<pages.length;i++){
            String s = pages[i] = pages[i].toLowerCase();
            //posL : meta 시작, posR : meta 끝, mid : url 시작
            int mid = 0,posL = 0,posR = 0;
            while(mid<=posL){
                posL = s.indexOf("<meta",posL+1);
                posR = s.indexOf(">",posL);
                mid = s.lastIndexOf("https://",posR);
            }
            posR = s.indexOf("\"",mid);//url의 마지막 위치를 저장!
            String url = s.substring(mid,posR);
            
            posL = s.indexOf("<body>",posR);//url찾았던 마지막 위치 posR부터 탐색하면 될듯!
            int basic = 0;
            for(int start = posL; ;){
                start = s.indexOf(word,start+1);//start위치에서 탐색 시작, 1씩 증가한다는 의미.
                if(start == -1) break;//검색어가 없는 경우 반복을 종료.
                if(!Character.isLetter(s.charAt(start-1)) &&
                  !Character.isLetter(s.charAt(start+wsize))){
                    basic++;
                    start+=wsize;//0123까지 검색어라면 4부터 탐색을 시작해야함.(4부터 검색어가 존재한다는 의미가 아니라 거기서부터 탐색을 다시 반복한다는 뜻!)
                }
            }
            
            int link = 0;
            for(int start = posL; ;){
                start = s.indexOf("<a href=",start+1);
                if(start == -1) break;
                link++;
            }
            pageMap.put(url,i);
            pageList.add(new Page(i,basic,link,(double)basic));//아래에서 각 페이지마다 a태그 파싱하면서 해당 외부링크를 map에서 찾아 현재페이지의 속성값을 이용해 링크점수를 더해줄 것이다.
            
        }
        
        for(int i=0;i<pages.length;i++){
            String s = pages[i];

            for(int posL=0,posR=0; ;){
                posL = s.indexOf("<a href",posL+1);
                if(posL == -1) break;
                
                posL = s.indexOf("https://",posL);
                posR = s.indexOf("\"",posL);
                String linkurl = s.substring(posL,posR);
                
                Integer index = pageMap.get(linkurl);
                if(index != null){
                    pageList.get(index).score += (double)pageList.get(i).basic / pageList.get(i).link;
                }
            }
            
        }
        pageList.sort(new Comp());
        return pageList.get(0).idx;
        
    }
}