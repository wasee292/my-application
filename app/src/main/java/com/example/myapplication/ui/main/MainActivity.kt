package com.example.myapplication.ui.main

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.model.Repo
import com.example.myapplication.ui.base.BaseActivity
import com.example.myapplication.utils.extensions.getSimpleName
import kotlin.reflect.KClass

class MainActivity :
  BaseActivity<ActivityMainBinding, MainViewModel>() {
  override fun activityName(): String = this.getSimpleName()
  override fun activityContext(): Context = this.baseContext
  override fun toolbar(): Toolbar? = binding.toolBar.toolbar
  override fun viewModelClass(): KClass<MainViewModel> = MainViewModel::class
  override fun requireConnection(): Boolean = true
  override fun layoutId(): Int = R.layout.activity_main
  override fun isLaunchingActivity() = true

  private val repoListAdapter by lazy {
    RepoListAdapter(object : RepoListAdapter.ItemClickListener<Repo> {
      override fun onItemClicked(item: Repo) {
        item.expand = item.expand.not()
      }
    })
  }

  private lateinit var localRepoList: List<Repo>

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewModel.repoList.observe(this, Observer {
      repoListAdapter.updateRepoList(it)
      localRepoList = it
    })
  }

  override fun onPostCreate(savedInstanceState: Bundle?) {
    super.onPostCreate(savedInstanceState)
    viewModel.loadPosts()
    binding.rvRepos.apply {
      layoutManager = LinearLayoutManager(this@MainActivity)
      adapter = repoListAdapter
    }
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_sort, menu)
    menu!!.findItem(R.id.menu_item_sort_name).isVisible = true
    menu.findItem(R.id.menu_item_sort_stars).isVisible = true
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.menu_item_sort_stars -> {
        viewModel.repoList.postValue(localRepoList.sortedWith(compareBy(Repo::stars)))
      }
      R.id.menu_item_sort_name -> {
        viewModel.repoList.postValue(localRepoList.sortedWith(compareBy(Repo::name)))
      }
    }
    return super.onOptionsItemSelected(item)
  }
}
