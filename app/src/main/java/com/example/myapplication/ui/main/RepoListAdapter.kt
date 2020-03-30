package com.example.myapplication.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.CardRepoBinding
import com.example.myapplication.model.Repo

class RepoListAdapter(private val clickListener: ItemClickListener<Repo>) :
  RecyclerView.Adapter<RepoListAdapter.ViewHolder>() {
  private var repoList: List<Repo> = listOf()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoListAdapter.ViewHolder {
    val binding: CardRepoBinding = DataBindingUtil.inflate(
      LayoutInflater.from(parent.context),
      R.layout.card_repo,
      parent,
      false
    )
    return ViewHolder(binding)
  }

  override fun onBindViewHolder(holder: RepoListAdapter.ViewHolder, position: Int) {
    repoList[position].let { item ->
      holder.bind(repoList[position])
      holder.binding.root.setOnClickListener {
        clickListener.onItemClicked(item)
      }
    }
  }

  override fun getItemCount(): Int {
    return repoList.size
  }

  fun updateRepoList(repoList: List<Repo>) {
    this.repoList = repoList
    notifyDataSetChanged()
  }

  class ViewHolder(val binding: CardRepoBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(repo: Repo) {
      binding.repo = repo
    }
  }

  interface ItemClickListener<D> {
    fun onItemClicked(item: D)
  }
}
