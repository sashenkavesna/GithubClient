package com.aliaksandramolchan.githubclient.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.aliaksandramolchan.githubclient.R
import com.aliaksandramolchan.githubclient.model.Repo

class ReposAdapter(private var repos: List<Repo>) : RecyclerView.Adapter<ReposAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.repoName?.text = repos.get(position).name
        holder?.repoDescr?.text = repos.get(position).description
        holder?.repoLang?.text = repos.get(position).language
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.repo_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = repos.size

    fun updateRepos(repos: List<Repo>) {
        this.repos = repos
        notifyDataSetChanged()
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val repoName = view.findViewById(R.id.repoName) as TextView
        val repoDescr = view.findViewById(R.id.repoDescription) as TextView
        val repoLang = view.findViewById(R.id.repoLang) as TextView
    }

}

