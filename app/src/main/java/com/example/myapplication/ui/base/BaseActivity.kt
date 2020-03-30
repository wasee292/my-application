package com.example.myapplication.ui.base

import android.content.Context
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {
  protected abstract fun activityName(): String
  protected abstract fun activityContext(): Context
  protected abstract fun toolbar(): Toolbar?
  protected abstract fun viewModelClass(): KClass<VM>
  protected abstract fun requireConnection(): Boolean
  @LayoutRes
  protected abstract fun layoutId(): Int

  open fun isLaunchingActivity(): Boolean = false

  protected lateinit var binding: B
  protected val viewModel: VM by viewModel(viewModelClass())

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    bindContentView(layoutId())
    if (toolbar() != null) {
      setSupportActionBar(toolbar())
      supportActionBar!!.setDisplayHomeAsUpEnabled(!isLaunchingActivity())
    }
  }

  private fun bindContentView(layoutId: Int) {
    binding = DataBindingUtil.setContentView(this, layoutId)
  }
}
