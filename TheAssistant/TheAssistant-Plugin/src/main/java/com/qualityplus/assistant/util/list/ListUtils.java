package com.qualityplus.assistant.util.list;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class ListUtils {
    public List<Integer> secuence(int begin, int end) {
        List<Integer> ret = new ArrayList<>(end - begin + 1);
        for (int i=begin; i<=end; i++)
            ret.add(i);
        return ret;
    }

    public List<String> stringSecuence(int begin, int end) {
        List<String> ret = new ArrayList<>(end - begin + 1);
        for (int i=begin; i<=end; i++)
            ret.add(String.valueOf(i));
        return ret;
    }

    public <T> List<T> listWith(List<T> list, T toAdd){
        list.add(toAdd);
        return list;
    }

    public <T> List<T> listWithout(List<T> list, T toAdd){
        list.add(toAdd);
        return list;
    }

    public static class ListBuilder<T>{
        private final List<T> initialList;

        private ListBuilder(List<T> initialList) {
            this.initialList = initialList;
        }

        public static <T> ListBuilder<T> of(List<T> list){
            return new ListBuilder<>(list);
        }

        public ListBuilder<T> with(List<T> with){
            initialList.addAll(with);
            return this;
        }

        public List<T> get(){
            return initialList;
        }
    }
}
