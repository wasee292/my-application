package com.example.myapplication.utils.extensions

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
  add(disposable)
}

fun CompositeDisposable.disposeAndClear() {
  if (!isDisposed) {
    dispose()
    clear()
  }
}
