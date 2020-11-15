package com.practice.jetpack

import android.os.Bundle
import com.mvvm.vm.BaseVMActivity
import com.ziroom.jetpackpractice.R
import com.ziroom.jetpackpractice.databinding.ActivityMainBinding

class MainActivity : BaseVMActivity<MainViewModel, ActivityMainBinding>() {
//     private val mViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun initView() {
        mBinding.tvHello.text = ""
    }

    override fun initData() {
    }

    override fun initVM(): MainViewModel {
    }

    override fun startObserve() {
    }
}