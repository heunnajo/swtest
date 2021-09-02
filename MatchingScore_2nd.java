import java.util.*;
class MatchingScore_2nd {
    class Page {
        int idx,basic,link;//link는 링크 점수가 아니라 외부 링크(a태그) 갯수
        double score;
        Page(int idx,int basic,int link,double score){
            this.idx = idx;
            this.basic = basic;
            this.link = link;
            this.score = score;
        }
    }
    class Comp implements Comparator<Page> {
        int compare(Page a,Page b){//리턴값이 양수일 때 자리바꿈이 일어난다!
            if(a.score == b.score){
                return a.idx - b.idx;//점수가 동일하다면 인덱스가 작은 것부터 오름차순
            } else if(a.score < b.score){//점수는 높은 순서로 내림차순!
                //return b.score - a.score;
                return 1;
            } else return -1;
        }
    }
    public int solution(String word, String[] pages) {
        //0.준비
        int wsize = word.length();
        Map<String,Integer> pageMap = new HashMap<String,Integer>();
        List<Page> pageList = new ArrayList<Page>();
        word.toLowerCase();
        //pages 배열을 순회하면서 각 페이지의 HTML 문자열을 파싱한다
        for(int i=0;i<pages.length;i++){
            String s = pages[i] = pages[i].toLowerCase();//굳이 pages[i] 없어도 될 것 같은데.
            //meta태그를 찾아서 url먼저 추출한다.
            int mid = 0,posL = 0,posR = 0;
            while(mid<=posL){
                posL = s.indexOf("<meta",posL+1);//0부터 시작해서 meta태그 찾는다.
                posR = s.indexOf(">",posL);//posL찾은 곳부터 검색 시작!
                mid = s.lastIndexOf("https://",posR);//posR에서부터 거꾸로 찾는다!
            }
            
            // posL = s.indexOf("<meta",0);//0부터 시작해서 meta태그 찾는다.
            // posR = s.indexOf(">",posL);//posL찾은 곳부터 검색 시작!
            // mid = s.lastIndexOf("https://",posR);//posR에서부터 거꾸로 찾는다!
            posR = s.indexOf("\"",mid);//url마지막을 posR에 저장
            String url = s.substring(mid,mid+wsize);
            
            //1.기본점수 : body 태그 찾아서 검색어 갯수 카운팅.
            //조건:검색어 1글자전, 1글자후는 알파벳이면 안된다! : Character.isLetter() 리턴값이 false여야함.
            //meta태그 종료위치에서 부터 찾는다.
            posL = s.indexOf("<body",posR);//찾은 다음 검색어 위치 찾아서 조건 검사해야한다.
            int basic = 0;
            for(int start = posL; ;){//검색어를 동일한지 비교하며 어떻게 찾을까?
                //검색어 위치 찾을 때 만약에 검색어 위치가 없으며 -1리턴하고 검색반복 종료
                start = s.indexOf(word,start+1);
                if(start == -1) break;
                //start = 검색어의 위치!?
                if(!Character.isLetter(s.charAt(start-1)) && !Character.isLetter(s.charAt(start+wsize))) {
                    basic++;
                    start += wsize;
                }
            }
            
            //2.외부 링크 수:a태그 갯수만 세주면 됨.a태그를 찾아서 갯수 카운팅.
            int link = 0;
            for(int start = posL; ;){
                start = s.indexOf("<a href",start+1);//body태그 시작 위치에서 검색 시작.
                if(start == -1) break;
                link++;
            }
            pageMap.put(url,i);
            pageList.add(new Page(i,basic,link,(double)basic))//3번째 매개변수 링크점수는 일단 0으로 셋팅.
        }
        
        //3.링크 점수:pages 배열을 다시 순회하면서 a태그의 url 추출하여 Map에서 찾아서 해당 페이지의 링크 점수 완성한다!
        for(int i=0;i<pages.length;i++){
            String s = pages[i];
            for(int posL=0,posR=0; ;){
                posL = s.indexOf("<a href",posR);
                if(posL == -1) break;
                
                //posR = s.indexOf(">",posL);
                //url 추출해야함.시작 위치, 끝위치 어떻게 알 수 있을까?
                // int urlS = s.indexOf("https://",posL);
                // int urlE = s.lastindexOf("\"",posR);
                // String linkurl = s.substring(urlS,urlE+1);
                posL = s.indexOf("https://",posL);
                posR = s.indexOf("\"",posL);//posL은 반복변수인데 여기에 값을 넣어줘도 되나?
                String linkurl = s.substring(posL,posR);

                //4.매칭 점수
                //int index = Map.get(linkurl);
                Integer value = pageMap.get(linkurl);
                pageList.get(index).score += (double)pagesList.get(i).basic / pageList.get(i).link;
            }
        }
        //Collections.sort(new Comp());
        pageList.sort(new Comp());
        return pageList.get(0).idx;
    }
}