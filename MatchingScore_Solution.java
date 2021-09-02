import java.util.*;
class MatchingScore_Solution {
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
    }
    public int solution(String word, String[] pages) {
        int wsize = word.length();
        //외부링크(a태그)를 통해서 어떤 링크를 가지고 있는지 알아내야한다.
        //a태그로 연결된 페이지의 인덱스를 쉽게 찾기 위해 Map을 사용한다!
        Map<String, Integer> pageMap = new HashMap<String, Integer>();
        //컬렉션에 페이지 정보들을 저장하고, 점수가 높은 것이 먼저 나오도록 정렬하면 정답을 찾을 수 있다.
        List<Page> pageList = new ArrayList<Page>();
        //찾을 검색어는 대소문자 구분X => 편의상 연산 편하게 하기 위해 소문자로 변환한다?! => word = Blind 라면 BLIND, blind, BlInD, bLiNd,..모두 해당되는 것이기 때문에 소문자로 통일한다!
        //pages 배열을 순회하며 찾을 때에도 연산을 용이하게 하기 위해 소문자로 변환한다.
        word = word.toLowerCase();
        for(int i=0;i<pages.length;i++){
            String s = pages[i] = pages[i].toLowerCase();
            //pages[i]의 url 구하기! => meta 태그에서 content 속성값
            int mid = 0, posL = 0, posR = 0;//posL:meta태그 시작, posR:meta태그 끝
            //meta태그의 시작,끝 위치 찾는다.
            while(mid<posL){//html 문자열에서 indexOf를 이용하여 <meta태그의 위치를 찾아서 반환한다!
                //posL을 1씩 증가시켜 <meta 위치를 찾고, 그 위치 인덱스를 posL에 저장한다.
                posL = s.indexOf("<meta",posL+1);//2번째 파라미터는 검색 시작 위치.1씩 증가.
                posR = s.indexOf(">",posL);
                //meta 태그 내에서 url 찾는다.뒤에서 찾는다.
                mid = s.lastIndexOf("https://",posR);
            }
            //url 위치 찾으면 추출=>url마지막 위치는 mid부터 시작, 큰따옴표만날 때까지
            posR = s.indexOf("/",mid);//url마지막을 posR에 저장
            String url = s.substring(mid,posR);
            
            //기본점수 계산 : 검색어 찾아서 카운팅.
            //meta태그 다음에 body 태그 있을 것이기 때문에
            posL = s.indexOf("<body>",posR);
            int basic = 0;
            //반복문 시작 : body부터
            for(int start = posL; ; ){
                start = s.indexOf(word,start+1);//찾으면 0 이상의 인덱스 값 반환.
                if(start == -1) break;//?왜 -1이 돼지?ㅇㅂㅇ...
                //검색어를 찾을 때, 아래 2가지가 알파벳이 아니여야한다! 알파벳을 제외한 다른 모든 문자로 구분한다고 했기 때문에.
                if(!Character.isLetter(s.charAt(start-1)) && !Character.isLetter(s.charAt(start+wsize))) {
                    basic++;
                    start += wsize;//다음 찾을 시작 위치는 검색어 길이만큼 증가시켜주면 됨.
                }
            }
            //외부링크수 구하기=>다시 body부터 시작해서 구한다. posL은 사용하지 않았기 때문에 여전히 body의 시작을 저장하고 있다.
            int link = 0;
            for(int start = posL; ;){
                start = s.indexOf("<a href",start +1);
                if(start == -1) break;
                link++;//그렇지 않은 경우는, a태그 찾은 것이므로 1증가.
            }
            //기본점수, 링크점수를 알아야 더해서 매칭점수를 구할 수 있기 때문에
            pageMap.put(url,i);
            pageList.add(new Page(i,basic,link,(double)basic));
        }
        //링크 점수 구한다=> html에서 구한다.
        //a태그에 어떤 링크가 있는지 확인하기 위해 a태그를 찾는다!
        for(int i=0;i<pages.length;i++){
            String s = pages[i];
            for(int posL = 0,posR = 0; ;){
                posL = s.indexOf("<a href",posR);
                //못 찾는 경우 -1을 반환?
                if(posL == -1) break;
                
                //a태그 찾았으면 외부 링크 url 주소 추출해야함!=>마찬가질 https를 찾는다!
                //시작과 끝을 알아야 substring으로 추출할 수 있기 때문에 시작과 끝 위치를 알아낸다!
                posL = s.indexOf("https://",posL);
                posR = s.indexOf("\"",posL);//끝은  큰따옴표로 끝난다.
                String linkurl = s.substring(posL,posR);
                                 
                //외부링크가 pages 배열에 있는 페이지라면, 그  페이지의 매칭접수 업데이트 해줘야함.
                //즉, 해당하는 페이지의 링크 점수를 추가로 더해줘야한다!
                                 
                Integer value = pageMap.get(linkurl);
                if(value != null){
                    pageList.get(value).score += (double)pageList.get(i).basic/pageList.get(i).link;
                }
                //이까지 함으로써 각각의 모든 페이지의 매칭 점수를 구할 수 있다.
            }
        }
        //문제에서 주어진 대로, 매칭점수가 큰 것이 앞에 오도록 정렬, 매칭 점수 동일하다면 index가 작은 것부터
        pageList.sort(new Comp());                   
         
        return pageList.get(0).idx;
    }
}