package UTIL;

import BE.Lesson;

import java.util.List;

public class ArrayTools {
    public static int getIndexOfLargest(List<List<Lesson>> list) {
        if(list == null || list.size() == 0) return -1;

        int largest = 0;
        for(int i = 1; i < list.size(); i++) {
            if(list.get(i).size() > list.get(largest).size()) largest = i;
        }

        return largest;
    }
}
