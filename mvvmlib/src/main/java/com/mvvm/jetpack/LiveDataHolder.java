package com.mvvm.jetpack;

import android.util.SparseArray;


import androidx.lifecycle.MutableLiveData;

import com.mvvm.base.Resources;
import com.mvvm.base.SingleLiveEvent;

import java.util.HashMap;
import java.util.Map;


public class LiveDataHolder<T> {

    private Map<Class, SingleLiveEvent> map = new HashMap<>();

    private Map<Class, SparseArray<SingleLiveEvent>> flagMap = new HashMap<>();

    public MutableLiveData<Resources<T>> getLiveData(Class<T> dataType, boolean single) {
        SingleLiveEvent<Resources<T>> liveData = map.get(dataType);
        if (liveData == null) {
            liveData = new SingleLiveEvent<>(single);
            map.put(dataType, liveData);
        }
        return liveData;
    }

    public MutableLiveData<Resources<T>> getLiveData(Class<T> dataType, int flag, boolean single) {
        SparseArray<SingleLiveEvent> array = flagMap.get(dataType);
        if (array == null) {
            array = new SparseArray<>();
            flagMap.put(dataType, array);
        }
        SingleLiveEvent<Resources<T>> liveData = array.get(flag);
        if (liveData == null) {
            liveData = new SingleLiveEvent<>(single);
            array.put(flag, liveData);
        }
        return liveData;
    }
}
