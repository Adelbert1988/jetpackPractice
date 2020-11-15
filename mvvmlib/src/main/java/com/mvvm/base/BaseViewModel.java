package com.mvvm.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;


import com.mvvm.jetpack.LiveDataHolder;

import java.util.List;



public class BaseViewModel extends AndroidViewModel {

    private LiveDataHolder holder = new LiveDataHolder();
    private LiveDataHolder listHolder = new LiveDataHolder<List>();

    /**
     * If the view model use event bus
     */
    private boolean useEventBus = false;

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    {
//        ViewModelConfiguration configuration = this.getClass().getAnnotation(ViewModelConfiguration.class);
//        if (configuration != null) {
//            useEventBus = configuration.useEventBus();
//            if (useEventBus) {
//                Bus.get().register(this);
//            }
//        }
    }

    /**
     * Get the LiveData of given type.
     *
     * @param dataType the data type.
     * @param <T>      the data type.
     * @return         the live data.
     */
    public <T> MutableLiveData<Resources<T>> getObservable(Class<T> dataType) {
        return holder.getLiveData(dataType, false);
    }

    /**
     * Get the LiveData of given type.
     *
     * @param dataType the data type.
     * @param single   is single event
     * @param <T>      the data type.
     * @return         the live data.
     * @see SingleLiveEvent
     */
    public <T> MutableLiveData<Resources<T>> getObservable(Class<T> dataType, boolean single) {
        return holder.getLiveData(dataType, single);
    }

    /**
     * Get the LiveData of given type and flag
     *
     * @param dataType the data type
     * @param flag     the flag
     * @param <T>      the generic data type.
     * @return         the live data.
     */
    public <T> MutableLiveData<Resources<T>> getObservable(Class<T> dataType, int flag) {
        return holder.getLiveData(dataType, flag, false);
    }

    /**
     * et the LiveData of given type and flag
     *
     * @param dataType the data type
     * @param flag     the flag
     * @param single   is single event
     * @param <T>      the generic data type.
     * @return         the live data.
     * @see SingleLiveEvent
     */
    public <T> MutableLiveData<Resources<T>> getObservable(Class<T> dataType, int flag, boolean single) {
        return holder.getLiveData(dataType, flag, single);
    }

    /**
     * Get the LiveData of given list type.
     *
     * @param dataType the data type of element in list.
     * @param <T>      the generic data type.
     * @return         the live data.
     */
    public <T> MutableLiveData<Resources<List<T>>> getListObservable(Class<T> dataType) {
        return listHolder.getLiveData(dataType, false);
    }

    /**
     * Get the LiveData of given list type.
     *
     * @param dataType the data type of element in list.
     * @param single   is single event
     * @param <T>      the generic data type.
     * @return         the live data.
     * @see SingleLiveEvent
     */
    public <T> MutableLiveData<Resources<List<T>>> getListObservable(Class<T> dataType, boolean single) {
        return listHolder.getLiveData(dataType, single);
    }

    /**
     * Get the LiveData of given list type and flag
     *
     * @param dataType the data type
     * @param flag     the flag
     * @param <T>      the generic data type.
     * @return         the live data.
     */
    public <T> MutableLiveData<Resources<List<T>>> getListObservable(Class<T> dataType, int flag) {
        return listHolder.getLiveData(dataType, flag, false);
    }

    /**
     * Get the LiveData of given list type and flag
     *
     * @param dataType the data type
     * @param flag     the flag
     * @param single   is single event
     * @param <T>      the generic data type.
     * @return         the live data.
     * @see SingleLiveEvent
     */
    public <T> MutableLiveData<Resources<List<T>>> getListObservable(Class<T> dataType, int flag, boolean single) {
        return listHolder.getLiveData(dataType, flag, single);
    }


    @Override
    protected void onCleared() {
//        if (useEventBus) {
//            Bus.get().unregister(this);
//        }
    }
}
