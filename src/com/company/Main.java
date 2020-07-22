package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String[] words = new String[]{"What","must","be","acknowledgment","shall","be"};
        fullJustify(words,16);
    }

    public static List<String> fullJustify(String[] words, int maxWidth) {

        List<String> result = new ArrayList<>();
        int len = -1 , count = -1, start = 0;

        for(int i=0;i<words.length;i++){

            if(len+words[i].length()+1<=maxWidth){
                len += words[i].length()+1;
                count++;
            }else{
                //note that last linne is always set to false inside for loop
                //it is only true when we exot for loop
                addLine(words,start,i-1,len,count,maxWidth,result,false);
                start = i;
                //we are here because last i overflowed the maxWidth
                //thus decrement it
                i--;
                len = -1;
                count= -1;
            }

        }
        //last add line covers what ever is a remiander from for loop
        //last line is passed as true here
        addLine(words,start,words.length-1,len,count,maxWidth,result,true);
        return result;
    }

    public static void addLine(String[] words, int start, int end, int len, int count, int maxWidth, List<String> result, boolean isLast){

        int spaces = maxWidth-len;
        spaces += count;
        // same  [spaces/count] is equal spacing applied to all of them
        // extra [spaces%count] is the ones that keep getting added and decremented, starting from left
        // very useful for last line, basically same is divied spaces equaly and extra is the reaminder of spaces that can not be equally distributed.

        int same  = isLast || (count==0)? 0 : spaces/count;
        int extra = isLast || (count==0)? count : spaces%count;

        //last line or one word in the line
        int trail = isLast || (count==0) ? maxWidth-len : 0;

        StringBuilder line = new StringBuilder();

        while(start<=end){

            line.append(words[start]);

            for(int k=0;k<same && start!=end;k++)
                line.append(' ');

            if(extra>0){
                line.append(' ');
                extra--;
            }

            start++;

        }

        while(trail>0){
            line.append(' ');
            trail--;
        }

        result.add(line.toString());

    }

}
