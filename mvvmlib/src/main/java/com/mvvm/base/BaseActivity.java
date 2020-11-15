package com.mvvm.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.lang.reflect.ParameterizedType;

public abstract class BaseActivity<U extends BaseViewModel> extends AppCompatActivity {

    private U vm;

    private int layoutResId;


    {
//        ActivityConfiguration configuration = this.getClass().getAnnotation(ActivityConfiguration.class);
//        if (configuration != null) {
//
//        }
    }

    /**
     * Do create view business.
     *
     * @param savedInstanceState the saved instance state.
     */
    protected abstract void initView(@Nullable Bundle savedInstanceState);

    /**
     * Get the layout resource id from subclass.
     *
     * @return layout resource id.
     */
    @LayoutRes
    protected abstract int getLayoutResId();

    /**
     * This method will be called before the {@link #setContentView(View)} was called.
     *
     * @param savedInstanceState the saved instance state.
     */
    protected void setupContentView(@Nullable Bundle savedInstanceState) {
        setContentView(layoutResId);
    }

    /**
     * Initialize view model. Override this method to add your own implementation.
     *
     * @return the view model will be used.
     */
    protected U createViewModel() {
        Class<U> vmClass = ((Class)((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
        return new ViewModelProvider(this).get(vmClass);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutResId = getLayoutResId();
        if (layoutResId <= 0) {
            throw new IllegalArgumentException("The subclass must provider a valid layout resources id.");
        }
        vm = createViewModel();
        setupContentView(savedInstanceState);
        initView(savedInstanceState);
    }

    protected U getVM() {
        return vm;
    }

    /**
     * Get fragment of given resources id.
     *
     * @param resId the resources id.
     * @return the fragment.
     */
    protected Fragment getFragment(@IdRes int resId) {
        return getSupportFragmentManager().findFragmentById(resId);
    }

    /**
     * To find view by id
     *
     * @param id  id
     * @param <V> the view type
     * @return    the view
     */
    protected <V extends View> V f(@IdRes int id) {
        return findViewById(id);
    }

    /**
     * Correspond to fragment's {@link Fragment#getContext()}
     *
     * @return context
     */
    protected Context getContext() {
        return this;
    }

    /**
     * Correspond to fragment's {@link Fragment#getActivity()}
     *
     * @return activity
     */
    protected Activity getActivity() {
        return this;
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * This method is used to call the super {@link #onBackPressed()} instead of the
     * implementation of current activity. Since the current {@link #onBackPressed()} may be override.
     */
    public void superOnBackPressed() {
        super.onBackPressed();
    }
}
