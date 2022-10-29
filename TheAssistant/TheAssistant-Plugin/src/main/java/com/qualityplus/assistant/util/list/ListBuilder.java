package com.qualityplus.assistant.util.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class ListBuilder<T> {
    private final List<T> placeholderList = new ArrayList<>();

    /*public <T> ListBuilder<T> create(T type){
        return new ListBuilder<T>();
    }*/

    public ListBuilder<T> with(List<T> datas){
        placeholderList.addAll(datas);
        return this;
    }

    public ListBuilder<T> with(ListBuilder<T> datas){
        placeholderList.addAll(datas.get());
        return this;
    }

    public ListBuilder<T> with(T... data){
        placeholderList.addAll(Arrays.stream(data).collect(Collectors.toList()));
        return this;
    }

    public List<T> get(){
        return placeholderList;
    }
}
