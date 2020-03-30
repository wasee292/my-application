package com.example.myapplication.ui.main

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.model.Repo
import com.example.myapplication.repository.GithubRepository
import com.example.myapplication.ui.base.BaseViewModel
import com.example.myapplication.utils.extensions.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlin.properties.Delegates

class MainViewModel(private val githubRepository: GithubRepository) : BaseViewModel() {
  var repoList = MutableLiveData<List<Repo>>()

  var inProgress: Boolean by Delegates.observable(true) { _, _, _ ->
    notifyChange()
  }

  fun loadPosts() {
    compositeDisposable += githubRepository.fetchRepos()
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .doOnSubscribe { inProgress = true }
      .doFinally { inProgress = false }
      .subscribe { _res, _err ->
        repoList.postValue(_res)
      }
  }
}
