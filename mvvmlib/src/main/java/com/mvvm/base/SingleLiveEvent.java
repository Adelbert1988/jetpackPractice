package com.mvvm.base;


import android.util.Log;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.concurrent.atomic.AtomicBoolean;


public class SingleLiveEvent<T> extends MutableLiveData<T> {

    private final AtomicBoolean mPending = new AtomicBoolean(false);

    private final boolean single;

    /**
     * the constructor
     *
     * @param single whether this live data was single
     */
    public SingleLiveEvent(boolean single) {
        this.single = single;
    }

    @MainThread
    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull final Observer<? super T> observer) {
        if (single) {
            if (hasActiveObservers()) {
                Log.d("", "Multiple observers registered but only one will be notified of changes");
            }
            // Observe the internal MutableLiveData
            super.observe(owner, new Observer<T>() {
                @Override
                public void onChanged(@Nullable T t) {
                    if (mPending.compareAndSet(true, false)) {
                        observer.onChanged(t);
                    }
                }
            });
        } else {
            super.observe(owner, observer);
        }
    }

    @MainThread
    @Override
    public void setValue(@Nullable T t) {
        if (single) {
            mPending.set(true);
        }
        super.setValue(t);
    }

    /**
     * Used for cases where T is Void, to make calls cleaner.
     */
    @MainThread
    public void call() {
        setValue(null);
    }
}
