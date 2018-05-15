package com.aliaksandramolchan.githubclient.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aliaksandramolchan.githubclient.*
import com.aliaksandramolchan.githubclient.api.GithubApi
import com.aliaksandramolchan.githubclient.model.Repo
import com.aliaksandramolchan.githubclient.presenter.ReposPresenter
import com.aliaksandramolchan.githubclient.scheduler.ReposContract
import com.aliaksandramolchan.githubclient.scheduler.SchedulerProviderImpl

class ReposFragment : Fragment(), ReposContract.ReposView, MainActivity.QuerySender {

    override fun sendCloseEvent(isClosed: Boolean) {
        reposPresenter.onReceivedCloseEvent(isClosed)
    }

    override fun sendQuery(query: String?) {
        reposPresenter.onReceivedQuery(query)
    }

    private lateinit var reposPresenter: ReposContract.ReposPresenter
    private lateinit var reposAdapter: ReposAdapter

    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        reposAdapter = ReposAdapter(listOf())
        reposPresenter = ReposPresenter(this, SchedulerProviderImpl(), GithubApi.create())
        reposPresenter.onCreate()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_main, container, false)
        recyclerView = view?.findViewById(R.id.recycler_view) as RecyclerView
        recyclerView?.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = reposAdapter
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        reposPresenter.onDestroy()
    }


    override fun update(repos: List<Repo>) {
        reposAdapter.updateRepos(repos)
    }

}

