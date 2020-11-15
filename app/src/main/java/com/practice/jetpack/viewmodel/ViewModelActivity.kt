package com.practice.jetpack.viewmodel

import android.os.Bundle
import com.mvvm.base.CommonActivity
import com.ziroom.jetpackpractice.R
import com.ziroom.jetpackpractice.databinding.MyViewModelActivityBinding

/**
 * Author: hongwei
 * Date: 2020/6/16
 * Description:
 */

class ViewModelActivity: CommonActivity<MyViewModel, MyViewModelActivityBinding>(){


    override fun getLayoutResId(): Int = R.layout.my_view_model_activity

    override fun initView(savedInstanceState: Bundle?) {
        binding.btnViewModel.setOnClickListener {
            binding.tvViewModel.text = "测试viewbinging"
        }

    }

}