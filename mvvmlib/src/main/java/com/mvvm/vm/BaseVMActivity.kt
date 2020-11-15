package com.mvvm.vm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


abstract class BaseVMActivity<VM : BaseViewModel, T : ViewDataBinding>(useDataBinding: Boolean = true) : BaseActivity() {

    private val _useBinding = useDataBinding
    protected lateinit var mBinding: T
    lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = initVM()

        startObserve()
        if (_useBinding) {
            mBinding = DataBindingUtil.setContentView(this, getLayoutResId())
            mBinding.lifecycleOwner = this
        } else setContentView(getLayoutResId())
        initView()
        initData()
    }

    abstract fun initVM(): VM
    abstract fun startObserve()

}