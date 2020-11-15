package com.mvvm.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import java.lang.reflect.ParameterizedType;


public abstract class BaseFragment<U extends BaseViewModel> extends Fragment {

    private U vm;

    private boolean shareViewModel;


    {
//        FragmentConfiguration configuration = this.getClass().getAnnotation(FragmentConfiguration.class);
//        if (configuration != null) {
//            shareViewModel = configuration.shareViewModel();
//            useEventBus = configuration.useEventBus();
//            UmengConfiguration umengConfiguration = configuration.umengConfiguration();
//            pageName = TextUtils.isEmpty(umengConfiguration.pageName()) ?
//                    getClass().getSimpleName() : umengConfiguration.pageName();
//            useUmengManual = umengConfiguration.useUmengManual();
//        }
    }

    /**
     * Get the layout resource id from subclass.
     *
     * @return layout resource id.
     */
    @LayoutRes
    protected abstract int getLayoutResId();

    /**
     * Do create view business.
     *
     * @param savedInstanceState the saved instance state.
     */
    protected abstract void doCreateView(@Nullable Bundle savedInstanceState);

    /**
     * Initialize view model according to the generic class type. Override this method to
     * add your owen implementation.
     *
     * Add {@link FragmentConfiguration} to the subclass and set
     * {@link FragmentConfiguration#shareViewModel()} true
     * if you want to share view model between several fragments.
     *
     * @return the view model instance.
     */
    protected U createViewModel() {
        Class<U> vmClass = ((Class)((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]);
        if (shareViewModel) {
            return new ViewModelProvider(getActivity()).get(vmClass);
        } else {
            return new ViewModelProvider(this).get(vmClass);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        int layoutResId = getLayoutResId();
        if (layoutResId <= 0) {
            throw new IllegalArgumentException("The subclass must provider a valid layout resources id.");
        }
        return inflater.inflate(layoutResId, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // fixed 2020-05-21: The callback must be called here
        // to use kotlin android extensions to avoid findViewById()
        doCreateView(savedInstanceState);
    }

    protected U getVM() {
        return vm;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        vm = createViewModel();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
