package com.shuoma;
import java.util.ArrayList;
import java.util.*;




public class InsertInterval {
    //second pass
    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> ret=new ArrayList<Interval>();
        
        if(newInterval==null) return intervals;
        if(intervals.size()==0){
            ret.add(newInterval);
            return ret;
        } 
        
        //insert the newInterval to the right position using the start time
        int i;
        for(i=0;i<intervals.size();i++){
            if(intervals.get(i).start>=newInterval.start) break; 
        }
        intervals.add(i, newInterval);
        
        Interval open=intervals.get(0);
        for(int j=1;j<intervals.size();j++){
            Interval next=intervals.get(j);
            if(next.start>open.end){
                ret.add(open);
                open=next;
            }else{
                if(next.end>open.end) open.end=next.end;
            }
        }
        ret.add(open);
        return ret;
    }
    
    //fist pass
    public ArrayList<Interval> insertFirstPass(ArrayList<Interval> intervals, Interval newInterval) {
        int i;
        for(i=0;i<intervals.size();i++){
            if(intervals.get(i).start>=newInterval.start) break;
        }
        intervals.add(i, newInterval);
        
        int n=intervals.size();
        if(n<2) return intervals;
        Collections.sort(intervals, new CustomComparator());
        
        Interval openInt=intervals.get(0);
        ArrayList<Interval> res=new ArrayList<Interval>();
                
        for(i=1;i<n;i++){
            Interval cur=intervals.get(i);
            if(openInt.end<cur.start){
                res.add(openInt);
                openInt=cur;
            }else{
                if(openInt.end<cur.end){
                    openInt.end=cur.end;
                }
            }
        }
        res.add(openInt);
        
        return res;
        
    }
    
    public class CustomComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval o1, Interval o2) {
            return o1.start-o2.start;
        }
    }   
}