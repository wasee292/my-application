package com.example.myapplication.ui.base

import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.example.myapplication.utils.extensions.disposeAndClear
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel(), LifecycleObserver, Observable {
  private val callbacks: PropertyChangeRegistry = PropertyChangeRegistry()
  protected val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

  var isConnected: Boolean = false

  override fun onCleared() {
    super.onCleared()
    compositeDisposable.disposeAndClear()
  }

  override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    callbacks.add(callback)
  }

  override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    callbacks.remove(callback)
  }

  protected fun notifyChange() {
    callbacks.notifyCallbacks(this, 0, null)
  }
}
