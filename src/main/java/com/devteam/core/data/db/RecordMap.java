package com.devteam.core.data.db;

import com.devteam.lib.util.error.ErrorType;
import com.devteam.lib.util.error.RuntimeError;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class RecordMap<Key, T>  {
    private Function<T, Key> getKeyFunc;
    @Getter
    private Map<Key, T> map = new HashMap<>();


    public RecordMap(Function<T, Key>  func) {
        this.getKeyFunc = func;
    }

    public RecordMap(List<T> records, Function<T, Key>  func) {
        this.getKeyFunc = func;
        addAll(records);
    }

    public T get(Key key) {
        return map.get(key);
    }

    public RecordMap<Key, T> addAll(List<T> records) {
        for(int i = 0; i < records.size(); i++) {
            T record = records.get(i);
            Key key = getKeyFunc.apply(record);
            if(map.containsKey(key)) {
                throw new RuntimeError(ErrorType.IllegalState, "Key is already existed");
            }
            map.put(key, record);
        }
        return this;
    }
}
